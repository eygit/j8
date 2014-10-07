import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;


public class LazyConditionalLog {
	private static final Logger logger = Logger.getLogger(LazyConditionalLog.class.getName());
	static {
		logger.addHandler(new StreamHandler(){
			{
				setOutputStream(System.out);
				setLevel(Level.ALL);
			}
		});
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
	}

	public static void logIf(Level level, Supplier<Boolean> isLoggable, Supplier<String> msgSupplier) {
		if (isLoggable.get()) {
			logger.log(level, msgSupplier);
		}
	}

	public static void main(String[] args) {

		int[] a = new int[100];
		for (int i = 0; i < 100; i++) {
			a[i] = i;
		}

		for (int j = 0; j < 100; j++) {
			int i = j; // 実質的Final用に不変な値にコピーしているが、不自然。。。
			logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]);
		}

	}

}
