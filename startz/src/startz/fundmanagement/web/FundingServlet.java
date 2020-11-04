package startz.fundmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import startz.fundmanagement.dao.FundingDAO;
import startz.fundmanagement.model.Funding;
import startz.fundmanagement.dao.GraphDAO;


/**
 * Servlet implementation class FundingServlet
 */
//@WebServlet("`/funds")
public class FundingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FundingDAO fundingDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingServlet() {
        this.fundingDAO = new FundingDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getPathInfo();
		
		//System.out.println(action);
		switch(action) {
		   case "/new":
			   fundForm(request,response);
			   break;
		   case "/insert":
			   try {
				addfund(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   break;
		   case "/delete":
			   try {
				deletefund(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   break;
		   case "/edit":
			   try {
				fundEditForm(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   break;
		   case "/update":
			    try {
				  updatefund(request,response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   break; 
		   default:
			   try {
				listfund(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void fundForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("../fund-form.jsp");
		rd.forward(request, response);
	}
	
	private void fundEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Funding existingfund = fundingDAO.selectFund(id);
		request.setAttribute("fund", existingfund);
		System.out.println(id);
		RequestDispatcher rd = request.getRequestDispatcher("../fund-form.jsp");
		rd.forward(request, response);
	}
	
	private void addfund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String name = request.getParameter("name");
		String details = request.getParameter("details");
		String date = request.getParameter("date");
		int amt = Integer.parseInt(request.getParameter("amt"));
		int type = Integer.parseInt(request.getParameter("type"));
		Funding fund = new Funding(name,details,date,type,amt);
		fundingDAO.addfund(fund);
		response.sendRedirect("list"); 
		
	}
	
	private void deletefund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
	
		fundingDAO.deletefund(id);
		response.sendRedirect("list"); 
		
	}
	
	private void updatefund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String details = request.getParameter("details");
		String date = request.getParameter("date");
		int amt = Integer.parseInt(request.getParameter("amt"));
		int type = Integer.parseInt(request.getParameter("type"));
		Funding fund = new Funding(id,name,details,date,type,amt);
		fundingDAO.updatefund(fund);
		response.sendRedirect("list"); 
		
	}
	
	private void listfund(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	    List<Funding> fundlist = fundingDAO.selectAllFund();
	    
	    GraphDAO graphDAO = new GraphDAO();
	    graphDAO=graphDAO.graphValues();
	    int[] arrayA = graphDAO.getArray1();
	    String[] arrayB = graphDAO.getArray2();
	    request.setAttribute("amtlist", arrayA);
	    request.setAttribute("datelist", arrayB);
	    request.setAttribute("fundlist", fundlist);

	    RequestDispatcher rd = request.getRequestDispatcher("../fund-list.jsp");
		rd.forward(request, response);
		
	}

}
