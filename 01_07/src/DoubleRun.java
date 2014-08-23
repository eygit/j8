

public class DoubleRun {

	public static void main(String[] args) {
		Runnable dr = DoubleRun.andThen( () -> System.out.println("first"), 
										() -> System.out.println("second") );
		
		dr.run();
		
	}

	private static Runnable andThen(Runnable first, Runnable second) {
		return () -> {
			first.run();
			second.run();
		};
	}
}
