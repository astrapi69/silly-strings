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

import java.util.Locale;

import org.apache.commons.text.CaseUtils;

/**
 * The class {@link CaseExtensions} provides methods for converting strings between different case
 * formats.
 */
public class CaseExtensions
{

	/**
	 * Converts a kebab-case string to camelCase: the first word in lower case, every further word
	 * with an upper-case first letter, as {@code silly-strings-version} becomes
	 * {@code sillyStringsVersion}.
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
		// camelCase as commons-text writes it; capitalizeFully gave PascalCase (#39).
		// CaseUtils lowercases in the default locale, where a Turkish I becomes a
		// dotless ı, so the identifier is lowercased in the root locale first (#45)
		return CaseUtils.toCamelCase(kebabCase.toLowerCase(Locale.ROOT), false, '-');
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
		// the root locale: in a Turkish one the upper case of i is the dotted İ (#45)
		return kebabCase.replace('-', '_').toUpperCase(Locale.ROOT);
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
		if (snakeCase == null)
		{
			return null;
		}
		// lowercased in the root locale first, as kebabToCamelCase does (#45)
		return CaseUtils.toCamelCase(snakeCase.toLowerCase(Locale.ROOT), true, '_');
	}
}
