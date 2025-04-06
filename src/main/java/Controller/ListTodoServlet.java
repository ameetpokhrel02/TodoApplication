package Controller;

import Model.TodoModel;
import Model.TodoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
@WebServlet("/ListTodoServlet")
public class ListTodoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        // 1. Retrieve all todos from the database using TodoDAO
        ArrayList<TodoModel> todoList = TodoDAO.listTodo();
        
        // 2. Set the todos as a request attribute
        request.setAttribute("todoList", todoList);
        
        // Check for any session messages and transfer to request
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Transfer success message from session to request (if exists)
            if (session.getAttribute("successMessage") != null) {
                request.setAttribute("successMessage", session.getAttribute("successMessage"));
                session.removeAttribute("successMessage");
            }
            
            // Transfer error message from session to request (if exists)
            if (session.getAttribute("errorMessage") != null) {
                request.setAttribute("errorMessage", session.getAttribute("errorMessage"));
                session.removeAttribute("errorMessage");
            }
        }
        
        // 3. Forward to the list-todo.jsp page
        request.getRequestDispatcher("/View/list-todo.jsp").forward(request, response);
    }
}