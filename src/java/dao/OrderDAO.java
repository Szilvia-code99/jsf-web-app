/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.CostumerProduct;
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
                       /*  PreparedStatement stmt = con.prepareStatement("insert into costumer(username,password) values(?,?);");  
                         //for(int i=0;i<costumer_products.size();i++){
                        // System.out.println(costumer_products.get(i).getCostumer_productId());
                         ps.setString(2, 1);
                         ps.setInt(1, costumer_products.get(i).getCostumer_productId());
                     
                         ps.executeUpdate();*/
                       
                        PreparedStatement stmt = con.prepareStatement("insert into shop.order(costumerId,productId,quantity) values(?,?,?);");  
                        for(int i=0;i<costumer_products.size();i++){
                       
                         stmt.setInt(1, costumer_products.get(i).getCostumerId());
                         stmt.setInt(2, costumer_products.get(i).getProductId());
                         stmt.setInt(3, costumer_products.get(i).getQuantity());
                     
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
}
