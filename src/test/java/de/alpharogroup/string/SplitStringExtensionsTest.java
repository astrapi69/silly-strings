package de.alpharogroup.string;

import io.github.astrapi69.collections.map.MapFactory;
import org.apache.commons.lang3.tuple.Triple;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;

/**
 * The unit test class for the class {@link SplitStringExtensions}
 */
public class SplitStringExtensionsTest
{

	/**
	 * Test method for {@link SplitStringExtensions#splitToWordsAndCount(String)}
	 */
	@Test(enabled = true) public void testSplitToWords() throws IOException
	{
		Map<String, String> converted;
		Map<String, Integer> actual;
		Map<String, Integer> expected;
		String input;
		// new scenario
		input = "[21:41, 13.9.2020] Julia: Aber hallo";
		actual = SplitStringExtensions.splitToWordsAndCount(input);
		expected = MapFactory.newHashMap();
		expected.put("13.9.2020]", 1);
		expected.put("Aber", 1);
		expected.put("Julia:", 1);
		expected.put("[21:41,", 1);
		expected.put("hallo", 1);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link SplitStringExtensions#getTripleFromMessage(String)}
	 */
	@Test public void testGetTripleFromMessage() throws IOException
	{
		String input;
		// new scenario
		input = "[21:41, 13.9.2020] Anton: Aber hallo";
		Triple<String, String, String> tripleFromMessage = SplitStringExtensions
			.getTripleFromMessage(input);
		// TODO continue here
		System.out.println(tripleFromMessage);
	}

}
