package src;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Path path = new Path();
		path.map.constructNodeMap();
		path.map.constructRoadMap();
		
		System.out.println("欢迎使用本劣质地铁导航系统，祝您使用不愉快");
		
		while (true) {
			
			path.map.setNodeVisted();
			System.out.println("请输入路径，如输入数字0则退出");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			//String input = "上海体育场 国权路";
			
			long start = System.currentTimeMillis();
			if (input.equals("0")) {
				break;
			}
			
			String[] strings = input.split(" ");
			String WholeRoute = "";
			int WholeTime = 0;
			
			for (int i = 0; i < strings.length - 1; i++) {
				path.search(strings[i], strings[i + 1]);
				path.getRoute(strings[i], strings[i + 1]);
				WholeRoute += path.Route;
				WholeTime += path.ShortestTime;
			}
			
			WholeRoute = WholeRoute + strings[strings.length - 1];
			System.out.println(WholeRoute);
			System.out.println("用时共 " + WholeTime + " 分钟");
			
			long end = System.currentTimeMillis();
			System.out.println("系统运行时间为：" + (end - start) + "毫秒");
			//break;
			
			//scanner.close();
		}
		

	}

}
