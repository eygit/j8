package hoge;

import java.util.ArrayList;
import java.util.List;

public class ForloopCapture {

	// 正等なコード。
	// 各ラムダはnamesの要素順に値をキャプチャする
	public static void main(String[] args) {
		String[] names = {"Peter", "Paul", "Mary"};
		List<Runnable> runners = new ArrayList<>();
		for (String name : names)
			runners.add( () -> System.out.println(name) );
		
		for (Runnable r : runners) {
			r.run();
			r.run();
		}

		
		List<Runnable> oldrunners = new ArrayList<>();
		for(int i = 0; i < names.length; i++) {
			// iは可変な値のため、ラムダ式からキャプチャできない
//			runners.add( () -> System.out.println(names[i]) );
		}

	}

}
