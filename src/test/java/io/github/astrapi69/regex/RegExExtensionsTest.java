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
package io.github.astrapi69.regex;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import io.github.astrapi69.string.StringExtensions;
import io.github.astrapi69.test.base.BaseTestCase;

/**
 * The unit test class for the class {@link RegExExtensions}.
 */
public class RegExExtensionsTest extends BaseTestCase
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/**
	 * Test method for {@link RegExExtensions#isMatching(String, String)}
	 */
	@Test
	public void testIsMatching()
	{
		String regexPattern = "(\\s|00|01|02|03|^$)";
		assertTrue(RegExExtensions.isMatching(regexPattern, ""));
		assertTrue(RegExExtensions.isMatching(regexPattern, " "));
		assertTrue(RegExExtensions.isMatching(regexPattern, "  "));
		assertTrue(RegExExtensions.isMatching(regexPattern, "00"));
		assertTrue(RegExExtensions.isMatching(regexPattern, "01"));
		assertTrue(RegExExtensions.isMatching(regexPattern, "02"));
		assertTrue(RegExExtensions.isMatching(regexPattern, "03"));
	}

	/**
	 * Test method for
	 * {@link io.github.astrapi69.regex.RegExExtensions#replaceWildcardsWithRE(java.lang.String)}.
	 */
	@Test
	public void testReplaceWildcardsWithRE()
	{
		final String expected = "Hel.*W[a-zA-Z0-9._-]{1}rld!";
		final String queryString = "Hel*W?rld!";
		final String compare = RegExExtensions.replaceWildcardsWithRE(queryString);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	@Test
	public void testValidPhone()
	{
		// Split input with the pattern
		final String[] valideInput = { "0173/123450", "+49173/123450", "(+49)173/123450",
				"(+49) 173/123450", "0049173/123450", "0049173/1234-50", "0049 173/123450",
				"0049(0)173/123450", "(0049)173/123450", "(0049) 173/1234 50" };
		for (final String element : valideInput)
		{
			String strip = element;
			strip = StringExtensions.replaceAll(strip, " ", "");
			strip = StringExtensions.replaceAll(strip, "(", "");
			strip = StringExtensions.replaceAll(strip, ")", "");
			strip = StringExtensions.replaceAll(strip, " ", "");
			strip = StringExtensions.replaceAll(strip, "-", "");
			actual = strip.matches(RegExExtensions.VALID_PHONE);
			assertTrue(actual);
		}

		final String[] invalideInput = { "(0049)17e/123450", };
		for (final String element : invalideInput)
		{
			String strip = element;
			strip = StringExtensions.replaceAll(strip, " ", "");
			strip = StringExtensions.replaceAll(strip, "(", "");
			strip = StringExtensions.replaceAll(strip, ")", "");
			strip = StringExtensions.replaceAll(strip, " ", "");
			strip = StringExtensions.replaceAll(strip, "-", "");
			actual = strip.matches(RegExExtensions.VALID_PHONE);
			assertFalse(actual);
		}
	}

	/**
	 * Test method for
	 * {@link io.github.astrapi69.regex.RegExExtensions#wildcardCriterionSQL(java.lang.String)}.
	 */
	@Test
	public void testWildcardCriterionSQL()
	{
		final String expected = "Hel%W_rld!";
		final String query = "Hel*W?rld!";
		final String compare = RegExExtensions.wildcardCriterionSQL(query);
		actual = expected.equals(compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link RegExExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RegExExtensions.class);
	}

}
