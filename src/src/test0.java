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
		
		System.out.println("��ӭʹ�ñ����ʵ�������ϵͳ��ף��ʹ�ò����");
		
		path.map.setNodeVisted();
		System.out.println("������·��������������0���˳�");
		
		String input = "���� ��ˮ��";
		
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
		System.out.println("��ʱ�� " + WholeTime + " ����");
		
		long end = System.currentTimeMillis();
		System.out.println("ϵͳ����ʱ��Ϊ��" + (end - start) + "����");

	}

}
