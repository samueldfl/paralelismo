import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InsertionSort {

    public static void sort(int arr[]) {
        int n = arr.length;

        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
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

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                sort(subarray);
                System.arraycopy(subarray, 0, arr, start, subarray.length);
            }, executor);

            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        allOf.thenRunAsync(() -> sort(arr), executor).join();

        executor.shutdown();
    }
}
