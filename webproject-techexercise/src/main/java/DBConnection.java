import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
   static Connection connection = null;
   static String url = "jdbc:mysql://ec2-52-14-247-90.us-east-2.compute.amazonaws.com:3306/MyMovieDB";
   static String user = "benmonday_remote";
   static String password = "Penguin965";

   static void getDBConnection() {
      System.out.println("-------- MySQL JDBC Connection Testing ------------");
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         System.out.println("Where is your MySQL JDBC Driver?");
         e.printStackTrace();
         return;
      }
      System.out.println("MySQL JDBC Driver Registered!");

      connection = null;
      try {
         connection = DriverManager.getConnection(url, user, password);
      } catch (Exception e) {
         System.out.println("Connection Failed! Check output console");
         e.printStackTrace();
         return;
      }

      if (connection != null) {
         System.out.println("You made it, take control your database now!");
      } else {
         System.out.println("Failed to make connection!");
      }
   }
}
