package startz.fundmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import startz.fundmanagement.dao.FundingDAO;

public class GraphDAO {
	
	FundingDAO fundingdao = new FundingDAO();
    
	private int[] array1;
    private String[] array2;
    public GraphDAO() {}
    public GraphDAO(int[] array1, String[] array2)
    {
        this.array1 = array1;
        this.array2 = array2;

    }
    public int[] getArray1() { return array1; }
    public String[] getArray2() { return array2; }
    
    
	public GraphDAO graphValues() throws SQLException
	{
	     int[] array1 = new int[100];
	     String[] array2 = new String[100];

	    Connection con = fundingdao.getConnection();
		PreparedStatement ps = con.prepareStatement(fundingdao.GRAPH_QUERY);
		
		ResultSet rs = ps.executeQuery();
		int resultAmount = 0;
		int index=0;
		while(rs.next()) {
			
			String date = rs.getString("date");
			int type = rs.getInt("type");
			int amt = rs.getInt("amt");
			
			if(type==1) {
				resultAmount+=amt;
			}
			else {
				resultAmount-=amt;
			}
			
			array1[index] = resultAmount;
			array2[index] = date;
			
			index+=1;
		}
	     
	     return new GraphDAO(array1, array2);
	}

}
