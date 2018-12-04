package src;

import java.util.ArrayList;
import java.util.HashMap;

public class Path {
	
	// �Ǹ���Ϣ���޴��ͼ�����������ֿ����õ����ֵ�
	public Map map = new Map();
	public ArrayList<Node> m = map.M;
	public HashMap<String, Integer> name2id = map.Name2ID;
	public HashMap<String, ArrayList<Integer>> name2line = map.Name2Line;
	public HashMap<String, Node> name2node = map.Name2Node;
	
	// ��m�Ǹ��ڵ���������BinaryHeap������Time����
	public BinaryHeap BH;
	//public ArrayList<Node> UnKnown = BH.heap;
	
	// ·������ʱ��
	public String Route;
	public int ShortestTime;
	
	// �Ѿ���Dijkstra�㷨���޸Ĺ��Ľڵ�����
	public ArrayList<Node> Known = new ArrayList<>();
	
	public void search (String from, String to) {
		
		BH = new BinaryHeap(m);
		Route = "";
		ShortestTime = 0;
		
		// ÿ�ζ�Ҫ����
		map.setNodeVisted();
		
		Node start = name2node.get(from);
		start.Time = 0;
		Known.add(start);
		
		int Key = BH.getIndex(start);
		BH.UpHeapify(Key);
		BH.BinaryHeapify(0);
		
		BH.extractMin();
		
		// ֻҪû���������һ����ͼ���
		while (!Known.get(Known.size() - 1).Name.equals(to)) {
			
			Node node = Known.get(Known.size() - 1);
			// ÿ�εõ�һ���µĵ㣬��Ҫ�������ڵĵ��Time�滻��
			for (int i = 0; i < node.Next.size(); i++) {				
				
				Node temp = node.NextNode.get(i);
				// ���δ֪��ʱ�������֪ + �߳����������õ��������Ÿ�
				if (temp.Time > node.Time + node.Distance.get(i)) {
					temp.Time = node.Time + node.Distance.get(i);
					
					// ·������ 
					temp.Parent = new ArrayList<>();
					temp.Parent.addAll(node.Parent);
					temp.Parent.add(node);
					
					// ��������������һ��
					int index = BH.getIndex(temp);
					BH.UpHeapify(index);
					//index = BH.getIndex(temp);
					//BH.BinaryHeapify(index);
				}
			}
			
			Known.add(BH.extractMin());
			if (Known.get(Known.size() - 1).Name.equals("�ٸ۴��")) {
				int K = 0;
				Known.get(K);
			}
			
		}	
	}
	
	// Ѱ������վ��Ĺ�����·�������ߺ��ĺ����ص����Ƕΰ����㷨Ϊ�����ߣ�
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
	
		// �õ��涨��ʽ��·��
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
			
			if (0 < i && i < parent.size() - 1 && parent.get(i).Name.equals("��Ϫ·")) {
				if (parent.get(i - 1).Name.equals("�Ϻ�����԰") && parent.get(i + 1).Name.equals("�����´�")) {
					Route += "��Ϫ· -> Line10 -> ";
				}
				else if (parent.get(i - 1).Name.equals("�����´�") && parent.get(i + 1).Name.equals("�Ϻ�����԰")) {
					Route += "��Ϫ· -> Line10 -> ";
				}
			}
			
			if (0 < i && i < parent.size() - 1 && parent.get(i).Name.equals("�ζ��³�")) {
				if (parent.get(i - 1).Name.equals("����·") && parent.get(i + 1).Name.equals("�Ϻ�������")) {
					Route += "�ζ��³� -> Line11 -> ";
				}
				else if (parent.get(i - 1).Name.equals("�Ϻ�������") && parent.get(i + 1).Name.equals("����·")) {
					Route += "�ζ��³� -> Line11 -> ";
				}
			}
			
			if (0 < i && i < parent.size() - 1 && parent.get(i).Name.equals("����·")) {
				if (parent.get(i - 1).Name.equals("���·") && parent.get(i + 1).Name.equals("����")) {
					Route += "����· -> Line2 -> ";
				}
				else if (parent.get(i - 1).Name.equals("����") && parent.get(i + 1).Name.equals("���·")) {
					Route += "����· -> Line2 -> ";
				}
			}
			
		}
		
		ShortestTime = name2node.get(to).Time;
	}
	
	

}
