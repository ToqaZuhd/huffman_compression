package project2;

public class Huffman {
	
	Heap heap;
	Node root;
	int Length;
	
	//find the huffman code of each character
	public void huffmanCode(int[] arr) {
		int count = 0;
		for (int i = 0; i < arr.length; i++)
			if (arr[i] > 0)
				count++;
		
		//build heap with non zero frequency
		heap = new Heap(arr, count);
		
		//build huffman tree
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

		
        //array after huffman 
		Node[] array = heap.getHeap();
		
		//root Node which represent the tree
		root=array[0];
		heap.clean();
		String s = "";
		
		//read the huffman code
		heap.inorder(root, s);
		
		//length is the total numbers of all huffman codes for all characters
        Length=heap.Length;
       
       
	}
	
	//return  the array of node
	public Node[] getArr() {
		return heap.getHeap();
	}
	
	
	
	
}
