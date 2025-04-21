package io.github.astrapi69.string.md;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

/**
 * Unit test class for {@link MarkdownLinkChecker}
 *
 * Tests fragment link extraction, heading ID extraction (explicit and implicit), and comparison
 * between linked and defined anchor IDs
 */
public class MarkdownLinkCheckerTest
{

	/**
	 * Tests that all valid internal fragment links are extracted correctly from markdown lines
	 */
	@Test
	void testExtractAllFragmentLinks()
	{
		List<String> lines = Arrays.asList("- [Intro](#introduction)", "- [Plan](#daily-plan)",
			"This is [another](#section-3) link");

		Set<String> links = MarkdownLinkChecker.extractAllFragmentLinks(lines);

		assertEquals(3, links.size());
		assertTrue(links.contains("introduction"));
		assertTrue(links.contains("daily-plan"));
		assertTrue(links.contains("section-3"));
	}

	/**
	 * Tests extraction of headings that explicitly define an anchor ID using {#id}
	 */
	@Test
	void testExtractAllHeadings_withExplicitIds()
	{
		List<String> lines = Arrays.asList("## Introduction {#introduction}",
			"## Planning {#daily-plan}", "### Section Three {#section-3}");

		Set<String> headings = MarkdownLinkChecker.extractAllHeadings(lines);

		assertEquals(3, headings.size());
		assertTrue(headings.contains("introduction"));
		assertTrue(headings.contains("daily-plan"));
		assertTrue(headings.contains("section-3"));
	}

	/**
	 * Tests extraction of headings that do not define explicit IDs Verifies slugification of
	 * heading text
	 */
	@Test
	void testExtractAllHeadings_withImplicitSlugs()
	{
		List<String> lines = Arrays.asList("## Introduction", "### Daily Plan!", "## Café ☕");

		Set<String> headings = MarkdownLinkChecker.extractAllHeadings(lines);

		assertTrue(headings.contains(MarkdownAnchorFixer.slugify("Introduction")));
		assertTrue(headings.contains(MarkdownAnchorFixer.slugify("Daily Plan!")));
		assertTrue(headings.contains(MarkdownAnchorFixer.slugify("Café ☕")));
	}

	/**
	 * Tests comparison logic between extracted links and headings Ensures missing and unlinked sets
	 * are computed as expected
	 */
	@Test
	void testMatchMissingAndUnlinked()
	{
		List<String> markdown = Arrays.asList("- [Intro](#introduction)", "- [Plan](#daily-plan)",
			"## Introduction", "### Extra Only");

		Set<String> links = MarkdownLinkChecker.extractAllFragmentLinks(markdown);
		Set<String> headings = MarkdownLinkChecker.extractAllHeadings(markdown);

		Set<String> missing = new HashSet<>(links);
		missing.removeAll(headings);

		Set<String> unlinked = new HashSet<>(headings);
		unlinked.removeAll(links);

		assertTrue(missing.contains("daily-plan"));
		assertTrue(unlinked.contains(MarkdownAnchorFixer.slugify("Extra Only")));
	}
}
