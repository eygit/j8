import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;


public interface Collection2<T> extends Collection<T> {
	/**
	 * どんな場面で使うか：
	 * ボタンのようなGUI作成時、forEachIfを用いることで入力値が妥当ならボタン謳歌を受け入れるような作りを
	 * 簡潔に実現できる。
	 */
	default void forEachIf(Consumer<T> action, Predicate<T> filter) {
		 for (T t : this) {
			 if (filter.test(t)) {
				 action.accept(t);
			 }
		 }
	}
}
