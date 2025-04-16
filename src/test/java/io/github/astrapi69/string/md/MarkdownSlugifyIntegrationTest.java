package io.github.astrapi69.string.md;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MarkdownSlugifyIntegrationTest {

	@Test
	void testGenerateSlugsFromHeadingsWithDisambiguation() throws IOException {
		Path mdFilePath = Paths.get("src/test/resources/ia_pour_tous_livre.gfm");
		assertTrue(Files.exists(mdFilePath), "Markdown file must exist");

		List<String> finalSlugs = new ArrayList<>();
		Map<String, Integer> slugCounter = new LinkedHashMap<>();

		try (BufferedReader reader = Files.newBufferedReader(mdFilePath)) {
			String line;
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("#")) {
					String headingText = line.replaceFirst("^#+\\s*", "");
					String baseSlug = SlugifyExtensions.slugify(headingText);

					int count = slugCounter.getOrDefault(baseSlug, 0);
					String uniqueSlug = baseSlug;
					if (count > 0) {
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
		assertEquals(finalSlugs.size(), slugSet.size(), "Slugs must be unique after disambiguation");
	}
}
