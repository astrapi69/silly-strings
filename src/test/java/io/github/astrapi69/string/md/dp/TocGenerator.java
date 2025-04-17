package io.github.astrapi69.string.md.dp;

/**
 * Step that generates a Table of Contents from slugs
 */
class TocGenerator implements MarkdownProcessingStep
{
	@Override
	public void process(MarkdownContext context)
	{
		StringBuilder tocBuilder = new StringBuilder();
		for (int i = 0; i < context.headings.size(); i++)
		{
			String heading = context.headings.get(i);
			String slug = context.slugs.get(i);
			int level = context.headingLevels.get(i);
			String indent = "  ".repeat(Math.max(0, level - 1));
			tocBuilder.append(indent).append("- [").append(heading).append("](#").append(slug)
				.append(")\n");
		}
		context.toc = tocBuilder.toString();
	}
}
