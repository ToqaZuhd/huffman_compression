package project2;

public class Huffman {
	
	Heap heap;
	Node root;
	int Length;
	public void huffmanCode(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++)
			if (arr[i] > 0)
				count++;
		heap = new Heap(arr, count);
		heap.print();
		System.out.println("______________________________________");
		
		for (int i = 1; i <count; i++) {
			Node newNode = new Node();
			Node min_Node1 = heap.deleteMin();
			
			Node min_Node2 = heap.deleteMin();
			
			newNode.left = min_Node1;
			newNode.Right = min_Node2;
			newNode.freq_char = min_Node1.freq_char + min_Node2.freq_char;
			newNode.value=i;
			
			
			heap.insert(newNode);
			

		}

		System.out.println("-------------------------------------------------");
         
		Node[] array = heap.getHeap();
		
		
		root=array[0];
		heap.clear();
		String s = "";
		
		//heap.inorder(root);
		heap.inorder(root, s);
					
       
        Length=heap.Length;
        
       
       
        showTable sT=new showTable(heap.getHeap());
	}
	
	public void print(Node []heap) {
        
		for (int i = 0; i < heap.length; i++) {
			System.out.println("heap[" + i + "]=" + heap[i].freq_char);
			if (heap[i].left != null)
				System.out.println("left[" + i + "]" + heap[i].left.freq_char);
			else
				System.out.println("left[" + i + "]" + null);
			if (heap[i].Right != null)
				System.out.println("right[" + i + "]" + heap[i].Right.freq_char);
			else
				System.out.println(" right[" + i + "]" + null);
			System.out.println("frea="+(int)heap[i].index+" "+"num="+heap[i].huffman+"len="+heap[i].length);
			System.out.println("----------------------");
		}
	}
	
	public Node[] getArr() {
		return heap.getHeap();
	}
	
	
	
	
}
