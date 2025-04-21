package io.github.astrapi69.string.md;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import io.github.astrapi69.string.StringExtensions;

/**
 * Utility class for converting strings to URL-friendly slugs
 *
 * <p>
 * Provides methods to transform text into slugs by:
 * <ul>
 * <li>Converting characters to lowercase</li>
 * <li>Replacing special characters with their ASCII equivalents</li>
 * <li>Removing or replacing whitespace</li>
 * <li>Stripping non-alphanumeric characters</li>
 * </ul>
 *
 * <p>
 * Includes default character replacements for common Western European characters and supports
 * custom configurations through {@link SlugifyConfig}
 * </p>
 */
public class SlugifyExtensions
{
	/**
	 * Default character replacements for slugification
	 * <p>
	 * Includes mappings for:
	 * <ul>
	 * <li>German umlauts (ä, ö, ü, ß)</li>
	 * <li>Common accented characters (à, á, â, ã, etc)</li>
	 * <li>Special characters (ç)</li>
	 * </ul>
	 */
	private static final Map<String, String> DEFAULT_REPLACEMENTS;
	static
	{
		Map<String, String> replacements = new HashMap<>();

		// German / Western European
		replacements.put("ä", "ae");
		replacements.put("Ä", "Ae");
		replacements.put("ö", "oe");
		replacements.put("Ö", "Oe");
		replacements.put("ü", "ue");
		replacements.put("Ü", "Ue");
		replacements.put("ß", "ss");

		// Accents (generic fallback)
		replacements.put("à", "a");
		replacements.put("â", "a");
		replacements.put("á", "a");
		replacements.put("ã", "a");

		replacements.put("é", "e");
		replacements.put("è", "e");
		replacements.put("ê", "e");
		replacements.put("ë", "e");

		replacements.put("î", "i");
		replacements.put("ï", "i");

		replacements.put("ô", "o");
		replacements.put("ó", "o");

		replacements.put("ù", "u");
		replacements.put("û", "u");

		replacements.put("ç", "c");

		DEFAULT_REPLACEMENTS = Collections.unmodifiableMap(replacements);
	}

	/**
	 * Default configuration for slugification
	 * <p>
	 * Includes:
	 * <ul>
	 * <li>Default character replacements</li>
	 * <li>Lowercase conversion</li>
	 * <li>Non-alphanumeric stripping</li>
	 * <li>Whitespace replacement with hyphens</li>
	 * <li>Edge trimming</li>
	 * </ul>
	 */
	private static final SlugifyConfig DEFAULT_CONFIG = new SlugifyConfig(DEFAULT_REPLACEMENTS,
		true, // toLowerCase
		true, // stripNonAlphanumeric
		"-", // whitespaceReplacement
		true, // trimEdges
		true, true, "[^a-z0-9\\s-]");

	/**
	 * Converts text to a URL-friendly slug using default configuration
	 *
	 * @param text
	 *            The input text to convert
	 * @return The generated slug with:
	 *         <ul>
	 *         <li>Special characters replaced</li>
	 *         <li>Whitespace converted to hyphens</li>
	 *         <li>Non-alphanumeric characters removed</li>
	 *         </ul>
	 * @throws NullPointerException
	 *             if the input text is null
	 */
	public static String slugify(String text)
	{
		return slugify(text, DEFAULT_CONFIG);
	}

	/**
	 * Converts text to a URL-friendly slug using custom configuration
	 *
	 * @param text
	 *            The input text to convert
	 * @param config
	 *            The configuration object specifying:
	 *            <ul>
	 *            <li>Character replacements</li>
	 *            <li>Case conversion</li>
	 *            <li>Whitespace handling</li>
	 *            <li>Edge trimming</li>
	 *            </ul>
	 * @return The generated slug according to the specified configuration
	 * @throws NullPointerException
	 *             if either text or config is null
	 */
	public static String slugify(String text, SlugifyConfig config)
	{
		Objects.requireNonNull(text);
		Objects.requireNonNull(config);

		String result = text;

		if (config.isToLowerCase())
		{
			result = result.toLowerCase(Locale.ROOT);
		}

		if (config.getReplacements() != null && !config.getReplacements().isEmpty())
		{
			result = StringExtensions.replaceAll(result, config.getReplacements());
		}

		if (config.isStripNonAlphanumeric())
		{
			if (config.isToLowerCase())
			{
				result = result.replaceAll("[^a-z0-9\\s-]", "");
			}
			else
			{
				result = result.replaceAll("[^a-zA-Z0-9\\s-]", "");
			}
		}

		String sep = config.getWhitespaceReplacement();
		if (sep != null && !sep.isEmpty())
		{
			result = result.replaceAll("\\s+", sep);
			result = result.replaceAll(Pattern.quote(sep) + "+", sep);
			if (config.isTrimEdges())
			{
				result = result
					.replaceAll("^" + Pattern.quote(sep) + "|" + Pattern.quote(sep) + "$", "");
			}
		}

		return result;
	}
}