package experiment5;

import java.sql.*;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
public class BankTransfer {
	public static void Fund_Transfer(int fromAccount, int toAccount, double Amount) {
		String url = "jdbc:mysql://localhost:3306/bankdb";
//Username
		String username = "root";
//password
		String password = "root";
// Establish JDBC Connection
		try {
// Load the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
// Establish connection
			Connection c = DriverManager.getConnection(url, username, password);
			c.setAutoCommit(false);
//statement
			Statement st = c.createStatement();
//Prepared Statement
			PreparedStatement pst = null;
//ResultSet
			ResultSet rs = null;
			rs = st.executeQuery("Select * from accounts");
			while (rs.next()) {
				System.out.println("Id: " + rs.getInt("accounts_id") + " , Name: " + rs.getString("account_holder")
						+ ", Price: " + rs.getDouble("balance"));
			}
//Check balance availability
			String query = "select balance from accounts where accounts_id= ? ";
			pst = c.prepareStatement(query);
			pst.setInt(1, fromAccount);
			rs = pst.executeQuery();
			if (rs.next()) {
				double Balance = rs.getDouble("Balance");
				if (Balance < Amount) {
					System.out.println("Insufficient Balance");
					return;
				}
//Deduct from 'from_Account' Record
				String Updatefromquery = "Update accounts set balance = balance - ? where accounts_id= ? ";
				pst = c.prepareStatement(Updatefromquery);
				pst.setDouble(1, Amount);
				pst.setInt(2, fromAccount);
				int rowFrom = pst.executeUpdate();
//Deduct from 'to_Account' Record
				String Updatetoquery = "Update accounts set balance = balance + ? where accounts_id= ? ";
				pst = c.prepareStatement(Updatetoquery);
				pst.setDouble(1, Amount);
				pst.setInt(2, toAccount);
				int rowTo = pst.executeUpdate();
//If Both are successfull commit transaction otherwise rollback
				if (rowFrom > 0 && rowTo > 0) {
					c.commit();
					System.out.println("Transfer Successfull");
				} else {
					c.rollback();
					System.out.println("Transfer Failed");
				}
			} else {
				System.out.println("Account Not found");
			}
			rs = st.executeQuery("Select * from accounts");
			while (rs.next()) {
				System.out.println("Id: " + rs.getInt("accounts_id") + " , Name: " + rs.getString("account_holder")
						+ ", Price: " + rs.getDouble("balance"));
			}
// Close the connection
			rs.close();
			st.close();
			c.close();
			System.out.println("Connection closed.");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver not found: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		Fund_Transfer(1, 2, 1000);
	}
}
