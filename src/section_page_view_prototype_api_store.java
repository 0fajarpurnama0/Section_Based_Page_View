// Loading required libraries
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

// Extend HttpServlet class
public class section_page_view_prototype_api_store extends HttpServlet {
 
  private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
  private static final String DB_URL="jdbc:mysql://localhost";
  private static final String DB_TABLE_URL="jdbc:mysql://localhost/SBPV";

  //  Database credentials
  private static final String USER = "user";
  private static final String PASS = "pass";

  Connection conn;
  Statement stmt;
    
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType =
        "<!doctype html public \"-//w3c//dtd html 4.0 " +
         "transitional//en\">\n";
         out.println(docType +
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor=\"#f0f0f0\">\n" +
         "<h1 align=\"center\">" + title + "</h1>\n");
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection 
         out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute SQL query
         out.println("Creating database...");
         stmt = conn.createStatement();
      
         String sql = "CREATE DATABASE IF NOT EXISTS SBPV";
         stmt.executeUpdate(sql);
         out.println("Database created successfully...");

         // Clean-up environment
         stmt.close();
         conn.close();

         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         out.println("Connecting to database table...");
         conn = DriverManager.getConnection(DB_TABLE_URL, USER, PASS);

         // Execute SQL query
         out.println("Creating table...");
         stmt = conn.createStatement();
         sql =     "CREATE TABLE IF NOT EXISTS SECTION_PAGE_VIEW " +
                   "(id INTEGER not NULL AUTO_INCREMENT, " +
                   "firstname VARCHAR(255), " + 
                   "lastname VARCHAR(255), " + 
                   "section VARCHAR(255), " + 
                   "date VARCHAR(255), " +
                   "duration INTEGER, " + 
                   "PRIMARY KEY ( id ));";
         stmt.executeUpdate(sql);
         out.println("Created table in given database...");

         out.println("Inserting records into the table..." + request.getParameter("first_name") + " " + request.getParameter("last_name")+ " " + request.getParameter("section") + " " + request.getParameter("current_date") + " " + request.getParameter("duration_discrete"));

         sql = "INSERT INTO SECTION_PAGE_VIEW (firstname,lastname,section,date,duration) " +
                   "VALUES ('"+request.getParameter("first_name")+"','"+request.getParameter("last_name")+"','"+request.getParameter("section")+"','"+request.getParameter("current_date")+"',"+request.getParameter("duration_discrete")+");";

         stmt.executeUpdate(sql);

         out.println("</body></html>");

         // Clean-up environment
         //rs.close();
         stmt.close();
         conn.close();
      }catch(SQLException se){
         //Handle errors for JDBC
         se.printStackTrace();
      }catch(Exception e){
         //Handle errors for Class.forName
         e.printStackTrace();
      }finally{
         //finally block used to close resources
         try{
            if(stmt!=null)
               stmt.close();
         }catch(SQLException se2){
         }// nothing we can do
         try{
            if(conn!=null)
            conn.close();
         }catch(SQLException se){
            se.printStackTrace();
         }//end finally try
      } //end try
   }
}
