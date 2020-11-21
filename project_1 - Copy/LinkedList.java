package project_1;


public class LinkedList {
	//instance variables
	int count;
	Node first, last;
	

	// Method to insert a new node at the beginning
	public void addFirst(Object Element) {
		// Create a new node 
		Node temp = new Node(); 
		//fill the data(Element)in the new node
		temp.data = Element;
		// linked the previous first node with the new node 
		temp.next = first;
		//first node will refer to the new node
		first =  temp;
		//if the list is empty the last node also will refer to the new node
		if(last==null)
			last=temp;
		count++;
	}
	

	// Method to insert a new node at end
	public void AddLast(Object Element) {
		//if the list is empty will call add first method
		if (count == 0) {
			addFirst(Element);
		}
        
		
		else //if is not empty
			{
			//Create a new node then fill it with data 
			Node temp = new Node();
			temp.data = Element;
			//linked the last node with the new node 
			last.next = temp;
			//last node will refer to the new node
			last = temp;
			count++;
		}
	}
	
	//method to remove first node 
	public void removeFirst() {
		//if the list is empty print a message
		if(count==0)
			System.out.println("is Empty");
		
		//if the list contains more than one node, first node will refer to the next of the previous first node 
		else if(first!=last)
			first=first.next;
		//if the list contains just one node, the first and last node will refer to null
		else 
			first=last=null;
		count --;
		
	}
	

	
	//method to remove last node
	public void removeLast() {
		//if the list is empty print a message
		if(count==0)
			System.out.println("is Empty");
		//if the list contains just one node, call removeFirst method
		else if(count==1)
			removeFirst();
		
		else //if the list contains more than one node
		{
			//create new node refer to the first node 
			Node curr=first;
			
			//loop to let new node refer to the node that before the last node 
			for(int i=0 ;i<count-2;i++)
				curr=curr.next;
			//the next of new node refer to null so the last node will remove
			curr.next=null;
			//last node refer to the new node 
			last=curr;
		    count --;
		}
	}
	
	
	

	//Method to insert a new node in order based on the name field
	public void addStd(Object std) {
		//create a new node 
		Node temp = new Node();
		//if the list is empty the new node will insert in first
		if(count==0) {
			temp.data =std;
			first=last=temp;
			count++;
			
		}
		else {
             //if the name in the first node greater than the index's name the node will insert at beginning
			if (((Students) first.data).getName().compareToIgnoreCase(((Students)std).getName()) > 0) 
				addFirst(std);
			
			 //if the name in the first node less than the index's name the node will insert at end
			else if (((Students) last.data).getName().compareToIgnoreCase(((Students)std).getName()) < 0) 
				AddLast(std);
				

			else {
				
				Node pre = first;
				Node curr = pre.next;
                
				//loop to find node contains name that greater than index's name to insert the node before it
				while (((Students) curr.data).getName().compareToIgnoreCase(((Students)std).getName()) < 0) {
					pre = pre.next;
					curr = curr.next;
				}
				temp.data = std;
				temp.next = curr;
				pre.next = temp;
				count++;
				
			}

		}
	}
	
	//Method to insert a new node ascending based on student's ID
	public void addCourse(Object course) {
		//create a new node 
		Node temp = new Node();
		//if the list is empty the new node will insert in first
		if(count==0) {
			temp.data =course;
			first=last=temp;
			count++;
			
		}
		else {
			//if the ID in the first node greater than the index's ID the node will insert at beginning
			if (((Course) first.data).getYear()>((Course)course).getYear()) 
				addFirst(course);
			
			 //if the ID in the first node less than the index's ID the node will insert at end
			else if (((Course) last.data).getYear()<=((Course)course).getYear())  
				AddLast(course);
				

			else {
				Node pre = first;
				Node curr = pre.next;
				
				//loop to find node contains ID that greater than index's Id to insert the node before it
				while (((Course) curr.data).getYear()<((Course)course).getYear()) {
					pre = pre.next;
					curr = curr.next;
				}
				temp.data = course;
				temp.next = curr;
				pre.next = temp;
				count++;
				
			}

		}
	}
}
