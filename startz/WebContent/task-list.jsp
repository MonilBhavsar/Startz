<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="com.google.gson.*"%>
<%@ page import="startz.fundmanagement.dao.FundingDAO"%>
<html>
<head>
<title>Startz | Tasks</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="../resources/css/style.css">	
</head>
<body>
	
	<header>
		<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			<div>
				<a href="/startz" class="navbar-brand"> Startz </a>
			</div>

			 <div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li><a href="<%=request.getContextPath()%>/funds/"
							class="nav-link">Manage Funding</a></li>
						<li><a href="<%=request.getContextPath()%>/tasks/"
						class="nav-link">Manage Tasks</a></li>
					</ul>
				</div>
		</nav>
	</header>
	<div class="container">
	<br>
	<div class="jumbotron">
            <div class="row">
                <div class="col-md-12">
                	
	                <img src="../resources/images/rocket.png" class="img img-fluid mx-auto" width="120">
		       
                    <h2>Welcome to Startz!</h2>
 					<p>Startz is a one-stop destination for entrepreneurs to manage their startups.</p>
                </div>
            </div>
      </div>

	<div class="row">

		<div class="container">
			<h3 class="text-center">My Tasks</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/tasks/new" class="btn btn-success">+ Add
					New Task</a>
			</div>
			<br>
			
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Sr. No</th>
						<th>Name</th>
						<th>Details</th>
						<th>Due Date</th>
						<th>Status</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:set var = "count" value="1" scope="page"/>
					<c:forEach var="task" items="${tasklist}">
						
						
						<tr>
							<td><c:out value="${count}" /></td>
							<td><c:out value="${task.name}" /></td>
							<td><c:out value="${task.details}" /></td>
							<td><c:out value="${task.due}" /></td>
							<c:if test="${task.status  == 0 }">
								<td><span class="badge badge-danger"> Pending </span></td>
							</c:if>
							<c:if test="${task.status  == 1 }">
								<td><span class="badge badge-success"> Complete </span></td>
							</c:if>
							<td><a href="edit?id=<c:out value='${task.id}' />"><button class="btn btn-warning btn-small">Edit</button></a>
								&nbsp;<a
								href="delete?id=<c:out value='${task.id}' />"><button class="btn btn-danger btn-small">Delete</button></a></td>
						</tr>
						<c:set var="count" value="${count + 1}" scope="page"/>
					</c:forEach>
					
				</tbody>

			</table>
		</div>
	</div>
	</div>
	<footer class="text-center">
		<p>Developed By Monil Bhavsar & Monik Bhesaniya</p>
	</footer>
</body>
</html>
