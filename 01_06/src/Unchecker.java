
public class Unchecker {

	public static Runnable uncheck(RunnableEx runner) {
		return  () -> {
			try {
				runner.run();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	};


	public static void main(String[] args) {
		new Thread( uncheck( () -> {
						System.out.println("Zzz");
						Thread.sleep(1000);
					})).start();
	}

}
