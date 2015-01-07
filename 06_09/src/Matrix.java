import java.util.Arrays;


public class Matrix {
	private long[] array = new long[4];

	public Matrix() {
		Arrays.parallelSetAll(array, i -> (i != 3) ? 1 : 0); // 行列の右下以外は1で初期化する。
//		array[0] = 0;
//		array[1] = 1;
//		array[2] = 2;
//		array[3] = 3;
	}

	void multiply(Matrix another) {
		// T.B.D. お題のパラレルプレフィクスを使う方法が思いつかない・・・。配列のindexがわかればなんとでもできるが・・・。
		Arrays.parallelPrefix(array, (l, r) -> {
			System.out.println( " " + l + ", " + r);
			return l+r;
		});
	}

	public String toString() {
		return array[0] + "," + array[1] + "," + array[2] + "," + array[3];
	}
}
