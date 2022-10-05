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

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.tuple.Triple;

import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.collection.map.MapFactory;

/**
 * The class {@link SplitStringExtensions} provides methods for split string objects.<br>
 * <br>
 */
public final class SplitStringExtensions
{
	private SplitStringExtensions()
	{
	}

	/**
	 * Splits the given input string into words and puts them to a counter map
	 *
	 * @param input
	 *            The input string
	 * @return The map with the words and the count
	 */
	public static Map<String, Integer> splitToWordsAndCount(String input)
	{
		return splitToWordsAndCount(input, " ");
	}

	/**
	 * Splits the given input string into words and puts them to a counter map
	 *
	 * @param input
	 *            The input string
	 * @param findString
	 *            An array with search patterns.
	 * @return The map with the words and the count
	 */
	public static Map<String, Integer> splitToWordsAndCount(final String input,
		final String[] findString)
	{
		String normalizedString = StringExtensions.replaceAll(input, findString, " ");
		return splitToWordsAndCount(normalizedString);
	}

	/**
	 * Splits the given input string into words and puts them to a counter map
	 *
	 * @param input
	 *            The input string
	 * @param regex
	 *            The regex string that is used for splitting
	 * @return The map with the words and the count
	 */
	public static Map<String, Integer> splitToWordsAndCount(String input, String regex)
	{
		String[] split = input.split(regex);
		Map<String, Integer> stringIntegerMap = MapFactory.newCounterMap(MapFactory.newHashMap(),
			ListFactory.newArrayList(split), false);
		return new TreeMap<>(stringIntegerMap);
	}

	/**
	 * Splits the given message string into three parts and put them into a {@link Triple} object
	 *
	 * @param message
	 *            The message string
	 * @return The splitted message in a {@link Triple} object
	 */
	public static Triple<String, String, String> getTripleFromMessage(String message)
	{
		int indexOfFirstCurlyBracket = message.indexOf(']');
		int indexOfEndUsername = message.indexOf(":", indexOfFirstCurlyBracket);
		String sendDate = message.substring(1, indexOfFirstCurlyBracket);
		String username = message.substring(indexOfFirstCurlyBracket + 1, indexOfEndUsername);
		String userMessage = message.substring(indexOfEndUsername + 1);
		return Triple.of(sendDate, username, userMessage);
	}
}
