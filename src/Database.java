import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;
	PreparedStatement preparedstatement = null;

	public Database() {

		String url = "jdbc:mysql://localhost:3306/TEST?serverTimezone=UTC";
		String username = "root";
		String password = "885799";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connecting database...");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect the database!", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void create() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(
					"CREATE TABLE User1 (No INTEGER, Account_Name VARCHAR(20), Account_Amount VARCHAR(20));");
		} catch (SQLException e) {
			System.out.println("CreateDB Exception :" + e.toString());
		}
	}

	public void delete() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM User1;");
		} catch (SQLException e) {
			System.out.println("DROP Exception :" + e.toString());
		}
	}

	public void getData() {
		try {
			System.out.println("\n讀取資料...");
			statement = connection.createStatement();
			resultset = statement.executeQuery("select * from User1 ");
			System.out.println("No\tAccount_Name\tAccount_Amount");
			while (resultset.next()) {
				System.out.println(resultset.getInt("No") + "\t" + resultset.getString("Account_Name") + "\t\t"
						+ resultset.getString("Account_Amount"));
			}
		} catch (SQLException e) {
			System.out.println("DropDB Exception :" + e.toString());
		}
	}

	public void insertTable(String name, String amount) {
		try {
			preparedstatement = connection.prepareStatement(
					"insert into User1(No,Account_Name,Account_Amount) select ifNULL(max(No),0)+1,?,? FROM User1");
			preparedstatement.setString(1, name);
			preparedstatement.setString(2, amount);
			preparedstatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("InsertDB Exception :" + e.toString());
		}
	}
}