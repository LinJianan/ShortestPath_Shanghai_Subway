package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
	
	// ����Ǻ��ģ����ǹ����Ĵ��ͼ
	public ArrayList<Node> M = new ArrayList<Node>();
	
	// ͨ������Ѱ��վ̨���
	public HashMap<String, Integer> Name2ID = new HashMap<>();
	
	// վ�����ƶԼ����������ӳ��
	public HashMap<String, ArrayList<Integer>> Name2Line = new HashMap<>();
	
	// վ�����ƶ�վ�㱾���ӳ��
	public HashMap<String, Node> Name2Node = new HashMap<>();
	
	// ������վ��ķ���״̬ Visted ����� false��ע��ÿ�������������·��֮ǰ��Ҫ����һ�α�����
	public void setNodeVisted () {
		for (int i = 0; i < M.size(); i++) {
			M.get(i).Visited = false;
			M.get(i).Parent = new ArrayList<>();
			M.get(i).Time = 999;
		}
	}
	
	// �����ж�һ���µ�վ���Ƿ�Ϊ��վ�㣬�����ڴ��ͼ M ��
	public boolean isNewNode (Node temp) {	
		
		// ֻ��Ҫ�ж������Ƿ���ͬ�ͺ���
		for (int i = 0; i < M.size(); i++) {
			if (M.get(i).Name.equals(temp.Name)) {
				return false;
			}
		}
		
		return true;		
	}
	
	// ����Ǿ�վ�㣬Ѱ�Ҹ� temp վ���ID
	public int getID (Node temp) {
		
		// ������վ�㣬�������������������ò���
		if (isNewNode(temp) == true) {
			return -1;
		}
		
		// Ѱ�Ҿ�վ��
		for (int i = 0; i < M.size(); i++) {
			if (M.get(i).Name.equals(temp.Name)) {
				return M.get(i).ID;
			}
		}
		
		// ����Ϊ�˲�����ֱ���ĺ�ɫ
		return -1;
		
	}
	
	// ��һ��վ������ͼ
	public void addNode (Node temp) {
		
		// ��վ���һ����ID��һ��ֱ�Ӹ�index����
		if (isNewNode(temp) == true) {
			temp.ID =  M.size();
			M.add(temp);
		}
		
		// ��վ��Ҫ�ϲ�����������Line��Next��Distance��̬���飬���õ���Line������ظ�
		else {
			int i = getID(temp);
			M.get(i).Distance.addAll(temp.Distance);
			M.get(i).Next.addAll(temp.Next);
			M.get(i).Line.addAll(temp.Line);
		}
		
	}
	
	// ���ǿ�ʼ���ļ������ͼ M
	public void constructNodeMap () throws FileNotFoundException {
		
		// ������17��txt�ļ�
		for (int i = 1; i <= 17; i++) {
			
			File Temp = new File("·��ͼ\\" + String.valueOf(i) + ".txt");
			ArrayList<String> Data = new ArrayList<String>();
			
			Scanner input = new Scanner(Temp);
			input.nextLine();
			
			// ����ȥ
			while (input.hasNextLine()) {
				
				Data.add(input.nextLine());
				
			}
			
			// ���������ڵ�����������ÿ�����ƺ�ʱ��
			int size = Data.size();
			String[] name = new String[size];
			int[] time = new int[size];
			
			// ����������ʱ������ǰ��������֣���������ʱ�ڵ�
			String[] t = new String[2];
			Node node = new Node();
			
			// �������ƺ�ʱ��
			for (int j = 0; j < size; j++) {
				
				t = Data.get(j).split(" ");
				name[j] = t[0];
				time[j] = Integer.valueOf(t[1]);
				
			}
			
			// �õ�����վ�㣬��������ʱ�䣬���ߵ�Ȩ�أ��Լ���·
			for (int j = 0; j < size; j++) {
				
				node = new Node(name[j]);
				
				// ���ں�ʱ��
				if (j != 0) {
					
					node.Next.add(name[j - 1]);
					node.Distance.add(Math.abs(time[j] - time[j-1]));
					
				}
				
				if (j != size - 1) {
					
					node.Next.add(name[j + 1]);
					node.Distance.add(Math.abs(time[j+1] - time[j]));
					
				}
				
				// ��·
				if (i == 14 || i == 15) {
					node.Line.add(i - 4);
				}
				else {
					node.Line.add(i);
				}
				
				// ���վ��
				addNode(node);
				
			}
			
			
			input.close();
			
		}
		
	}
	
	// ��ӡ�ڵ�ͼ
	public void printNode () {
		
		for (int i = 0; i < M.size(); i++) {
			System.out.println(M.get(i).Name + " " + i);
			
			for (int j = 0; j < M.get(i).Next.size(); j++) {
				System.out.print(M.get(i).Next.get(j) + " ");
			}
			System.out.println();
			
			for (int j = 0; j < M.get(i).Distance.size(); j++) {
				System.out.print(M.get(i).Distance.get(j) + " ");
			}
			System.out.println();
			
			for (int j = 0; j < M.get(i).Line.size(); j++) {
				System.out.print(M.get(i).Line.get(j) + " ");
			}
			
			System.out.println();
		}
		
	}
	
	// Ѱ������վ��Ĺ�����·�������ߺ��ĺ����ص����Ƕΰ����㷨Ϊ�����ߣ�
	public int getLine (String s1, String s2) {
		
		ArrayList<Integer> A1 = M.get(Name2ID.get(s1)).Line;
		ArrayList<Integer> A2 = M.get(Name2ID.get(s2)).Line;
		
		for (int i = 0; i < A1.size(); i++) {
			for (int j = 0; j < A2.size(); j++) {
				if (A1.get(i) == A2.get(j)) {
					return A1.get(i);
				}
			}
		}
		
		return 0;
	}
	
	// ����·������ͼ
	public void constructRoadMap () {
		
		// ˳�ֹ���Name->ID��HashMap
		for (int i = 0; i < M.size(); i++) {
			Name2ID.put(M.get(i).Name, M.get(i).ID);
		}
		
		// ˳�ֹ���Name->Line��HashMap
		for (int i = 0; i < M.size(); i++) {
			Name2Line.put(M.get(i).Name, M.get(i).Line);
		}
		
		for (int i = 0; i < M.size(); i++) {
			Name2Node.put(M.get(i).Name, M.get(i));
		}
		
		for (int i = 0; i < M.size(); i++) {
			Node node = M.get(i);
			for (int j = 0; j < node.Next.size(); j++) {
				node.NextNode.add(Name2Node.get(node.Next.get(j)));
			}
		}
			
	}
	
	// ��ӡ������HashMap
	public void printHashMap () {
			
		for (int i = 0; i < M.size(); i++) {
				
			String string = M.get(i).Name;
			System.out.print(string + " ");
			System.out.print(Name2ID.get(string) + "�� ");
			System.out.print(Name2Line.get(string));
			System.out.println();
				
		}
	}
	
}	