/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Product;
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
public class ProductDAO {
    
	public static ArrayList getAllProducts() {
		Connection con = null;
		PreparedStatement ps = null;
                ArrayList<Product> products = new ArrayList<Product>();
               
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select * from product");
			ResultSet rs = ps.executeQuery();
			
                       while (rs.next()) {
                       Product product=new Product(rs.getInt("productId"),rs.getString("name"),rs.getFloat("price"),rs.getString("image"),rs.getString("description"));
                        
                       products.add(product);

                    }
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
                }finally {
			DataConnect.close(con);
		}
		return products;
	}
        
        public static Product getProductById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
               
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select name,price,image,description from product where productId = ? ");
                        ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
                        
                       while (rs.next()) {
                       Product product=new Product(id,rs.getString("name"),rs.getFloat("price"),rs.getString("image"),rs.getString("description"));
                        
                       return product;

                    }
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
                }finally {
			DataConnect.close(con);
		}
		return null;
	}
}
