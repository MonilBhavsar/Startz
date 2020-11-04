package startz.fundmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import startz.fundmanagement.model.Task;

public class TaskDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/startz?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Monilbhavsar";
	 
	private static final String INSERT_TASK = "INSERT INTO tasks" + "(name,details,due,status) VALUES" + "(?,?,?,0);";
	private static final String SELECT_ALL_TASK = "SELECT * FROM tasks ORDER BY due";
	private static final String DELETE_TASK = "DELETE FROM tasks WHERE id=?;";
	private static final String SELECT_TASK_ID = "SELECT * FROM tasks WHERE id=?;";
	private static final String UPDATE_TASK = "UPDATE tasks SET name=?,details=?,due=?,status=? WHERE id=?;";
	private static final String PENDING_TASK = "SELECT COUNT(id) as pt FROM tasks WHERE status = 0;";

	
	public Connection getConnection() {
		Connection con =null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void addtask(Task task) throws SQLException {

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_TASK);
		ps.setString(1, task.getName());
		ps.setString(2, task.getDetails());
		ps.setString(3, task.getDue());
		
		ps.executeUpdate();
		
	}
	
	public boolean updatetask(Task task) throws SQLException {

		boolean rowsupdated; 
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_TASK);
		ps.setString(1, task.getName());
		ps.setString(2, task.getDetails());
		ps.setString(3, task.getDue());
		ps.setInt(4, task.getStatus());
		ps.setInt(5, task.getId());
		
		rowsupdated = ps.executeUpdate() > 0;
		
		return rowsupdated;
		
	}
	
	public Task selecttask(int id) throws SQLException {

		Task t  = null; 
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_TASK_ID);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			String name = rs.getString("name");
			String details = rs.getString("details");
			String due = rs.getString("due");
			int status = rs.getInt("status");
			
			t = new Task(id,name,details,due,status);
		}
		
		return t;
		
	}
	
	public List<Task> selectAlltask() throws SQLException {

		List<Task> tasks = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_TASK);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String details = rs.getString("details");
			String due = rs.getString("due");
			int status = rs.getInt("status");
			
			tasks.add(new Task(id,name,details,due,status));
		    
		    //System.out.println(tasks);
		}
		
		return tasks;
		
	}
	
	public boolean deletetask(int id) throws SQLException {

		boolean rowsdeleted; 
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_TASK);
		ps.setInt(1, id);
		
		
		rowsdeleted = ps.executeUpdate() > 0;
		
		return rowsdeleted;
		
	}
	
	public int getPendingTasks() throws SQLException {
		int pendingtasks=0;
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(PENDING_TASK);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			pendingtasks = rs.getInt("pt");
		}
	
		return pendingtasks;
			
	}
}
