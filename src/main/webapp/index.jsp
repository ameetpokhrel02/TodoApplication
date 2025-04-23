<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Todo Application</title>
    <link rel="stylesheet" type="text/css" href="./assets/Css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Welcome to the Todo Application</h1>
            <p class="subtitle">Manage your tasks efficiently</p>
        </header>
        <hr/>
        
        <div class="hero-section">
            <div class="hero-content">
                <h2>Keep Track of Your Tasks</h2>
                <p>This application helps you manage your daily tasks. Add new todos, mark them as complete, and remove them when they're no longer needed.</p>
                
                <div class="button-container">
                    <a href="${pageContext.request.contextPath}/View/add_todo.jsp" class="button primary-button">Add a New Todo</a>
                    <a href="${pageContext.request.contextPath}/ListTodoServlet" class="button secondary-button">View All Todos</a>
                </div>
            </div>
        </div>
        
        <hr/>
        
        <div class="features">
            <div class="feature">
                <h3>Create Tasks</h3>
                <p>Add detailed todo items with title, description, and status.</p>
            </div>
            
            <div class="feature">
                <h3>Manage Tasks</h3>
                <p>View and organize all your tasks in one place.</p>
            </div>
            
            <div class="feature">
                <h3>Remove Tasks</h3>
                <p>Delete completed tasks to keep your list clean.</p>
            </div>
        </div>
        
        <footer>
            <p>Todo Application - Advanced Programming and Technologies</p>
        </footer>
    </div>
</body>
</html>
