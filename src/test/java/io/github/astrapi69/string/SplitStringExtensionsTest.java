/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.github.astrapi69.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.collection.array.ArrayFactory;
import io.github.astrapi69.collection.map.MapFactory;

/**
 * The unit test class for the class {@link SplitStringExtensions}
 */
public class SplitStringExtensionsTest
{

	/**
	 * Test method for {@link SplitStringExtensions#splitToWordsAndCount(String)}
	 */
	@Test
	public void testSplitToWords()
	{
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
	 * Test method for {@link SplitStringExtensions#splitToWordsAndCount(String, String[])}
	 */
	@Test
	public void testSplitToWordsStringArray()
	{
		Map<String, Integer> actual;
		Map<String, Integer> expected;
		String[] array;
		String input;
		// new scenario
		array = ArrayFactory.newArray("[", "]", ",", " ");
		input = "[21:41, 13.9.2020] Julia: Aber hallo";
		actual = SplitStringExtensions.splitToWordsAndCount(input, array);
		expected = MapFactory.newHashMap();
		expected.put("", 3);
		expected.put("13.9.2020", 1);
		expected.put("Aber", 1);
		expected.put("Julia:", 1);
		expected.put("21:41", 1);
		expected.put("hallo", 1);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link SplitStringExtensions#getTripleFromMessage(String)}
	 */
	@Test
	public void testGetTripleFromMessage() throws IOException
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
