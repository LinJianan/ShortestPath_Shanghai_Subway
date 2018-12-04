package src;

import java.util.ArrayList;
import java.util.HashMap;

public class Path {
	
	// 那个信息量巨大的图，连带着三种可能用得上字典
	public Map map = new Map();
	public ArrayList<Node> m = map.M;
	public HashMap<String, Integer> name2id = map.Name2ID;
	public HashMap<String, ArrayList<Integer>> name2line = map.Name2Line;
	public HashMap<String, Node> name2node = map.Name2Node;
	
	// 把m那个节点数组塞入BinaryHeap，依据Time排序
	public BinaryHeap BH;
	//public ArrayList<Node> UnKnown = BH.heap;
	
	// 路径和总时间
	public String Route;
	public int ShortestTime;
	
	// 已经在Dijkstra算法中修改过的节点数组
	public ArrayList<Node> Known = new ArrayList<>();
	
	public void search (String from, String to) {
		
		BH = new BinaryHeap(m);
		Route = "";
		ShortestTime = 0;
		
		// 每次都要清零
		map.setNodeVisted();
		
		Node start = name2node.get(from);
		start.Time = 0;
		Known.add(start);
		
		int Key = BH.getIndex(start);
		BH.UpHeapify(Key);
		BH.BinaryHeapify(0);
		
		BH.extractMin();
		
		// 只要没有来到最后一个点就继续
		while (!Known.get(Known.size() - 1).Name.equals(to)) {
			
			Node node = Known.get(Known.size() - 1);
			// 每次得到一个新的点，都要把它相邻的点的Time替换掉
			for (int i = 0; i < node.Next.size(); i++) {				
				
				Node temp = node.NextNode.get(i);
				// 如果未知点时间大于已知 + 边长，这样不用担心它逆着改
				if (temp.Time > node.Time + node.Distance.get(i)) {
					temp.Time = node.Time + node.Distance.get(i);
					
					// 路径更新 
					temp.Parent = new ArrayList<>();
					temp.Parent.addAll(node.Parent);
					temp.Parent.add(node);
					
					// 别忘了重新排序一次
					int index = BH.getIndex(temp);
					BH.UpHeapify(index);
					//index = BH.getIndex(temp);
					//BH.BinaryHeapify(index);
				}
			}
			
			Known.add(BH.extractMin());
			if (Known.get(Known.size() - 1).Name.equals("临港大道")) {
				int K = 0;
				Known.get(K);
			}
			
		}	
	}
	
	// 寻找两个站点的公共线路（三号线和四号线重叠的那段按照算法为三号线）
		public int getLine (Node n1, Node n2) {
			
			ArrayList<Integer> A1 = n1.Line;
			ArrayList<Integer> A2 = n2.Line;
			
			for (int i = 0; i < A1.size(); i++) {
				for (int j = 0; j < A2.size(); j++) {
					if (A1.get(i) == A2.get(j)) {
						return A1.get(i);
					}
				}
			}
			
			return 0;
		}
	
		// 得到规定格式的路径
	public void getRoute (String from, String to) {
		
		Route = "";
		ShortestTime = 0;
		int Line = -1;
		ArrayList<Node> parent = name2node.get(to).Parent;
		parent.add(name2node.get(to));
		for (int i = 0; i < parent.size() - 1; i++) {
			
			int temp = getLine(parent.get(i), parent.get(i + 1));
			if (temp != Line) {
				Line = temp;
				Route += parent.get(i).Name + " -> Line" + Line + " -> ";
			}
			
			if (0 < i && i < parent.size() - 1 && parent.get(i).Name.equals("龙溪路")) {
				if (parent.get(i - 1).Name.equals("上海动物园") && parent.get(i + 1).Name.equals("龙柏新村")) {
					Route += "龙溪路 -> Line10 -> ";
				}
				else if (parent.get(i - 1).Name.equals("龙柏新村") && parent.get(i + 1).Name.equals("上海动物园")) {
					Route += "龙溪路 -> Line10 -> ";
				}
			}
			
			if (0 < i && i < parent.size() - 1 && parent.get(i).Name.equals("嘉定新城")) {
				if (parent.get(i - 1).Name.equals("白银路") && parent.get(i + 1).Name.equals("上海赛车场")) {
					Route += "嘉定新城 -> Line11 -> ";
				}
				else if (parent.get(i - 1).Name.equals("上海赛车场") && parent.get(i + 1).Name.equals("白银路")) {
					Route += "嘉定新城 -> Line11 -> ";
				}
			}
			
			if (0 < i && i < parent.size() - 1 && parent.get(i).Name.equals("广兰路")) {
				if (parent.get(i - 1).Name.equals("金科路") && parent.get(i + 1).Name.equals("唐镇")) {
					Route += "广兰路 -> Line2 -> ";
				}
				else if (parent.get(i - 1).Name.equals("唐镇") && parent.get(i + 1).Name.equals("金科路")) {
					Route += "广兰路 -> Line2 -> ";
				}
			}
			
		}
		
		ShortestTime = name2node.get(to).Time;
	}
	
	

}
