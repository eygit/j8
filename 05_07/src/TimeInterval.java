import java.time.Instant;


public class TimeInterval {
	private Instant begin;
	private Instant end;

	public TimeInterval(Instant begin, Instant end) {
		this.begin = begin;
		this.end = end;
	}

	/**
	 * 指定のタイムインターバルが重なるか判別する.
	 * @param another 指定のタイムインターバル.
	 * @return 指定のタイムインターバルが重なる場合はtrue.
	 */
	boolean contains(TimeInterval another) {
//		System.out.println("DEBUG::" + this.end.isBefore(another.end) + this.end.isAfter(another.end) );
		return (  this.begin.equals(another.begin)
				|| this.end.equals(another.end)
				|| this.begin.isBefore(another.end) &&  this.end.isAfter(another.end)
				|| this.begin.isBefore(another.begin) &&  this.end.isAfter(another.begin)
				|| this.begin.isAfter(another.begin) &&  this.end.isBefore(another.end) );
	}

	public static void main(String[] args) {
		Instant now = Instant.now();
		TimeInterval a = new TimeInterval(now, now.plusMillis(6));

		System.out.println("===OK pattern===");
		System.out.println(a.contains(a));
		System.out.println(a.contains(new TimeInterval(now.plusMillis(-1), now.plusMillis(1))));
		System.out.println(a.contains(new TimeInterval(now.plusMillis(1), now.plusMillis(6))));
		System.out.println(a.contains(new TimeInterval(now.plusMillis(4), now.plusMillis(7))));
		System.out.println(a.contains(new TimeInterval(now.plusMillis(-1), now.plusMillis(7))));
		System.out.println("===NG pattern===");
		System.out.println(a.contains(new TimeInterval(now.plusMillis(-6), now)));
		System.out.println(a.contains(new TimeInterval(now.plusMillis(-6), now.plusMillis(-2))));
		System.out.println(a.contains(new TimeInterval(now.plusMillis(6), now.plusMillis(10))));
		System.out.println(a.contains(new TimeInterval(now.plusMillis(7), now.plusMillis(10))));

	}

}
