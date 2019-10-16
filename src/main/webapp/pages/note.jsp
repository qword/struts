<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit note</title>
</head>
<body>
<p>Create a new note or update an existing one</p>
<p>
<form id="noteForm" name="noteForm" action="./notes.do?action=createOrUpdate" method="post">
    <input type="hidden" id="id" name="id" value="${noteForm.id}">
    <table>
        <tbody>
        <tr>
            <td>Title</td>
            <td><input type="text" name="title" value="${noteForm.title}"/></td>
        </tr>
        <tr>
            <td>Description</td>
            <td><input type="text" name="description" value="${noteForm.description}"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="Save" value="Save"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>

</p>
<p><a href="${pageContext.request.contextPath}">Go back</a> to the home page.</p>
</body>
</html>
