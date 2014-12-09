import java.time.LocalTime;


public class TimeInterval {
	private LocalTime begin;
	private LocalTime end;

	public TimeInterval(LocalTime begin, LocalTime end) {
		this.begin = begin;
		this.end = end;
	}

	/**
	 * 指定のタイムインターバルが重なるか判別する.
	 * @param another 指定のタイムインターバル.
	 * @return 指定のタイムインターバルが重なる場合はtrue.
	 */
	boolean contains(TimeInterval another) {
		return (this.equals(another)
				|| this.begin.isBefore(another.end) &&  this.end.isAfter(another.end)
				|| this.begin.isBefore(another.begin) &&  this.end.isAfter(another.begin)
				|| this.begin.isAfter(another.begin) &&  this.end.isBefore(another.end) );
	}

	public static void main(String[] args) {
		LocalTime now = LocalTime.now();
		TimeInterval a = new TimeInterval(now, now.plusHours(6));
		System.out.println("===OK pattern===");
		System.out.println(a.contains(a));
		System.out.println(a.contains(new TimeInterval(now.plusHours(-1), now.plusHours(1))));
		System.out.println(a.contains(new TimeInterval(now.plusHours(1), now.plusHours(6))));
		System.out.println(a.contains(new TimeInterval(now.plusHours(4), now.plusHours(7))));
		System.out.println(a.contains(new TimeInterval(now.plusHours(-1), now.plusHours(7))));
		System.out.println("===NG pattern===");
		System.out.println(a.contains(new TimeInterval(now.plusHours(-6), now)));
		System.out.println(a.contains(new TimeInterval(now.plusHours(-6), now.plusHours(-2))));
		System.out.println(a.contains(new TimeInterval(now.plusHours(6), now.plusHours(10))));
		System.out.println(a.contains(new TimeInterval(now.plusHours(7), now.plusHours(10))));

	}

}
