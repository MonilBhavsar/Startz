<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="col-md-6 mx-auto">
			<div class="card">
				<div class="card-body">
					<c:if test="${task != null}">
						<form action="update" method="post">
					</c:if>
					<c:if test="${task == null}">
						<form action="insert" method="post">
					</c:if>
	
					<caption>
						<h2>
							<c:if test="${task != null}">
	            			Edit Task
	            		</c:if>
							<c:if test="${task == null}">
	            			Add New Task
	            		</c:if>
						</h2>
					</caption>
	
					<c:if test="${task != null}">
						<input type="hidden" name="id" value="<c:out value='${task.id}' />" />
					</c:if>
	
					<fieldset class="form-group">
						<label>Task Name</label> <input type="text"
							value="<c:out value='${task.name}' />" class="form-control"
							name="name" required="required">
					</fieldset>
	
					<fieldset class="form-group">
						<label>Details</label> 
						<textarea class="form-control"
							name="details"><c:out value='${task.details}' /></textarea>
					</fieldset>
	
					<fieldset class="form-group">
						<label>Due Date</label> <input type="date"
							value="<c:out value='${task.due}' />" class="form-control"
							name="due">
					</fieldset>
					<c:if test="${task != null}">
						<fieldset class="form-group">
							<label>Status</label> 
							<select class="form-control" name="status">
								<option value="0" <c:if test="${task.status == 0}"> <c:out value= "selected=selected"/></c:if>>Pending</option>
								<option value="1" <c:if test="${task.status == 1}"> <c:out value= "selected=selected"/></c:if>>Complete</option>
							</select>
						</fieldset>
					</c:if>
					
					
	
					<button type="submit" class="btn btn-success">Save</button>
					</form>
				</div>
			</div>
			</div>
		</div>
	</div>
	<footer class="text-center">
		<p>Developed By Monil Bhavsar & Monik Bhesaniya</p>
	</footer>
</body>
</html>