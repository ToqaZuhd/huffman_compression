package project2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Header {

	FileInputStream FIS;
	BufferedInputStream BIS;

	String character = "", numbers = "", test = "";
	Node root;
	String namefile = "";
	int diff;

	public Header() {
	}

	//read huffman tree in post order way 
	public void postOrderTree(Node root) {

		if (root != null) {
			if (root.left == null && root.Right == null) {
				numbers += "1";
				character += root.index;
                test+="1"+root.index;
				return;
			}
			postOrderTree(root.left);
			postOrderTree(root.Right);
			numbers += "0";
            test+="0";
		}

	}
	
    //append zero in the end of numbers to be byte
	public int convert() {
		int len = numbers.length();

		if (numbers.length() > 0) {

			if (numbers.length() % 8 > 0) {

				int append = 8 - (numbers.length() % 8);
				for (int i = 0; i < append; i++)
					numbers += '0';
			}

		}
		int length = numbers.length() - len;
		return length;

	}

	//read the beginning in compress file 
	public void readHeader(FileInputStream FIS,BufferedInputStream BIS) {
		try {
			namefile="";
			int num;

			byte[] bytes;

			//size of file name
			num = BIS.read();
			

			bytes = new byte[num];
			
			//read the file name
			num = BIS.read(bytes);
			for (int i = 0; i < num; i++) {
				namefile += (char) bytes[i];
			}
			
			

			//size of s in tree
			num = BIS.read();
			
			bytes = new byte[num];
			String numOftree = "";

			//number of zero will not read in tree
			int numOfbits = BIS.read();

			//read the numbers
			num = BIS.read(bytes);

			//convert the numbers from byte to string
			for (int i = 0; i < num; i++) {
				String conv = "";
				int var = bytes[i];

				if (var < 0)
					var = 256 + var;
				conv += Integer.toBinaryString(var);
				int diff = 8 - conv.length();
				if (conv.length() < 8) {

					for (int j = 0; j < diff; j++) {
						conv = "0" + conv;
					}
				}
				numOftree += conv;

			}
			numOfbits = numOftree.length() - numOfbits;//number of bits will read 
			numOftree = numOftree.substring(0, numOfbits);//set numbers in tree without addition zero

			//size of character in tree
			num = BIS.read() + 1;

			bytes = new byte[num];
			
			//read characters
			num = BIS.read(bytes);
			String chars = "";
			for (int i = 0; i < num; i++) {
				chars += (char) bytes[i];
			}

			//rebuild huffman tree
			rebuildTree(numOftree, chars);

			//number  of zero will not read in compressed data
			 diff = BIS.read();
			
			
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, e1);// Handling with Exception via show special message
		}
	}

	//rebuild huffman tree
	public void rebuildTree(String numOftree, String chars) {
		
		//array it size equal size of numbers '0' '1'
		Node[] nodes = new Node[numOftree.length()];
		
		for (int i = 0; i < nodes.length; i++)
			nodes[i] = new Node();
		//if the character was '1' then its leaf so read character
		for (int i = 0, j = 0; i < numOftree.length(); i++) {
			if (numOftree.charAt(i) == '1') {

				nodes[i].index = chars.charAt(j);
				nodes[i].value = i;
				nodes[i].Right = null;
				nodes[i].left = null;
				j++;

			}

			/*if the character was '0' then its not leaf is root so read character set the right node is the previous node
			 * set left node to serch in left node of previous node to reach nod its left node is null then take the previous index of this node 
			 * 
			 * 
			 * */
			if (numOftree.charAt(i) == '0') {

				nodes[i].value = i;
				nodes[i].Right = nodes[i - 1];

				nodes[i].left = nodes[Search(nodes[i - 1])];
				root = nodes[i];

			}
		}

		
	}

	public int Search(Node node) {

		while (node.left != null) {
			node = node.left;
		}

		return node.value - 1;
	}

	
}
