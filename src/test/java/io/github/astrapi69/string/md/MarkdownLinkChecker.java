package io.github.astrapi69.string.md;

import java.util.*;
import java.util.regex.*;

public class MarkdownLinkChecker
{

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
