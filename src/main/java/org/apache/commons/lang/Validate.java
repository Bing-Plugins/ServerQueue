/*
 * Decompiled with CFR 0.151.
 */
package org.apache.commons.lang;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class Validate {
    public static void isTrue(boolean expression, String message, Object value) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(message) + value);
        }
    }

    public static void isTrue(boolean expression, String message, long value) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(message) + value);
        }
    }

    public static void isTrue(boolean expression, String message, double value) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(message) + value);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException("The validated expression is false");
        }
    }

    public static void notNull(Object object) {
        Validate.notNull(object, "The validated object is null");
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object[] array, String message) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Object[] array) {
        Validate.notEmpty(array, "The validated array is empty");
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        Validate.notEmpty(collection, "The validated collection is empty");
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (map == null || map.size() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        Validate.notEmpty(map, "The validated map is empty");
    }

    public static void notEmpty(String string, String message) {
        if (string == null || string.length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty(String string) {
        Validate.notEmpty(string, "The validated string is empty");
    }

    public static void noNullElements(Object[] array, String message) {
        Validate.notNull(array);
        int i = 0;
        while (i < array.length) {
            if (array[i] == null) {
                throw new IllegalArgumentException(message);
            }
            ++i;
        }
    }

    public static void noNullElements(Object[] array) {
        Validate.notNull(array);
        int i = 0;
        while (i < array.length) {
            if (array[i] == null) {
                throw new IllegalArgumentException("The validated array contains null element at index: " + i);
            }
            ++i;
        }
    }

    public static void noNullElements(Collection<?> collection, String message) {
        Validate.notNull(collection);
        Iterator<?> it = collection.iterator();
        do {
            if (it.hasNext()) continue;
            return;
        } while (it.next() != null);
        throw new IllegalArgumentException(message);
    }

    public static void noNullElements(Collection<?> collection) {
        Validate.notNull(collection);
        int i = 0;
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                throw new IllegalArgumentException("The validated collection contains null element at index: " + i);
            }
            ++i;
        }
    }

    public static void allElementsOfType(Collection<?> collection, Class<?> clazz, String message) {
        Validate.notNull(collection);
        Validate.notNull(clazz);
        Iterator<?> it = collection.iterator();
        do {
            if (it.hasNext()) continue;
            return;
        } while (clazz.isInstance(it.next()));
        throw new IllegalArgumentException(message);
    }

    public static void allElementsOfType(Collection<?> collection, Class<?> clazz) {
        Validate.notNull(collection);
        Validate.notNull(clazz);
        int i = 0;
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            if (!clazz.isInstance(it.next())) {
                throw new IllegalArgumentException("The validated collection contains an element not of type " + clazz.getName() + " at index: " + i);
            }
            ++i;
        }
    }
}

