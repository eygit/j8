import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class CheckedQUser {

	public static void main(String[] args) {
		Queue<Integer> rawq = new LinkedList<Integer>();
		Queue rawq2 = new LinkedList<Integer>();
		Queue cq = Collections.checkedQueue(rawq, Integer.class);
		Long l = Long.MAX_VALUE;
		Object o = (Object)l;
		rawq2.add(o); // チェックされず素通し
		cq.add(o); // 例外がスローされる
		
		

	}

}
