package startz.fundmanagement.model;

public class Task {

	private int id;
	private String name;
	private String details;
	private String due;
	private int status;
	
	
	
	public Task(String name, String details, String due, int status) {
		super();
		this.name = name;
		this.details = details;
		this.due = due;
		this.status = status;
	}

	public Task(int id, String name, String details, String due, int status) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.due = due;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getDue() {
		return due;
	}
	public void setDue(String due) {
		this.due = due;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
