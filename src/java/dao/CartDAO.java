
package dao;

import beans.Costumer;
import beans.CartItem;
import beans.Product;
import beans.SessionUtils;
import static com.oracle.util.Checksums.update;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                     ps = con.prepareStatement("Select cart_itemId, quantity from cart_item where costumerId = ? and productId=?");
		     ps.setInt(1, costumerId);
                     ps.setInt(2, productId);
                     
                     ResultSet rs = ps.executeQuery();
                     
                     if (rs.next()) {
                        ps = con.prepareStatement("update cart_item set quantity = ? where cart_itemId = ? ");
			ps.setInt(1, rs.getInt("quantity")+1);
                        ps.setInt(2, rs.getInt("cart_itemId"));
                        ps.executeUpdate();
		     }else{
                     PreparedStatement stmt = con.prepareStatement("insert into cart_item(costumerId,productId,quantity) values(?,?,?);");  
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
        
       
        public static boolean emptyCart(List<CartItem> cart_items) {
		Connection con = null;
		PreparedStatement ps = null;
                int costumerId = SessionUtils.getUserId();
	
                int result = 0;  
		try {
                       con = DataConnect.getConnection();
                       ps = con.prepareStatement("delete from cart_item where cart_itemId = ? ");
                         for(int i=0;i<cart_items.size();i++){
                            
                              ps.setInt(1, cart_items.get(i).getCart_itemId());
                              result= ps.executeUpdate();
                     }
                               
		 }catch(SQLException ex) {
			System.out.println("Emptying cart error -->" + ex.getMessage());
               }
                  if(result == 1){  
                    return true; }
                  else return false; 
     }
    
        
        public static void deleteProductFromCart(int productId) {
		Connection con = null;
		PreparedStatement ps = null;
                int costumerId = SessionUtils.getUserId();
	
                int result = 0;  
		try {
                    con = DataConnect.getConnection();
                     ps = con.prepareStatement("Select cart_itemId, quantity from cart_item where costumerId = ? and productId=?");
		     ps.setInt(1, costumerId);
                     ps.setInt(2, productId);
                     
                     ResultSet rs = ps.executeQuery();
                     
                     if (rs.next()) {
                         if (rs.getInt("quantity")==1){
                               ps = con.prepareStatement("delete from cart_item where cart_itemId = ? ");
                               ps.setInt(1, rs.getInt("cart_itemId"));
                               ps.executeUpdate();
                               
                         } else{
                                    ps = con.prepareStatement("update cart_item set quantity = ? where cart_itemId = ? ");
                                    ps.setInt(1, rs.getInt("quantity")-1);
                                    ps.setInt(2, rs.getInt("cart_itemId"));
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
                ArrayList<CartItem> products = new ArrayList<CartItem>();
                int costumerId = SessionUtils.getUserId();
               
		try {
                        con = DataConnect.getConnection();
                        ps = con.prepareStatement("Select cart_itemId, productId, quantity from cart_item where costumerId = ?");
			ps.setInt(1, costumerId);

			ResultSet rs = ps.executeQuery();
			
                        while (rs.next()) {
                        CartItem cart_item = new CartItem(rs.getInt("cart_itemId"),costumerId,rs.getInt("productId"),rs.getInt("quantity"));
                        
                        products.add(cart_item);
                    }
		} catch (SQLException ex) {
			System.out.println("cart_item error -->" + ex.getMessage());
                }finally {
			DataConnect.close(con);
		}
		return products;
	}
  }
