package project_1;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.time.LocalTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;





public class Driver extends Application {
	
	
	LinkedList listCourses = new LinkedList();
	
	LocalTime date1 = null, date2 = null;

	LinkedList listStudents = new LinkedList();
	
	RadixSort Rad=new RadixSort(listCourses);
	
public void start(Stage stage) throws Exception {
		

		// Create panes and set the properties
		GridPane gPane = new GridPane();
		BorderPane p=new BorderPane();
		gPane.setAlignment(Pos.TOP_CENTER);
        p.setCenter(gPane);	
		gPane.setVgap(15);
		gPane.setHgap(15);

		// Create a Title label and set its properties
		Label Title = new Label("BirZeit University Courses View");
		Title.setFont(Font.font("Andalus", FontWeight.BOLD, 30));
		Title.setPadding(new Insets(20,20,20,90));
		p.setTop(Title);
		

		// Create a Course File label and set its properties
		Label CFN = new Label("Courses File");
		CFN.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
		TextField cfn = new TextField();// Create a field to write in
		cfn.setPromptText("Choose the file from browser");
		cfn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
		cfn.setFocusTraversable(false);

		// create a File chooser
		FileChooser fileCourse = new FileChooser();
		
		// create a Browser button 
		Button buttonCourse = new Button("Browser");

		// Create a student file label and set its properties
		Label SFN = new Label("Students File");
		SFN.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
		
		// Create a field to write in
		TextField sfn = new TextField();
		sfn.setPromptText("Choose the file from browser");
		sfn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
		sfn.setFocusTraversable(false);
        
		// create a File chooser
		FileChooser fileStudent = new FileChooser();
		
		// create a Browser button 
		Button buttonStudent = new Button("Browser");

		// Create an Horizontal Box with the specified vertical gap between nodes
		HBox HBCFN = new HBox();
		HBCFN.getChildren().addAll(CFN, cfn, buttonCourse);
		HBCFN.setSpacing(5);
		gPane.add(HBCFN, 0, 3);// Place nodes in the pane

		// Create an Horizontal Box with the specified vertical gap between nodes
		HBox HBSFN = new HBox();
		HBSFN.getChildren().addAll(SFN, sfn, buttonStudent);
		HBSFN.setSpacing(5);
		gPane.add(HBSFN, 0, 4);// Place nodes in the pane

		// create a button 
		Button Finish = new Button("Next");
		gPane.add(Finish, 1, 8);// Place nodes in the pane
		
        //buttonCourse clicked do the instruction
		buttonCourse.setOnAction(e -> {
			
			//Create a File that selected by user
			File selectedFile = fileCourse.showOpenDialog(stage);
			if (selectedFile != null) {
				cfn.setText(selectedFile.getAbsolutePath());
			}

		});

		//filtering the types of files to make them selectable in the dialog to select files
		fileCourse.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.txt")

		);

		//buttonStudent clicked do the instruction
		buttonStudent.setOnAction(e -> {
			
			//Create a File that selected by user
			File selectedFile = fileStudent.showOpenDialog(stage);
			if (selectedFile != null) {

				sfn.setText(selectedFile.getAbsolutePath());
			}

		});
		
