package project_1;

import java.time.LocalTime;



// Class contains Course's information
public class Course {
	
	private String name;
	private String id;
	private int year;
	private LocalTime start;
	private LocalTime finish;
	private LinkedList list = new LinkedList();
	private int max;
	public int count=0;
	

	// Create a construct with argument
	public Course(String name,String id,int year,LocalTime start,LocalTime finish,int max) {
		this.name=name;
		this.id=id;
		this.year=year;
		this.start=start;
		this.finish=finish;
		this.max=max;
	}
	
	
	public Course(String id) {
		
		this.id=id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getFinish() {
		return finish;
	}

	public void setFinish(LocalTime finish) {
		this.finish = finish;
	}
	
	public LinkedList getList() {
		return list;
	}


	public void setList(LinkedList list) {
		this.list = list;
	}


	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	
}
