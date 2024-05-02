<jsp:include page="include/header.jsp"/>


<h2>Add User</h1>

<form action="${pageContext.request.contextPath}/site" method="post">
	User Name : <input type="text" name = "username" value="${param.username}" required="required"><br/>
	Email : <input type="email" name = "email" value="${param.email}" required="required"><br/>
		<input type="hidden" name="form" value="updateUserOperation">
		<input type="hidden" name="user_id" value="${param.user_id}">
		<input type="submit" value="Update User">
</form>

<jsp:include page="include/footer.jsp" />
