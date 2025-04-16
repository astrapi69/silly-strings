package io.github.astrapi69.string.md;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MarkdownAnchorFixerTest {

    @Test
    void testExtractFragmentLinks() {
        List<String> markdownLines = Arrays.asList(
            "- [Introduction](#introduction)",
            "- [How it works](#how-it-works)",
            "Some text without a link"
        );

        Set<String> result = MarkdownAnchorFixer.extractFragmentLinks(markdownLines);

        assertEquals(2, result.size());
        assertTrue(result.contains("introduction"));
        assertTrue(result.contains("how-it-works"));
    }

    @Test
    void testAddMissingHeadingIds() {
        List<String> markdownLines = Arrays.asList(
            "## Introduction",
            "Some content here.",
            "## How it works",
            "More text here."
        );

        Set<String> fragmentIds = new HashSet<>(Arrays.asList("introduction", "how-it-works"));

        List<String> result = MarkdownAnchorFixer.addMissingHeadingIds(markdownLines, fragmentIds);

        assertEquals("## Introduction {#introduction}", result.get(0));
        assertEquals("## How it works {#how-it-works}", result.get(2));
    }

    @Test
    void testSlugify() {
        assertEquals("introduction-au-cafe", MarkdownAnchorFixer.slugify("Introduction au café ☕"));
        assertEquals("idees-creatives", MarkdownAnchorFixer.slugify("Idées créatives !"));
        assertEquals("section-1", MarkdownAnchorFixer.slugify("Section 1"));
    }
}
