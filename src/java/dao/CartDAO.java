
package dao;

import beans.Costumer;
import beans.CostumerProduct;
import beans.Product;
import beans.SessionUtils;
import static com.oracle.util.Checksums.update;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataConnect;

public class CartDAO {

	public static boolean addToCart(int productId) {
		Connection con = null;
		PreparedStatement ps = null;
                int costumerId = SessionUtils.getUserId();
	
                int result = 0;  
		try {
                    con = DataConnect.getConnection();
                     ps = con.prepareStatement("Select costumer_productId, quantity from costumer_product where costumerId = ? and productId=?");
		     ps.setInt(1, costumerId);
                     ps.setInt(2, productId);
                     
                     ResultSet rs = ps.executeQuery();
                     
                     if (rs.next()) {
                        ps = con.prepareStatement("update costumer_product set quantity = ? where costumer_productId = ? ");
			ps.setInt(1, rs.getInt("quantity")+1);
                        ps.setInt(2, rs.getInt("costumer_productId"));
                        ps.executeUpdate();
		     }else{
                     PreparedStatement stmt = con.prepareStatement("insert into costumer_product(costumerId,productId,quantity) values(?,?,?);");  
                     stmt.setInt(1, costumerId );  
                     stmt.setInt(2, productId);  
                     stmt.setInt(3, 1);  
             
                    result = stmt.executeUpdate();  }
			
		 }catch(SQLException ex) {
			System.out.println("Register error -->" + ex.getMessage());
			return false;
                     }
                        if(result == 1){  
                            return true;  
                      }else return false;  
    }  
        
        public static void deleteProduct(int productId) {
		Connection con = null;
		PreparedStatement ps = null;
                int costumerId = SessionUtils.getUserId();
	
                int result = 0;  
		try {
                    con = DataConnect.getConnection();
                     ps = con.prepareStatement("Select costumer_productId, quantity from costumer_product where costumerId = ? and productId=?");
		     ps.setInt(1, costumerId);
                     ps.setInt(2, productId);
                     
                     ResultSet rs = ps.executeQuery();
                     
                     if (rs.next()) {
                         if (rs.getInt("quantity")==1){
                               ps = con.prepareStatement("delete from costumer_product where costumer_productId = ? ");
                               ps.setInt(1, rs.getInt("costumer_productId"));
                               ps.executeUpdate();
                               
                         } else{
                                    ps = con.prepareStatement("update costumer_product set quantity = ? where costumer_productId = ? ");
                                    ps.setInt(1, rs.getInt("quantity")-1);
                                    ps.setInt(2, rs.getInt("costumer_productId"));
                                    ps.executeUpdate();
                         }
		     }
			
		 }catch(SQLException ex) {
			System.out.println("Register error -->" + ex.getMessage());
        }
    }  

        public static ArrayList getProductsFromCartById() {
		Connection con = null;
		PreparedStatement ps = null;
                ArrayList<CostumerProduct> products = new ArrayList<CostumerProduct>();
                int costumerId = SessionUtils.getUserId();
               
		try {
                         con = DataConnect.getConnection();
                        ps = con.prepareStatement("Select costumer_productId, productId, quantity from costumer_product where costumerId = ?");
			ps.setInt(1, costumerId);

			ResultSet rs = ps.executeQuery();
			
                        while (rs.next()) {
                        CostumerProduct costumer_product = new CostumerProduct(rs.getInt("costumer_productId"),costumerId,rs.getInt("productId"),rs.getInt("quantity"));
                        
                        products.add(costumer_product);

                    }
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
                }finally {
			DataConnect.close(con);
		}
		return products;
	}
        
        
       
  }
