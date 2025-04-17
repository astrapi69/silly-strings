package io.github.astrapi69.string.md;

import java.util.Map;

public class SlugifyConfig
{

	/**
	 * Map of characters to replace (e.g., é → e, ü → u)
	 */
	private final Map<String, String> replacements;

	/**
	 * Whether to convert to lowercase
	 */
	private final boolean toLowerCase;

	/**
	 * Whether to remove special characters (non a-z0-9)
	 */
	private final boolean stripNonAlphanumeric;

	/**
	 * Character to replace whitespace with (e.g., '-')
	 */
	private final String whitespaceReplacement;

	/**
	 * Whether to trim dashes or separator characters at the start/end
	 */
	private final boolean trimEdges;

	public SlugifyConfig(Map<String, String> replacements, boolean toLowerCase,
		boolean stripNonAlphanumeric, String whitespaceReplacement, boolean trimEdges)
	{
		this.replacements = replacements;
		this.toLowerCase = toLowerCase;
		this.stripNonAlphanumeric = stripNonAlphanumeric;
		this.whitespaceReplacement = whitespaceReplacement;
		this.trimEdges = trimEdges;
	}

	// Getters
	public Map<String, String> getReplacements()
	{
		return replacements;
	}

	public boolean isToLowerCase()
	{
		return toLowerCase;
	}

	public boolean isStripNonAlphanumeric()
	{
		return stripNonAlphanumeric;
	}

	public String getWhitespaceReplacement()
	{
		return whitespaceReplacement;
	}

	public boolean isTrimEdges()
	{
		return trimEdges;
	}
}
