package src;

import java.util.ArrayList;

public class Node {
	
	
	// 站台的序号，独一无二
	public int ID = -1;
	
	// 站台名称，其实也独一无二
	public String Name = "";
	
	// 是否访问过
	public boolean Visited = false;
	
	// 相邻站台的名称
	public ArrayList<String> Next = new ArrayList<>();
	
	// 相邻站台节点
	public ArrayList<Node> NextNode = new ArrayList<>();
	
	// 相邻站台到达所需要的时间
	public ArrayList<Integer> Distance = new ArrayList<>();
	
	// 所属线路
	public ArrayList<Integer> Line = new ArrayList<>();
	
	// 记录路径
	public ArrayList<Node> Parent = new ArrayList<>();

	// 起始点到目前为止的时间
	public int Time = 999;
	
	// 初始化
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
