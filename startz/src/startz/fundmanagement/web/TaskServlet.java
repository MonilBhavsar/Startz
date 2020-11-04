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

import startz.fundmanagement.dao.TaskDAO;
import startz.fundmanagement.model.Task;

/**
 * Servlet implementation class TaskServlet
 */
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TaskDAO taskDAO;
    
    public TaskServlet() {
    
        this.taskDAO = new TaskDAO();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		//System.out.println(action);
		switch(action) {
		   case "/new":
			   fundForm(request,response);
			   break;
		   case "/insert":
			   try {
				addtask(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   break;
		   case "/delete":
			   try {
				deletetask(request,response);
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
				  updatetask(request,response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   break; 
		   default:
			   try {
				listtask(request,response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
private void fundForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("../task-form.jsp");
		rd.forward(request, response);
	}
	
	private void fundEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Task existingtask = taskDAO.selecttask(id);
		request.setAttribute("task", existingtask);
		System.out.println(id);
		RequestDispatcher rd = request.getRequestDispatcher("../task-form.jsp");
		rd.forward(request, response);
	}
	
	private void addtask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String name = request.getParameter("name");
		String details = request.getParameter("details");
		String due = request.getParameter("due");
		int status = 0;
		Task task = new Task(name,details,due,status);
		taskDAO.addtask(task);
		response.sendRedirect("list"); 
		
	}
	
	private void deletetask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
	
		taskDAO.deletetask(id);
		response.sendRedirect("list"); 
		
	}
	
	private void updatetask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String details = request.getParameter("details");
		String due = request.getParameter("due");
		int status = Integer.parseInt(request.getParameter("status"));
		
		Task task = new Task(id,name,details,due,status);
		taskDAO.updatetask(task);
		response.sendRedirect("list"); 
		
	}
	
	private void listtask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
	    List<Task> tasklist = taskDAO.selectAlltask();
	    
	    request.setAttribute("tasklist", tasklist);

	    RequestDispatcher rd = request.getRequestDispatcher("../task-list.jsp");
		rd.forward(request, response);
		
	}

}
