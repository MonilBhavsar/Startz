<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="startz.fundmanagement.dao.FundingDAO"%>
<%@ page import="startz.fundmanagement.dao.TaskDAO"%>
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Startz Dashboard</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/style.css">	
<style>
   .col-md-6{
       margin:10px auto;
   }
	
</style>
</head>
<body>
<%
	TaskDAO taskDAO = new TaskDAO();
	FundingDAO fundingDAO = new FundingDAO();
	int pendingtasks = taskDAO.getPendingTasks();
	int totalfund = fundingDAO.getTotalFund();
	
%>
	

	<header>
			<nav class="navbar navbar-expand-md navbar-dark bg-dark">
			
				<a href="#" class="navbar-brand"> Startz </a>
				  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
			      <span class="navbar-toggler-icon"></span>
			  	</button>
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
                	
	                <img src="resources/images/rocket.png" class="img img-fluid mx-auto" width="120">
		       
                    <h2>Welcome to Startz!</h2>
 					<p>Startz is a one-stop destination for entrepreneurs to manage their startups.</p>
                </div>
            </div>
        </div>
		<div class="row">
			<div class="col-md-6">
				<div class="card bg-success text-white h-100">
                       <div class="card-body bg-success">
                           <h6 class="text-uppercase">Funding</h6>
                           <h1 class="display-4">&#x20b9; <%out.println(totalfund); %></h1>
                       </div>
                   </div>
			</div>
			<div class="col-md-6">
				<div class="card bg-success text-white h-100">
                       <div class="card-body bg-info">
                           <h6 class="text-uppercase">Pending Tasks</h6>
                           <h1 class="display-4"><%out.println(pendingtasks); %></h1>
                       </div>
                   </div>
			</div>
		</div>
	</div>
	<footer class="text-center">
		<p>Developed By Monil Bhavsar & Monik Bhesaniya</p>
	</footer>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>