package project2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Math;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {

	Node[] TableArr;
	compress comp;
	public void start(Stage stage) throws Exception {
		// Create panes and set the properties
		GridPane gPane = new GridPane();
		BorderPane p = new BorderPane();
		gPane.setAlignment(Pos.TOP_CENTER);
		p.setCenter(gPane);
		gPane.setVgap(15);
		gPane.setHgap(15);

		// Create a Title label and set its properties
		Label Title = new Label("Compress And Decompress Files");
		Title.setFont(Font.font("Andalus", FontWeight.BOLD, 30));
		Title.setPadding(new Insets(20, 20, 20, 90));
		p.setTop(Title);

		// Create a Course File label and set its properties
		Label File = new Label("choose one of browsers");
		File.setFont(Font.font("Times New Roman", FontWeight.BOLD, 22));
		gPane.add(File, 0, 3);

		TextField file = new TextField();// Create a field to write in
		file.setPromptText("Choose file from browser");
		file.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
		file.setFocusTraversable(false);
		gPane.add(file, 0, 4);
		file.setMinHeight(60);

		// create a Browsers button
		Button buttonCompress = new Button("Compress");
		buttonCompress.setMinWidth(85);
		Button buttonDeCompress = new Button("DeCompress");
		buttonDeCompress.setMinWidth(85);

		// Create an Vertical Box with the specified vertical gap between nodes
		VBox VBButton = new VBox();
		VBButton.getChildren().addAll(buttonCompress, buttonDeCompress);
		VBButton.setSpacing(10);
		gPane.add(VBButton, 1, 4);// Place nodes in the pane

		// create button to shoe header
		Button Header = new Button("show Header");
		gPane.add(Header, 0, 7);// Place nodes in the pane
		Header.setMinWidth(200);
		Header.setMinHeight(40);

		// create table
		Button Table = new Button("show Table");
		gPane.add(Table, 0, 8);// Place nodes in the pane
		Table.setMinWidth(200);
		Table.setMinHeight(40);

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// create a File chooser to compressFile
		FileChooser filechooserComp = new FileChooser();

		// filtering the types of files
		filechooserComp.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*")

		);

		
		// buttonCompress clicked show the files
		buttonCompress.setOnAction(e -> {
			 comp= new compress();
			// Create a File that selected by user
			File selectedFile = filechooserComp.showOpenDialog(stage);
			if (selectedFile != null) {
				file.setText(selectedFile.getAbsolutePath());
			}

			try {
				if(file.getText().substring(file.getText().lastIndexOf('.'))==".huf")
					JOptionPane.showMessageDialog(null, "The file already compressed");
			
				else {
				byte[] bytes = new byte[1024];

				if (file.getText().isEmpty())
					throw new IllegalArgumentException();// To throw Exception if there

					// FileInputStream to read the file
				FileInputStream FIS = new FileInputStream(file.getText());

				BufferedInputStream BIS = new BufferedInputStream(FIS);
				int read;
				int[] arr = new int[256];

				// Read each byte and create array of ascii character contain the frequency
				while (BIS.available() > 0) {
					read = BIS.read(bytes);

					for (int i = 0; i < read; i++) {

						int ch = bytes[i];

						if (ch < 0) {

							int num = (256 - (-2 * ch)) + Math.abs(ch);

							ch = num;
						}

						arr[ch]++;

					}

				}

				FIS.close();
				BIS.close();

				// Huffman object
				Huffman huff = new Huffman();
				huff.huffmanCode(arr);
				TableArr = huff.getArr();

				// Compress the file
				
				comp.compressFile(file.getText(), huff.getArr(), huff.root, huff.Length);
				JOptionPane.showMessageDialog(null, "Compressed file is ready");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);// Handling with Exception via show special message

			}

		});

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// create a File chooser to Decompress file
		FileChooser filechooserDeComp = new FileChooser();

		// filtering the types of files
		filechooserDeComp.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.huf")

		);

		// buttonDecompress clicked show the files
		buttonDeCompress.setOnAction(e -> {
			// Create a File that selected by user
			File selectedFile = filechooserDeComp.showOpenDialog(stage);
			if (selectedFile != null) {
				file.setText(selectedFile.getAbsolutePath());
			}

			try {
				FileInputStream FIS = new FileInputStream(file.getText());
				BufferedInputStream BIS = new BufferedInputStream(FIS);
				
				
				Header header=new Header();
				
				header.readHeader(FIS,BIS);
				
				reader(BIS, header.namefile, header.diff, header.root);
				
				JOptionPane.showMessageDialog(null, "DeCompressed file is ready");

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);// Handling with Exception via show special message

			}

		});

		///////////////////////////////////////////////////////////////////////////////////////////////////////////

		// Table Button Click show table
		Table.setOnAction(e -> {
			try {
			showTable sT = new showTable(TableArr);}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);// Handling with Exception via show special message

			}
		});

		// Header Button Click show Header
		Header.setOnAction(e -> {
			try {
			BorderPane p2=new BorderPane();
			TextArea TA=new TextArea();
			p2.setCenter(TA);
			
			TA.setPadding(new Insets(8, 8, 8, 8));
			TA.setText("");
			TA.setText(comp.show);
			
			
			Stage newStage=new Stage();
			newStage.setTitle("Print Selected");
			
			Scene scene3=new Scene(p2,550,480);
			newStage.setScene(scene3);
			
			newStage.show();
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1);// Handling with Exception via show special message

			}
			
		});

		Scene scene = new Scene(p, 600, 400); // Create a scene
		stage.setTitle("Compress and DeCompress Files"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
	}

	public static void main(String[] args) throws FileNotFoundException {
		launch(args);

	}
	
	//read the compress file to decompress it
	public void reader(BufferedInputStream BIS, String ext, int len, Node root) throws IOException {
		try {
			
			FileOutputStream file = new FileOutputStream(len+"out."+ext);
			int read;
			byte[] bytes = new byte[30];
			byte[] bits = new byte[30];
			

			//the total bits of huffman code will read
			int nums = ((BIS.available() * 8) - len);
			Node temp = root;
			int count = 0;
			int countBits = 0;
			int length=0;
			int j=0;
			
			//read the compress data
			while (BIS.available() > 0) {
				read = BIS.read(bytes);
				String str = "";
				for (int i = 0; i < read; i++) {
					String conv = "";
					int ch = bytes[i];
					
					//convert the byte to binary string
					conv= String.format("%8s", Integer.toBinaryString(ch & 0xFF)).replace(' ', '0');
					str += conv;
					}
				
				length=str.length();
				countBits+=str.length();//total bits in file
				
				//if the bits in file greater than the bits should read it 
				if(countBits>nums)
					length=length-len;

				
				
	       //read the characters from the tree
			for (int i = 0; i < length; i++) {
						if (str.charAt(i) == '0')
							temp = temp.left;
						else
							temp = temp.Right;
						if (temp.left == null && temp.Right == null) {

							bits[j] = (byte) temp.index;
                            
							count++;
							j++;
							if (count == 30) {
								
								file.write(bits);
								
								count = 0;
								j =0;
								
							}
							temp = root;
							
						}

					}
		 
			}	

			
			if(count>0) {
					
				file.write(bits, 0, count);
				
			}

			file.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);// Handling with Exception via show special message
		}
	}

}
