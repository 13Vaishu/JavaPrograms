package experiment4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRetrieval {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/productdb";
        String username = "root";
        String password = "root";

        try {
        	
            // 1. Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            // 2. Establish Connection
            Connection con = DriverManager.getConnection(url, username, password);

            
            // 3. Create Statement
            Statement stmt = con.createStatement();

            // 4. SQL Query
            String query = "SELECT * FROM product";

            // 5. Execute Query
            ResultSet rs = stmt.executeQuery(query);

            // 6. Process ResultSet
            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                double price = rs.getDouble("price");

                System.out.println("Product ID: " + id);
                System.out.println("Product Name: " + name);
                System.out.println("Price: " + price);
                System.out.println("---------------------------");
            }

            // 7. Close Resources
            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error");
            e.printStackTrace();
        }
    }
}