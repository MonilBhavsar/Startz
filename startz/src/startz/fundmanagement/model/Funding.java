package startz.fundmanagement.model;

public class Funding {
	private int id;
	private String name;
	private String details;
	private String date;
	private int type;
	private int amt;
	
	
	public Funding(int id, String name, String details, String date, int type, int amt) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.date = date;
		this.type = type;
		this.amt = amt;
	}
	
	
	public Funding(String name, String details, String date, int type, int amt) {
		super();
		this.name = name;
		this.details = details;
		this.date = date;
		this.type = type;
		this.amt = amt;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	
	
	

	
}
