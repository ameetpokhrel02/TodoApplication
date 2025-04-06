package Controller;

import Model.TodoModel;
import Model.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AddTodoServlet")
public class AddTodoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        // 1. Retrieve form data (title, description, completed status)
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean completed = "on".equals(request.getParameter("completed"));
        
        // Validate input
        if (title == null || title.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Title cannot be empty");
            request.getRequestDispatcher("/view/add_todo.jsp").forward(request, response);
            return;
        }
        
        // 2. Create a new TodoModel object with the form data
        TodoModel newTodo = new TodoModel(0, title, description, completed);
        
        try {
            // 3. Add the todo to the database using TodoDAO
            int generatedId = TodoDAO.addTodo(newTodo);
            
            if (generatedId > 0) {
                // 4. Set success message in session (will survive redirect)
                request.getSession().setAttribute("successMessage", "Todo added successfully!");
                
                // Redirect to the list page after successful addition
                response.sendRedirect(request.getContextPath() + "/ListTodoServlet");
            } else {
                // Handle error
                request.setAttribute("errorMessage", "Failed to add todo");
                request.getRequestDispatcher("/view/add_todo.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/View/add_todo.jsp").forward(request, response);
        }
    }
}