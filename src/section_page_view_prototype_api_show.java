// Loading required libraries
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class section_page_view_prototype_api_show extends HttpServlet{

  private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
  private static final String DB_URL="jdbc:mysql://localhost/SBPV";

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
         "<head><title>" + title + "</title>"+
         "<style>table, th, td {border: 1px solid black;}</style></head>\n" +
         "<body bgcolor=\"#f0f0f0\">\n" +
         "<h1 align=\"center\">" + title + "</h1>\n");
      try{
         // Register JDBC driver
         Class.forName("com.mysql.jdbc.Driver");

         // Open a connection
         conn = DriverManager.getConnection(DB_URL, USER, PASS);

         // Execute SQL query
         stmt = conn.createStatement();
         String sql;
         sql = "SELECT * FROM SECTION_PAGE_VIEW WHERE firstname='"+request.getParameter("first_name")+"' AND lastname='"+request.getParameter("last_name")+"';";
         ResultSet rs = stmt.executeQuery(sql);

         // HTML Table
         out.println("<table>"+ 
                        "<tr>"+
                          "<th>ID</th>"+
                          "<th>Firstname</th>"+
                          "<th>Lastname</th>"+
                          "<th>Section</th>"+
                          "<th>Date</th>"+ 
                          "<th>Duration</th>"+
                        "</tr>");
         
         // Extract data from result set
         while(rs.next()){
            //Retrieve by column name
            int id  = rs.getInt("id");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String section = rs.getString("section");
            String date = rs.getString("date");
            int duration = rs.getInt("duration");
            //Display values
            out.println("<tr>"+
                          "<th>"+id+"</th>"+
                          "<th>"+firstname+"</th>"+
                          "<th>"+lastname+"</th>"+
                          "<th>"+section+"</th>"+
                          "<th>"+date+"</th>"+ 
                          "<th>"+duration+" seconds </th>"+
                        "</tr>");
         }
         out.println("</table></body></html>");

         // Clean-up environment
         rs.close();
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
