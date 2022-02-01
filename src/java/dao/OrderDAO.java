/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.CostumerProduct;
import beans.OrderProduct;
import beans.SessionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DataConnect;

/**
 *
 * @author Szilvia
 */
public class OrderDAO {
    
    public static boolean placeOrder(List<CostumerProduct> costumer_products) {
		Connection con = null;
		PreparedStatement ps = null;
                int costumerId = SessionUtils.getUserId();
	
                int result = 0;  
		try {
                         con = DataConnect.getConnection();
                      
                       
                        PreparedStatement stmt = con.prepareStatement("insert into shop.order(costumerId,productId,quantity,date) values(?,?,?,?);");  
                        for(int i=0;i<costumer_products.size();i++){
                       
                         stmt.setInt(1, costumer_products.get(i).getCostumerId());
                         stmt.setInt(2, costumer_products.get(i).getProductId());
                         stmt.setInt(3, costumer_products.get(i).getQuantity());
                         java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                         stmt.setTimestamp(4, date);
                     
                          result =stmt.executeUpdate();
                        }
                         
                      }catch(SQLException ex) {
			System.out.println("Adding order error -->" + ex.getMessage());
			
                     }
                catch (Exception exx) {
			System.out.println(exx.getMessage() + "Costumer_product error -->");
                }
                
                          if(result == 1){  
                           return true;  
                             }else return false; 
            }
    
     public static ArrayList getProductsFromOrdersById() {
		Connection con = null;
		PreparedStatement ps = null;
                ArrayList<OrderProduct> products = new ArrayList<OrderProduct>();
                int costumerId = SessionUtils.getUserId();
               
		try {
                        con = DataConnect.getConnection();
                        ps = con.prepareStatement("Select orderId, productId, quantity,date from shop.order where costumerId = ?");
			ps.setInt(1, costumerId);

			ResultSet rs = ps.executeQuery();
			
                        while (rs.next()) {
                        OrderProduct costumer_product = new OrderProduct(rs.getInt("orderId"),costumerId,rs.getInt("productId"),rs.getInt("quantity"),rs.getTimestamp("date"));
                        
                        products.add(costumer_product);

                    }
		} catch (SQLException ex) {
			System.out.println("Orders error -->" + ex.getMessage());
                }finally {
			DataConnect.close(con);
		}
		return products;
	}
   
}
