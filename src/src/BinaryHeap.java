package src;

import java.util.ArrayList;

public class BinaryHeap {
	
    public ArrayList<Node> heap = new ArrayList<>();

    //@Contract(pure = true)
    public int left(int i) {
        return 2 * i + 1;
    }

    //@Contract(pure = true)
    public int right(int i) {
        return 2 * i + 2;
    }
    
    // ������
    public BinaryHeap(ArrayList<Node> elements) {
        heap.addAll(elements);
        buildBinaryHeap();
    }
    
    // ��������
    public void buildBinaryHeap() {
        for (int i = heap.size() / 2 + 1; i >= 0; i--)
            BinaryHeapify(i);
    }

    // �³�����
    public void BinaryHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = i;
        if (l < heap.size() && heap.get(l).Time < heap.get(smallest).Time)
            smallest = l;
        if (r < heap.size() && heap.get(r).Time < heap.get(smallest).Time)
            smallest = r;
        if (smallest != i) {
            Node temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);
            BinaryHeapify(smallest);
        }
    }
    
    // �ϸ�
    public void UpHeapify (int i) {
    	if (i== 0)
    		return;
    	int p = (i - 1) / 2;
    	if (p >= 0 && heap.get(i).Time < heap.get(p).Time) {
    		Node temp = heap.get(i);
    		heap.set(i, heap.get(p));
    		heap.set(p, temp);
    	    UpHeapify(p);
    	}
    }

    // ȡ����Сֵ������
    public Node extractMin() {
        
        Node min = heap.get(0);
        if (heap.size() > 1) { 
            heap.set(0, heap.remove(heap.size() - 1));
            BinaryHeapify(0);
        }
        else if (heap.size() == 1) {
        	return min;
        }
        else
            throw new Error("heap underflow");
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
    
    // ���Ԫ��λ��
    public int getIndex (Node node) {
    	for (int i = 0; i < heap.size(); i++) {
    		if (heap.get(i).Name.equals(node.Name)) {
    			return i;
    		}
    	}
    	return -1;
    }
}
