package project2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class compress {
	byte[] out = new byte[1040];
	int count = 0;
	String show="";

	//method compress file
	public void compressFile(String string, Node[] arr, Node root,int length) {

		try {
            show="";
			String stri=string.substring(0, string.lastIndexOf('.'));
			String stri1=string.substring(string.lastIndexOf('.'));
			
			//write the content of compress data in
			FileOutputStream FOUT = new FileOutputStream(stri+".huf");
			
			byte[] bytes = new byte[1040];

			// FileInputStream to read the file
			FileInputStream FIS = new FileInputStream(string);
			BufferedInputStream BIS = new BufferedInputStream(FIS);
			
			
			int read;
			String huff = "";
            
			//header will be in the beginning of compressed file to use it in decompress
			Header header = new Header();
				
			//the length of name original file 
			int number=stri1.length();
			show +="\nSize of name of original file: "+number+" \n Name of original File: ";
			//first thing will be in header the size of extension 
			FOUT.write(number);
			
			//convert the name of file to byte 
			convertChar(stri1);
			
			//second thing in header is the name of original file which convert 
			FOUT.write(out, 0, count);
			for(int i=0;i<count;i++) {
				
				show+=out[i]+" ";}
			count=0;
			
			//root represents huffman tree and here read the tree in post order way
			header.postOrderTree(root);
			
			//len is the length of numbers '0' '1' in post order tree 
			int len=header.convert();
			
			//convert the string of numbers '0' '1' to byte
			convert(header.numbers);
			
			//third thing in header is the size of number in byte which is part of tree
			FOUT.write(count);
			show+="\n\n Size of number in tree: "+count;
			
			//fourth thing is exact size of number (numbers of bits)
			FOUT.write(len);
			show+="\n Number of zero will not read in the end of the tree: "+count+"\n Number: ";
			//fifth thing is the numbers '0' '1' 
			FOUT.write(out, 0, count);	
			for(int i=0;i<count;i++)
				show+=out[i]+" ";
			
			count = 0;
			
			///convert the string of character to byte part of tree
			convertChar(header.character);
			
			//sixth thing is the size of characters
			FOUT.write(count-1);
			show+="\n\n Size of character in tree: "+count+"\n Character in tree: ";
			//seventh thing is the character
			FOUT.write(out,0,count);
			for(int i=0;i<count;i++)
				show+=out[i]+" ";
			
			show+="\n\n (the tree is: "+header.test+" )";
			//numbers of zero will add to numbers string to convert correctly to byte
			int diff=8-(length%8);
			
			//last thing in header is the number of zero and will use it to avoid read 
			FOUT.write(diff);
			show+="\n\n Size of zero will not read in the end of data: "+diff;
            count=0;
            
            //read the original file
			while (BIS.available() > 0) {
				read = BIS.read(bytes);

				//convert the character to number
				for (int i = 0; i < read; i++) {

					int ch = bytes[i];
					if (ch < 0) {

						int num = (256 - (-2 * ch)) + Math.abs(ch);
						ch = num;
					}

					//string huff will be the all hufman code of each character
					huff += arr[ch].huffman;

					//if the length of huff greater or equal 80 the first 80 charater will convert to bytes 	   
					if (huff.length() >= 80) {
						convert(huff.substring(0, 80));

				 //if huff was greater than 80 new huff will be the remainder character after cut first 80 character
						huff = huff.substring(80);

						if (huff.length() == 0)
							huff = "";

					}

					//if the bytes become 1040 write the buffer in file
					if (count == 1040) {

						FOUT.write(out, 0, count);
						count = 0;
					}

				}

			}

			//write the buffer wasn't write in file if exist
			if (count > 0) {
				FOUT.write(out, 0, count);
				count = 0;
			}

			
			//if still character wasn't converted to byte check if it can be byte -8bits- the converted it 
			if (huff.length() > 0) {

				if (huff.length() % 8 > 0) {

					int append =8-(huff.length() % 8) ;
					for (int i = 0; i < append; i++)
						huff += '0';
				}

				convert(huff);
				FOUT.write(out, 0, count);
				
			}
			
			
			FIS.close();
			BIS.close();
			FOUT.close();

		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, e1);// Handling with Exception via show special message
		}

	}
	
//convert the string -each 8 bits- to bytes
	public void convert(String huff) {
		byte var = 0;
		
		//loop to end of sent string
		for (int i = 0, j = 0; i < huff.length(); i++, j++) {

			//if the character is 1 make shift left 
			//string is 01010010, var=0  --> 1<<6 (01000000 | 00000000)--> 0100000 .... 
			if ((huff.charAt(i) + "").equals("1")) {
				var = (byte) (var | (1 << (7 - j % 8)));
				
			}
			
			//if the bits was 8 add to byte array
			if (j == 7) {
				
				out[count] = var;
				
				count++;
				var = 0;
				j = -1;

			}
		}

	}
	
    //convert character to byte to write in file
	public void convertChar(String number) {
		
           for(int i=0;i<number.length();i++) {
        	    out[count]=(byte) number.charAt(i);

        	    count++;
	}}
	
	
	
}
