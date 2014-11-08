import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;


public class FutureMap {
	static <T, U> Future<U> map(Future<T> futuret, Function<T, U> function) {
		return new Future<U>() {
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return futuret.cancel(mayInterruptIfRunning);
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return function.apply(futuret.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit) throws InterruptedException,
					ExecutionException, TimeoutException {
				return function.apply(futuret.get(timeout, unit));
			}

			@Override
			public boolean isCancelled() {
				return futuret.isCancelled();
			}

			@Override
			public boolean isDone() {
				return futuret.isDone();
			}
			
		};
	}

}
