import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        final String fileName = "input.txt";
        boolean generateRandomNumber = false;

        if (generateRandomNumber) {
            generateRandomNumbersToFile(400_000, 1, 599_999, fileName);
        }

        int[] arr = readNumbersFromFile(fileName);

        serial(arr);
    }

    public static void serial(int[] arr) {
        long startTotalTime = System.nanoTime();

        long bubbleSortStartTime = System.nanoTime();
        int[] ordenedBubbleSort = arr.clone();
        BubleSort.sort(ordenedBubbleSort);
        long bubbleSortEndTime = System.nanoTime();

        System.out.println("BubleSort just ordened the array in "
                + nanoToSeconds(bubbleSortStartTime, bubbleSortEndTime) + " seconds");

        long insertionSortStartTime = System.nanoTime();
        int[] ordenedInsertionSort = arr.clone();
        InsertionSort.sort(ordenedInsertionSort);
        long insertionSortEndTime = System.nanoTime();

        System.out.println("InsertionSort just ordened the array in "
                + nanoToSeconds(insertionSortStartTime, insertionSortEndTime) + " seconds");

        long selectionSortStartTime = System.nanoTime();
        int[] ordenedSelectionSort = arr.clone();
        SelectionSort.sort(ordenedSelectionSort);
        long selectionSortEndTime = System.nanoTime();

        System.out.println("SelectionSort just ordened the array in "
                + nanoToSeconds(selectionSortStartTime, selectionSortEndTime) + " seconds");

        long quickSortStartTime = System.nanoTime();
        int[] ordenedQuickSort = arr.clone();
        QuickSort.sort(ordenedQuickSort, 0, ordenedQuickSort.length - 1);
        long quickSortEndTime = System.nanoTime();

        System.out.println("QuickSort just ordened the array in "
                + nanoToSeconds(quickSortStartTime, quickSortEndTime) + " seconds");

        long endTotalTime = System.nanoTime();

        System.out.println("Total time in seconds: " + nanoToSeconds(startTotalTime, endTotalTime));
    }

    public static void parallel(int[] arr) {
        long startTotalTime = System.nanoTime();

        // to-do

        long endTotalTime = System.nanoTime();

        System.out.println("Total time in seconds: " + nanoToSeconds(startTotalTime, endTotalTime));
    }

    private static double nanoToSeconds(long startTime, long endTime) {
        return (endTime - startTime) / 1_000_000_000.0;
    }

    private static int[] readNumbersFromFile(String fileName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        reader.close();

        String[] numberStrings = line.split(",");
        int[] numbers = new int[numberStrings.length];
        for (int i = 0; i < numberStrings.length; i++) {
            numbers[i] = Integer.parseInt(numberStrings[i]);
        }
        return numbers;
    }

    public static void generateRandomNumbersToFile(int quantity, int start, int end, String fileName) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < quantity; i++) {
            int randomNumber = random.nextInt(end - start + 1) + start;

            sb.append(randomNumber);

            if (i < quantity - 1) {
                sb.append(",");
            }
        }

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
