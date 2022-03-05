package ch.finnova.java.schulung.db;

import java.sql.*;

public class JdbcExample {
   static final String DB_URL = "jdbc:h2:mem:mydb";
   static final String USER = "sa";
   static final String PASS = "password";
   static final String QUERY = "SELECT id, name FROM Person";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY);) {
         // Extract data from result set
         while (rs.next()) {
            // Retrieve by column name
            System.out.print("id: " + rs.getInt("id"));
            System.out.println(", name: " + rs.getString("name"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
