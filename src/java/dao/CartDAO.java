
package dao;

import beans.Costumer;
import beans.Product;
import beans.SessionUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataConnect;

public class CartDAO {

	public static boolean addToCart(Costumer costumer,Product product, int quantity) {
		Connection con = null;
		PreparedStatement ps = null;
                String costumerId = SessionUtils.getUserId();
	
                int result = 0;  
		try {
                    con = DataConnect.getConnection();
                     PreparedStatement stmt = con.prepareStatement("insert into costumer_product(costumerId,productId,quantity) values(?,?,?);");  
                     stmt.setInt(1, costumer.getId());  
                     stmt.setInt(2, product.getProductId());  
                     stmt.setInt(3, quantity);  
             
                    result = stmt.executeUpdate();  
             
			
		 }catch(SQLException ex) {
			System.out.println("Register error -->" + ex.getMessage());
			return false;
        }
        if(result == 1){  
            return true;  
        }else return false;  
    }  


        
        
        public static boolean register(String user, String password){  
        int result = 0;  
        try{  
             
             Connection con = con = DataConnect.getConnection();
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
