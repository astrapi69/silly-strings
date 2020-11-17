package de.alpharogroup.string;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapFactory;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.TreeMap;

public final class SplitStringExtensions
{
	private SplitStringExtensions(){}

	public static final Map<String, Integer> splitToWords(String input) {
		String[] split = input.split(" ");
		Map<String, Integer> stringIntegerMap = MapFactory
			.newCounterMap(ListFactory.newArrayList(split));
		Map<String, Integer> wordCount = new TreeMap<>(stringIntegerMap);
		return wordCount;
	}

	public static Triple<String, String, String> getTripleFromMessage(String message) {
		int indexOfFirstCurlyBracket = message.indexOf(']');
		int indexOfEndUsername = message.indexOf(":", indexOfFirstCurlyBracket);
		String sendDate = message.substring(1, indexOfFirstCurlyBracket);
		String username = message.substring(indexOfFirstCurlyBracket +1, indexOfEndUsername);
		String userMessage = message.substring(indexOfEndUsername+1);
		return Triple.of(sendDate, username, userMessage);
	}
}
