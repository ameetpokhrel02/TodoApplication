package Controller;

import Model.TodoModel;
import Model.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings({ "serial", "unused" })
@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        // 1. Retrieve the TODO ID from the request parameter
        String idParam = request.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int todoId = Integer.parseInt(idParam);
                
                // 2. Delete the TODO from the database using TodoDAO
                boolean success = TodoDAO.removeTodo(todoId);
                
                // 3. Set a message in session (will survive redirect)
                if (success) {
                    request.getSession().setAttribute("successMessage", "Todo deleted successfully!");
                } else {
                    request.getSession().setAttribute("errorMessage", "Failed to delete todo");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("errorMessage", "Invalid todo ID");
            }
        } else {
            request.getSession().setAttribute("errorMessage", "Todo ID is required");
        }
        
        // 4. Redirect to the list page
        response.sendRedirect(request.getContextPath() + "/ListTodoServlet");
    }
}