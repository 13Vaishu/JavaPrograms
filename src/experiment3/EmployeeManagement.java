package experiment3;

//Parent Class
class Employee {
	int empId;
	String name;
	double salary;

//Constructor
	Employee(int empId, String name, double salary) {
		this.empId = empId;
		this.name = name;
		this.salary = salary;
	}

	// Method to calculate salary (can be overridden)
	double calculateSalary() {
		return salary;
	}

	// Method Overloading
	void displayDetails() {
		System.out.println("ID: " + empId);
		System.out.println("Name: " + name);
		System.out.println("Salary: " + salary);
	}

	void displayDetails(String department) {// Overloaded method
		displayDetails();
		System.out.println("Department: " + department);
	}

}

//Child Class 1
class Manager extends Employee {
	double bonus;

	Manager(int empId, String name, double salary, double bonus) {
		super(empId, name, salary);
		this.bonus = bonus;
	}

//Method Overriding
	@Override

	double calculateSalary() {
		return salary + bonus;
	}
}

//Child Class2
class Developer extends Employee {
	double overtimePay;

	Developer(int empId, String name, double salary, double overtimePay) {
		super(empId, name, salary);
		this.overtimePay = overtimePay;
	}

	// Method Overriding
	@Override
	double calculateSalary() {
		return salary + overtimePay;
	}
}

//Mai Class
public class EmployeeManagement {
	public static void main(String[] args) {

		Manager m = new Manager(101, "Vaishnavi", 50000, 10000);
		Developer d = new Developer(102, "Priya", 40000, 5000);

		System.out.println("Manager Salary: " + m.calculateSalary());
		m.displayDetails("HR");

		System.out.println("\nDeveloper Salary: " + d.calculateSalary());
		d.displayDetails("IT");
	}
}
