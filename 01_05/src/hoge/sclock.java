package hoge;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class sclock extends JFrame {
	private static final long serialVersionUID = 1L;
	JLabel label = new JLabel();
	public sclock() {
		setSize(100, 50);
		setLayout(new BorderLayout());
		getContentPane().add(label, BorderLayout.CENTER);
		
		// ラムダを利用することで、９行の処理が５行でかけている→４行短くなった。
//		new Timer(500, new Raction()).start();
		new Timer(500, e -> {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh : mm : ss");
			GregorianCalendar calendar = new GregorianCalendar();
			label.setText(simpleDateFormat.format(calendar.getTime()));
		}).start();
		this.setVisible(true);
	}
	
//	private class Raction implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh : mm : ss");
//			GregorianCalendar calendar = new GregorianCalendar();
//			label.setText(simpleDateFormat.format(calendar.getTime()));
//		}
//	}
	
	
	public static void main(String[] args) {
		new sclock();
	}

}