
public class UnsignedOperations {
	static public int plusU(int a, int b) {
		return a+b;
	}
	static public int minusU(int a, int b) {
		return a-b;
	}
	static public int divideU(int a, int b) {
		return Integer.divideUnsigned(a, b);
	}
	static public int compareU(int a, int b) {
		return Integer.compareUnsigned(a, b);
	}

	public static void main(String[] args) {
		int umaxint = plusU(Integer.MAX_VALUE, Integer.MAX_VALUE);
		System.out.println(Integer.toUnsignedLong(umaxint));
		System.out.println(Integer.toUnsignedLong(minusU(umaxint, Integer.MAX_VALUE)));
		System.out.println(Integer.toUnsignedLong(divideU(umaxint, umaxint)));
		System.out.println(compareU(umaxint, umaxint));
		System.out.println(compareU(umaxint, 1));
		System.out.println(compareU(1, umaxint));

		//divideUnsignedとremainderUnsignedが必要な理由は、
		//divideUnsignedのJavadocより以下のとおり表現にunsignedとそうでない倍に差異がないため
		//
		//2の補数計算で、他の3つの基本算術演算(加算、減算、および乗算)は、
		//2つのオペランドがどちらも符号付き、またはどちらも符号なしと見なされる場合、
		//ビット単位で同じであることに注意してください。
		//したがって、個々のaddUnsignedなどのメソッドは提供されません。


	}

}
