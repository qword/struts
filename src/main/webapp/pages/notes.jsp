<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="static qword.struts.action.NoteAction.NOTES_LIST_ATTRIBUTE" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Notes list</title>
</head>
<body>
<p>Here's a list of Notes</p>
<p>
<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Created</th>
        <th>Updated</th>
    </tr>
    </thead>
    <tbody>

    <logic:iterate name="<%=NOTES_LIST_ATTRIBUTE%>" id="note">
        <tr>
            <td>${note.id}</td>
            <td>${note.title}</td>
            <td>${note.description}</td>
            <td>${note.created}</td>
            <td>${note.updated}</td>
        </tr>
    </logic:iterate>

    </tbody>
</table>
</p>
<p><a href="${pageContext.request.contextPath}">Go back</a> to the home page.</p>
</body>
</html>
