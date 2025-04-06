<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Todo</title>
    <link rel="stylesheet" type="text/css" href="../assets/Css/style.css">
</head>
<body>
    <div class="container">
        <h1>Add a New Todo</h1>
        <hr/>
        
        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="error-message">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        
        <div class="form-container">
            <form action="../AddTodoServlet" method="post">
                <div class="form-group">
                    <label for="title">Title:</label>
                    <input type="text" id="title" name="title" required>
                </div>
                
                <div class="form-group">
                    <label for="description">Description:</label>
                    <textarea id="description" name="description" rows="4"></textarea>
                </div>
                
                <div class="form-group checkbox">
                    <label for="completed">
                        <input type="checkbox" id="completed" name="completed">
                        Completed
                    </label>
                </div>
                
                <div class="form-actions">
                    <input type="submit" value="Add Todo" class="button primary-button">
                    <a href="${pageContext.request.contextPath}/index.jsp" class="button secondary-button">Cancel</a>
                </div>
            </form>
        </div>
        
        <hr/>
        <p><a href="${pageContext.request.contextPath}/index.jsp" class="back-link">Back to Home</a></p>
        
        <footer>
            <p>Todo Application - Advanced Programming and Technologies</p>
        </footer>
    </div>
</body>
</html>