		//filtering the types of files to make them selectable in the dialog to select files
		fileStudent.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.txt")

		);

		
		Scene scene = new Scene(p, 600, 400); // Create a scene
		
		
		
		

	// Create panes and set the properties
	GridPane gPane2=new GridPane();	
	BorderPane p2=new BorderPane();
	gPane2.setAlignment(Pos.TOP_CENTER);
    p2.setCenter(gPane2);
	gPane2.setVgap(20);
	gPane2.setHgap(20);
	
	// Create a Title label and set its properties
	Label Title2 = new Label("REPORT'S");
	Title2.setFont(Font.font("Andalus", FontWeight.BOLD, 30));
	Title2.setPadding(new Insets(25,0,0,180));
	p2.setTop(Title2);
	
	// Create a label and set its properties
	Label RadixSort = new Label("Click To View All Students In Each Course");
	RadixSort.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
	
	// Create a button and set its properties
	Button radix = new Button("Students' Information");	
	radix.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
	
	// Create a vertical box with the specified vertical gap between nodes
	VBox VBRadix = new VBox();
	VBRadix.getChildren().addAll(RadixSort, radix);
	VBRadix.setSpacing(12);
	gPane2.add(VBRadix, 0, 3);
	
	// Create a label and set its properties
	Label StudentsName = new Label("Click To View All Students In All Courses");
	StudentsName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
		
	// Create a button and set its properties
	Button studentsName = new Button("All Students");	
	studentsName.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
	
	// Create a vertical box with the specified vertical gap between nodes
	VBox VBstudentsName = new VBox();
	VBstudentsName.getChildren().addAll(StudentsName, studentsName);
	VBstudentsName.setSpacing(12);
	gPane2.add(VBstudentsName, 0, 4);
	
	// Create a label and set its properties
	Label SpecificStudent = new Label("Student's ID");
	SpecificStudent.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
	
	// Create a field to write in and set its properties
	TextField specificstudent = new TextField();// Create a field to write in
	specificstudent.setPrefWidth(300);
	specificstudent.setPromptText("Enter The Student's ID To View His Schedual");
	specificstudent.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
	specificstudent.setFocusTraversable(false);
	
	// Create a button and set its properties
	Button OK = new Button("OK");	
	OK.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
	
	// Create a horizontal box with the specified vertical gap between nodes
	HBox HBspecificstudent = new HBox();
	HBspecificstudent.getChildren().addAll(specificstudent,OK);
	HBspecificstudent.setSpacing(12);

	// Create a vertical box with the specified vertical gap between nodes
	VBox VBspecific = new VBox();
	VBspecific.getChildren().addAll(SpecificStudent,HBspecificstudent);
	VBspecific.setSpacing(12);
	gPane2.add(VBspecific, 0, 5);
	
	// Create a label and set its properties
	Label CourseInfo = new Label("Click To View A Course list");
	CourseInfo.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
		
	// Create a button and set its properties
	Button courseInfo = new Button("Courses' Information");	
	courseInfo.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
	
	// Create a vertical box with the specified vertical gap between nodes
	VBox VBCourseInfo = new VBox();
	VBCourseInfo.getChildren().addAll(CourseInfo, courseInfo);
	VBCourseInfo.setSpacing(12);
	gPane2.add(VBCourseInfo, 0, 6);
	
	
		
		
	Scene scene2 = new Scene(p2, 500, 500); // Create a scene	

     
	//Finish clicked do the instruction
	Finish.setOnAction(e -> {
		try {
			if(cfn.getText().isEmpty()||sfn.getText().isEmpty())
				 throw new IllegalArgumentException();// To throw Exception if there
			//read from file the courses' information and add to linkedlist
			try {
				
				File course = new File(cfn.getText());
				Scanner Read = new Scanner(course);
				while (Read.hasNextLine()) {

					String info = Read.nextLine();
					
					if (!info.isEmpty()) {
						
						
					String[] array = info.split("#");

					date1 = LocalTime.parse(array[3]);

					date2 = LocalTime.parse(array[4]);

					listCourses.AddLast(new Course(array[0], array[1], Integer.parseInt(array[2]), date1, date2,
							Integer.parseInt(array[5])));
					}
				}

				Read.close();
			} catch (FileNotFoundException e1) {
				System.out.println("An error occurred.");
				e1.printStackTrace();
			}
			

			//read from file the students' information and add to linkedlist
			try {
				File student = new File(sfn.getText());
				Scanner Read1 = new Scanner(student);
			
				while (Read1.hasNextLine()) {
					
					String info = Read1.nextLine();
					if (!info.isEmpty()) {
					String[] array1 = info.split("#");
	                
					LinkedList arr = new LinkedList();
	                
					int id =(Integer.parseInt(array1[1]))/ 10000;
					
					
					for (int i = 2; i < array1.length; i++) {
						int flag = 0;
						Node temp=listCourses.first;
						//loop to find if the course is offered by the department and add to course list in student's data
						while (temp!=null) {
							if ((Integer) array1[i].trim().compareToIgnoreCase(((Course)temp.data).getId())== 0) {
								arr.AddLast((Course)temp.data);
								flag = 1;
								break;
							}
							temp=temp.next;

						}
						//if flag =1 the course is offered by the department then check the other conditions
						if(flag==1) {
							int year = ((Course)temp.data).getYear();
							if((id<=year)&&isMatching(arr)) 
								 arr.last.status=true;
								
							
							else
								 arr.last.status=false;
						}
						//the course is not offered by the department
						else if (flag == 0) {
							arr.AddLast(array1[i]);
							arr.last.status=false;
							
						}
					}
					
				
					listStudents.AddLast(new Students(array1[0], Integer.parseInt(array1[1]),arr));
					}
				}
				Read1.close();
			} catch (FileNotFoundException e1) {
				System.out.println("An error occurred.");
				e1.printStackTrace();
			}
			
			//add students to courses
			Node temp=listCourses.first;
			//loop to find the students that registered in specific course 
			while(temp!=null) {
				Node std=listStudents.first;
				while(std!=null) {
					Node curr=((Students)std.data).getList().first;
				    while (curr!=null) {
				    	if((curr.status==true)&&(((Course)curr.data).getId().trim().compareToIgnoreCase(((Course)temp.data).getId()) == 0)) {
				    		if(temp.count_num<((Course)temp.data).getMax()&&curr.count_num<5) 
				    			 {
				    			 ((Course)temp.data).getList().addStd(std.data);
				    			 temp.count_num++;
				    			 std.count_num++;
				    			
				    			 
				    		}else 
				    			 curr.status=false;
				    		}
				    	    
				    		curr=curr.next;	
				    			
				    	  }
				    std=std.next;		
				}
				temp=temp.next;
			}
			
		
		stage.setScene(scene2);
		}catch (Exception E) {
			JOptionPane.showMessageDialog(null, E);// Handling with Exception via show special message
		}
		

	});
	
	//button radix clicked do the instruction 
	radix.setOnAction(e ->	{
		try {
			/*print in file The students’ list that contains all students’ information (ordered based on student’s ID) in
			each course*/
			Rad.Files();
			
			
			JOptionPane.showMessageDialog(null, "The File Is Ready",null, JOptionPane.PLAIN_MESSAGE);
		} catch (FileNotFoundException e1) {

			JOptionPane.showMessageDialog(null, e1);
		}
			
	});
	
	//button studentsName clicked do the instruction 
	studentsName.setOnAction(e ->	{
		try {
			//print in file A list of all students in all courses (ordered based on student’s name). 
			
			AllStudents(listStudents);
			
			JOptionPane.showMessageDialog(null, "The File Is Ready",null, JOptionPane.PLAIN_MESSAGE);
		} catch (FileNotFoundException e1) {

			JOptionPane.showMessageDialog(null, e1);
		}
			
	});
	
	//button Ok clicked do the instruction
	OK.setOnAction(e->{
		
		
		try {
				int number=Integer.parseInt(specificstudent.getText());
				//print in file The schedule of a specific student in that semester
				SpecificStudent(listStudents,number);
			
			
			} catch (Exception e1) {
			
			JOptionPane.showMessageDialog(null, e1+"\nEnter Student's ID Again");
		}
		
		
		
	});
	
	
		
	//button courseInfo clicked do the instruction
	courseInfo.setOnAction(e ->	{
		try {
			/*print in file A course list that contains, the course name, id and number of registered students in that
			course (ordered based on the year)*/
			CoursesInfo(listCourses);
			
			JOptionPane.showMessageDialog(null, "The File Is Ready",null, JOptionPane.PLAIN_MESSAGE);
		} catch (FileNotFoundException e1) {

			JOptionPane.showMessageDialog(null, e1);
		}
			
	});
		
		
		stage.setTitle("Course View"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage

	}
	
	
	

	public static void main(String[] args) {
		
		
		
		launch(args);
	}
	
