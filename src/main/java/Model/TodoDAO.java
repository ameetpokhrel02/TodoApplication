package Model;

import java.sql.*;
import java.util.ArrayList;

public class TodoDAO {
    // Database connection parameters - using the newly created database
    private static final String URL = "jdbc:mysql://localhost:3306/todo_database";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Use your actual MySQL password
    
    static {
        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load MySQL driver", e);
        }
    }
    
    // Method to establish a database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Method to add a todo to the database
    public static int addTodo(TodoModel todoModel) throws SQLException {
        String sql = "INSERT INTO todo_table (title, description, completed) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int generatedId = -1;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, todoModel.getTitle());
            pstmt.setString(2, todoModel.getDescription());
            pstmt.setBoolean(3, todoModel.isCompleted());
            
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    generatedId = rs.getInt(1);
                }
            }
        } finally {
            closeResources(conn, pstmt, rs);
        }
        
        return generatedId;
    }
    
    // Method to remove a todo from the database
    public static boolean removeTodo(int todoId) {
        String sql = "DELETE FROM todo_table WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, todoId);
            
            int affectedRows = pstmt.executeUpdate();
            success = (affectedRows > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
        
        return success;
    }
    
    // Method to list all todos from the database
    public static ArrayList<TodoModel> listTodo() {
        String sql = "SELECT * FROM todo_table ORDER BY id DESC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<TodoModel> todoList = new ArrayList<>();
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean completed = rs.getBoolean("completed");
                
                TodoModel todo = new TodoModel(id, title, description, completed);
                todoList.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        
        return todoList;
    }
    
    // Helper method to close database resources
    private static void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to get a single todo by ID
    public static TodoModel getTodoById(int todoId) {
        String sql = "SELECT * FROM todo_table WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TodoModel todo = null;
        
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, todoId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                boolean completed = rs.getBoolean("completed");
                
                todo = new TodoModel(todoId, title, description, completed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        
        return todo;
    }
}