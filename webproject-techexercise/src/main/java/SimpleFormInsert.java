
/**
 * @file SimpleFormInsert.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleFormInsert")
public class SimpleFormInsert extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SimpleFormInsert() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String title = request.getParameter("title");
      String director = request.getParameter("director");
      String year = request.getParameter("year");
      String rating = request.getParameter("rating");

      Connection connection = null;
      String insertSql = " INSERT INTO MyMovieTable (id, TITLE, DIRECTOR, YEAR, RATING) values (default, ?, ?, ?, ?)";

      try {
         DBConnection.getDBConnection();
         connection = DBConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, title);
         preparedStmt.setString(2, director);
         preparedStmt.setString(3, year);
         preparedStmt.setString(4, rating);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String heading = "Movie Information Inserted:";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + heading + "</title></head>\n" + //
            "<body style=\"background-color:rgb(50, 50, 50)\", text=\"white\">\n" + //
            "<h2 align=\"center\">" + heading + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>Title</b>: " + title + "\n" + //
            "  <li><b>Director</b>: " + director + "\n" + //
            "  <li><b>Year</b>: " + year + "\n" + //
            "  <li><b>Rating</b>: " + rating + "\n" + //
            "</ul>\n");

      out.println("<a href=/webproject-techexercise/simpleFormSearch.html>Search Movies</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
