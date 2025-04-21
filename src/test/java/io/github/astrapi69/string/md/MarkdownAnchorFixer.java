package io.github.astrapi69.string.md;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.astrapi69.string.StringExtensions;

/**
 * Utility class for processing Markdown files by fixing missing heading anchor IDs Extracts all
 * internal fragment links, generates slugified IDs, and injects missing anchor IDs into heading
 * lines that reference those links
 */
public class MarkdownAnchorFixer
{

	/**
	 * Main entry point to fix a markdown file by injecting missing heading IDs Reads from
	 * 'ia_pour_tous_livre.gfm' and writes to 'ia_pour_tous_livre_fixed.gfm'
	 *
	 * @param args
	 *            command-line arguments (not used)
	 * @throws IOException
	 *             if file I/O operations fail
	 */
	public static void main(String[] args) throws IOException
	{
		Path inputPath = Paths.get("ia_pour_tous_livre.gfm");
		Path outputPath = Paths.get("ia_pour_tous_livre_fixed.gfm");

		List<String> lines = Files.readAllLines(inputPath);
		Set<String> fragmentIds = extractFragmentLinks(lines);

		List<String> fixedLines = addMissingHeadingIds(lines, fragmentIds);
		Files.write(outputPath, fixedLines);

		System.out.println("Done! Fixed file written to: " + outputPath);
	}

	/** Default map of common diacritic characters to ASCII replacements */
	public static final Map<String, String> DEFAULT_REPLACEMENTS = Map.ofEntries(
		Map.entry("à", "a"), Map.entry("â", "a"), Map.entry("ä", "a"), Map.entry("á", "a"),
		Map.entry("ã", "a"),

		Map.entry("é", "e"), Map.entry("è", "e"), Map.entry("ê", "e"), Map.entry("ë", "e"),

		Map.entry("î", "i"), Map.entry("ï", "i"),

		Map.entry("ô", "o"), Map.entry("ö", "o"), Map.entry("ó", "o"),

		Map.entry("ù", "u"), Map.entry("û", "u"), Map.entry("ü", "u"),

		Map.entry("ç", "c"));

	/**
	 * Extracts all internal Markdown fragment link IDs from the given lines
	 *
	 * @param lines
	 *            the list of lines to scan
	 * @return a set of all extracted fragment link IDs (e.g., from [text](#fragment-id))
	 */
	public static Set<String> extractFragmentLinks(List<String> lines)
	{
		Set<String> ids = new HashSet<>();
		Pattern linkPattern = Pattern.compile("\\[[^\\]]+\\]\\(#([^)]+)\\)");
		for (String line : lines)
		{
			Matcher matcher = linkPattern.matcher(line);
			while (matcher.find())
			{
				ids.add(matcher.group(1));
			}
		}
		return ids;
	}

	/**
	 * Adds missing anchor IDs to headings that are referenced by links but do not yet have an ID
	 *
	 * @param lines
	 *            the original Markdown lines
	 * @param ids
	 *            the set of fragment IDs that should exist
	 * @return a list of lines with missing heading IDs injected where appropriate
	 */
	public static List<String> addMissingHeadingIds(List<String> lines, Set<String> ids)
	{
		List<String> result = new ArrayList<>();
		Pattern headingPattern = Pattern.compile("^(#{2,6})\\s+(.*)$");

		for (String line : lines)
		{
			Matcher matcher = headingPattern.matcher(line);
			if (matcher.matches())
			{
				String headingText = matcher.group(2);
				String slug = slugify(headingText);
				if (ids.contains(slug) && !line.contains("{#"))
				{
					line += " {#" + slug + "}";
				}
			}
			result.add(line);
		}

		return result;
	}

	/**
	 * Converts a string to a slug by lowercasing and removing or replacing non-URL-safe characters
	 * Uses a predefined replacement map for common diacritics
	 *
	 * @param text
	 *            the input string to slugify
	 * @return the resulting slug
	 */
	public static String slugify(String text)
	{
		Objects.requireNonNull(text);

		String cleaned = text.toLowerCase(Locale.ROOT);
		cleaned = StringExtensions.replaceAll(cleaned, DEFAULT_REPLACEMENTS);
		cleaned = cleaned.replaceAll("[^a-z0-9\\s-]", "");
		cleaned = cleaned.replaceAll("\\s+", "-");
		cleaned = cleaned.replaceAll("-+", "-");
		cleaned = cleaned.replaceAll("^-|-$", "");

		return cleaned;
	}

	/**
	 * Converts a string to a slug using the provided {@link SlugifyConfig} for customization
	 *
	 * @param text
	 *            the input text to slugify
	 * @param config
	 *            the configuration for slugification behavior
	 * @return the resulting slug based on the provided configuration
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
			result = result.replaceAll("[^a-z0-9\\s-]", "");
		}

		if (config.getWhitespaceReplacement() != null
			&& !config.getWhitespaceReplacement().isEmpty())
		{
			result = result.replaceAll("\\s+", config.getWhitespaceReplacement());
			result = result.replaceAll(Pattern.quote(config.getWhitespaceReplacement()) + "+",
				config.getWhitespaceReplacement());
		}

		if (config.isTrimEdges() && config.getWhitespaceReplacement() != null)
		{
			String sep = Pattern.quote(config.getWhitespaceReplacement());
			result = result.replaceAll("^" + sep + "|" + sep + "$", "");
		}

		return result;
	}

}
