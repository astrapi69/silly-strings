package io.github.astrapi69.string.md;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import org.junit.jupiter.api.Test;

/**
 * Integration test class for generating unique slugs from markdown headings with disambiguation
 *
 * <p>
 * This test reads a markdown file and processes all headings to generate unique slugs, handling
 * duplicates by appending incremental numbers
 * </p>
 */
public class MarkdownSlugifyIntegrationTest
{

	/**
	 * Tests the generation of unique slugs from markdown headings with disambiguation
	 *
	 * <p>
	 * The test performs the following steps:
	 * <ol>
	 * <li>Reads a markdown file from the test resources</li>
	 * <li>Processes each heading line to generate a base slug</li>
	 * <li>Handles duplicate slugs by appending incremental numbers</li>
	 * <li>Verifies that all generated slugs are unique</li>
	 * </ol>
	 * </p>
	 *
	 * @throws IOException
	 *             if an I/O error occurs while reading the markdown file
	 */
	@Test
	void testGenerateSlugsFromHeadingsWithDisambiguation() throws IOException
	{
		Path mdFilePath = Paths.get("src/test/resources/ia_pour_tous_livre.gfm");
		assertTrue(Files.exists(mdFilePath), "Markdown file must exist");

		List<String> finalSlugs = new ArrayList<>();
		Map<String, Integer> slugCounter = new LinkedHashMap<>();

		try (BufferedReader reader = Files.newBufferedReader(mdFilePath))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				line = line.trim();
				if (line.startsWith("#"))
				{
					String headingText = line.replaceFirst("^#+\\s*", "");
					String baseSlug = SlugifyExtensions.slugify(headingText);

					int count = slugCounter.getOrDefault(baseSlug, 0);
					String uniqueSlug = baseSlug;
					if (count > 0)
					{
						uniqueSlug = baseSlug + "-" + (count + 1);
					}
					slugCounter.put(baseSlug, count + 1);
					finalSlugs.add(uniqueSlug);
				}
			}
		}

		// Output result
		System.out.println("Generated Slugs with Disambiguation:");
		finalSlugs.forEach(System.out::println);

		// Optionally assert that all slugs are now unique
		Set<String> slugSet = new HashSet<>(finalSlugs);
		assertEquals(finalSlugs.size(), slugSet.size(),
			"Slugs must be unique after disambiguation");
	}
}