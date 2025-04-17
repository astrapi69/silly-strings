package io.github.astrapi69.string.md.dp;

/**
 * A basic implementation that lowercases and replaces spaces with dashes
 */
class DefaultSlugStrategy implements SlugStrategy
{
	@Override
	public String toSlug(String heading)
	{
		return heading.toLowerCase().replaceAll("[^a-z0-9 ]", "").replace(" ", "-");
	}
}
