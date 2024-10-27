import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BubleSort {

    public static void sort(int[] arr) {
        int arrLength = arr.length;
        boolean swapped;

        for (int i = 0; i < arrLength - 1; i++) {
            swapped = false;

            for (int j = 0; j < arrLength - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped)
                break;
        }
    }

    public static void sortAsync(int[] arr, int numThreads, int numSubarrays) {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        int subarraySize = (int) Math.ceil((double) arr.length / numSubarrays);

        for (int i = 0; i < arr.length; i += subarraySize) {
            int start = i;
            int end = Math.min(i + subarraySize, arr.length);
            int[] subarray = Arrays.copyOfRange(arr, start, end);

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> sort(subarray), executor)
                    .thenRun(() -> System.arraycopy(subarray, 0, arr, start, subarray.length));

            futures.add(future);
        }

        CompletableFuture<Void> finalSortFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRunAsync(() -> sort(arr), executor);

        futures.add(finalSortFuture);

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        executor.shutdown();
    }
}
