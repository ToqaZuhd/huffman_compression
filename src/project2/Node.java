package project2;

public class Node {

	char index;//the char
	int value;
	public char getIndex() {
		return index;
	}

	public void setIndex(char index) {
		this.index = index;
	}

	public String getHuffman() {
		return huffman;
	}

	public void setHuffman(String huffman) {
		this.huffman = huffman;
	}

	public int getFreq_char() {
		return freq_char;
	}

	public void setFreq_char(int freq_char) {
		this.freq_char = freq_char;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	String huffman="";
	Node left;
	Node Right;
	int freq_char;//number of freq of char
	int length;
	
	public Node() {
		this.value=(int)index;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return Right;
	}

	public void setRight(Node right) {
		Right = right;
	}

	public Node(char ch,String huff,int length,int freq) {
		this.index=ch;
		this.huffman=huff;
		this.length=length;
		this.freq_char=freq;
	}
}
