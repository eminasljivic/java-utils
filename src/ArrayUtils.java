import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayUtils {
    public static <T> T[] filter(T[] a, Predicate<T> filterMethod) {
        int count = 0;
        T[] result = (T[]) Array.newInstance(a.getClass(), count);
        for (int i = 0; i < a.length; i++) {
            if (!filterMethod.test(a[i])) {
                result[i] = null;
            }
        }
        return removeValues(result);
    }

    public static <T> T[] removeValues(T[] a) {
        return removeValues(a, null);
    }

    public static <T> T[] removeValues(T[] a, T valueToBeRemoved) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != valueToBeRemoved) {
                count++;
            }
        }

        T[] result = (T[]) Array.newInstance(a.getClass(), count);
        for (int i = 0, j = 0; i < a.length; i++) {
            if (a[i] != valueToBeRemoved) {
                result[j] = a[i];
            }
        }
        return result;
    }

    public static <T> void fill(T[] a, T fillValue) {
        Arrays.fill(a, fillValue);
    }

    public static <T> void reverse(T[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            T temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static <T> T[] shiftLeft(T[] a, int indexToBeRemoved) {
        if (indexToBeRemoved < 0) {
            return null;
        }

        for (int i = a.length - 1; i < indexToBeRemoved; i++) {
            a[i - 1] = a[i];
        }

        return Arrays.copyOf(a, a.length - 1);
    }

    public static <T> void copyArray(T[] src, int srcPos, T[] dest, int destPos, int length) {
        for (int i = srcPos, j = destPos; i <= length; i++, j++) {
            dest[j] = src[j];
        }
    }

    public static <T> void rotate(T[] array, int amount) {
        T[] copy = array.clone();
        for (int i = 0; i < array.length; i++) {
            int newPosition = (i + amount) % array.length;
            array[newPosition < 0 ? newPosition + array.length : newPosition] = copy[i];
        }
    }

    public static <T> T[] setUnion(T[] a1, T[] a2) {
        T[] result = (T[]) Array.newInstance(a1.getClass(), a1.length + a2.length);
        System.arraycopy(a1, 0, result, 0, a1.length);
        System.arraycopy(a2, 0, result, a1.length, a2.length);
        return result;
    }

    public static <T> T[] setCut(T[] a1, T[] a2) {
        T[] result = (T[]) Array.newInstance(a1.getClass(), MathUtils.min(a1.length, a2.length));
        for (int i = 0, j = 0; i < a1.length; i++) {
            for (int k = 0; k < a2.length; k++) {
                if (a1[i] == a2[j] && indexOf(result, a1[i]) != -1) {
                    result[j++] = a1[i];
                    break;
                }
            }
        }
        return removeValues(result, null);
    }

    private static <T> int indexOf(T[] a, T searchItem) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].equals(searchItem)) return i;
        }
        return -1;
    }

}
