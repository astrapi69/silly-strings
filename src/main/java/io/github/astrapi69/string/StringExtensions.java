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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * The class {@link StringExtensions} provides methods for manipulate string objects.<br>
 * <br>
 * Note: As the {@link String} class is immutable not the given String is manipulated, a new
 * {@link String} object is created with the manipulation.
 */
public final class StringExtensions
{
	/** A char array from the hexadecimal digits. */
	private static final char[] HEXADECIMAL_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private StringExtensions()
	{
	}

	/**
	 * Puts to the given String at the start and end quotes.
	 *
	 * @param s
	 *            The String to handle.
	 * @return The String with quotes.
	 */
	public static String addDoubleQuotationMarks(final String s)
	{
		Objects.requireNonNull(s);
		return "\"" + s + "\"";
	}

	/**
	 * Puts to the given String at the start and end quotes.
	 *
	 * @param s
	 *            The String to handle.
	 * @return The String with quotes.
	 */
	public static String addSingleQuotationMarks(final String s)
	{
		Objects.requireNonNull(s);
		return "'" + s + "'";
	}

	/**
	 * Converts the given chararray to a bytearray.
	 *
	 * @param source
	 *            the source.
	 * @return the byte[]
	 */
	public static byte[] convertToBytearray(final char[] source)
	{
		if (source == null)
		{
			return null;
		}
		final byte[] result = new byte[source.length];
		for (int i = 0; i < source.length; i++)
		{
			result[i] = (byte)source[i];
		}
		return result;
	}

	/**
	 * Convert the given byte array to char array.
	 *
	 * @param source
	 *            the source.
	 * @return the char[]
	 */
	public static char[] convertToCharArray(final byte[] source)
	{
		if (source == null)
		{
			return null;
		}
		final char[] result = new char[source.length];
		for (int i = 0; i < source.length; i++)
		{
			result[i] = (char)source[i];
		}
		return result;
	}

	/**
	 * Converts the given unicode String object to character object. <br>
	 * Example: the given unicode String object is "&#92;u03A9" <br>
	 * the return character object would be "&#937;".
	 *
	 * @param theUnicodeString
	 *            the unicode string
	 * @return the converted character object.
	 */
	public static Character convertUnicodeStringToCharacter(final String theUnicodeString)
	{
		Objects.requireNonNull(theUnicodeString);
		char current;
		final int length = theUnicodeString.length();
		final StringBuilder sb = new StringBuilder(length);

		for (int outerCount = 0; outerCount < length;)
		{
			current = theUnicodeString.charAt(outerCount++);
			if (current == '\\')
			{
				current = theUnicodeString.charAt(outerCount++);
				if (current == 'u')
				{
					int value = 0;
					for (int i = 0; i < 4; i++)
					{
						current = theUnicodeString.charAt(outerCount++);
						switch (current)
						{
							case '0' :
							case '1' :
							case '2' :
							case '3' :
							case '4' :
							case '5' :
							case '6' :
							case '7' :
							case '8' :
							case '9' :
								value = (value << 4) + current - '0';
								break;
							case 'a' :
							case 'b' :
							case 'c' :
							case 'd' :
							case 'e' :
							case 'f' :
								value = (value << 4) + 10 + current - 'a';
								break;
							case 'A' :
							case 'B' :
							case 'C' :
							case 'D' :
							case 'E' :
							case 'F' :
								value = (value << 4) + 10 + current - 'A';
								break;
							default :
								throw new MalformedUnicodeException("Given String object :::"
									+ theUnicodeString
									+ "::: is not a well formed unicode String object.\n"
									+ "Format for a well formed unicode String object:'\\uxxxx'.");
						}
					}
					sb.append((char)value);
				}
				else
				{
					if (current == 't')
					{
						current = '\t';
					}
					else if (current == 'r')
					{
						current = '\r';
					}
					else if (current == 'n')
					{
						current = '\n';
					}
					else if (current == 'f')
					{
						current = '\f';
					}
					sb.append(current);
				}
			}
			else
			{
				sb.append(current);
			}
		}
		final String result = sb.toString();
		return result.charAt(0);
	}

