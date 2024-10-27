public class QuickSort {

    public static void sort(int[] arr, int start, int end) {
        if (start < end) {

            int partitionIndex = partition(arr, start, end);

            sort(arr, start, partitionIndex - 1);
            sort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];

        int smallerElementIndex = start - 1;

        for (int current = start; current <= end - 1; current++) {
            if (arr[current] < pivot) {
                smallerElementIndex++;
                swap(arr, smallerElementIndex, current);
            }
        }

        swap(arr, smallerElementIndex + 1, end);
        return smallerElementIndex + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
