package project2;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class Header {

	FileOutputStream FIS;
	BufferedOutputStream BIS;
	String str = "";
	String character = "", numbers = "";
	public Header() {
	}

	public Header(FileOutputStream FIS, BufferedOutputStream BIS) {
		this.FIS = FIS;
		this.BIS = BIS;

	}

	public void postOrderTree(Node root) {

		if (root != null) {
			if (root.left == null && root.Right == null) {
				str += "1" + root.index;

				return;
			}
			postOrderTree(root.left);
			postOrderTree(root.Right);
			str += "0";

		}

	}

	public void convert() {
		

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '1' || str.charAt(i) == '0')
				numbers += str.charAt(i);
			else
				character += str.charAt(i);
		}

		int res = 0;
		if (numbers.length() > 0) {

			if (numbers.length() % 8 > 0) {

				res = (numbers.length() / 8) + 1;

				int append = (8 * res) - numbers.length();
				for (int i = 0; i < append; i++)
					numbers += '0';
			}

		}

	}
	
	
}
