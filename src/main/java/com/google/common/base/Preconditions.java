/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.common.base;

final class Preconditions
{

    private static String badElementIndex(final int index, final int size, final String desc)
    {
        if (index < 0)
        {
            return Preconditions.format("%s (%s) must not be negative", desc, index);
        } else if (size < 0)
        {
            throw new IllegalArgumentException("negative size: " + size);
        } else
        { // index >= size
            return Preconditions.format("%s (%s) must be less than size (%s)", desc, index, size);
        }
    }

    private static String badPositionIndex(final int index, final int size, final String desc)
    {
        if (index < 0)
        {
            return Preconditions.format("%s (%s) must not be negative", desc, index);
        } else if (size < 0)
        {
            throw new IllegalArgumentException("negative size: " + size);
        } else
        { // index > size
            return Preconditions.format("%s (%s) must not be greater than size (%s)", desc, index, size);
        }
    }

    private static String badPositionIndexes(final int start, final int end, final int size)
    {
        if ((start < 0) || (start > size))
        {
            return Preconditions.badPositionIndex(start, size, "start index");
        }
        if ((end < 0) || (end > size))
        {
            return Preconditions.badPositionIndex(end, size, "end index");
        }
        // end < start
        return Preconditions.format("end index (%s) must not be less than start index (%s)", end, start);
    }

    public static void checkArgument(final boolean expression)
    {
        if (!expression)
        {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(final boolean expression, final Object errorMessage)
    {
        if (!expression)
        {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void checkArgument(final boolean expression, final String errorMessageTemplate, final Object... errorMessageArgs)
    {
        if (!expression)
        {
            throw new IllegalArgumentException(Preconditions.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static int checkElementIndex(final int index, final int size)
    {
        return Preconditions.checkElementIndex(index, size, "index");
    }

    private static int checkElementIndex(final int index, final int size, final String desc)
    {
        if ((index < 0) || (index >= size))
        {
            throw new IndexOutOfBoundsException(Preconditions.badElementIndex(index, size, desc));
        }
        return index;
    }

    public static <T> T checkNotNull(final T reference)
    {
        if (reference == null)
        {
            throw new NullPointerException();
        }
        return reference;
    }

    public static <T> T checkNotNull(final T reference, final Object errorMessage)
    {
        if (reference == null)
        {
            throw new NullPointerException(String.valueOf(errorMessage));
        }
        return reference;
    }

    public static <T> T checkNotNull(final T reference, final String errorMessageTemplate, final Object... errorMessageArgs)
    {
        if (reference == null)
        {
            throw new NullPointerException(Preconditions.format(errorMessageTemplate, errorMessageArgs));
        }
        return reference;
    }

    public static int checkPositionIndex(final int index, final int size)
    {
        return Preconditions.checkPositionIndex(index, size, "index");
    }

    private static int checkPositionIndex(final int index, final int size, final String desc)
    {
        if ((index < 0) || (index > size))
        {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndex(index, size, desc));
        }
        return index;
    }

    public static void checkPositionIndexes(final int start, final int end, final int size)
    {
        // Carefully optimized for execution by hotspot (explanatory comment
        // above)
        if ((start < 0) || (end < start) || (end > size))
        {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndexes(start, end, size));
        }
    }

    public static void checkState(final boolean expression)
    {
        if (!expression)
        {
            throw new IllegalStateException();
        }
    }

    public static void checkState(final boolean expression, final Object errorMessage)
    {
        if (!expression)
        {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static void checkState(final boolean expression, final String errorMessageTemplate, final Object... errorMessageArgs)
    {
        if (!expression)
        {
            throw new IllegalStateException(Preconditions.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    private static String format(final String templaten, final Object... args)
    {
        final String template = String.valueOf(templaten); // null -> "null"
        final StringBuilder builder = new StringBuilder(template.length() + (16 * args.length));
        int templateStart = 0;
        int i = 0;
        while (i < args.length)
        {
            final int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1)
            {
                break;
            }
            builder.append(template.substring(templateStart, placeholderStart));
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template.substring(templateStart));
        if (i < args.length)
        {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length)
            {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }

        return builder.toString();
    }

    private Preconditions()
    {
    }

}