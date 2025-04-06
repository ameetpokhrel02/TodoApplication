<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, Model.TodoModel" %>
<html>
<head>
    <title>Todo List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/Css/style.css">
</head>
<body>
    <div class="container">
        <h1>Todo List</h1>
        <hr/>
        
        <% if(request.getAttribute("successMessage") != null) { %>
            <div class="success-message">
                <%= request.getAttribute("successMessage") %>
            </div>
        <% } %>
        
        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<TodoModel> todoList = (List<TodoModel>) request.getAttribute("todoList");
                        if (todoList != null && !todoList.isEmpty()) {
                            for (TodoModel todo : todoList) {
                    %>
                                <tr>
                                    <td><%= todo.getId() %></td>
                                    <td><%= todo.getTitle() %></td>
                                    <td><%= todo.getDescription() != null ? todo.getDescription() : "" %></td>
                                    <td class="status <%= todo.isCompleted() ? "completed" : "pending" %>">
                                        <%= todo.isCompleted() ? "Completed" : "Pending" %>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/DeleteTodoServlet?id=<%= todo.getId() %>" 
                                           class="delete-button" 
                                           onclick="return confirm('Are you sure you want to delete this todo?')">Delete</a>
                                    </td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="5" class="no-records">No todo items found</td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/View/add_todo.jsp" class="button primary-button">Add New Todo</a>
            <a href="${pageContext.request.contextPath}/index.jsp" class="button secondary-button">Back to Home</a>
        </div>
        
        <hr/>
        
        <footer>
            <p>Todo Application - Advanced Programming and Technologies</p>
        </footer>
    </div>
</body>
</html>