package io.github.astrapi69.string.md.dp;


/**
 * Step that maps headings to slugs using a strategy
 */
class SlugMapper implements MarkdownProcessingStep
{
	private final SlugStrategy slugStrategy;

	public SlugMapper(SlugStrategy slugStrategy)
	{
		this.slugStrategy = slugStrategy;
	}

	@Override
	public void process(MarkdownContext context)
	{
		for (String heading : context.headings)
		{
			context.slugs.add(slugStrategy.toSlug(heading));
		}
	}
}
