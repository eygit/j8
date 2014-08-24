package SI;

public interface I {
	//抽象
//	void f();
	
	// デフォルト
	default void f() {
		System.out.println(I.class.toString() + "　default method");
	}
	
	// static
//	static void f() {
//		System.out.println(I.class.toString() + "　static method");
//	}
	
}
