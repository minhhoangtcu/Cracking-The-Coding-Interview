package ctcInterview.objectOrientedDesign;

import java.util.List;

/**
 * Imagine you have a call center with three levels of employees: respondent,
 * manager, and director. An incoming telephone call must be first allocated to
 * a respondent who is free. If the respondent cannot handle the call, he or she
 * must escalate the call to a manager. And, so on. Design the classes and data
 * structures for this problem. Implement a method dispatchCall() which assigns
 * a call to the first available employee.
 * 
 * @author minhhoang
 *
 */
public class OOD82_CallCenter {
	
}

class Center {
	
	// Singleton
	private static Center instance;
	private Center() { }
	
	public Center getInstance() {
		if (instance == null) instance = new Center();
		return instance;
	}
	
	// employees holds 3 lists of respondents, managers, and directors in index
	// 0, 1, 2 respectively
	private List<List<Employee>> employees;
	private List<Call> queue;
	
	public Employee dispatchCall(Call call) {
		// find the first available employee
		Employee handler = getAvailable();
		
		// assign the call to him
		if (handler != null) {
			// give to a handler
			return handler;
		} else {
			// response to caller, ...
			return null;
		}
	}
	
	public Employee getAvailable() { return null; };
	
	
	
}

// A Call prodivides communication between the Employee and the Caller
class Call {
	
	private Caller caller;
	private Employee handler; 
	
}

class Caller {
	
	private Call call;
	
	// methods to tell the caller stuff
	
}

abstract class Employee {
	
	private int rank;
	private Call call;
	
	protected Employee(int rank) { this.rank = rank; }
	
	// receive call
	
	// release call -> contact Center so it can put another person into the call line
	
}

class Respondent extends Employee {
	
	public Respondent() {
		super(0);
	}
	
}

class Manager extends Employee {
	
	public Manager() {
		super(1);
	}
	
}

class Director extends Employee {
	
	public Director() {
		super(2);
	}
	
}
