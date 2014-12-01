import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;


public class NextWeekDay {

	static TemporalAdjuster next(Predicate<LocalDate> date) {
		// 関数型インタフェース：　Temporal adjustInto(Temporal temporal)　の実装.
		return t -> {
			LocalDate candidate = LocalDate.from(t).plus(1, ChronoUnit.DAYS);
			while (! date.test((LocalDate) candidate)) {
				candidate = candidate.plus(1, ChronoUnit.DAYS);
			}
			return candidate;
		};
	}

	public static void main(String[] args) {
		LocalDate today = LocalDate.of(2014, 12, 01); // 月曜日
		LocalDate nextWeekDay = today.with(next( w ->
					w.getDayOfWeek().getValue() < 6
				));
		System.out.println(nextWeekDay);


		today = LocalDate.of(2014, 12, 05); // 金曜日
		nextWeekDay = today.with(next( w ->
					w.getDayOfWeek().getValue() < 6
				));
		System.out.println(nextWeekDay);

	}

}
