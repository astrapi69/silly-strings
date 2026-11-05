/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.regex.RegExExtensions;
import io.github.astrapi69.string.CaseExtensions;
import io.github.astrapi69.string.CharsetExtensions;
import io.github.astrapi69.string.SplitStringExtensions;
import io.github.astrapi69.string.StringExtensions;

/**
 * Runs the examples of the README: each block between "README: begin" and "README: end" is the
 * block of the same section in README.md, and the values its comments name are the assertions after
 * it (#41)
 */
class ReadmeExamplesTest
{

	@Test
	void escaping()
	{
		// README: begin
		String escaped = StringExtensions.toUnicodeChars("Größe = 1", false);
		// "Gr\\u00F6\\u00DFe \\= 1", the escaping of a value in a properties file
		String everyCharacter = StringExtensions.toUnicode("Ωmega", false);
		// "\\u03A9\\u006D\\u0065\\u0067\\u0061", one escape for each character
		Character omega = StringExtensions.convertUnicodeStringToCharacter("\\u03A9"); // 'Ω'
		// README: end

		assertEquals("Gr\\u00F6\\u00DFe \\= 1", escaped);
		assertEquals("\\u03A9\\u006D\\u0065\\u0067\\u0061", everyCharacter);
		assertEquals(Character.valueOf('Ω'), omega);
	}

	@Test
	void replacingAndSplitting()
	{
		// README: begin
		String dashed = StringExtensions.replaceAll("a, b, c", ", ", "-"); // a-b-c
		String last = StringExtensions.replaceLast("one-two-three", "-", "+"); // one-two+three
		List<String> parts = StringExtensions.splitByFixedLength("abcdefg", 3); // [abc, def, g]
		String firstLine = StringExtensions.readLine("first\nsecond"); // first
		// README: end

		assertEquals("a-b-c", dashed);
		assertEquals("one-two+three", last);
		assertEquals(List.of("abc", "def", "g"), parts);
		assertEquals("first", firstLine);
	}

	@Test
	void changingTheCase()
	{
		// README: begin
		String camel = CaseExtensions.kebabToCamelCase("silly-strings-version"); // sillyStringsVersion
		String constant = CaseExtensions.kebabToUpperSnakeCase("silly-strings"); // SILLY_STRINGS
		String pascal = CaseExtensions.snakeToPascalCase("silly_strings"); // SillyStrings
		String property = CaseExtensions.kebabToDotCase("silly-strings"); // silly.strings
		// README: end

		assertEquals("sillyStringsVersion", camel);
		assertEquals("SILLY_STRINGS", constant);
		assertEquals("SillyStrings", pascal);
		assertEquals("silly.strings", property);
	}

	@Test
	void wordsPatternsAndCharsets()
	{
		// README: begin
		Map<String, Integer> counts = SplitStringExtensions
			.splitToWordsAndCount("to be or not to be");
		// {be=2, not=1, or=1, to=2}
		int matches = RegExExtensions.countMatches("o", "foo boo"); // 4
		String sqlPattern = RegExExtensions.wildcardCriterionSQL("Mül*er?"); // Mül%er_
		Optional<Charset> utf8 = CharsetExtensions.getCharset("UTF-8"); // Optional[UTF-8]
		Optional<Charset> none = CharsetExtensions.getCharset("no such charset"); // Optional.empty
		// README: end

		assertEquals(Map.of("be", 2, "not", 1, "or", 1, "to", 2), counts);
		assertEquals("{be=2, not=1, or=1, to=2}", counts.toString());
		assertEquals(4, matches);
		assertEquals("Mül%er_", sqlPattern);
		assertEquals(Optional.of(StandardCharsets.UTF_8), utf8);
		assertEquals(Optional.empty(), none);
	}

	/**
	 * The blocks of this class are the java blocks of README.md, in their order, apart from white
	 * space: the formatter aligns neither the comments nor the line breaks as the README does
	 *
	 * @throws IOException
	 *             if README.md or this class cannot be read
	 */
	@Test
	void theBlocksAreTheJavaBlocksOfTheReadme() throws IOException
	{
		final List<String> readmeBlocks = blocks(Files.readString(Path.of("README.md")),
			"```java\\n(.*?)```");
		final List<String> testBlocks = blocks(
			Files.readString(Path.of("src/test/java/io/github/astrapi69/ReadmeExamplesTest.java")),
			"// README: begin\\n(.*?)// README: end");
		assertEquals(4, testBlocks.size());
		assertEquals(testBlocks, readmeBlocks);
	}

	private static List<String> blocks(final String text, final String regex)
	{
		final List<String> blocks = new ArrayList<>();
		final Matcher matcher = Pattern.compile(regex, Pattern.DOTALL).matcher(text);
		while (matcher.find())
		{
			blocks.add(matcher.group(1).replaceAll("\\s+", ""));
		}
		return blocks;
	}

	/**
	 * The comparison of the blocks leaves out the white space of the layout, which the formatter
	 * changes, and keeps the white space inside a string or a character literal, which is part of
	 * the example; line ends of a CRLF checkout are line ends as well (#51)
	 */
	@Test
	void theComparisonKeepsTheWhiteSpaceOfLiteralsAndReadsCrlf()
	{
		assertEquals(
			blocks("// README: begin\nf(\"a, b\");  // x\n// README: end",
				"// README: begin\\n(.*?)// README: end"),
			blocks("// README: begin\n   f( \"a, b\" ); // x\n// README: end",
				"// README: begin\\n(.*?)// README: end"));
		org.junit.jupiter.api.Assertions.assertNotEquals(
			blocks("// README: begin\nf(\"a, b\");\n// README: end",
				"// README: begin\\n(.*?)// README: end"),
			blocks("// README: begin\nf(\"a,b\");\n// README: end",
				"// README: begin\\n(.*?)// README: end"));
		org.junit.jupiter.api.Assertions.assertNotEquals(
			blocks("// README: begin\nchar c = ' ';\n// README: end",
				"// README: begin\\n(.*?)// README: end"),
			blocks("// README: begin\nchar c = '_';\n// README: end",
				"// README: begin\\n(.*?)// README: end"));
		assertEquals(List.of("f();"), blocks("```java\r\nf();\r\n```", "```java\\n(.*?)```"));
	}
}
