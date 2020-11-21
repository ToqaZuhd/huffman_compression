package project2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class compress {
	byte[] out = new byte[1040];
	int count = 0;

	public void compressFile(String string, Node[] arr, Node root,int length) {

		try {
            
			FileOutputStream FOUT = new FileOutputStream("out.huf");
			

			byte[] bytes = new byte[1040];

			// FileInputStream to read the file
			FileInputStream FIS = new FileInputStream(string);
			BufferedInputStream BIS = new BufferedInputStream(FIS);
			int read;
			String huff = "";
            
			Header header = new Header();
			String str=header.extension(string);
			
			int number=str.length();
			FOUT.write(number);
			
			convertChar(str);
			FOUT.write(out, 0, count);
			count=0;
			header.postOrderTree(root);
			int len=header.convert();
			convert(header.numbers);
			
			FOUT.write(count);
			FOUT.write(len);
			FOUT.write(out, 0, count);
			
			count = 0;
			convertChar(header.character);
			FOUT.write(count-1);
			FOUT.write(out,0,count);
			System.out.println("len= "+length);
			int diff=8-(length%8);
			FOUT.write(diff);
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

						FOUT.write(out, 0, count);
						count = 0;
					}

				}

			}

			if (count > 0) {
				FOUT.write(out, 0, count);
				count = 0;
			}

			System.out.println("huff= "+huff);
			
			if (huff.length() > 0) {

				if (huff.length() % 8 > 0) {

					int append =8-(huff.length() % 8) ;
					for (int i = 0; i < append; i++)
						huff += '0';
				}

				convert(huff);
				FOUT.write(out, 0, count);
				
			}
			System.out.println("huff= "+huff);
			header.readHeader("out.huf");
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