	/**
	 * Filter the given {@link List} of {@link String} objects with the given separator.
	 *
	 * @param ignoreFieldNames
	 *            the ignore field names
	 * @param separator
	 *            the separator
	 * @return the filtered {@link List}
	 */
	public static List<String> filterStringsWithSeperator(final List<String> ignoreFieldNames,
		final String separator)
	{
		Objects.requireNonNull(ignoreFieldNames);
		Objects.requireNonNull(separator);
		return ignoreFieldNames.stream().map(str -> {
			int pos = str.indexOf(separator);
			if (pos == -1)
			{
				return "";
			}
			return str.substring(pos + separator.length());
		}).filter(value -> value != null && !value.isEmpty()).collect(Collectors.toList());
	}

	/**
	 * Sets the first character from the given string to lower case and returns it. Example:<br>
	 * Given fieldName: UserName <br>
	 * Result: userName
	 *
	 * @param fieldName
	 *            The String to modify.
	 * @return The modified string.
	 */
	public static String firstCharacterToLowerCase(final String fieldName)
	{

		if (StringUtils.isNotEmpty(fieldName))
		{
			String firstCharacter = fieldName.substring(0, 1);
			firstCharacter = firstCharacter.toLowerCase();
			final char[] fc = firstCharacter.toCharArray();
			final char[] fn = fieldName.toCharArray();
			fn[0] = fc[0];
			return new String(fn);
		}
		return fieldName;
	}

	/**
	 * Sets the first character from the given string to upper case and returns it. Example:<br>
	 * Given fieldName: userName <br>
	 * Result: UserName
	 *
	 * @param fieldName
	 *            The String to modify.
	 * @return The modified string.
	 */
	public static String firstCharacterToUpperCase(final String fieldName)
	{
		if (StringUtils.isNotEmpty(fieldName))
		{
			String firstCharacter = fieldName.substring(0, 1);
			firstCharacter = firstCharacter.toUpperCase();
			final char[] fc = firstCharacter.toCharArray();
			final char[] fn = fieldName.toCharArray();
			fn[0] = fc[0];
			return new String(fn);
		}
		return fieldName;
	}

	/**
	 * Gets the Attribut-name without brackets. For example: If "indio[5][1]" is the given String
	 * than the name is "indio" and the first index is "5" and the second is "1". Than the Method
	 * returns "indio".
	 *
	 * @param name
	 *            The name with brackets.
	 * @return The name without brackets.
	 */
	public static String getAttributName(final String name)
	{
		Objects.requireNonNull(name);
		final int indexStart = name.indexOf("[");
		return name.substring(0, indexStart);
	}

	/**
	 * Gets the first index from the brackets. For example: If "indio[5][1]" is the given String
	 * than the name is "indio" and the first index is "5" and the second is "1". Than the Method
	 * returns "5".
	 *
	 * @param name
	 *            The name from the Attribute
	 * @return the index from the Attribute
	 */
	public static String getIndex(final String name)
	{
		Objects.requireNonNull(name);
		final int firstIndexStart = name.indexOf("[");
		final int firstIndexEnd = name.indexOf("]");
		return name.substring(firstIndexStart + 1, firstIndexEnd);
	}

	/**
	 * Gets the ItemNumber from the String name. For example: If "indio[5][1]" is the given String
	 * than the name is "indio" and the first index is "5" and the second is "1". Than the Method
	 * returns "1".
	 *
	 * @param name
	 *            the name
	 * @return the ItemNumber
	 */
	public static String getItemNumber(final String name)
	{
		Objects.requireNonNull(name);
		final int lastIndexStart = name.lastIndexOf("[");
		final int lastIndexEnd = name.lastIndexOf("]");
		return name.substring(lastIndexStart + 1, lastIndexEnd);
	}

