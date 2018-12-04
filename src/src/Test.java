package src;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		/*
		Map map = new Map();
	    map.constructNodeMap();
		map.printNode();
		*/
		
	    //map.constructRoadMap();
	    //map.printRoad();
	    //map.printHashMap();
		
		
		/*
		String a = "南京东路 10";
		String b = "南京东路 11";
		String []A = a.split(" ");
		String []B = b.split(" ");
		System.out.println(A[0].equals(B[0]));
		*/
		
		/*
		Node node = new Node("123");
		ArrayList arrayList = new ArrayList();
		arrayList.add(node);
		System.out.println(arrayList.get(0).Name);
		*/
		
		/*
		Unit unit1 = new Unit("123");
		Unit unit2 = unit1;
		unit2.Name = "321";
		System.out.println(unit1.Name + "   " + unit2.Name);
		*/
		
		/*
		ArrayList<Integer> A = new ArrayList<>();
		A.add(1);
		ArrayList<Integer> B = A;
		B.add(2);
		System.out.println(A + "   " + B);
		*/ 
		
		/*
		String A = "123";
		String B = A;
		//B = "321";
		A = "234";
		System.out.println(A + " " + B);
		*/
		
		
		Path path = new Path();
		path.map.constructNodeMap();
		path.map.constructRoadMap();
		
		System.out.println("欢迎使用本劣质地铁导航系统，祝您使用不愉快");
		ArrayList<Node> test = path.map.M;
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < test.size(); i++) {
		for (int j = 0; j < test.size(); j++) {	
			
			path.map.setNodeVisted();
			//System.out.println("请输入路径，如输入数字0则退出");
			//Scanner scanner = new Scanner(System.in);
			//String input = scanner.nextLine();
			//String input = "上海体育场 国权路";
			
			if (i == j) {
				continue;
			}
			
			String input = test.get(i).Name + " " + test.get(j).Name;
			//String input = "滴水湖 花桥";
			
			/*
			if (input.equals("0")) {
				break;
			}
			*/
			
			String[] strings = input.split(" ");
			String WholeRoute = "";
			int WholeTime = 0;
			
			for (int k = 0; k < strings.length - 1; k++) {
				path.search(strings[k], strings[k + 1]);
				path.getRoute(strings[k], strings[k + 1]);
				WholeRoute += path.Route;
				WholeTime += path.ShortestTime;
			}
			
			WholeRoute += strings[strings.length - 1];
			System.out.println(input);
			System.out.println(WholeRoute);
			System.out.println("用时共 " + WholeTime + " 分钟");
			
			//break;
			
			//scanner.close();
		}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("系统运行时间为：" + (end - start) + "毫秒");

	}

}
