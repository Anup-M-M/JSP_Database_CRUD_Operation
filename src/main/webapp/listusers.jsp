<%@page import="com.example.entity.User"%>
<%@page import="java.util.List"%>

<jsp:include page="include/header.jsp"/>

<h2>List of Users</h2>

<table style="border: 1px solid black;">
	<thead>
        <tr>
            <th style='border: 1px solid black;'>User Id</th>
            <th style='border: 1px solid black;'>User Name</th>
            <th style='border: 1px solid black;'>Email</th>
            <th style='border: 1px solid black;'>Operations</th>
        </tr>
    </thead>
    <tbody>
    <%
    List<User> listusers = (List<User>) request.getAttribute("listusers");
    if (listusers != null && !listusers.isEmpty()) {
        for (User _user : listusers) {
            out.print("<tr>");
            out.print("<td style='border: 1px solid black;'>" + _user.getUser_id() + "</td>");
            out.print("<td style='border: 1px solid black;'>" + _user.getUsername() + "</td>");
            out.print("<td style='border: 1px solid black;'>" + _user.getEmail() + "</td>");           
            out.print("<td style='border: 1px solid black;'><a href="+request.getContextPath()+"/site?page=updateuser&user_id="+_user.getUser_id()+
            		"&username="+_user.getUsername()+"&email="+_user.getEmail()+">Update</a>");
            out.print(" | ");
            out.print("<a href="+request.getContextPath()+"/site?page=deleteuser&user_id="+_user.getUser_id()+">Delete</a></td>");
            out.print("</tr>");
        }
    } else {
        out.print("<tr><td colspan='3'>No users found</td></tr>");
    }
    %>
    </tbody>

</table>


<jsp:include page="include/footer.jsp" />