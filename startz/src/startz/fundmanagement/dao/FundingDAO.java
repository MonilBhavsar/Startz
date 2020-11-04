package startz.fundmanagement.dao;

import java.sql.*;
import java.util.*;

import startz.fundmanagement.model.Funding;



public class FundingDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/startz?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Monilbhavsar";
	 
	private static final String INSERT_FUND = "INSERT INTO funding" + "(name,details,amt,type,date) VALUES" + "(?,?,?,?,?);";
	private static final String SELECT_ALL_FUND = "SELECT * FROM funding ORDER BY id DESC";
	private static final String DELETE_FUND = "DELETE FROM funding WHERE id=?;";
	private static final String SELECT_FUND_ID = "SELECT * FROM funding WHERE id=?;";
	private static final String UPDATE_FUND = "UPDATE funding SET name=?,details=?,amt=?,type=?,date=? WHERE id=?;";
	public static final String GRAPH_QUERY = "SELECT amt,date,type FROM funding";
	
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
	
	public void addfund(Funding funding) throws SQLException {

		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(INSERT_FUND);
		ps.setString(1, funding.getName());
		ps.setString(2, funding.getDetails());
		ps.setInt(3, funding.getAmt());
		ps.setInt(4, funding.getType());
		ps.setString(5, funding.getDate());
		
		ps.executeUpdate();
		
	}
	
	public boolean updatefund(Funding funding) throws SQLException {

		boolean rowsupdated; 
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_FUND);
		ps.setString(1, funding.getName());
		ps.setString(2, funding.getDetails());
		ps.setInt(3, funding.getAmt());
		ps.setInt(4, funding.getType());
		ps.setString(5, funding.getDate());
		ps.setInt(6, funding.getId());
		
		rowsupdated = ps.executeUpdate() > 0;
		
		return rowsupdated;
		
	}
	
	public Funding selectFund(int id) throws SQLException {

		Funding f  = null; 
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_FUND_ID);
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			String name = rs.getString("name");
			String details = rs.getString("details");
			String date = rs.getString("date");
			int type = rs.getInt("type");
			int amt = rs.getInt("amt");
			
			f = new Funding(id,name,details,date,type,amt);
		}
		
		return f;
		
	}
	
	public List<Funding> selectAllFund() throws SQLException {

		List<Funding> funds = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_FUND);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String details = rs.getString("details");
			String date = rs.getString("date");
			int type = rs.getInt("type");
			int amt = rs.getInt("amt");
			
			funds.add(new Funding(id,name,details,date,type,amt));
		    
		    //System.out.println(funds);
		}
		
		return funds;
		
	}
	
	public boolean deletefund(int id) throws SQLException {

		boolean rowsdeleted; 
		
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_FUND);
		ps.setInt(1, id);
		
		
		rowsdeleted = ps.executeUpdate() > 0;
		
		return rowsdeleted;
		
	}
	
	public int getTotalFund() throws SQLException
	{
	     
	    Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(SELECT_ALL_FUND);
		
		ResultSet rs = ps.executeQuery();
		int resultAmount = 0;
		while(rs.next()) {
			
			int type = rs.getInt("type");
			int amt = rs.getInt("amt");
			
			if(type==1) {
				resultAmount+=amt;
			}
			else {
				resultAmount-=amt;
			}
			
			
		}
	     
	     return resultAmount;
	}

	
	 
	
	
}
