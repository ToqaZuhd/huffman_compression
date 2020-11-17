package project2;

/**
 * @author THINK
 *
 */
public class Heap {

	private int size = 0;
	private int count ;

	

	public Node[] heap;

	
	
	public Node[] getHeap() {
		return heap;
	}

	
	public void setHeap(Node[] heap) {
		this.heap = heap;
	}

	// construct passes the original array zero and non
	// count is number non zero indexes
	public Heap(int [] arr, int count) {
		
        this.count=count;
		this.heap = new Node[arr.length];
		for (int i = 0; i < heap.length; i++) {
			heap[i] = new Node();
		}

		newArr(arr);
		if (heap.length > 0)
			build_heap();
	}

	public void newArr(int [] arr) {
		for (int i = 0, j = 0; i < arr.length; i++) {
			if (arr[i] > 0) {

				heap[j].freq_char = arr[i];
				heap[j].index = (char) i;
				heap[j].value = i;

				j++;
			}
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void build_heap_property(int index) {

		int left = (2 * index) + 1;
		int right = (2 * index) + 2;
		int smallest = index;

		System.out.println("-----------------------------------------------------");
		if ((left < count) && (heap[left].freq_char < heap[smallest].freq_char)) {
			smallest = left;

		}
		if ((right < count) && (heap[right].freq_char < heap[smallest].freq_char))
			smallest = right;

		if (smallest != index) {
			Node temp = heap[index];
			heap[index] = heap[smallest];
			heap[smallest] = temp;

			build_heap_property(smallest);

		}

	}

	public void build_heap() {

		for (int i = (int) Math.ceil(count / 2.0) - 1; i >= 0; i--) {

			build_heap_property(i);

		}
		size = count;

	}

	public boolean isEmpty() {

		return size == 0;

	}

	public boolean isFull() {
		return size == count;

	}

	public Node deleteMin() {

		int i, child;
		Node minVal = new Node(), lastVal=new Node();
		if (isEmpty())
			System.out.println("Empty");

		else {

			minVal.freq_char = heap[0].freq_char;
			minVal.index = heap[0].index;
			minVal.left = heap[0].left;
			minVal.Right = heap[0].Right;
			minVal.value = heap[0].value;
               
			
			
			lastVal= heap[--size];
			
			
			for (i = 0; i * 2 + 1 < size; i = child) {

				child = 2 * i + 1;// child = left
				
				if ((child < size) && heap[child + 1].freq_char < heap[child].freq_char) {
					
					child = child + 1;
					}
				if (lastVal.freq_char > heap[child].freq_char) {
					
					heap[i]= heap[child];
					
				} else
					break;

			}

			heap[i] = lastVal;
			

		}
		return minVal;

	}

	public void insert(Node element) {
		if (isFull())
			System.out.println("Is Full");
		else {
			
			

			int i = size++;
			
			int j = (int) Math.ceil(i / 2.0) - 1;

			
			while ((i > 0) && heap[j].freq_char > element.freq_char) {
               
				heap[i] = heap[j];
               
				
				i = j;
				j = (int) Math.ceil(i / 2.0) - 1;

			}
			heap[i] = element;
			
		}
	}

	public void print() {

		for (int i = 0; i < size; i++) {
			System.out.println("heap[" + i + "]=" + heap[i].freq_char);
			if (heap[i].left != null)
				System.out.println("left[" + i + "]" + (int)heap[i].left.index);
			else
				System.out.println("left[" + i + "]" + null);
			if (heap[i].Right != null)
				System.out.println("right[" + i + "]" + (int)heap[i].Right.index);
			else
				System.out.println(" right[" + i + "]" + null);
			System.out.println("frea="+(int)heap[i].index+" "+"num="+heap[i].freq_char);
			System.out.println("----------------------");
		}
	}

	public void inorder(Node root,String s) {
		
		if(root==null) {
			
			return;}
		
		else{
			
        if(root.Right==null&&root.left==null) {
        	root.huffman+=s;
        	
        	root.length=s.length();
        	
        	heap[(int)root.index]=root;
        	System.out.println((int)heap[(int)root.index].index+"  "+heap[(int)root.index].huffman+"  "+heap[(int)root.index].length+"  "+heap[(int)root.index].freq_char);
			
        	
        	return;}
        
			inorder(root.left,s+"0");
			inorder(root.Right,s+"1");
		
		}
		}
	public void inorder(Node root) {
		if(root==null)
			return;
		else {
			if(root.Right==null&&root.left==null) {
				System.out.println("root"+root.freq_char);
				return;}
			
			inorder(root.left);
			inorder(root.Right);
			
		}
	}
	
	public void clear() {
		for (int i = 0; i < heap.length; i++) {
			heap[i] = new Node();
		}
	}
	
	
}
		
