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

		// new scenario...
		actual = CaseExtensions.kebabToCamelCase(input);
		expected = "ExampleStringForConversion";
		assertEquals(expected, actual);

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
