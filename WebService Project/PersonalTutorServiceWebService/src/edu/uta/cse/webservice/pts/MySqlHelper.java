/**
 * 
 */
package edu.uta.cse.webservice.pts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

/**
 * @author Arun
 *
 */
public class MySqlHelper {

	Connection conn;

	public MySqlHelper() {
		// TODO Auto-generated constructor stub
		//personal tutor database to which we're connecting
		String url = "jdbc:mysql://localhost:3306/personaltutor";
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, "root", "rainbow");
			
			System.out.println("Connection Successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Deprecated
	public ResultSet executeQueryAndGetResultSet(String query) {
		ResultSet result = null;

		try {
			Statement st = conn.createStatement();
			result = st.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	//this method will return insert id
	@Deprecated
	public int executeInsertQueryAndReturnId(String query) {
		int id = -1;
		try {
			Statement st = conn.createStatement();
			 st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			 //get the inserted row id
			ResultSet rs = st.getGeneratedKeys();
			while(rs.next())
			{
				id = rs.getInt(1);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return id;
	}
	@Deprecated
	public void executeQuery(String query) {

		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	

	// dispose the connection
	public void disposeConnection() {
		try {
			conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Dispose Failed");
			e.printStackTrace();
		}
	}

}
