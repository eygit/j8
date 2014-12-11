import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Reminder {
	// 同一時刻の複数スケジュールを表現するために、敢えてアイデンティティで管理する。
	private Map<ZonedDateTime, String> schedules = new IdentityHashMap<>();

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void add(ZonedDateTime schedule, String msg) {
		schedules.putIfAbsent(schedule, msg);
	}

	public void start() {
		final Runnable reminder = () -> {
			ZonedDateTime before1 = ZonedDateTime.now().minusHours(1);
			for (Map.Entry<ZonedDateTime, String> entry : schedules.entrySet()) {
				if (before1.isBefore(entry.getKey().withZoneSameLocal(ZoneId.of("Asia/Tokyo"))) ) {
					System.out.println(entry);
				} else {
					System.out.println(before1 + " NG: " + entry.getKey().withZoneSameLocal(ZoneId.of("Asia/Tokyo")) + entry.getValue());
				}
			}
		};

		final ScheduledFuture<?> reminderHandle = scheduler.scheduleAtFixedRate(reminder, 0, 1, TimeUnit.MINUTES);
		scheduler.schedule( () -> { reminderHandle.cancel(false);}, 0, TimeUnit.SECONDS);
	}

	public String toString() {
		return schedules.entrySet().toString();
	}

	public static void main(String[] args) {
		Reminder r = new Reminder();
		r.add(ZonedDateTime.of(2014, 12, 1, 12, 0, 0, 0, ZoneId.of("CET")), "2014年12月１日　中央ヨーロッパ");
		r.add(ZonedDateTime.of(2014, 12, 11, 21, 10, 0, 0, ZoneId.of("CET")), "takoCET");
		r.add(ZonedDateTime.of(2014, 12, 11, 21, 10, 0, 0, ZoneId.of("Asia/Tokyo")), "tako");
		r.add(ZonedDateTime.of(2016, 8, 8, 21, 10, 0, 0, ZoneId.of("Asia/Tokyo")), "future");
		r.start();
	}

}