	/**
	 * The Method getStringAfterUnderscore(String) gets the substring after the first underscore.
	 * For example:Assume that element=foobar_value then the result string is "value".
	 *
	 * @param element
	 *            The element to get the substing.
	 * @return The result String.
	 */
	public static String getStringAfterUnderscore(final String element)
	{
		Objects.requireNonNull(element);
		String returnString;
		final int i = element.indexOf("_");
		returnString = element.substring(i + 1);
		return returnString;
	}

	/**
	 * The Method getStringBeforeUnderscore(String) gets the substring before the first underscore.
	 * For example:Assume that element=foobar_value then the result string is "foobar".
	 *
	 * @param element
	 *            The element to get the substing.
	 * @return The result String.
	 */
	public static String getStringBeforeUnderscore(final String element)
	{
		Objects.requireNonNull(element);
		return element.substring(0, element.indexOf("_"));
	}

	/**
	 * Gets the value from the given map and if it does not exist or is empty the given default
	 * value will be returned.
	 *
	 * @param data
	 *            the data
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the value
	 */
	public static String getValue(final Map<String, String> data, final String key,
		final String defaultValue)
	{
		Objects.requireNonNull(data);
		String value = data.get(key);
		if (!(value != null && !value.isEmpty()))
		{
			value = defaultValue;
		}
		return value;
	}

	/**
	 * The method isNullOrEmpty(String) checks if the given String is empty or null.
	 *
	 * @param str
	 *            The String to check.
	 * @return true if the given String is empty or null otherwise false.
	 */
	public static boolean isNullOrEmpty(final String str)
	{
		return str == null || str.length() == 0;
	}

