/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.abubusoft.kripton.common;


import java.io.Serializable;

import static com.abubusoft.kripton.common.Preconditions.checkNotNull;


/**
 * Utility class for converting between various ASCII case formats. Behavior is undefined for
 * non-ASCII input.
 *
 * @author Mike Bostock
 * @since 1.0
 */
public enum CaseFormat {
  /**
   * Hyphenated variable naming convention, e.g., "lower-hyphen".
   */
  LOWER_HYPHEN(CharMatcher.is('-'), "-") {
    @Override String normalizeWord(String word) {
      return Ascii.toLowerCase(word);
    }
    @Override String convert(CaseFormat format, String s) {
      if (format == LOWER_UNDERSCORE) {
        return s.replace('-', '_');
      }
      if (format == UPPER_UNDERSCORE) {
        return Ascii.toUpperCase(s.replace('-', '_'));
      }
      return super.convert(format, s);
    }
  },

  /**
   * C++ variable naming convention, e.g., "lower_underscore".
   */
  LOWER_UNDERSCORE(CharMatcher.is('_'), "_") {
    @Override String normalizeWord(String word) {
      return Ascii.toLowerCase(word);
    }
    @Override String convert(CaseFormat format, String s) {
      if (format == LOWER_HYPHEN) {
        return s.replace('_', '-');
      }
      if (format == UPPER_UNDERSCORE) {
        return Ascii.toUpperCase(s);
      }
      return super.convert(format, s);
    }
  },

  /**
   * Java variable naming convention, e.g., "lowerCamel".
   */
  LOWER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
    @Override String normalizeWord(String word) {
      return firstCharOnlyToUpper(word);
    }
  },

  /**
   * Java and C++ class naming convention, e.g., "UpperCamel".
   */
  UPPER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
    @Override String normalizeWord(String word) {
      return firstCharOnlyToUpper(word);
    }
  },

  /**
   * Java and C++ constant naming convention, e.g., "UPPER_UNDERSCORE".
   */
  UPPER_UNDERSCORE(CharMatcher.is('_'), "_") {
    @Override String normalizeWord(String word) {
      return Ascii.toUpperCase(word);
    }
    @Override String convert(CaseFormat format, String s) {
      if (format == LOWER_HYPHEN) {
        return Ascii.toLowerCase(s.replace('_', '-'));
      }
      if (format == LOWER_UNDERSCORE) {
        return Ascii.toLowerCase(s);
      }
      return super.convert(format, s);
    }
  };

  /** The word boundary. */
  private final CharMatcher wordBoundary;
  
  /** The word separator. */
  private final String wordSeparator;

  /**
   * Instantiates a new case format.
   *
   * @param wordBoundary the word boundary
   * @param wordSeparator the word separator
   */
  CaseFormat(CharMatcher wordBoundary, String wordSeparator) {
    this.wordBoundary = wordBoundary;
    this.wordSeparator = wordSeparator;
  }

  /**
   * Converts the specified {@code String str} from this format to the specified {@code format}. A
   * "best effort" approach is taken; if {@code str} does not conform to the assumed format, then
   * the behavior of this method is undefined but we make a reasonable effort at converting anyway.
   *
   * @param format the format
   * @param str the str
   * @return the string
   */
  public final String to(CaseFormat format, String str) {
    checkNotNull(format);
    checkNotNull(str);
    return (format == this) ? str : convert(format, str);
  }

  /**
   * Enum values can override for performance reasons.
   *
   * @param format the format
   * @param s the s
   * @return the string
   */
  String convert(CaseFormat format, String s) {
    // deal with camel conversion
    StringBuilder out = null;
    int i = 0;
    int j = -1;
    while ((j = wordBoundary.indexIn(s, ++j)) != -1) {
      if (i == 0) {
        // include some extra space for separators
        out = new StringBuilder(s.length() + 4 * wordSeparator.length());
        out.append(format.normalizeFirstWord(s.substring(i, j)));
      } else {
        out.append(format.normalizeWord(s.substring(i, j)));
      }
      out.append(format.wordSeparator);
      i = j + wordSeparator.length();
    }
    return (i == 0)
      ? format.normalizeFirstWord(s)
      : out.append(format.normalizeWord(s.substring(i))).toString();
  }

  /**
   * Returns a {@code Converter} that converts strings from this format to {@code targetFormat}.
   *
   * @param targetFormat the target format
   * @return the converter
   * @since 16.0
   */
  public Converter<String, String> converterTo(CaseFormat targetFormat) {
    return new StringConverter(this, targetFormat);
  }

  /**
   * The Class StringConverter.
   */
  private static final class StringConverter
      extends Converter<String, String> implements Serializable {

    /** The source format. */
    private final CaseFormat sourceFormat;
    
    /** The target format. */
    private final CaseFormat targetFormat;

    /**
     * Instantiates a new string converter.
     *
     * @param sourceFormat the source format
     * @param targetFormat the target format
     */
    StringConverter(CaseFormat sourceFormat, CaseFormat targetFormat) {
      this.sourceFormat = checkNotNull(sourceFormat);
      this.targetFormat = checkNotNull(targetFormat);
    }

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.common.Converter#doForward(java.lang.Object)
     */
    @Override protected String doForward(String s) {
      return sourceFormat.to(targetFormat, s);
    }

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.common.Converter#doBackward(java.lang.Object)
     */
    @Override protected String doBackward(String s) {
      return targetFormat.to(sourceFormat, s);
    }

    /* (non-Javadoc)
     * @see com.abubusoft.kripton.common.Converter#equals(java.lang.Object)
     */
    @Override public boolean equals(Object object) {
      if (object instanceof StringConverter) {
        StringConverter that = (StringConverter) object;
        return sourceFormat.equals(that.sourceFormat)
            && targetFormat.equals(that.targetFormat);
      }
      return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override public int hashCode() {
      return sourceFormat.hashCode() ^ targetFormat.hashCode();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override public String toString() {
      return sourceFormat + ".converterTo(" + targetFormat + ")";
    }

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 0L;
  }

  /**
   * Normalize word.
   *
   * @param word the word
   * @return the string
   */
  abstract String normalizeWord(String word);

  /**
   * Normalize first word.
   *
   * @param word the word
   * @return the string
   */
  private String normalizeFirstWord(String word) {
    return (this == LOWER_CAMEL) ? Ascii.toLowerCase(word) : normalizeWord(word);
  }

  /**
   * First char only to upper.
   *
   * @param word the word
   * @return the string
   */
  private static String firstCharOnlyToUpper(String word) {
    return (word.isEmpty())
        ? word
        : new StringBuilder(word.length())
            .append(Ascii.toUpperCase(word.charAt(0)))
            .append(Ascii.toLowerCase(word.substring(1)))
            .toString();
  }
}