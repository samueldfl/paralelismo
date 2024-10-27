public class SelectionSort {
    public static void sort(int[] arr) {
        int arrLength = arr.length;

        for (int i = 0; i < arrLength - 1; i++) {
            int min_idx = i;

            for (int j = i + 1; j < arrLength; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[min_idx];
            arr[min_idx] = temp;
        }
    }

    public static void sortAsync(int[] arr) {
    }
}
