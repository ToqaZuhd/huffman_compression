package project2;
import java.util.ArrayList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class showTable {
	// defining the required attributes
	
	Node[] heap;
	

	
	// constructor for the Hash table
	public showTable(Node [] heap) {
		this.heap = heap;
		showHash();
	}


	
	// displaying the formatted table
		private void showHash() {

			// creating the table
			TableView<Node> table = new TableView<Node>();

			// Determining its columns
			TableColumn<Node, Character> character = new TableColumn<Node, Character>("Character:");
			TableColumn<Node, Integer> chara = new TableColumn<Node, Integer>("Character Val:");
			TableColumn<Node, String> Huffcode = new TableColumn<Node, String>("Huffman Code:");
			TableColumn<Node, Integer> Length = new TableColumn<Node, Integer>("Length:");
			TableColumn<Node, Integer> freq = new TableColumn<Node, Integer>("Frequncy:");

			
			Stage stage = new Stage();
			VBox pane = new VBox();
			pane.setAlignment(Pos.CENTER);

			Label title = new Label("Contents of the table");
			title.setTextFill(Color.GOLD);
			title.setFont(Font.font("Andalus", FontWeight.BOLD, FontPosture.ITALIC, 30));

			character.setCellValueFactory(new PropertyValueFactory<Node, Character>("index"));
			character.setMinWidth(80);
			
			chara.setCellValueFactory(new PropertyValueFactory<Node,Integer>("value"));
			chara.setMinWidth(80);
			


			Huffcode.setCellValueFactory(new PropertyValueFactory<Node, String>("huffman"));
			Huffcode.setMinWidth(350);
		
			Length.setCellValueFactory(new PropertyValueFactory<Node, Integer>("length"));
			Length.setMinWidth(80);

			freq.setCellValueFactory(new PropertyValueFactory<Node, Integer>("freq_char"));
			freq.setMinWidth(80);


			table.setItems(get(heap));

			table.getColumns().addAll(chara,character, Huffcode, Length,freq);

			
			

			pane.getChildren().addAll(title, table);

			Scene s = new Scene(pane, 600, 580);
			
			stage.setScene(s);

			stage.show();
		}
		
		// getting the contents for the Hash table
		public ObservableList<Node> get(Node[] heap) {

			ObservableList<Node> array = FXCollections.observableArrayList();

			

			for(int i=0;i<heap.length;i++) {
				if(heap[i].freq_char>0)
				array.add(heap[i]);
				//System.out.println((int)heap[i].index+"  "+heap[i].huffman+"  "+heap[i].length+"  "+heap[i].freq_char);
			}

				

			

			return array;

		}
	
}
