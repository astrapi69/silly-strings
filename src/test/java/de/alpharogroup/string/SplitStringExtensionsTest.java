package de.alpharogroup.string;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapExtensions;
import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.collections.properties.SortedProperties;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.system.SystemFileExtensions;
import de.alpharogroup.file.write.WriteFileExtensions;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import static org.testng.AssertJUnit.assertEquals;

/**
 * The unit test class for the class {@link SplitStringExtensions}
 */
public class SplitStringExtensionsTest
{

	/**
	 * Test method for {@link SplitStringExtensions#splitToWords(String)}
	 */
	@Test(enabled = false)
	public void testSplitToWords() throws IOException
	{
		Map<String, String> converted;
		Map<String, Integer> actual;
		Map<String, Integer> expected;
		String input;
		// new scenario
		input = "";
		String foo = "[21:41, 13.9.2020] Julia: Aber hallo";
		actual = SplitStringExtensions.splitToWords(input);
		expected = MapFactory.newHashMap();
		expected.put("", 0);
		assertEquals(expected, actual);
		// new scenario
//		input = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna";
//		actual = SplitStringExtensions.splitToWords(input);
//		expected = MapFactory.newHashMap();
//		expected.put("", 0);
//		assertEquals(expected, actual);

		File userHomeDir = SystemFileExtensions.getUserHomeDir();
		File userTmpDir = new File(userHomeDir, "tmp");
		File entryFile = new File(userTmpDir, "van-convers.txt");
		List<String> linesInList = ReadFileExtensions.readLinesInList(entryFile, Charset.forName("UTF-8"));
		String inputOriginal = ReadFileExtensions.readFromFile(entryFile);
		String replace = inputOriginal.replace("\n", " ");
		replace = replace.replace("[", " ");
		replace = replace.replace("]", " ");
		replace = replace.replace(",", " ");
		replace = replace.replace(".", " ");
		replace = replace.replace("?", " ");
		actual = SplitStringExtensions.splitToWords(replace);
//		List<String> stringList = convertToList(actual);
//		File output = new File(userTmpDir, "van-convers-statistic.txt");
//		WriteFileExtensions.writeLinesToFile(output, stringList, "UTF-8");
//		MapExtensions.sortByValue(actual, false);
//		converted = convert(actual);

		expected.put("", 0);
//		assertEquals(expected, actual);

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
		Triple<String, String, String> tripleFromMessage = SplitStringExtensions.getTripleFromMessage(input);
		// TODO continue here
	}
//
//	public static Map<String, String> convert(Map<String, Integer> map) {
//		Map<String,String> newMap =new TreeMap<>();
//		for (Map.Entry<String, Integer> entry : map.entrySet()) {
//			if(entry.getValue() instanceof Integer) {
//				newMap.put(entry.getKey(), entry.getValue().toString());
//			}
//		}
//		return newMap;
//	}
//
//
//
//
//	public static List<String> convertToList(Map<String, Integer> map) {
//		List<String> list = ListFactory.newArrayList();
//		for (Map.Entry<String, Integer> entry : map.entrySet()) {
//			if(entry.getValue() instanceof Integer) {
////				newMap.put(entry.getKey(), entry.getValue().toString());
//				list.add(entry.getKey() + "=" + entry.getValue());
//			}
//		}
//		return list;
//	}
//
//	/**
//	 * Converts the given Map to a Properties object
//	 *
//	 * @param map
//	 *            The map to convert
//	 * @return The Properties produced from the Map
//	 */
//	public static<K, V> Properties toProperties(final Map<K, V> map)
//	{
//		final Properties answer = new Properties();
//		if (map != null) {
//			for (final Map.Entry<K, V> entry2 : map.entrySet()) {
//				final Map.Entry<?, ?> entry = entry2;
//				final Object key = entry.getKey().toString();
//				final Object value = entry.getValue().toString();
//				answer.put(key, value);
//			}
//		}
//		return answer;
//	}
}
