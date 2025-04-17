package io.github.astrapi69.string.md.dp;

/**
 * Interface to allow pluggable slug strategies (GitHub, Pandoc, etc.)
 */
interface SlugStrategy
{
	String toSlug(String heading);
}
