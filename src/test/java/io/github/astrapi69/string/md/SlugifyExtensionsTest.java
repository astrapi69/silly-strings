package io.github.astrapi69.string.md;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link SlugifyExtensions} utility methods
 *
 * <p>
 * Verifies the functionality of string slugification with various configurations including:
 * <ul>
 * <li>Default slugification behavior</li>
 * <li>Custom separator usage</li>
 * <li>Case preservation</li>
 * <li>Edge trimming options</li>
 * <li>Null replacement handling</li>
 * </ul>
 */
public class SlugifyExtensionsTest
{
	/**
	 * Tests default slugification behavior
	 * <p>
	 * Verifies that:
	 * <ul>
	 * <li>Special characters are replaced (é → e)</li>
	 * <li>Text is converted to lowercase</li>
	 * <li>Whitespace is replaced with hyphens</li>
	 * <li>Non-alphanumeric symbols are removed (☕)</li>
	 * </ul>
	 */
	@Test
	void testDefaultSlugify()
	{
		String input = "Introduction au café ☕";
		String expected = "introduction-au-cafe";
		String result = SlugifyExtensions.slugify(input);

		assertEquals(expected, result);
	}

	/**
	 * Tests slugification with custom separator
	 * <p>
	 * Verifies that:
	 * <ul>
	 * <li>Custom replacements are applied (é → e, ☕ → removed)</li>
	 * <li>Whitespace is replaced with underscores</li>
	 * <li>Result matches expected format</li>
	 * </ul>
	 */
	@Test
	void testSlugifyWithCustomSeparator()
	{
		Map<String, String> replacements = new HashMap<>();
		replacements.put("é", "e");
		replacements.put("☕", "");

		SlugifyConfig config = new SlugifyConfig(replacements, true, // toLowerCase
			true, // stripNonAlphanumeric
			"_", // whitespaceReplacement
			true, // trimEdges
			true, true, "[^a-z0-9\\s-]");

		String input = "Introduction au café ☕";
		String expected = "introduction_au_cafe";

		String result = SlugifyExtensions.slugify(input, config);
		assertEquals(expected, result);
	}

	/**
	 * Tests slugification with uppercase preservation
	 * <p>
	 * Verifies that:
	 * <ul>
	 * <li>Case is preserved when configured (toLowerCase = false)</li>
	 * <li>German special characters are properly replaced (ä → ae, Ä → Ae, ß → ss)</li>
	 * <li>Result maintains expected capitalization</li>
	 * </ul>
	 */
	@Test
	void testSlugifyWithUpperCasePreserved()
	{
		Map<String, String> replacements = new HashMap<>();
		replacements.put("ä", "ae");
		replacements.put("Ä", "Ae");
		replacements.put("ß", "ss");

		SlugifyConfig config = new SlugifyConfig(replacements, false, // toLowerCase
			true, // stripNonAlphanumeric
			"-", // whitespaceReplacement
			true, // trimEdges
			true, true, "[^a-z0-9\\s-]");

		String input = "Märchen Straße";
		String expected = "Maerchen-Strasse";

		String result = SlugifyExtensions.slugify(input, config);
		assertEquals(expected, result);
	}

	/**
	 * Tests slugification with edge trimming disabled
	 * <p>
	 * Verifies that:
	 * <ul>
	 * <li>Leading/trailing whitespace is preserved as separators when trimEdges = false</li>
	 * <li>Internal whitespace is still properly replaced</li>
	 * </ul>
	 */
	@Test
	void testSlugifyTrimFalse()
	{
		SlugifyConfig config = new SlugifyConfig(new HashMap<>(), true, // toLowerCase
			true, // stripNonAlphanumeric
			"-", // whitespaceReplacement
			false, // trimEdges
			true, true, "[^a-z0-9\\s-]");
		String input = "  Keep me ";
		String expected = "-keep-me-";

		String result = SlugifyExtensions.slugify(input, config);
		assertEquals(expected, result);
	}

	/**
	 * Tests slugification with null replacement map
	 * <p>
	 * Verifies that:
	 * <ul>
	 * <li>Method handles null replacement map gracefully</li>
	 * <li>Basic slugification still works (lowercase, whitespace replacement)</li>
	 * <li>Non-alphanumeric characters from default set are still removed</li>
	 * </ul>
	 */
	@Test
	void testSlugifyWithNoReplacement()
	{
		SlugifyConfig config = new SlugifyConfig(null, true, // toLowerCase
			true, // stripNonAlphanumeric
			"-", // whitespaceReplacement
			true, // trimEdges
			true, true, "[^a-z0-9\\s-]");
		String input = "No change";
		String expected = "no-change";

		String result = SlugifyExtensions.slugify(input, config);
		assertEquals(expected, result);
	}
}