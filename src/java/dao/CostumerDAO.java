package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataConnect;

public class CostumerDAO {

	public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select costumerId, username, password from costumer where username = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
                }finally {
			DataConnect.close(con);
		}
		return false;
	}
        
        public static int getId(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select costumerId from costumer where username = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("costumerId");
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return 0;
                }finally {
			DataConnect.close(con);
		}
		return 0;
	}

        
        
        public static boolean register(String user, String password){  
        int result = 0;  
        try{  
             
             Connection con = con = DataConnect.getConnection();
             PreparedStatement ps = con.prepareStatement("Select username, password from costumer where username = ? and password = ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found,user already exists, means invalid inputs
				return false;
			}
                        
             PreparedStatement stmt = con.prepareStatement("insert into costumer(username,password) values(?,?);");  
             stmt.setString(1, user);  
             stmt.setString(2, password);  
             result = stmt.executeUpdate();  
        }catch(SQLException ex) {
			System.out.println("Register error -->" + ex.getMessage());
			return false;
        }
        if(result == 1){  
            return true;  
        }else return false;  
     }  
  }
