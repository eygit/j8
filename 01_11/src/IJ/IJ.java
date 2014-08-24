package IJ;

public class IJ implements I, J {

	@Override
	public void f() {
		System.out.println("Clas IJ");

	}

	public static void main(String[] args) {
		new IJ().f();
	}

}
