package hoge;

import java.util.ArrayList;
import java.util.List;

public class ForloopCapture {

	// �����ȃR�[�h�B
	// �e�����_��names�̗v�f���ɒl���L���v�`������
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
			// i�͉ςȒl�̂��߁A�����_������L���v�`���ł��Ȃ�
//			runners.add( () -> System.out.println(names[i]) );
		}

	}

}