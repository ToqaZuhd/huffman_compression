package project2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class compress {
	byte[] out = new byte[1040];
	int count = 0;

	public void compressFile(String string, Node[] arr, Node root) {

		try {

			FileOutputStream FOUT = new FileOutputStream("out.huf");
			BufferedOutputStream BOUT = new BufferedOutputStream(FOUT);

			byte[] bytes = new byte[1040];

			// FileInputStream to read the file
			FileInputStream FIS = new FileInputStream(string);
			BufferedInputStream BIS = new BufferedInputStream(FIS);
			int read;
			String huff = "";

			Header header = new Header();
			header.postOrderTree(root);
			header.convert();
			convert(header.numbers);
			BOUT.write(out, 0, count);
			count = 0;
			BOUT.write(out,0,count);
            count=0;
			while (BIS.available() > 0) {
				read = BIS.read(bytes);

				for (int i = 0; i < read; i++) {

					int ch = bytes[i];
					if (ch < 0) {

						int num = (256 - (-2 * ch)) + Math.abs(ch);
						ch = num;
					}

					huff += arr[ch].huffman;

					if (huff.length() >= 80) {
						convert(huff.substring(0, 80));

						huff = huff.substring(80);

						if (huff.length() == 0)
							huff = "";

					}

					if (count == 1040) {

						BOUT.write(out, 0, count);
						count = 0;
					}

				}

			}

			if (count > 0) {
				BOUT.write(out, 0, count);
				count = 0;
			}

			System.out.println(huff);
			int res = 0;
			if (huff.length() > 0) {

				if (huff.length() % 8 > 0) {

					res = (huff.length() / 8) + 1;

					int append = (8 * res) - huff.length();
					for (int i = 0; i < append; i++)
						huff += '0';
				}

				convert(huff);
				BOUT.write(out, 0, count);
				System.out.println("count  "+count);
				System.out.println("out  "+(char)out[0]+" "+(char)out[1]+" "+(char)out[2]);

			}
			System.out.println(huff);
			FIS.close();
			BIS.close();
			FOUT.close();

		} catch (IOException e1) {
			System.out.println("An error occurred.");
			e1.printStackTrace();
		}

	}

	public void convert(String huff) {
		byte var = 0;

		for (int i = 0, j = 0; i < huff.length(); i++, j++) {

			if ((huff.charAt(i) + "").equals("1")) {
				var = (byte) (var | (1 << (7 - j % 8)));
				
			}

			if (j == 7) {
				System.out.println("var= "+(char)var);
				out[count] = var;
				count++;
				var = 0;
				j = -1;

			}
		}

	}

	public void convertChar(String number) {
		
           for(int i=0;i<number.length();i++) {
        	    out[count]=(byte) number.charAt(i);
        	    count++;
	}}
}
