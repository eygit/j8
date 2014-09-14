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

//        long count = 0;
//        for (String w : words) {
//           if (w.length() > 12) count++;  
//        }
//        System.out.println(count);
        // answer 33
        
        
        AtomicInteger atomicCounter = new AtomicInteger();
        AtomicInteger endFlag = new AtomicInteger();
       
        int segs = 4;
        int size = words.size() / segs;

        int start = 0;
        new Thread( () -> {
        	for (int i = start*0; i < start+size*1; i++) {
        		if (words.get(i).length() > 12) atomicCounter.addAndGet(1);
        	}
        	endFlag.addAndGet(1);
        }).start();
        
        new Thread( () -> {
        	for (int i = start+size*1; i < start+size*2; i++) {
        		if (words.get(i).length() > 12) atomicCounter.addAndGet(1);
        	}
        	endFlag.addAndGet(1);
        }).start();
        new Thread( () -> {
        	for (int i = start+size*2; i < start+size*3; i++) {
        		if (words.get(i).length() > 12) atomicCounter.addAndGet(1);
        	}
        	endFlag.addAndGet(1);
        }).start();
        new Thread( () -> {
        	for (int i = start+size*3; i < start+size*4; i++) {
        		if (words.get(i).length() > 12) atomicCounter.addAndGet(1);
        	}
        	endFlag.addAndGet(1);
        }).start();
        
        //処理の待ち合わせが必要になるため単一カウンター更新にスレッドは使いたくない
        while(true) {
        	Thread.sleep(1000);
        	if (endFlag.get() == 4) break;
        }
        System.out.println(atomicCounter.get());

	}

}
