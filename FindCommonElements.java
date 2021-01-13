import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/**
 * This program will sort 2 arrays separately and find common elements in both
 * arrays
 * 
 * @author Nadeesha Maduranga (nadeesham332@gmail.com)
 *
 */

public class FindCommonElements {

	static Random random;
	static int temp;

	/**
	 * Finds commin elements
	 * 
	 * @param a   - Sorted array which has less length
	 * @param l   - lowest index of considering part on array
	 * @param h   - highest index of considering part on array
	 * @param key - Searching element from array which has max length
	 */
	static void binarySearch(int[] a, int l, int h, int key) {
		if (l >= h) {
			if (a[l] == key) {
				System.out.print(key + " ");
				;
			}
		} else {
			int mid = (l + h) / 2; // middle of the considering part on array
			if (key == a[mid]) {
				System.out.print(key + " ");
			} else if (key < a[mid]) {
				binarySearch(a, l, mid - 1, key);
			} else {
				binarySearch(a, mid + 1, h, key);
			}

		}

	}

	/**
	 * Places pivot element at its correct position in sorted array
	 * 
	 * @param arr - unsorted array
	 * @param p   - pivot index in unsorted part
	 * @param r   - last index in unsorted part
	 * @return the position of pivot element in sorted array
	 */
	static int partition(int[] arr, int p, int r) {
		int x = arr[p]; // x is pivot element
		int i = p; // i is starting index

		for (int j = p + 1; j <= r; j++) {
			if (arr[j] <= x) {
				i = i + 1;
				// swap elements
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		// swap and save pivot element
		temp = arr[i];
		arr[i] = arr[p];
		arr[p] = temp;
		return i;
	}

	/**
	 * Gives random numbers between first and last elements
	 * 
	 * @param p - pivot index in unsorted part
	 * @param r - last index in unsorted part
	 * @return a random number between first and last indexes in unsorted part
	 */
	static int random(int p, int r) {
		Random rand = new Random();
		return rand.nextInt(r - p + 1) + p;
	}

	/**
	 * Gives random inputs to partition function
	 * 
	 * @param arr - unsorted array
	 * @param p   - pivot index in unsorted part
	 * @param r   - last index in unsorted part
	 * @return the input p and r values to partition function
	 */
	static int randamizedPartition(int[] arr, int p, int r) {

		int i = random(p, r); // i is a random number between first and last indexes in unsorted part
		// swap pivot element
		temp = arr[p];
		arr[p] = arr[i];
		arr[p] = temp;
		return partition(arr, p, r);
	}

	/**
	 * implements quick sort recursively
	 * 
	 * @param arr - unsorted array
	 * @param p   - pivot index in unsorted part
	 * @param r   - last index in unsorted part
	 */
	static void randomizedQuickSort(int[] arr, int p, int r) {
		// check whether recursion is need or not
		if (p < r) {
			int q = randamizedPartition(arr, p, r); // q is the position of pivot element in sorted array
			randomizedQuickSort(arr, p, q - 1);
			randomizedQuickSort(arr, q + 1, r);
		}
	}

	/**
	 * Drives code
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		int inputSize = 0;
		System.out.print("This program will sort 2 arrays separately and find common elements in both arrays.\n"
				+ "n - " + "number of integers in input array 1.\nm - number of integers in input array 2.\n"
				+ "\nEnter input array 1 size(n): ");

		// Getting input size from user
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader1.readLine());

		int array1[] = new int[n];

		String input1;

		do {
			System.out.print("Enter " + n + " integers, separated using space, to sort (n1 n2 n3 ...): ");
			input1 = reader1.readLine();
			inputSize = input1.split(" ").length;
		} while (inputSize != n);

		String[] numbers1 = input1.split(" ");
		for (int j = 0; j < n; j++) {
			array1[j] = Integer.parseInt(numbers1[j]);
		}

		System.out.print("\nEnter input array 2 size(m): ");

		// Getting input size from user
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(reader2.readLine());

		int array2[] = new int[m];
		String input2;

		do {
			System.out.print("Enter " + m + " integers, separated using space, to sort (m1 m2 m3 ...): ");
			input2 = reader2.readLine();
			inputSize = input2.split(" ").length;
		} while (inputSize != m);

		String[] numbers2 = input2.split(" ");
		for (int j = 0; j < m; j++) {
			array2[j] = Integer.parseInt(numbers2[j]);
		}

		System.out.println("\nUnsorted array 1: " + Arrays.toString(array1));
		System.out.println("Unsorted array 2: " + Arrays.toString(array2));

		randomizedQuickSort(array1, 0, n - 1);
		System.out.println("\nSorted array 1: " + Arrays.toString(array1));

		randomizedQuickSort(array2, 0, m - 1);
		System.out.println("Sorted array 2: " + Arrays.toString(array2));

		System.out.print("Common elements: ");
		// check that which array has high length and low length
		if (n > m) {
			for (int i = 0; i < n; i++) {
				binarySearch(array2, 0, m - 1, array1[i]);
			}
		} else {
			for (int i = 0; i < m; i++) {
				binarySearch(array1, 0, n - 1, array2[i]);
			}
		}
	}

}
