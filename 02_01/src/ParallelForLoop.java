import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ParallelForLoop {

	public static void main(String[] args) throws IOException, InterruptedException {

        String contents = new String(Files.readAllBytes(
                Paths.get("./alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        AtomicInteger atomicCounter = new AtomicInteger();
        AtomicInteger endSegment = new AtomicInteger();
        AtomicInteger segNoCounter = new AtomicInteger();

        int segs = 4;
        int segSize = words.size() / segs;

        for (int seg = 0; seg < segs; seg++) {
            new Thread( () -> {
            	int mySegNo = segNoCounter.getAndIncrement();
            	for (int i = segSize*mySegNo; i < segSize*(mySegNo+1); i++) {
            		if (words.get(i).length() > 12) atomicCounter.addAndGet(1);
            	}
            	endSegment.addAndGet(1);
            }).start();
        }


        //処理の待ち合わせが必要になるため単一カウンター更新にスレッドは使いたくない
        while(true) {
        	Thread.sleep(1000);
        	if (endSegment.get() == segs) break;
        }
        System.out.println(atomicCounter.get());

	}

}
