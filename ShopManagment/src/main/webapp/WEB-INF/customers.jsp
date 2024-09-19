<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="jakarta.servlet.jsp.PageContext" %>
<%@ page import="jakarta.servlet.jsp.JspException" %>
<!-- Rest of the code... -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shop Management</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>CUSTOMERS MANAGEMENT</h1>
		<br>
		<h2>
			<a href="customers/new">Add New Customer</a>
		</h2>
		<nav class="navbar navbar-light bg-light">
			<form class="form-inline d-flex" action="${pageContext.request.contextPath}/customers/search" method="get">
				<input class="form-control mx-2" type="search" name="searchText"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</nav>
		<h2>
			List Customers
		</h2>
		<table class="table">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Country</th>
				<th>Action</th>
			</tr>
			<c:forEach var="customer" items="${listCustomers}">
				<tr>
					<td><c:out value="${customer.id}"></c:out></td>
					<td><c:out value="${customer.name}"></c:out></td>
					<td><c:out value="${customer.email}"></c:out></td>
					<td><c:out value="${customer.country.name}"></c:out></td>
					<td>
						<a href="customers/edit?id=${customer.id}">Edit</a>
						&nbsp;&nbsp;
						<a href="customers/delete?id=${customer.id}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>