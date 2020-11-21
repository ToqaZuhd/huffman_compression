package project_1;


// Class contains student's information
public class Students {
	private String name;
	private int id;
	private LinkedList list;
	
	// Create a construct with argument
	public Students(String name,int id,LinkedList list) {
		this.name=name;
		this.id=id;
		this.list=list;
		
		
	}
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public LinkedList getList() {
		return list;
	}


	public void setList(LinkedList list) {
		this.list = list;
	}

	
}
