package project2;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Header {

	FileInputStream FIS;
	BufferedInputStream BIS;

	String character = "", numbers = "", test = "";

	public Header() {
	}

	public void postOrderTree(Node root) {

		if (root != null) {
			if (root.left == null && root.Right == null) {
				numbers += "1";
				character += root.index;

				return;
			}
			postOrderTree(root.left);
			postOrderTree(root.Right);
			numbers += "0";

		}

	}

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

	public String extension(String string) {

		String str = "";
		str = string.substring(string.lastIndexOf(".") + 1);
		return str;
	}

	public void readHeader(String string) {
		try {
			int num;

			FileInputStream FIS = new FileInputStream(string);
			BufferedInputStream BIS = new BufferedInputStream(FIS);
			byte[] bytes;

			num = BIS.read();
			System.out.println("num " + num);

			bytes = new byte[num];
			String extension = "";
			num = BIS.read(bytes);
			for (int i = 0; i < num; i++) {
				extension += (char) bytes[i];
			}
			System.out.println("ext " + extension);

			num = BIS.read();
			System.out.println("num " + num);
			bytes = new byte[num];
			String numOftree = "";

			int numOfbits = BIS.read();

			num = BIS.read(bytes);

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
			numOfbits = numOftree.length() - numOfbits;
			numOftree = numOftree.substring(0, numOfbits);

			num = BIS.read() + 1;

			bytes = new byte[num];
			num = BIS.read(bytes);
			String chars = "";
			for (int i = 0; i < num; i++) {
				chars += (char) bytes[i];
			}

			rebuildTree(numOftree, chars);

			int diff = BIS.read();
			System.out.println("diff= " + diff);
			/*
			 * System.out.println("str= "+BIS.read());
			 * System.out.println("str= "+BIS.read());
			 * System.out.println("str= "+BIS.read());
			 * System.out.println("str= "+BIS.read());
			 */
			reader(BIS, extension, diff, rebuildTree(numOftree, chars));
			BIS.close();
		} catch (IOException e1) {
			System.out.println("An error occurred.");
			e1.printStackTrace();
		}
	}

	public Node rebuildTree(String numOftree, String chars) {
		Node root = new Node();

		Node[] nodes = new Node[numOftree.length()];
		for (int i = 0; i < nodes.length; i++)
			nodes[i] = new Node();
		for (int i = 0, j = 0; i < numOftree.length(); i++) {
			if (numOftree.charAt(i) == '1') {

				nodes[i].index = chars.charAt(j);
				nodes[i].value = i;
				nodes[i].Right = null;
				nodes[i].left = null;
				j++;

			}

			if (numOftree.charAt(i) == '0') {

				nodes[i].value = i;
				nodes[i].Right = nodes[i - 1];

				nodes[i].left = nodes[Recursion(nodes[i - 1])];
				root = nodes[i];

			}
		}

		return root;
	}

	public int Recursion(Node node) {

		while (node.left != null) {
			node = node.left;
		}

		return node.value - 1;
	}

	public void reader(BufferedInputStream BIS, String ext, int len, Node root) throws IOException {
		try {
			FileOutputStream file = new FileOutputStream("file." + ext);
			int read;
			byte[] bytes = new byte[30];
			byte[] bits = new byte[30];
			String str = "";

			int nums = ((BIS.available() * 8) - len);
			Node temp = root;
			int count = 0;
			int countBits = 0;
			int length=0;
			while (BIS.available() > 0) {
				read = BIS.read(bytes);

				for (int i = 0; i < read; i++) {
					String conv = "";
					int ch = bytes[i];
					conv= String.format("%8s", Integer.toBinaryString(ch & 0xFF)).replace(' ', '0');
					str += conv;
					}

			}		
	       
			for (int i = 0, j = 0; i < nums; i++) {
						if (str.charAt(i) == '0')
							temp = temp.left;
						else
							temp = temp.Right;
						if (temp.left == null && temp.Right == null) {

							bits[j] = (byte) temp.index;
                           
							count++;
							j++;
							if (count == 30) {
								file.write(bits, 0, count);
								count = 0;
								j = 0;
							}
							temp = root;
						}

					}
                
				

			
			if(count>0) {
				file.write(bits, 0, count);
				
			}

			file.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
