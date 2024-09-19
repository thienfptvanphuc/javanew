<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update product</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>Update Product</h1>
		<h2>
			<a href="${pageContext.request.contextPath}${'/products'}">List
				All Products</a>
		</h2>
		<c:if test="${product == null}">
			<h3>Product not found.</h3>
		</c:if>
		<br>

		<form action="products" method="post" enctype="multipart/form-data">
			<input type="hidden" name="action" value="update">
			Code <input readonly="readonly" type="text" name="code" value="${product.getCode()}"><br>
			<br> Name: 
				<input type="text" name="name" value="${product.name}"><br> <br> Price: 
				<input
				type="number" name="price" value="${product.price}"><br>
			<img class="img-thumbnail my-5" src="${product.image}" height="150px"
				width="120px" /> <br> Choose a Image: <input type="file"
				name="image"><br> <br> <input type="submit"
				value="Submit">
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>