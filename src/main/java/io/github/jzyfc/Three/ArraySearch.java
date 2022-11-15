package io.github.jzyfc.Three;

public class ArraySearch {
    public int[] array;

    public ArraySearch(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array is null");
        }
        this.array = array;
    }

    public int searchArray(int target) {
        int ret = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                ret = i;
                break;
            }
        }
        return ret;
    }
}
