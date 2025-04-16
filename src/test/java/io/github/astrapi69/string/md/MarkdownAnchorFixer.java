package io.github.astrapi69.string.md;

import io.github.astrapi69.string.StringExtensions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownAnchorFixer
{

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

	public static String slugify(String text) {
		Objects.requireNonNull(text);

		// Define replacement map for diacritics
		Map<String, String> replacements = new HashMap<>();
		replacements.put("à", "a");
		replacements.put("â", "a");
		replacements.put("ä", "a");
		replacements.put("á", "a");
		replacements.put("ã", "a");

		replacements.put("é", "e");
		replacements.put("è", "e");
		replacements.put("ê", "e");
		replacements.put("ë", "e");

		replacements.put("î", "i");
		replacements.put("ï", "i");

		replacements.put("ô", "o");
		replacements.put("ö", "o");
		replacements.put("ó", "o");

		replacements.put("ù", "u");
		replacements.put("û", "u");
		replacements.put("ü", "u");

		replacements.put("ç", "c");

		String cleaned = text.toLowerCase(Locale.ROOT);
		cleaned = StringExtensions.replaceAll(cleaned, replacements);
		cleaned = cleaned.replaceAll("[^a-z0-9\\s-]", ""); // Remove anything that's not a-z, 0-9, space or dash
		cleaned = cleaned.replaceAll("\\s+", "-");         // Convert spaces to hyphens
		cleaned = cleaned.replaceAll("-+", "-");           // Remove duplicate hyphens
		cleaned = cleaned.replaceAll("^-|-$", "");         // Trim hyphens from start and end

		return cleaned;
	}

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
			result = result.replaceAll("[^a-z0-9\\s-]", "");
		}

		if (config.getWhitespaceReplacement() != null && !config.getWhitespaceReplacement().isEmpty()) {
			result = result.replaceAll("\\s+", config.getWhitespaceReplacement());
			result = result.replaceAll(Pattern.quote(config.getWhitespaceReplacement()) + "+",
					config.getWhitespaceReplacement());
		}

		if (config.isTrimEdges() && config.getWhitespaceReplacement() != null) {
			String sep = Pattern.quote(config.getWhitespaceReplacement());
			result = result.replaceAll("^" + sep + "|" + sep + "$", "");
		}

		return result;
	}


}
