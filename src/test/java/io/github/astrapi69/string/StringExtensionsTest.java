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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import io.github.astrapi69.collection.array.ArrayFactory;
import io.github.astrapi69.collection.list.ListExtensions;
import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.collection.map.MapFactory;
import io.github.astrapi69.comparator.factory.ComparatorFactory;
import io.github.astrapi69.test.base.BaseTestCase;
import io.github.astrapi69.test.object.Person;

/**
 * The unit test class for the class StringExtensions.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class StringExtensionsTest extends BaseTestCase
{

	/**
	 * Test method for test the method {@link StringExtensions#toString(List)}
	 */
	@Test
	public void testToStringFromCharacterList()
	{
		String actual;
		String expected;
		List<Character> characterList;
		// new scenario...
		characterList = ListFactory.newArrayList(Character.valueOf('t'), Character.valueOf('o'),
			Character.valueOf('p'), Character.valueOf(' '), Character.valueOf('s'),
			Character.valueOf('e'), Character.valueOf('c'), Character.valueOf('r'),
			Character.valueOf('e'), Character.valueOf('t'));
		actual = StringExtensions.toString(characterList);
		expected = "top secret";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for test the method {@link StringExtensions#newCharacterList(String)}
	 */
	@Test
	public void testNewCharacterList()
	{
		List<Character> actual;
		List<Character> expected;
		String text;
		// new scenario...
		text = "top secret";
		actual = StringExtensions.newCharacterList(text);
		expected = ListFactory.newArrayList(Character.valueOf(' '), Character.valueOf('c'),
			Character.valueOf('e'), Character.valueOf('o'), Character.valueOf('p'),
			Character.valueOf('r'), Character.valueOf('s'), Character.valueOf('t'));
		assertEquals(actual, expected);
		// new scenario...
		text = "Lorem ipsum dolor sit amet, sea consul verterem perfecto id. Alii prompta electram te nec, at minimum copiosae quo. Eos iudico nominati oportere ei, usu at dicta legendos. In nostrum insolens disputando pro, iusto equidem ius id.";
		actual = StringExtensions.newCharacterList(text);

		expected = ListFactory.newArrayList(Character.valueOf((char)0x20), ',', '.', 'A', 'E', 'I',
			'L', 'a', 'c', 'd', 'e', 'f', 'g', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v');
		assertEquals(actual, expected);
	}

	/**
	 * Test method for test the method {@link StringExtensions#newCharacterList(String, Comparator)}
	 */
	@Test
	public void testNewCharacterListWithComparator()
	{
		List<Character> actual;
		List<Character> expected;
		List<Character> definedOrder;
		String text;

		// new scenario...
		text = "Lorem ipsum dolor sit amet, sea consul verterem perfecto id. Alii prompta electram te nec, at minimum copiosae quo. Eos iudico nominati oportere ei, usu at dicta legendos. In nostrum insolens disputando pro, iusto equidem ius id.";
		actual = StringExtensions.newCharacterList(text,
			Comparator.<Character> naturalOrder().reversed());

		expected = ListExtensions.revertOrder(ListFactory.newArrayList(
			Character.valueOf((char)0x20), ',', '.', 'A', 'E', 'I', 'L', 'a', 'c', 'd', 'e', 'f',
			'g', 'i', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'));
		assertEquals(actual, expected);

		// new scenario...
		text = "top secret";
		actual = StringExtensions.newCharacterList(text, Comparator.naturalOrder());
		expected = ListFactory.newArrayList(Character.valueOf(' '), Character.valueOf('c'),
			Character.valueOf('e'), Character.valueOf('o'), Character.valueOf('p'),
			Character.valueOf('r'), Character.valueOf('s'), Character.valueOf('t'));
		assertEquals(actual, expected);

		// new scenario with reversed order...
		text = "top secret";
		actual = StringExtensions.newCharacterList(text,
			Comparator.<Character> naturalOrder().reversed());
		expected = ListFactory.newArrayList(Character.valueOf('t'), Character.valueOf('s'),
			Character.valueOf('r'), Character.valueOf('p'), Character.valueOf('o'),
			Character.valueOf('e'), Character.valueOf('c'), Character.valueOf(' '));
		assertEquals(actual, expected);

		// new scenario with defined order...
		definedOrder = ListFactory.newArrayList(Character.valueOf('r'), Character.valueOf('t'),
			Character.valueOf('s'), Character.valueOf('e'), Character.valueOf('c'),
			Character.valueOf(' '), Character.valueOf('p'), Character.valueOf('o'));
		Comparator<Character> definedOrderComparator = ComparatorFactory
			.newDefinedOrderComparator(definedOrder);
		text = "top secret";
		actual = StringExtensions.newCharacterList(text, definedOrderComparator);
		expected = definedOrder;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#addSingleQuotationMarks(String)}.
	 */
	@Test
	public void testAddSingleQuotationMarks()
	{
		String expected;
		String actual;
		actual = StringExtensions.addSingleQuotationMarks("foo");
		expected = "'foo'";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#convertToBytearray(char[])}.
	 */
	@Test
	public void testConvertToBytearray()
	{
		byte[] actualBytearray;
		byte expected;
		byte actual;
		char[] charArray = { 'a', 'b', 'c', 'd', 'e' };
		byte[] expectedBytearray = "abcde".getBytes();
		actualBytearray = StringExtensions.convertToBytearray(charArray);

		for (int i = 0; i < actualBytearray.length; i++)
		{
			expected = expectedBytearray[i];
			actual = actualBytearray[i];
			assertEquals(expected, actual);
		}

		actualBytearray = StringExtensions.convertToBytearray(null);
		assertNull(actualBytearray);
	}

	/**
	 * Test method for {@link StringExtensions#convertToCharArray(byte[])}.
	 */
	@Test
	public void testConvertToCharArray()
	{
		char[] actualCharArray;
		char expected;
		char actual;
		char[] expectedCharArray = { 'a', 'b', 'c', 'd', 'e' };
		byte[] bytearray = "abcde".getBytes();
		actualCharArray = StringExtensions.convertToCharArray(bytearray);

		for (int i = 0; i < actualCharArray.length; i++)
		{
			expected = expectedCharArray[i];
			actual = actualCharArray[i];
			assertEquals(expected, actual);
		}
		actualCharArray = StringExtensions.convertToCharArray(null);
		assertNull(actualCharArray);
	}

	/**
	 * Test method for {@link StringExtensions#convertUnicodeStringToCharacter(String)}.
	 */
	@Test
	public void testConvertUnicodeStringToCharacter()
	{
		Character expected;
		Character actual;
		String theUnicodeString;
		theUnicodeString = "\u03A9";
		actual = StringExtensions.convertUnicodeStringToCharacter(theUnicodeString);
		expected = Character.valueOf('\u03A9');
		assertEquals(expected, actual);

		theUnicodeString = "\\u1F78";
		actual = StringExtensions.convertUnicodeStringToCharacter(theUnicodeString);
		expected = Character.valueOf('\u1F78');
		assertEquals(expected, actual);

		theUnicodeString = "\\u03BC";
		actual = StringExtensions.convertUnicodeStringToCharacter(theUnicodeString);
		expected = Character.valueOf('\u03BC');
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#firstCharacterToLowerCase(String)}
	 */
	@Test
	public void testFirstCharacterToLowerCase()
	{
		String expected;
		String actual;
		String argument;

		argument = "UserName";
		actual = StringExtensions.firstCharacterToLowerCase(argument);
		expected = "userName";
		assertEquals(actual, expected);

		argument = "";
		actual = StringExtensions.firstCharacterToLowerCase(argument);
		expected = "";
		assertEquals(actual, expected);

		argument = null;
		actual = StringExtensions.firstCharacterToLowerCase(argument);
		expected = null;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#firstCharacterToUpperCase(String)}
	 */
	@Test
	public void testFirstCharacterToUpperCase()
	{
		String expected;
		String actual;
		String argument;

		argument = "userName";
		actual = StringExtensions.firstCharacterToUpperCase(argument);
		expected = "UserName";
		assertEquals(actual, expected);

		argument = "";
		actual = StringExtensions.firstCharacterToUpperCase(argument);
		expected = "";
		assertEquals(actual, expected);

		argument = null;
		actual = StringExtensions.firstCharacterToUpperCase(argument);
		expected = null;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#getAttributName(String)}
	 */
	@Test
	public void testGetAttributName()
	{
		final String expected = "indio";
		final String testString = "indio[5][1]";
		final String compare = StringExtensions.getAttributName(testString);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#getIndex(String)}
	 */
	@Test
	public void testGetIndex()
	{
		final String expected = "5";
		final String testString = "indio[5][1]";
		final String compare = StringExtensions.getIndex(testString);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#getItemNumber(String)}
	 */
	@Test
	public void testGetItemNumber()
	{
		final String expected = "1";
		final String testString = "indio[5][1]";
		final String compare = StringExtensions.getItemNumber(testString);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#getStringAfterUnderscore(String)}
	 */
	@Test
	public void testGetStringAfterUnderscore()
	{
		final String expected = "value";
		final String element = "foobar_value";
		final String compare = StringExtensions.getStringAfterUnderscore(element);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#getStringBeforeUnderscore(String)}
	 */
	@Test
	public void testGetStringBeforeUnderscore()
	{
		final String expected = "foobar";
		final String element = "foobar_value";
		final String compare = StringExtensions.getStringBeforeUnderscore(element);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#getValue(Map, String, String)}.
	 */
	@Test
	public void testGetValue()
	{
		String expected;
		String actual;
		Map<String, String> data;
		String key;
		String value;
		String defaultValue;
		data = MapFactory.newHashMap();
		key = "foo";
		value = "bar";
		defaultValue = "bla";
		data.put(key, value);
		actual = StringExtensions.getValue(data, key, defaultValue);
		expected = value;
		assertEquals(expected, actual);

		key = "fasel";
		actual = StringExtensions.getValue(data, key, defaultValue);
		expected = defaultValue;
		assertEquals(expected, actual);

		key = "fasel";
		value = "";
		data.put(key, value);
		actual = StringExtensions.getValue(data, key, defaultValue);
		expected = defaultValue;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#isNullOrEmpty(String)}
	 */
	@Test
	public void testIsNullOrEmpty()
	{
		final String isNull = null;
		final String isEmpty = "";
		final String isNotNullOrEmpty = "foobar";

		actual = StringExtensions.isNullOrEmpty(isNull);
		assertTrue(actual);
		actual = StringExtensions.isNullOrEmpty(isEmpty);
		assertTrue(actual);
		actual = StringExtensions.isNullOrEmpty(isNotNullOrEmpty);
		assertFalse(actual);

	}

	/**
	 * Test method for {@link StringExtensions#isNumber(String)}
	 */
	@Test
	public void testIsNumber()
	{
		boolean expected;
		boolean actual;
		String argument;

		argument = "5";
		actual = StringExtensions.isNumber(argument);
		expected = true;
		assertEquals(actual, expected);

		argument = "foo";
		actual = StringExtensions.isNumber(argument);
		expected = false;
		assertEquals(actual, expected);

		argument = "";
		actual = StringExtensions.isNumber(argument);
		expected = false;
		assertEquals(actual, expected);

		argument = null;
		actual = StringExtensions.isNumber(argument);
		expected = false;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#lastCharacter(String)}
	 */
	@Test
	public void testLastCharacter()
	{
		String expected;
		String actual;
		expected = "e";
		actual = StringExtensions.lastCharacter("UserName");
		assertEquals(actual, expected);

		expected = "e";
		actual = StringExtensions.lastCharacter("e");
		assertEquals(actual, expected);

		expected = " ";
		actual = StringExtensions.lastCharacter(" ");
		assertEquals(actual, expected);

		expected = "";
		actual = StringExtensions.lastCharacter("");
		assertEquals(actual, expected);

		expected = "";
		actual = StringExtensions.lastCharacter(null);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#lastCharacterToUpperCase(String)}
	 */
	@Test
	public void testLastCharacterToUpperCase()
	{
		String expected;
		String actual;
		expected = "UserNamE";
		actual = StringExtensions.lastCharacterToUpperCase("UserName");
		assertEquals(actual, expected);

		expected = "E";
		actual = StringExtensions.lastCharacterToUpperCase("e");
		assertEquals(actual, expected);

		expected = " ";
		actual = StringExtensions.lastCharacterToUpperCase(" ");
		assertEquals(actual, expected);

		expected = "";
		actual = StringExtensions.lastCharacterToUpperCase("");
		assertEquals(actual, expected);

		expected = null;
		actual = StringExtensions.lastCharacterToUpperCase(null);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#filterStringsWithSeperator(List, String)}
	 */
	@Test
	public void testPrepareForNextIteration()
	{
		List<String> actual;
		List<String> expected;
		List<String> ignoreFieldNames;

		ignoreFieldNames = ListFactory.newArrayList("id", "serialVersionUID", "address.street",
			"address.city", "contact.name", "contact.email");
		actual = StringExtensions.filterStringsWithSeperator(ignoreFieldNames, ".");
		expected = ListFactory.newArrayList("street", "city", "name", "email");
		assertEquals(expected, actual);

		ignoreFieldNames = ListFactory.newArrayList("id", "serialVersionUID", "address.street",
			"address.city", "contact.name", "contact.email");
		actual = StringExtensions.filterStringsWithSeperator(ignoreFieldNames, "contact.");
		expected = ListFactory.newArrayList("name", "email");
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#addDoubleQuotationMarks(String)}
	 */
	@Test
	public void testPutQuotesToString()
	{
		final String expected = "\"Leonidas\"";
		final String withoutQuotes = "Leonidas";
		final String compare = StringExtensions.addDoubleQuotationMarks(withoutQuotes);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#readLine(String)}
	 */
	@Test
	public void testReadLine()
	{
		String expected;
		String actual;
		String argument;

		argument = "This is a test: Aha :\n and than foo bar:";
		expected = "This is a test: Aha :";
		actual = StringExtensions.readLine(argument);
		assertEquals(actual, expected);

		argument = "This is a test: Aha :\r and than foo bar:";
		expected = "This is a test: Aha :";
		actual = StringExtensions.readLine(argument);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link StringExtensions#removeEmptyString(String[])}.
	 */
	@Test
	public void testRemoveEmptyString()
	{
		final String expected = "Hickory   Dickory Dock mouse ran up the clock The struck one The ran down";
		String[] words = expected.split("\\W");

		String[] actual = StringExtensions.removeEmptyString(words);
		assertNotNull(actual);
		assertTrue(words.length == 16);
		assertTrue(actual.length == 14);
	}

	/**
	 * Test method for {@link StringExtensions#removeFirstAndLastCharacter(String)}
	 */
	@Test
	public void testRemoveFirstAndLastCharacter()
	{
		String actual;
		String expected;
		String input;
		// new scenario...
		input = "\"Leonidas\"";
		actual = StringExtensions.removeFirstAndLastCharacter(input);
		expected = "Leonidas";
		assertEquals(expected, actual);
		// new scenario...
		input = "[1,2,3]";
		actual = StringExtensions.removeFirstAndLastCharacter(input);
		expected = "1,2,3";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#removeNewlineCharacters(String)}.
	 */
	@Test
	public void testRemoveNewlineCharacters()
	{
		String expected;
		String actual;
		String subject = "Foo bar\r\n bla fasel\n";
		actual = StringExtensions.removeNewlineCharacters(subject);
		expected = "Foo bar bla fasel";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#removeQuotationMarks(String)}
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testRemoveQuotesFromString()
	{
		final String expected = "Leonidas";
		final String withQuotes = "\"Leonidas\"";
		final String compare = StringExtensions.removeQuotationMarks(withQuotes);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#replaceAll(String, String, String)}
	 */
	@Test
	public void testReplaceAll()
	{

		final String original = "This is a test: Aha : and than foo bar:";
		final String expected = "This is a test; Aha ; and than foo bar;";
		final String compare = StringExtensions.replaceAll(original, ":", ";");
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#replaceAll(String, String, String)}
	 */
	@Test
	public void testReplaceAllFromString()
	{
		final String original = "This is a test: Aha : and than foo bar:";
		final String expected = "This is a test; Aha ; and than foo bar;";
		final String compare = original.replaceAll(":", ";");
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#replaceAll(String, String[], String)}
	 */
	@Test
	public void testReplaceAllStringArray()
	{
		String expected;
		String original;
		String[] array;
		String compare;

		original = "(0049) 173/1234-50";
		expected = "0049173123450";
		array = ArrayFactory.newArray("(", ")", "-", "/", " ");
		compare = StringExtensions.replaceAll(original, array, "");
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link StringExtensions#replaceAll(String, Map)}
	 */
	@Test
	public void testReplaceAllWithMap()
	{
		String expected;
		String original;
		String compare;
		Map<String, String> replaceWith;
		// new scenario ...
		original = "(0049) 173/1234-50";
		expected = "0049173123450";
		final String[] array = { "(", ")", "-", "/", " " };
		replaceWith = MapFactory.newHashMap();
		replaceWith.put("(", "");
		replaceWith.put(")", "");
		replaceWith.put("-", "");
		replaceWith.put("/", "");
		replaceWith.put(" ", "");
		compare = StringExtensions.replaceAll(original, replaceWith);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	// =================================================================================== //


	/**
	 * Test method for {@link StringExtensions#replaceEach(String, String, String)}
	 */
	@Test
	public void testReplaceEach()
	{
		final String search = "\"com.foo.bar\"";
		final String replace = "\"bla.fasel\"";
		final String input = "Hickory Dickory Dock \"com.foo.bar\" mouse ran up the clock The \"com.foo.bar\" struck one The \"com.foo.bar\" ran down";
		final String expected = "Hickory Dickory Dock \"bla.fasel\" mouse ran up the clock The \"bla.fasel\" struck one The \"bla.fasel\" ran down";
		final String actual = StringExtensions.replaceEach(input, search, replace);
		assertTrue(expected.equals(actual));
	}

	/**
	 * Test method for {@link StringExtensions#replaceLast(String, String, String)}
	 */
	@Test
	public void testReplaceLast()
	{
		String expected;
		String actual;
		String argument;

		argument = "This is a test: Aha : and than foo bar:";
		actual = StringExtensions.replaceLast(argument, ":", ";");
		expected = "This is a test: Aha : and than foo bar;";
		assertEquals(expected, actual);

		argument = "This is a test: Aha : and than foo bar:";
		actual = StringExtensions.replaceLast(argument, ",", ";");
		expected = argument;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#splitByFixedLength(String, int)}
	 */
	@Test
	public void testSplitByLength()
	{
		final String input = "HickoryDickoryDockxxxmousexranxupxthexclockxThexcom.foo.barxstruckxonexThexxyxranxdownBlogBarFooEEE";

		final List<String> output = StringExtensions.splitByFixedLength(input, 7);

		assertTrue(output.size() == 15);
		assertEquals(output.get(1), "Dickory");
	}

	/**
	 * Test method for {@link StringExtensions#toIntegerArray(String, String)}
	 */
	@Test
	public void testToIntegerArray()
	{
		final String stringArray1 = "11, 12, 13, 14, 15, 16, 17, 18";

		final int[] expectedIntArray = { 11, 12, 13, 14, 15, 16, 17, 18 };
		final int[] intArray = StringExtensions.toIntegerArray(stringArray1, ",");
		for (int i = 0; i < intArray.length; i++)
		{
			assertTrue(intArray[i] == expectedIntArray[i]);
		}
	}

	/**
	 * Test method for {@link StringExtensions#toString(Object)}.
	 */
	@Test
	public void testToStringObject()
	{
		String expected;
		String actual;
		Person person = Person.builder().build();
		actual = StringExtensions.toString(person);
		expected = "Person(about=, gender=UNDEFINED, married=false, name=, nickname=)";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#toUnicode(String, boolean)}
	 */
	@Test
	public void testToUnicode()
	{
		String expected = "\\u00f6";
		String actual = StringExtensions.toUnicode("ö", true);
		assertEquals(expected, actual);

		expected = "\\u00F6";
		actual = StringExtensions.toUnicode("ö", false);
		assertEquals(expected, actual);

		expected = "\\u00F6\\u002C\\u0020\\u00DF\\u0020\\u00E4";
		actual = StringExtensions.toUnicode("ö, ß ä", false);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions#toUnicodeChars(String, boolean)}
	 */
	@Test
	public void testToUnicodeChars()
	{
		String expected;
		String actual;
		String argument;

		argument = "ö";
		actual = StringExtensions.toUnicodeChars(argument, true);
		expected = "\\u00f6";
		assertEquals(expected, actual);

		actual = StringExtensions.toUnicodeChars(argument, false);
		expected = "\\u00F6";
		assertEquals(expected, actual);

		argument = "ö, ß ä";
		actual = StringExtensions.toUnicodeChars(argument, false);
		expected = "\\u00F6, \\u00DF \\u00E4";
		assertEquals(expected, actual);

		argument = "τὸ μὲν οὖν κατὰ τὴν Ἀράτου τοῦ νεωτέρου στρατηγίαν ἔτος ἐτύγχανε διεληλυθὸς περὶ τὴν τῆς Πλειάδος";
		actual = StringExtensions.toUnicodeChars(argument, false);
		expected = "\\u03C4\\u1F78 \\u03BC\\u1F72\\u03BD \\u03BF\\u1F56\\u03BD"
			+ " \\u03BA\\u03B1\\u03C4\\u1F70 \\u03C4\\u1F74\\u03BD "
			+ "\\u1F08\\u03C1\\u03AC\\u03C4\\u03BF\\u03C5 \\u03C4\\u03BF\\u1FE6"
			+ " \\u03BD\\u03B5\\u03C9\\u03C4\\u03AD\\u03C1\\u03BF\\u03C5 "
			+ "\\u03C3\\u03C4\\u03C1\\u03B1\\u03C4\\u03B7\\u03B3\\u03AF\\u03B1\\u03BD"
			+ " \\u1F14\\u03C4\\u03BF\\u03C2 "
			+ "\\u1F10\\u03C4\\u03CD\\u03B3\\u03C7\\u03B1\\u03BD\\u03B5 "
			+ "\\u03B4\\u03B9\\u03B5\\u03BB\\u03B7\\u03BB\\u03C5\\u03B8\\u1F78\\u03C2 "
			+ "\\u03C0\\u03B5\\u03C1\\u1F76 \\u03C4\\u1F74\\u03BD \\u03C4\\u1FC6\\u03C2"
			+ " \\u03A0\\u03BB\\u03B5\\u03B9\\u03AC\\u03B4\\u03BF\\u03C2";
		assertEquals(expected, actual);

		argument = "";
		actual = StringExtensions.toUnicodeChars(argument, true);
		expected = "";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link StringExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(StringExtensions.class);
	}

}
