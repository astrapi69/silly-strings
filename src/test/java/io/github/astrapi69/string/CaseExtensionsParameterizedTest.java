package io.github.astrapi69.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

/**
 * The class {@link CaseExtensionsParameterizedTest} provides unit tests for the methods in the
 * {@link CaseExtensions} class.
 */
public class CaseExtensionsParameterizedTest
{

	/**
	 * Parameterized test method for {@link CaseExtensions#kebabToUpperSnakeCase(String)}
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/kebabToUpperSnakeCase-data.csv", numLinesToSkip = 1)
	void kebabToUpperSnakeCase(String input, String expected)
	{
		String actualValue = CaseExtensions.kebabToUpperSnakeCase(input);
		assertEquals(expected, actualValue);
	}

	/**
	 * Parameterized test method for {@link CaseExtensions#kebabToSnakeCase(String)}
	 */
	@ParameterizedTest
	@CsvFileSource(resources = "/kebabToSnakeCase-data.csv", numLinesToSkip = 1)
	void kebabToSnakeCase(String input, String expected)
	{
		String actualValue = CaseExtensions.kebabToSnakeCase(input);
		assertEquals(expected, actualValue);
	}

}
