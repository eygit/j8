import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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
			System.out.println("==== 定時リマインダシステム： " + LocalDateTime.now() + " ====");
			ZonedDateTime before1 = ZonedDateTime.now().plusHours(1);
			for (Map.Entry<ZonedDateTime, String> entry : schedules.entrySet()) {
				if (before1.isAfter(entry.getKey().withZoneSameLocal(ZoneId.of("Asia/Tokyo"))) ) {
					System.out.println("[!!!ALARM!!!]" + entry);
				} else {
					System.out.println("[future task]" + entry.getKey().withZoneSameLocal(ZoneId.of("Asia/Tokyo")) + entry.getValue());
				}
			}
		};
		scheduler.scheduleAtFixedRate(reminder, 0, 5, TimeUnit.SECONDS);// TimeUnit.MINUTES
	}

	public String toString() {
		return schedules.entrySet().toString();
	}

	public static void main(String[] args) {
		Reminder r = new Reminder();
		r.add(ZonedDateTime.now().minusMinutes(10), "now - 10min");
		r.add(ZonedDateTime.now(), "now");
		r.add(ZonedDateTime.now().plusMinutes(1), "now + 1min");
		r.add(ZonedDateTime.now().plusHours(1).plusMinutes(10), "now + 1hour10min");
		r.add(ZonedDateTime.now().plusHours(1).plusMinutes(10), "now + 1hour10min");
		
		r.add(ZonedDateTime.now(ZoneId.of("CET")).plusHours(1).plusMinutes(10), "CET: now + 1hour10min");
		r.add(ZonedDateTime.now(ZoneId.of("CET")).plusHours(1).plusMinutes(10).plusHours(9), "CET: now + 1hour10min + 9hour");
		
		
		r.start();
	}

}
