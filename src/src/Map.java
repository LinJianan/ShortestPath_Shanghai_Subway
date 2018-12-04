package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
	
	// 这个是核心，我们构建的大地图
	public ArrayList<Node> M = new ArrayList<Node>();
	
	// 通过名称寻找站台序号
	public HashMap<String, Integer> Name2ID = new HashMap<>();
	
	// 站点名称对几号线数组的映射
	public HashMap<String, ArrayList<Integer>> Name2Line = new HashMap<>();
	
	// 站点名称对站点本身的映射
	public HashMap<String, Node> Name2Node = new HashMap<>();
	
	// 把所有站点的访问状态 Visted 都变成 false，注意每次主函数做最短路径之前都要调用一次本函数
	public void setNodeVisted () {
		for (int i = 0; i < M.size(); i++) {
			M.get(i).Visited = false;
			M.get(i).Parent = new ArrayList<>();
			M.get(i).Time = 999;
		}
	}
	
	// 用来判断一个新的站点是否为新站点，即不在大地图 M 中
	public boolean isNewNode (Node temp) {	
		
		// 只需要判断名字是否相同就好了
		for (int i = 0; i < M.size(); i++) {
			if (M.get(i).Name.equals(temp.Name)) {
				return false;
			}
		}
		
		return true;		
	}
	
	// 如果是旧站点，寻找该 temp 站点的ID
	public int getID (Node temp) {
		
		// 考虑新站点，但正常情况下这个函数用不上
		if (isNewNode(temp) == true) {
			return -1;
		}
		
		// 寻找旧站点
		for (int i = 0; i < M.size(); i++) {
			if (M.get(i).Name.equals(temp.Name)) {
				return M.get(i).ID;
			}
		}
		
		// 纯粹为了不想出现报错的红色
		return -1;
		
	}
	
	// 把一个站点加入地图
	public void addNode (Node temp) {
		
		// 新站点给一个新ID，一般直接给index就行
		if (isNewNode(temp) == true) {
			temp.ID =  M.size();
			M.add(temp);
		}
		
		// 旧站点要合并进来，调整Line，Next，Distance动态数组，不用担心Line数组的重复
		else {
			int i = getID(temp);
			M.get(i).Distance.addAll(temp.Distance);
			M.get(i).Next.addAll(temp.Next);
			M.get(i).Line.addAll(temp.Line);
		}
		
	}
	
	// 我们开始读文件构造地图 M
	public void constructNodeMap () throws FileNotFoundException {
		
		// 我们有17个txt文件
		for (int i = 1; i <= 17; i++) {
			
			File Temp = new File("路线图\\" + String.valueOf(i) + ".txt");
			ArrayList<String> Data = new ArrayList<String>();
			
			Scanner input = new Scanner(Temp);
			input.nextLine();
			
			// 读进去
			while (input.hasNextLine()) {
				
				Data.add(input.nextLine());
				
			}
			
			// 行数（即节点数），储存每个名称和时刻
			int size = Data.size();
			String[] name = new String[size];
			int[] time = new int[size];
			
			// 这俩都是临时变量，前者用来拆分，后者是临时节点
			String[] t = new String[2];
			Node node = new Node();
			
			// 储存名称和时刻
			for (int j = 0; j < size; j++) {
				
				t = Data.get(j).split(" ");
				name[j] = t[0];
				time[j] = Integer.valueOf(t[1]);
				
			}
			
			// 得到相邻站点，计算运行时间，即边的权重，以及线路
			for (int j = 0; j < size; j++) {
				
				node = new Node(name[j]);
				
				// 相邻和时间
				if (j != 0) {
					
					node.Next.add(name[j - 1]);
					node.Distance.add(Math.abs(time[j] - time[j-1]));
					
				}
				
				if (j != size - 1) {
					
					node.Next.add(name[j + 1]);
					node.Distance.add(Math.abs(time[j+1] - time[j]));
					
				}
				
				// 线路
				if (i == 14 || i == 15) {
					node.Line.add(i - 4);
				}
				else {
					node.Line.add(i);
				}
				
				// 添加站点
				addNode(node);
				
			}
			
			
			input.close();
			
		}
		
	}
	
	// 打印节点图
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
	
	// 寻找两个站点的公共线路（三号线和四号线重叠的那段按照算法为三号线）
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
	
	// 构造路线网络图
	public void constructRoadMap () {
		
		// 顺手构建Name->ID的HashMap
		for (int i = 0; i < M.size(); i++) {
			Name2ID.put(M.get(i).Name, M.get(i).ID);
		}
		
		// 顺手构建Name->Line的HashMap
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
	
	// 打印那两个HashMap
	public void printHashMap () {
			
		for (int i = 0; i < M.size(); i++) {
				
			String string = M.get(i).Name;
			System.out.print(string + " ");
			System.out.print(Name2ID.get(string) + "号 ");
			System.out.print(Name2Line.get(string));
			System.out.println();
				
		}
	}
	
}	