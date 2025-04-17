package io.github.astrapi69.string.md.dp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class MarkdownProcessorTest
{

	@Test
	void testDefaultPipelineWithBasicMarkdown()
	{
		String markdown = """
			    # Title One
			    Some text here.

			    ## Subtitle Two
			    More text.

			    ### Final Section
			    Even more text.
			""".stripIndent();

		MarkdownContext context = new MarkdownContext();
		context.originalContent = markdown;

		MarkdownProcessor processor = MarkdownProcessor.defaultPipeline(new DefaultSlugStrategy());
		processor.process(context);

		// Assert headings
		List<String> headings = context.headings;
		assertEquals(3, headings.size());
		assertEquals("Title One", headings.get(0));
		assertEquals("Subtitle Two", headings.get(1));
		assertEquals("Final Section", headings.get(2));

		// Assert heading levels
		assertEquals(List.of(1, 2, 3), context.headingLevels);

		// Assert slugs
		List<String> slugs = context.slugs;
		assertEquals("title-one", slugs.get(0));
		assertEquals("subtitle-two", slugs.get(1));
		assertEquals("final-section", slugs.get(2));

		// Assert TOC content with indentation
		String expectedToc = String.join("\n", "- [Title One](#title-one)",
			"  - [Subtitle Two](#subtitle-two)", "    - [Final Section](#final-section)");

		assertEquals(expectedToc.trim(), context.toc.trim());
	}

	@Test
	void testHeadingsWithSpecialCharacters()
	{
		String markdown = """
			    # Welcome to the Jungle!
			    ## What's New in v2.0?
			    ### Über-cool Stuff & Features
			""".stripIndent();

		MarkdownContext context = new MarkdownContext();
		context.originalContent = markdown;

		MarkdownProcessor processor = MarkdownProcessor.defaultPipeline(new DefaultSlugStrategy());
		processor.process(context);

		assertEquals(
			List.of("Welcome to the Jungle!", "What's New in v2.0?", "Über-cool Stuff & Features"),
			context.headings);
		assertEquals(List.of(1, 2, 3), context.headingLevels);
		assertEquals(
			List.of("welcome-to-the-jungle", "whats-new-in-v20", "bercool-stuff--features"),
			context.slugs);
	}
}
