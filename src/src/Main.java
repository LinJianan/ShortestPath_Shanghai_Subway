package src;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Path path = new Path();
		path.map.constructNodeMap();
		path.map.constructRoadMap();
		
		System.out.println("��ӭʹ�ñ����ʵ�������ϵͳ��ף��ʹ�ò����");
		
		while (true) {
			
			path.map.setNodeVisted();
			System.out.println("������·��������������0���˳�");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			//String input = "�Ϻ������� ��Ȩ·";
			
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
			System.out.println("��ʱ�� " + WholeTime + " ����");
			
			long end = System.currentTimeMillis();
			System.out.println("ϵͳ����ʱ��Ϊ��" + (end - start) + "����");
			//break;
			
			//scanner.close();
		}
		

	}

}
