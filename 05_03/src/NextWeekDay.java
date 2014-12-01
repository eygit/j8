import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;


public class NextWeekDay {

	static TemporalAdjuster next(Predicate<LocalDate> date) {
		// 関数型インタフェース：　Temporal adjustInto(Temporal temporal)　の実装.
		return null;
//		return t -> {
//			Temporal candidate = t.plus(1, ChronoUnit.DAYS);
//			while (date) {
//
//			}
//			return t.plus(1, ChronoUnit.DAYS);
//		};
	}

	public static void main(String[] args) {
		LocalDate today = LocalDate.of(2014, 12, 01);
		LocalDate nextWeekDay = today.with(next( w ->
					w.getDayOfWeek().getValue() < 6
				));
		System.out.println(nextWeekDay);

	}

}
