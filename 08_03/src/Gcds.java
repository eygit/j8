
public class Gcds {

	static private int gcd_percent(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return Math.abs( gcd_percent(b, a % b) );
		}
	}
	static private int gcd_floorMod(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd_floorMod(b, (Math.floorMod(a, b) + Math.abs(b)) % Math.abs(b) ); // Mathのjavadocに明記の実装方法
//			return Math.abs( gcd_floorMod(b, Math.floorMod(a, b) ) ); // 結果は同じように見えるが、異なるパターンがあるかもしれない独自実装方法
		}
	}
	static private int rem(int a, int b) {
		int ret = a  - b;
		return Math.abs( ret < 0 ? a : ret );
	}
	static private int gcd_rem(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return Math.abs( gcd_rem(b, rem(a, b) ) );
		}
	}

	public static void main(String[] args) {

		// %が一番簡単に思えるが、負数などの扱いで

		System.out.println( gcd_percent(30, 18) );
		System.out.println( gcd_percent(-30, -18) );
		System.out.println("----------------");
		System.out.println( gcd_floorMod(30, 18) );
		System.out.println( gcd_floorMod(-30, -18) );
		System.out.println("----------------");
		System.out.println( gcd_rem(30, 18) );
		System.out.println( gcd_rem(-30, -18) );

	}

}
