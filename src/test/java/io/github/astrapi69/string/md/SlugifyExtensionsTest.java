package io.github.astrapi69.string.md;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SlugifyExtensionsTest
{

	@Test
	void testDefaultSlugify()
	{
		String input = "Introduction au café ☕";
		String expected = "introduction-au-cafe";
		String result = SlugifyExtensions.slugify(input);

		assertEquals(expected, result);
	}

	@Test
	void testSlugifyWithCustomSeparator()
	{
		Map<String, String> replacements = new HashMap<>();
		replacements.put("é", "e");
		replacements.put("☕", "");

		SlugifyConfig config = new SlugifyConfig(replacements, true, // toLowerCase
			true, // stripNonAlphanumeric
			"_", // whitespaceReplacement
			true // trimEdges
		);

		String input = "Introduction au café ☕";
		String expected = "introduction_au_cafe";

		String result = SlugifyExtensions.slugify(input, config);
		assertEquals(expected, result);
	}

	@Test
	void testSlugifyWithUpperCasePreserved()
	{
		Map<String, String> replacements = new HashMap<>();
		replacements.put("ä", "ae");
		replacements.put("Ä", "Ae");
		replacements.put("ß", "ss");

		SlugifyConfig config = new SlugifyConfig(replacements, false, // keep case
			true, // stripNonAlphanumeric
			"-", true);

		String input = "Märchen Straße";
		String expected = "Maerchen-Strasse";

		String result = SlugifyExtensions.slugify(input, config);
		assertEquals(expected, result);
	}

	@Test
	void testSlugifyTrimFalse()
	{
		SlugifyConfig config = new SlugifyConfig(new HashMap<>(), true, true, "-", false // do not
																							// trim
																							// leading/trailing
		);

		String input = "  Keep me ";
		String expected = "-keep-me-";

		String result = SlugifyExtensions.slugify(input, config);
		assertEquals(expected, result);
	}

	@Test
	void testSlugifyWithNoReplacement()
	{
		SlugifyConfig config = new SlugifyConfig(null, true, true, "-", true);

		String input = "No change";
		String expected = "no-change";

		String result = SlugifyExtensions.slugify(input, config);
		assertEquals(expected, result);
	}
}
