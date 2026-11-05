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
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * The class {@link CaseExtensionsTest} provides unit tests for the methods in the
 * {@link CaseExtensions} class.
 */
public class CaseExtensionsTest
{

	/**
	 * Test method for {@link CaseExtensions#kebabToUpperSnakeCase(String)}.
	 */
	@Test
	void kebabToUpperSnakeCase()
	{
		String actual;
		String expected;
		String input;

		input = "example-string-for-conversion";

		// new scenario...
		actual = CaseExtensions.kebabToUpperSnakeCase(input);
		expected = "EXAMPLE_STRING_FOR_CONVERSION";
		assertEquals(expected, actual);

		// Test for null input
		input = null;
		actual = CaseExtensions.kebabToUpperSnakeCase(input);
		assertNull(actual);

		// Test for empty input
		input = "";
		actual = CaseExtensions.kebabToUpperSnakeCase(input);
		expected = "";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CaseExtensions#kebabToSnakeCase(String)}.
	 */
	@Test
	void kebabToSnakeCase()
	{
		String actual;
		String expected;
		String input;

		input = "example-string-for-conversion";

		// new scenario...
		actual = CaseExtensions.kebabToSnakeCase(input);
		expected = "example_string_for_conversion";
		assertEquals(expected, actual);

		// Test for null input
		input = null;
		actual = CaseExtensions.kebabToSnakeCase(input);
		assertNull(actual);

		// Test for empty input
		input = "";
		actual = CaseExtensions.kebabToSnakeCase(input);
		expected = "";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CaseExtensions#snakeToPascalCase(String)}.
	 */
	@Test
	void snakeToPascalCase()
	{
		String actual;
		String expected;
		String input;

		input = "example_string_for_conversion";

		// new scenario...
		actual = CaseExtensions.snakeToPascalCase(input);
		expected = "ExampleStringForConversion";
		assertEquals(expected, actual);

		// Test for null input
		input = null;
		actual = CaseExtensions.snakeToPascalCase(input);
		assertNull(actual);

		// Test for empty input
		input = "";
		actual = CaseExtensions.snakeToPascalCase(input);
		expected = "";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CaseExtensions#kebabToDotCase(String)}.
	 */
	@Test
	void kebabToDotCase()
	{
		String actual;
		String expected;
		String input;

		input = "example-string-for-conversion";

		// new scenario...
		actual = CaseExtensions.kebabToDotCase(input);
		expected = "example.string.for.conversion";
		assertEquals(expected, actual);

		// Test for null input
		input = null;
		actual = CaseExtensions.kebabToDotCase(input);
		assertNull(actual);

		// Test for empty input
		input = "";
		actual = CaseExtensions.kebabToDotCase(input);
		expected = "";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link CaseExtensions#kebabToCamelCase(String)}.
	 */
	@Test
	void kebabToCamelCase()
	{
		String actual;
		String expected;
		String input;

		input = "example-string-for-conversion";

		// new scenario... camelCase starts with a lower-case letter, PascalCase is the form of
		// snakeToPascalCase (#39)
		actual = CaseExtensions.kebabToCamelCase(input);
		expected = "exampleStringForConversion";
		assertEquals(expected, actual);
		assertEquals("silly", CaseExtensions.kebabToCamelCase("silly"));
		assertEquals("", CaseExtensions.kebabToCamelCase("-"));

		// Test for null input
		input = null;
		actual = CaseExtensions.kebabToCamelCase(input);
		assertNull(actual);

		// Test for empty input
		input = "";
		actual = CaseExtensions.kebabToCamelCase(input);
		expected = "";
		assertEquals(expected, actual);
	}

}
