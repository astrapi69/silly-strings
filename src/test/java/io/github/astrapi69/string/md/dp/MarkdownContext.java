package io.github.astrapi69.string.md.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all intermediate and final data during markdown processing
 */
class MarkdownContext
{
	public String originalContent;
	public List<String> headings = new ArrayList<>();
	public List<Integer> headingLevels = new ArrayList<>();
	public List<String> slugs = new ArrayList<>();
	public String toc = "";
	// You can add more fields like line numbers, etc.
}