	/**
	 * Checks if the given String is an Number.
	 *
	 * @param testString
	 *            The String to check.
	 * @return true if the given String is a number otherwise false.
	 */
	public static boolean isNumber(final String testString)
	{
		if (StringUtils.isEmpty(testString))
		{
			return false;
		}
		for (int i = 0; i < testString.length(); i++)
		{
			if (!Character.isDigit(testString.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Gets the last character of the given String object.
	 *
	 * @param str
	 *            the str
	 * @return the last character
	 */
	public static String lastCharacter(final String str)
	{
		if (str == null || str.length() == 0)
		{
			return "";
		}
		return str.substring(str.length() - 1);
	}

	/**
	 * Sets the last character from the given string to upper case and returns it. Example:<br>
	 * Given fieldName: userName <br>
	 * Result: userNamE
	 *
	 * @param fieldName
	 *            The String to modify.
	 * @return The modified string.
	 */
	public static String lastCharacterToUpperCase(final String fieldName)
	{
		if (StringUtils.isNotEmpty(fieldName))
		{
			final int length = fieldName.length();
			String lastCharacter = fieldName.substring(length - 1, length);
			lastCharacter = lastCharacter.toUpperCase();
			final char[] fc = lastCharacter.toCharArray();
			final char[] fn = fieldName.toCharArray();
			fn[length - 1] = fc[0];
			return new String(fn);
		}
		return fieldName;
	}

	/**
	 * Reads the given String till it finds a carriage return and returns it.
	 *
	 * @param stringWithCarriageReturns
	 *            The String.
	 * @return Return the String till the carriage return.
	 */
	public static String readLine(final String stringWithCarriageReturns)
	{
		String returnString = "";
		int index = 0;
		index = stringWithCarriageReturns.indexOf(System.getProperty("line.separator"));
		if (index < 0)
		{
			index = stringWithCarriageReturns.indexOf("\n");
		}
		if (index < 0)
		{
			index = stringWithCarriageReturns.indexOf("\r");
		}
		returnString = stringWithCarriageReturns.substring(0, index);
		return returnString;

	}

	/**
	 * Removes empty Strings from the given string array and gives it back. For instance if you use
	 * the String.split("\\W") method you get all the words from the given String as an string
	 * array. But this array contains empty Strings too. This method removes this empty strings from
	 * that array.
	 *
	 * @param words
	 *            The string array to remove empty strings.
	 * @return An string array without empty Strings.
	 */
	public static String[] removeEmptyString(final String[] words)
	{
		final List<String> al = new ArrayList<>();
		for (int i = 0; i < words.length; i++)
		{
			if (!words[i].isEmpty())
			{
				al.add(words[i]);
			}
		}
		return al.toArray(new String[al.size()]);
	}

	/**
	 * Removes the first and the last character from the given String
	 *
	 * @param s
	 *            The String to handle
	 * @return The resulted String
	 */
	public static String removeFirstAndLastCharacter(final String s)
	{
		Objects.requireNonNull(s);
		return s.substring(1, s.length() - 1);
	}

	/**
	 * Removes the newline characters from the given String.
	 *
	 * @param subject
	 *            the subject
	 * @return the string
	 */
	public static String removeNewlineCharacters(String subject)
	{
		if (subject.contains("\n"))
		{
			subject = subject.replace("\n", "");
		}
		if (subject.contains("\r"))
		{
			subject = subject.replace("\r", "");
		}
		return subject;
	}

	/**
	 * Removes from the given String at the start and end quotes.
	 *
	 * @param s
	 *            The String to handle.
	 * @return The String without quotes.
	 */
	public static String removeQuotationMarks(final String s)
	{
		Objects.requireNonNull(s);
		return removeFirstAndLastCharacter(s);
	}

	/**
	 * Replaces all occurrences of a String within another String.
	 *
	 * @param original
	 *            The String to search.
	 * @param findString
	 *            The search pattern.
	 * @param replaceWith
	 *            Replace with that String.
	 * @return Returns a new String with the replaced Strings.
	 */
	public static String replaceAll(final String original, final String findString,
		final String replaceWith)
	{
		Objects.requireNonNull(original);
		Objects.requireNonNull(findString);
		Objects.requireNonNull(replaceWith);
		final StringBuilder originalFiltered = new StringBuilder();
		int next = 0;
		int count = 0;
		final int length = findString.length();

		while (count > -1)
		{
			count = original.indexOf(findString, next);

			if (count > -1)
			{
				originalFiltered.append(original, next, count);
				originalFiltered.append(replaceWith);
				next = count + length;
			}
		}

		originalFiltered.append(original.substring(next));

		return originalFiltered.toString();
	}

	/**
	 * Replaces all occurrences of a String within another String.
	 *
	 * @param original
	 *            The String to search.
	 * @param findString
	 *            An array with search patterns.
	 * @param replaceWith
	 *            Replace with that String.
	 * @return Returns a new String with the replaced Strings.
	 */
	public static String replaceAll(final String original, final String[] findString,
		final String replaceWith)
	{
		Objects.requireNonNull(original);
		Objects.requireNonNull(findString);
		Objects.requireNonNull(replaceWith);
		String result = original;
		for (final String element : findString)
		{
			result = replaceAll(result, element, replaceWith);
		}
		return result;
	}

	/**
	 * Replaces all occurrences of a String within another String.
	 *
	 * @param original
	 *            The String to search.
	 * @param replaceWith
	 *            Replace map with the specific replace values
	 * @return Returns a new String with the replaced Strings.
	 */
	public static String replaceAll(final String original, final Map<String, String> replaceWith)
	{
		Objects.requireNonNull(original);
		Objects.requireNonNull(replaceWith);
		String result = original;
		for (Map.Entry<String, String> entry : replaceWith.entrySet())
		{
			result = replaceAll(result, entry.getKey(), entry.getValue());
		}
		return result;
	}

	/**
	 * Replace each occurences from the search pattern(regex) with the given replace String of the
	 * given input String.
	 *
	 * @param input
	 *            the input String that will be changed.
	 * @param searchRegexPattern
	 *            the search regex pattern
	 * @param replace
	 *            the String to replace with.
	 * @return the resulted string
	 */
	public static String replaceEach(final String input, final String searchRegexPattern,
		final String replace)
	{
		Objects.requireNonNull(input);
		Objects.requireNonNull(searchRegexPattern);
		Objects.requireNonNull(replace);
		final Pattern pattern = Pattern.compile(searchRegexPattern);
		final Matcher matcher = pattern.matcher(input);
		return matcher.replaceAll(replace);
	}

	/**
	 * Replaces the last occurrence of a String within another String.
	 *
	 * @param original
	 *            The String to search.
	 * @param findString
	 *            The search pattern.
	 * @param replacement
	 *            Replace with that String.
	 * @return Returns a new String with the replaced Strings.
	 */
	public static String replaceLast(final String original, final String findString,
		final String replacement)
	{
		Objects.requireNonNull(original);
		Objects.requireNonNull(findString);
		Objects.requireNonNull(replacement);
		final int index = original.lastIndexOf(findString);
		if (index == -1)
		{
			return original;
		}
		final StringBuilder originalFiltered = new StringBuilder();
		originalFiltered.append(original, 0, index);
		originalFiltered.append(replacement);
		originalFiltered.append(original.substring(index + findString.length()));
		return originalFiltered.toString().trim();
	}

	/**
	 * Split the given {@link String} in parts in the given fixed length.
	 *
	 * @param input
	 *            the input
	 * @param fixedLength
	 *            the fixed length
	 * @return the list with the splitted {@link String} objects
	 */
	public static List<String> splitByFixedLength(final String input, final int fixedLength)
	{
		Objects.requireNonNull(input);
		final List<String> parts = new ArrayList<>();
		int beginIndex = 0;
		while (beginIndex < input.length())
		{
			final int endIndex = Math.min(beginIndex + fixedLength, input.length());
			final String part = input.substring(beginIndex, endIndex);
			parts.add(part);
			beginIndex += fixedLength;
		}
		return parts;
	}

	/**
	 * To hex.
	 *
	 * @param i
	 *            the i
	 * @return the char
	 */
	private static char toHex(final int i)
	{
		return HEXADECIMAL_DIGITS[i & 0xF];
	}

	/**
	 * Transforms the given {@link String} that holds successive numbers delimited with a delimiter
	 * into an integer array. Note: A valid {@link String} object holds only integer values
	 * delimited with the given delimiter. Example: A valid {@link String} object is "12, 13, 14,
	 * 16, 17, 18" that results in an integer array of [12, 13, 14, 16, 17, 18]
	 *
	 * @param integerArrayAsString
	 *            the integer array as string
	 * @param delimiter
	 *            the delimiter
	 * @return the created integer array from the given {@link String}
	 * @throws NumberFormatException
	 *             is thrown if the given {@link String} is not valid.
	 */
	public static int[] toIntegerArray(final String integerArrayAsString, final String delimiter)
		throws NumberFormatException
	{
		Objects.requireNonNull(integerArrayAsString);
		Objects.requireNonNull(delimiter);
		final String[] splittedNumbers = integerArrayAsString.replaceAll("\\s", "")
			.split(delimiter);
		final int[] integerArray = new int[splittedNumbers.length];
		for (int i = 0; i < splittedNumbers.length; i++)
		{
			final int currentNumber = Integer.parseInt(splittedNumbers[i]);
			integerArray[i] = currentNumber;
		}
		return integerArray;
	}

	/**
	 * Prints the {@link Object#toString()} and if the given object is null a corresponding
	 * information.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @return the string
	 */
	public static <T> String toString(final T object)
	{
		Objects.requireNonNull(object);
		return Objects.toString(object);
	}

	/**
	 * Converts all characters from the given String to unicodes characters encoded like &#92;uxxxx.
	 *
	 * @param toUnicode
	 *            The String to convert.
	 * @param toLowerCase
	 *            If true the letters from the unicode characters are lower case.
	 * @return The converted String.
	 */
	public static String toUnicode(final String toUnicode, final boolean toLowerCase)
	{
		Objects.requireNonNull(toUnicode);
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < toUnicode.length(); i++)
		{
			String hex = Integer.toHexString(toUnicode.codePointAt(i));
			if (toLowerCase)
			{
				hex = hex.toLowerCase();
			}
			else
			{
				hex = hex.toUpperCase();
			}
			final String hexWithZeros = "0000" + hex;
			final String hexCodeWithLeadingZeros = hexWithZeros
				.substring(hexWithZeros.length() - 4);
			sb.append("\\u" + hexCodeWithLeadingZeros);
		}
		return sb.toString();
	}

	/**
	 * Converts all characters from the given String to unicodes characters encoded like &#92;uxxxx.
	 *
	 * @param s
	 *            The String to convert.
	 * @param toLowerCase
	 *            If true the letters from the unicode characters are lower case.
	 * @return The converted String.
	 */
	public static String toUnicodeChars(final String s, final boolean toLowerCase)
	{
		Objects.requireNonNull(s);
		if (s.length() == 0)
		{
			return s;
		}
		final int length = s.length();
		final int sbLength = length * 2;
		final StringBuilder sb = new StringBuilder(sbLength);
		for (int i = 0; i < length; i++)
		{
			final char c = s.charAt(i);
			if (c > 61 && c < 127)
			{
				if (c == '\\')
				{
					sb.append('\\');
					sb.append('\\');
					continue;
				}
				sb.append(c);
				continue;
			}
			switch (c)
			{
				case '\f' :
					sb.append('\\');
					sb.append('f');
					break;
				case '\n' :
					sb.append('\\');
					sb.append('n');
					break;
				case '\r' :
					sb.append('\\');
					sb.append('r');
					break;
				case '\t' :
					sb.append('\\');
					sb.append('t');
					break;
				case ' ' :
					if (i == 0)
					{
						sb.append('\\');
					}
					sb.append(' ');
					break;
				case ':' :
				case '#' :
				case '=' :
				case '!' :
					sb.append('\\');
					sb.append(c);
					break;
				default :
					if (c < 0x0020 || c > 0x007e)
					{
						sb.append('\\');
						sb.append('u');
						sb.append(toHex(c >> 12 & 0xF));
						sb.append(toHex(c >> 8 & 0xF));
						sb.append(toHex(c >> 4 & 0xF));
						sb.append(toHex(c & 0xF));
					}
					else
					{
						sb.append(c);
					}
			}
		}
		final String returnString = sb.toString();
		if (toLowerCase)
		{
			return returnString.toLowerCase();
		}
		else
		{
			return returnString;
		}
	}

	/**
	 * Factory method for create new {@link ArrayList} of unique characters from the given text
	 *
	 * @param input
	 *            the input text
	 * @return the new {@link List} with the unique characters
	 */
	public static List<Character> newCharacterList(String input)
	{
		Objects.requireNonNull(input);
		return newCharacterList(input, Comparator.naturalOrder());
	}

	/**
	 * Factory method for create new {@link ArrayList} of unique characters from the given text
	 * sorted with the given {@link Comparator} <br>
	 * <br>
	 * Note: This method can be used for a custom Comparator that have a defined order. For example:
	 * <code>
	 * // defined custom order
	 * List&lt;Character&gt; definedOrder = Arrays.asList('c', 'b', 'a', 'd', '.', ...);
	 * Comparator&lt;Character&gt; customComparator = Comparator.comparing(character -&gt; definedOrder.indexOf(character));
	 * </code>
	 *
	 * @param text
	 *            the text
	 * @param comparator
	 *            the comparator
	 * @return the new {@link List} with the unique characters
	 */
	public static List<Character> newCharacterList(final String text,
		final Comparator<Character> comparator)
	{
		Objects.requireNonNull(text);
		Objects.requireNonNull(comparator);
		return new ArrayList<>(text.chars().mapToObj(i -> (char)i)
			.collect(Collectors.toCollection(() -> new TreeSet<>(comparator))));
	}

	/**
	 * Converts the given character list to a String
	 *
	 * @param characterList
	 *            the list of characters to convert
	 * @return the new {@link String} from the given character list
	 */
	public static String toString(List<Character> characterList)
	{
		StringBuilder sb = new StringBuilder();
		for (char ac : characterList)
		{
			sb.append(ac);
		}
		return sb.toString();
	}

}
