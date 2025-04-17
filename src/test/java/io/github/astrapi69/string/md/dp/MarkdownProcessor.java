package io.github.astrapi69.string.md.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class to process a Markdown file using a sequence of processing steps
 */
public class MarkdownProcessor
{

	private final List<MarkdownProcessingStep> steps = new ArrayList<>();

	public MarkdownProcessor addStep(MarkdownProcessingStep step)
	{
		steps.add(step);
		return this;
	}

	public void process(MarkdownContext context)
	{
		for (MarkdownProcessingStep step : steps)
		{
			step.process(context);
		}
	}

	public static MarkdownProcessor defaultPipeline(SlugStrategy slugStrategy)
	{
		return new MarkdownProcessor().addStep(new HeadingExtractor())
			.addStep(new SlugMapper(slugStrategy)).addStep(new TocGenerator());
	}
}