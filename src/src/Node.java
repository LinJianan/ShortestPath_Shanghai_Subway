package src;

import java.util.ArrayList;

public class Node {
	
	
	// վ̨����ţ���һ�޶�
	public int ID = -1;
	
	// վ̨���ƣ���ʵҲ��һ�޶�
	public String Name = "";
	
	// �Ƿ���ʹ�
	public boolean Visited = false;
	
	// ����վ̨������
	public ArrayList<String> Next = new ArrayList<>();
	
	// ����վ̨�ڵ�
	public ArrayList<Node> NextNode = new ArrayList<>();
	
	// ����վ̨��������Ҫ��ʱ��
	public ArrayList<Integer> Distance = new ArrayList<>();
	
	// ������·
	public ArrayList<Integer> Line = new ArrayList<>();
	
	// ��¼·��
	public ArrayList<Node> Parent = new ArrayList<>();

	// ��ʼ�㵽ĿǰΪֹ��ʱ��
	public int Time = 999;
	
	// ��ʼ��
	public Node () {
		
		Next = new ArrayList<>();
		NextNode = new ArrayList<>();
		Distance = new ArrayList<>();
		Line = new ArrayList<>();
		Parent = new ArrayList<>();

		
	}
	
    public Node (String Name) {
		
		this.Name = Name;
		Next = new ArrayList<>();
		NextNode = new ArrayList<>();
		Distance = new ArrayList<>();
		Line = new ArrayList<>();
		Parent = new ArrayList<>();
		
	}
   
}