//Method to print in file  The schedule of a specific student in that semester	
public static void SpecificStudent(LinkedList List,int num) throws FileNotFoundException {
		
		Node std = List.first;
		int flag=0;
		
		//loop to find if the students exist or not
		while(std!=null) {
			if(((Students)std.data).getId()==num) {
				flag=1;
				break;}
			std=std.next;		
		}
		
		if(flag==0)
			JOptionPane.showMessageDialog(null,"The Student Is Not Found, Try Again");
		
		else {
            
			
			File outfile = new File("Student's info.txt");
			PrintWriter info = new PrintWriter(outfile);
			
			Node temp = ((Students)std.data).getList().first;
			info.println("Student's Name: " + ((Students) std.data).getName() + "\t\t\t\t Student's ID: "
					+ ((Students) std.data).getId() + "\n\n");
			
			if(std.count_num==0)
				info.println("No Courses");
			else{
				while (temp != null) {
			
				if (temp.status)
					info.println(" Course Name: " + ((Course) temp.data).getName() + "\tCourse ID: "
							+ ((Course) temp.data).getId() + "\tStart time: " + ((Course) temp.data).getStart()
							+ "\tFinish time: " + ((Course) temp.data).getFinish() + "\n");
				temp = temp.next;
			}
		}
			info.close();
			JOptionPane.showMessageDialog(null, "The File Is Ready",null, JOptionPane.PLAIN_MESSAGE);
			}
        
		
		
        
		
		

	}

	
    //Method to print in file the courses' information
	public static void CoursesInfo(LinkedList course) throws FileNotFoundException {
		File info = new File("Courses' info.txt");
		PrintWriter Courses = new PrintWriter(info);
		
		LinkedList cou = new LinkedList();
		Node temp=course.first;
		while(temp!=null) {
			cou.addCourse(temp.data);
			temp=temp.next;
		}
			

		

		Courses.println("Courss' Name\t\t\t  Courses' ID\t\t\t No.Of Registered Students\n\n ");
		 temp=course.first;
		 while(temp!=null)  {
			Courses.println("\n"+((Course)temp.data).getName() + "\t\t" + ((Course)temp.data).getId() + "\t\t" + temp.count_num);
			Courses.println("__________________________________________________________________________________");
            temp=temp.next;
		}
		Courses.close();

	}

	//Method to print in file All students in all courses
	public static void AllStudents(LinkedList list) throws FileNotFoundException {

		Node std = list.first;
		LinkedList students = new LinkedList();
		//loop to find if students register in one course at least or not
		while (std != null) {
			if (std.count_num > 0)
				students.addStd(std.data);
			std = std.next;
		}

		File file = new File("Students.txt");

		PrintWriter student = new PrintWriter(file);

		Node temp = students.first;

		student.println("Students Names\t\tStudents ID\n---------------------------------------");
		while (temp != null) {
			student.println(((Students) temp.data).getName() + "\t\t  " + ((Students) temp.data).getId());

			temp = temp.next;

		}
		student.print("\n\n");

		student.close();

	}

	//Method to make sure there is no conflicted in the student’s schedule
	public static boolean isMatching(LinkedList arr) {

		if (arr.count == 1)
			return true;

		Node curr = arr.first;
		Node course = arr.last;
	
		LocalTime start = ((Course) course.data).getStart();
		LocalTime finish = ((Course) course.data).getFinish();
      
		
		while (curr != null) {
			if (curr.status == true) {
			if (!((start.isAfter(((Course) curr.data).getFinish()))
					|| finish.isBefore((((Course) curr.data).getStart())))) 
		
				return false;
			}
			curr = curr.next;
		}

		return true;
	
	}

}
