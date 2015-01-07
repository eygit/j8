/**
 * フィボナッチ数の計算を並列化するためにparallelPrefixメソッドを使用することができます。
 * n番目のフィボナッチ数は、F=(1 1
 *                             1 0)とした場合の、F^nを計算した結果の行列の左上の値です。
 * 2X2の行列で配列を埋めなさい。
 * 乗算のメソッドを持つMatrixクラスを定義し、行列の配列を作成するためにparallelSetAllを使用し
 * 行列の乗算を計算するためにparallelPrefixを使用しなさい。
 */
public class ParallelFibonacci {

	public static void main(String[] args) {
		Matrix m = new Matrix();
		System.out.println(m);
		Matrix n = new Matrix();
		m.multiply(n);
		System.out.println(m);


	}

}
