import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

/**
 * 皆さんのコンピュータでは、Arrays.parallelSortは、配列がそのくらいの大きさであればArrays.sortより速くなりますか。
 */

public class BenchmarkArraysParallelSort {

	public static void main(String[] args) {
		Random r = new Random(2015);
		for (int i =1; i < 9; i++) {
			int size = (int) Math.pow(10, i);
			int[] array = new int[size];
			for (int j = 0; j < size; j++) {
				array[j] = r.nextInt();
			}
			int[] array2 = Arrays.copyOf(array, array.length);

			Instant start = Instant.now();
			Arrays.sort(array);
			Instant end = Instant.now();
			System.out.println("Single[" + size + "]=" + Duration.between(start, end).toMillis());

			start = Instant.now();
			Arrays.parallelSort(array2);
			end = Instant.now();
			System.out.println("Parall[" + size + "]=" + Duration.between(start, end).toMillis());

			/**
			 * 実行結果は以下のようになり、int[1000000]の配列からparallelSortの方が高速になった。
				Single[10]=0
				Parall[10]=0
				Single[100]=0
				Parall[100]=0
				Single[1000]=0
				Parall[1000]=0
				Single[10000]=2
				Parall[10000]=52
				Single[100000]=10
				Parall[100000]=20
				Single[1000000]=100
				Parall[1000000]=24
				Single[10000000]=850
				Parall[10000000]=218
				Single[100000000]=9690
				Parall[100000000]=2175
			 */

		}
	}

}
