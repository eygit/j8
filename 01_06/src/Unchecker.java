
public class Unchecker {

	// RunnableExでCallback<Void>を定義してもThreadの引数の型(Runnable)とは異なるため
	// 型推定で不整合を生じ、利用できない
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
