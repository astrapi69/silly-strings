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

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Optional;

/**
 * The class {@link CharsetExtensions} provides extension methods for {@link Charset} objects
 */
public final class CharsetExtensions
{

	private CharsetExtensions()
	{
	}

	/**
	 * Gets the {@link Charset} object from the given encoding as {@link String} object
	 *
	 * @param encoding
	 *            the encoding as {@link String} object
	 * @return an {@link Optional} object with the resolved {@link Charset} object or empty if not
	 *         found
	 */
	public static Optional<Charset> getCharset(final String encoding)
	{
		if (encoding == null)
		{
			return Optional.empty();
		}
		Charset charset = null;
		try
		{
			charset = Charset.forName(encoding);
		}
		catch (UnsupportedCharsetException exception)
		{
			Optional.empty();
		}
		return charset != null ? Optional.of(charset) : Optional.empty();
	}
}
