package hoge;

import java.io.File;

public class RambdaDirectories {

	private static File[] rambdaDir(File root) {
		File[] dirs = root.listFiles(pathname
					-> pathname.isDirectory()
				);
		return dirs;
	}
	
	private static File[] refMethodDir(File root) {
		File[] dirs = root.listFiles(File::isDirectory);
		return dirs;
	}

	public static void main(String[] args) {
		File root = new File("C:\\");
		
//		File[] dirs = rambdaDir(root);
		File[] dirs = refMethodDir(root);
		
		
		for (File dir : dirs)
			System.out.println(dir);

	}


}
