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
}
