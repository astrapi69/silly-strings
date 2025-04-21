package io.github.astrapi69.string.md;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for analyzing Markdown documents for internal anchor link consistency
 *
 * Provides functionality to detect missing or unlinked heading fragment identifiers and outputs
 * diagnostics for matched, missing, and unused anchor links
 */
public class MarkdownLinkChecker
{

	/**
	 * Prints a summary of fragment links in the given Markdown lines
	 *
	 * Matches and compares anchor links (e.g., {@code [text](#fragment)}) with the actual heading
	 * IDs in the document
	 *
	 * @param lines
	 *            the lines of the Markdown file to analyze
	 */
	public static void checkFragmentLinks(List<String> lines)
	{
		Set<String> links = extractAllFragmentLinks(lines);
		Set<String> headings = extractAllHeadings(lines);

		Set<String> missing = new HashSet<>(links);
		missing.removeAll(headings);

		Set<String> unlinked = new HashSet<>(headings);
		unlinked.removeAll(links);

		Set<String> matched = new HashSet<>(links);
		matched.retainAll(headings);

		System.out.println("✅ Matched links:");
		matched.forEach(l -> System.out.println("  - " + l));

		System.out.println("\n❌ Missing fragments (used in links but not found in headings):");
		missing.forEach(m -> System.out.println("  - " + m));

		System.out.println("\n🪪 Unlinked headings (exist but not referenced):");
		unlinked.forEach(u -> System.out.println("  - " + u));
	}

	/**
	 * Extracts all internal fragment identifiers from markdown link references
	 *
	 * @param lines
	 *            the markdown lines to scan
	 * @return a set of fragment IDs extracted from markdown links
	 */
	public static Set<String> extractAllFragmentLinks(List<String> lines)
	{
		Set<String> ids = new HashSet<>();
		Pattern pattern = Pattern.compile("\\[[^\\]]+\\]\\(#([^\\)]+)\\)");
		for (String line : lines)
		{
			Matcher matcher = pattern.matcher(line);
			while (matcher.find())
			{
				ids.add(matcher.group(1));
			}
		}
		return ids;
	}

	/**
	 * Extracts all heading anchor identifiers from markdown heading lines
	 *
	 * If a heading contains an explicit anchor (e.g., {@code ## Title {#anchor}}), that ID is used.
	 * Otherwise, a slugified version of the heading text is generated
	 *
	 * @param lines
	 *            the markdown lines to analyze
	 * @return a set of anchor IDs from headings
	 */
	public static Set<String> extractAllHeadings(List<String> lines)
	{
		Set<String> headings = new HashSet<>();
		Pattern pattern = Pattern.compile("^(#{2,6})\\s+(.*?)\\s*(\\{#([^}]+)\\})?$");
		for (String line : lines)
		{
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches())
			{
				String headingText = matcher.group(2);
				String id = matcher.group(4);
				if (id != null)
				{
					headings.add(id);
				}
				else
				{
					headings.add(MarkdownAnchorFixer.slugify(headingText));
				}
			}
		}
		return headings;
	}
}
