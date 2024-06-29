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

import org.apache.commons.text.CaseUtils;
import org.apache.commons.text.WordUtils;

/**
 * The class {@link CaseExtensions} provides methods for converting strings between different case
 * formats.
 */
public class CaseExtensions
{

	/**
	 * Converts a kebab-case string to camelCase.
	 *
	 * @param kebabCase
	 *            the kebab-case string
	 * @return the camelCase string
	 */
	public static String kebabToCamelCase(String kebabCase)
	{
		if (kebabCase == null || kebabCase.isEmpty())
		{
			return kebabCase;
		}
		return WordUtils.capitalizeFully(kebabCase, new char[] { '-' }).replaceAll("-", "");
	}

	/**
	 * Converts a kebab-case string to dot.case.
	 *
	 * @param kebabCase
	 *            the kebab-case string
	 * @return the dot.case string, or the original string if it is null or empty
	 */
	public static String kebabToDotCase(String kebabCase)
	{
		if (kebabCase == null || kebabCase.isEmpty())
		{
			return kebabCase;
		}
		return kebabCase.replace('-', '.');
	}

	/**
	 * Converts a kebab-case string to snake_case.
	 *
	 * @param kebabCase
	 *            the kebab-case string
	 * @return the snake_case string, or the original string if it is null or empty
	 */
	public static String kebabToSnakeCase(String kebabCase)
	{
		if (kebabCase == null || kebabCase.isEmpty())
		{
			return kebabCase;
		}
		return kebabCase.replace('-', '_');
	}

	/**
	 * Converts a kebab-case string to UPPER_SNAKE_CASE.
	 *
	 * @param kebabCase
	 *            the kebab-case string
	 * @return the UPPER_SNAKE_CASE string, or the original string if it is null or empty
	 */
	public static String kebabToUpperSnakeCase(String kebabCase)
	{
		if (kebabCase == null || kebabCase.isEmpty())
		{
			return kebabCase;
		}
		return kebabCase.replace('-', '_').toUpperCase();
	}

	/**
	 * Converts a snake_case string to PascalCase.
	 *
	 * @param snakeCase
	 *            the snake_case string
	 * @return the PascalCase string
	 */
	public static String snakeToPascalCase(String snakeCase)
	{
		String pascalCase = CaseUtils.toCamelCase(snakeCase, true, '_');
		return pascalCase;
	}
}
