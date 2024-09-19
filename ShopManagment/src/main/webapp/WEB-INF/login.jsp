<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<h1>Login</h1>
		<form action="login" method="post">
			<div class="form-group">
				<label for="email">Email address</label> <input type="email" name="email"
					class="form-control" id="email" aria-describedby="emailHelp"
					placeholder="Enter email">
			</div>
			<br>
			<div class="form-group">
				<label for=passord>Password</label> <input type="password" name="password"
					class="form-control" id="passord" placeholder="Password">
			</div>
			<br>
			<div class="form-check">
				<input type="checkbox" name="remember" class="form-check-input" id="remember">
				<label class="form-check-label" for="remember">Remember me</label>
			</div>
			<br>
			<button type="submit" class="btn btn-primary">Login</button>
		</form>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>