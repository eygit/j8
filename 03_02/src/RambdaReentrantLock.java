import java.util.concurrent.locks.ReentrantLock;


public class RambdaReentrantLock {

	public static void withLock(ReentrantLock reentrantLock, Runnable action) {
		reentrantLock.lock();
		try {
			action.run();
		} finally {
			reentrantLock.unlock();
		}
	}

	public static void main(String[] args) {
		ReentrantLock myLock = new ReentrantLock();
		withLock(myLock, () -> {System.out.println("withLock called");});
	}

}
