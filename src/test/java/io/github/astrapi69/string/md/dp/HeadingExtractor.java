package io.github.astrapi69.string.md.dp;

/**
 * Step that extracts headings from the markdown content
 */
class HeadingExtractor implements MarkdownProcessingStep
{
	@Override
	public void process(MarkdownContext context)
	{
		String[] lines = context.originalContent.split("\n");
		for (String line : lines)
		{
			String trimmed = line.trim();
			if (trimmed.matches("^#{1,6} .+"))
			{
				int level = 0;
				while (level < trimmed.length() && trimmed.charAt(level) == '#')
				{
					level++;
				}
				context.headingLevels.add(level);
				context.headings.add(trimmed.replaceFirst("^#+ ", ""));
			}
		}
	}
}