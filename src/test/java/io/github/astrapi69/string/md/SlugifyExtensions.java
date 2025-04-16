package io.github.astrapi69.string.md;

import io.github.astrapi69.string.StringExtensions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class SlugifyExtensions {

	private static final Map<String, String> DEFAULT_REPLACEMENTS;
	static {
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

	private static final SlugifyConfig DEFAULT_CONFIG = new SlugifyConfig(
		DEFAULT_REPLACEMENTS,
		true,   // toLowerCase
		true,   // stripNonAlphanumeric
		"-",    // whitespaceReplacement
		true    // trimEdges
	);

	/**
	 * Slugifies the given text using the default config
	 *
	 * @param text The input text
	 * @return The slugified result
	 */
	public static String slugify(String text) {
		return slugify(text, DEFAULT_CONFIG);
	}

	/**
	 * Slugifies the given text using a custom configuration
	 *
	 * @param text   The input text
	 * @param config Configuration for slugification
	 * @return The slugified result
	 */
	public static String slugify(String text, SlugifyConfig config) {
		Objects.requireNonNull(text);
		Objects.requireNonNull(config);

		String result = text;

		if (config.isToLowerCase()) {
			result = result.toLowerCase(Locale.ROOT);
		}

		if (config.getReplacements() != null && !config.getReplacements().isEmpty()) {
			result = StringExtensions.replaceAll(result, config.getReplacements());
		}

		if (config.isStripNonAlphanumeric()) {
			if (config.isToLowerCase()) {
				result = result.replaceAll("[^a-z0-9\\s-]", "");
			} else {
				result = result.replaceAll("[^a-zA-Z0-9\\s-]", "");
			}
		}

		String sep = config.getWhitespaceReplacement();
		if (sep != null && !sep.isEmpty()) {
			result = result.replaceAll("\\s+", sep);
			result = result.replaceAll(Pattern.quote(sep) + "+", sep);
			if (config.isTrimEdges()) {
				result = result.replaceAll("^" + Pattern.quote(sep) + "|" + Pattern.quote(sep) + "$", "");
			}
		}

		return result;
	}
}
