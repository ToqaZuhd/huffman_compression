package project_1;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.PrintWriter;


public class RadixSort {

	private LinkedList list;

	LinkedList[] Sort = new LinkedList[10];
	LinkedList[] Sort2 = new LinkedList[10];
	int max = 0;

	public RadixSort() {

	}

	public RadixSort(LinkedList list) {
		this.list = list;

	}

	public int DigtsOfMax(Course num) {

		int count = 0, digit = 0;
		Node temp = num.getList().first;

		while (temp != null) {
			if (((Students) temp.data).getId() > max)
				max = ((Students) temp.data).getId();

			temp = temp.next;
		}

		while (max != 0)

		{
			digit = max % 10;
			count++;
			max = max / 10;
		}
		return count;
	}

	public LinkedList radixSort(Course val) {

		int num = DigtsOfMax(val);
		int n = 0;
		LinkedList student = new LinkedList();
		Node temp = val.getList().first;
		Node std;
		if (temp != null) {

			while (temp != null) {
				student.AddLast(temp.data);
				temp = temp.next;

			}

			for (int i = 0; i < Sort.length; i++) {
				Sort[i] = new LinkedList();
			}

			Node info = student.first;
			while (info != null) {
				n = ((Students) info.data).getId() % 10;

				Sort[n].AddLast(info.data);
				info = info.next;
				student.removeFirst();

			}

			for (int i = 0; i <= (num - 1); i++) {
				for (int k = 0; k < Sort.length; k++) {
					std = Sort[k].first;

					while (std != null) {

						n = (((Students) std.data).getId() / (int) Math.pow(10, i + 1)) % 10;

						if (n != k) {

							if (k > n)
								Sort[n].AddLast(std.data);
							else
								Sort[n].addFirst(std.data);
							std = std.next;
							Sort[k].removeFirst();

						} else
							std = std.next;
					}
				}
			}

			for (int j = 0; j < Sort.length; j++) {
				std = Sort[j].first;
				while (std != null) {

					student.AddLast(std.data);
					std = std.next;
				}
			}
			return student;

		}

		return val.getList();

	}

	public void Files() throws FileNotFoundException {
	
		File outfile = new File("student_names.txt");

		PrintWriter info = new PrintWriter(outfile);
		Node course=list.first;

		while(course!=null) {
			LinkedList L = radixSort((Course)course.data);
			Node temp = L.first;
			info.println(((Course)course.data).getId());
			if(temp==null)
				info.println("\nNo Students Registered In This Course ");
			while (temp != null) {
				info.println("\n"+((Students) temp.data).getName() + "\t\t" + ((Students) temp.data).getId());
                temp=temp.next;
			}
			info.println("_________________________________________________________");
			
			course=course.next;
		}
		
		info.close();
		
	}

}
