package project2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Math;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {

	public void start(Stage stage) throws Exception {
		// Create panes and set the properties
		GridPane gPane = new GridPane();
		BorderPane p = new BorderPane();
		gPane.setAlignment(Pos.TOP_CENTER);
		p.setCenter(gPane);
		gPane.setVgap(15);
		gPane.setHgap(15);

		// Create a Title label and set its properties
		Label Title = new Label("BirZeit University Courses View");
		Title.setFont(Font.font("Andalus", FontWeight.BOLD, 30));
		Title.setPadding(new Insets(20, 20, 20, 90));
		p.setTop(Title);

		// Create a Course File label and set its properties
		Label File = new Label("File");
		File.setFont(Font.font("Times New Roman", FontWeight.BOLD, 22));
		TextField file = new TextField();// Create a field to write in
		file.setPromptText("Choose the file from browser");
		file.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
		file.setFocusTraversable(false);

		// create a File chooser
		FileChooser filechooser = new FileChooser();

		// create a Browser button
		Button buttonBrowse = new Button("Browser");

		// Create an Horizontal Box with the specified vertical gap between nodes
		HBox HBCFN = new HBox();
		HBCFN.getChildren().addAll(File, file, buttonBrowse);
		HBCFN.setSpacing(5);
		gPane.add(HBCFN, 0, 3);// Place nodes in the pane

		// create a button
		Button Next = new Button("Next");
		gPane.add(Next, 1, 8);// Place nodes in the pane

		// buttonCourse clicked do the instruction
		buttonBrowse.setOnAction(e -> {

			// Create a File that selected by user
			File selectedFile = filechooser.showOpenDialog(stage);
			if (selectedFile != null) {
				file.setText(selectedFile.getAbsolutePath());
			}

		});

		// filtering the types of files to make them selectable in the dialog to select
		// files
		filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*")

		);

		Next.setOnAction(e -> {

			try {

				byte[] bytes = new byte[1024];

				if (file.getText().isEmpty())
					throw new IllegalArgumentException();// To throw Exception if there

				// FileInputStream to read the file
				FileInputStream FIS = new FileInputStream(file.getText());

				
				BufferedInputStream BIS = new BufferedInputStream(FIS);
				int read ;
				int[] arr = new int[256];
				
				
					
				while (BIS.available()>0) {
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
				

				
				Huffman huff=new Huffman();
		        
				
				huff.huffmanCode(arr);
				
				compress comp=new compress();
				comp.compressFile(file.getText(),huff.getArr(),huff.root);
				

			} catch (IOException e1) {
				System.out.println("An error occurred.");
				e1.printStackTrace();
			}

		});

		Scene scene = new Scene(p, 600, 400); // Create a scene
		stage.setTitle("Course View"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
	}

	public static void main(String[] args) throws FileNotFoundException {
		launch(args);

	}

	
}
