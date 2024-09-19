<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Products</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h1>Product Management</h1>
		<h2>
			<c:if test="${listProducts.size() == 0}">
				<h1>No product available.</h1>
			</c:if>
			<a href="products?action=add">Add product</a> <br>
		</h2>
		<br>
		<nav class="navbar navbar-light bg-light">
			<form class="form-inline d-flex" action="${pageContext.request.contextPath}/products/search" method="get">
				<input class="form-control mx-2" type="search" name="searchText"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success" type="submit">Search</button>
			</form>
		</nav>
		<h2>List of Products</h2>
		<table class="table">
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th>Image</th>
				<th>Cart</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="product" items="${listProducts}">
				<tr>
					<td>${product.code}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td><img class="img-thumbnail" src="${pageContext.request.contextPath}/${product.image}"
						height="150px" width="120px" /></td>
					<td><a href="cart?action=add&code=${product.code}">Add to
							cart</a></td>
					<td><a href="products?action=update&code=${product.code}">Edit</a></td>
					<td><a href="products?action=delete&code=${product.code}">Delete</a></td>
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