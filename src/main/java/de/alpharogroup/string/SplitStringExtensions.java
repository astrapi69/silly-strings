package de.alpharogroup.string;

import io.github.astrapi69.collections.list.ListFactory;
import io.github.astrapi69.collections.map.MapFactory;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.TreeMap;

/**
 * The class {@link SplitStringExtensions} provides methods for split string objects.<br>
 * <br>
 */
public final class SplitStringExtensions
{
	private SplitStringExtensions(){}

	/**
	 * Splits the given input string into words and puts them to a counter map
	 *
	 * @param input
	 *            The input string
	 * @return The map with the words and the count
	 */
	public static Map<String, Integer> splitToWordsAndCount(String input) {
		return splitToWordsAndCount(input, " ");
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
	public static Map<String, Integer> splitToWordsAndCount(String input, String regex) {
		String[] split = input.split(regex);
		Map<String, Integer> stringIntegerMap = MapFactory
			.newCounterMap(MapFactory.newHashMap(), ListFactory.newArrayList(split), false);
		return new TreeMap<>(stringIntegerMap);
	}

	/**
	 * Splits the given message string into three parts and put them into a {@link Triple} object
	 *
	 * @param message
	 *            The message string
	 * @return The splitted message in a {@link Triple} object
	 */
	public static Triple<String, String, String> getTripleFromMessage(String message) {
		int indexOfFirstCurlyBracket = message.indexOf(']');
		int indexOfEndUsername = message.indexOf(":", indexOfFirstCurlyBracket);
		String sendDate = message.substring(1, indexOfFirstCurlyBracket);
		String username = message.substring(indexOfFirstCurlyBracket +1, indexOfEndUsername);
		String userMessage = message.substring(indexOfEndUsername+1);
		return Triple.of(sendDate, username, userMessage);
	}
}
