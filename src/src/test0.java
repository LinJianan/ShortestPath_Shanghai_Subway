package src;

import java.io.FileNotFoundException;

public class test0 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Map map = new Map();
	    map.constructNodeMap();
	    map.constructRoadMap();
		//map.printNode();
		
		Path path = new Path();
		path.map.constructNodeMap();
		path.map.constructRoadMap();
		
		System.out.println("欢迎使用本劣质地铁导航系统，祝您使用不愉快");
		
		path.map.setNodeVisted();
		System.out.println("请输入路径，如输入数字0则退出");
		
		String input = "花桥 滴水湖";
		
		long start = System.currentTimeMillis();
		
		
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

	}

}
