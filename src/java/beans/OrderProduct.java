/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CartDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ReferencedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Szilvia
 */
@ManagedBean  
@ReferencedBean 
public class OrderProduct {
        
        private int orderId;
        private int costumerId;
        private int productId;
        private int quantity;
        private float price;
        private Date date;
        
        public OrderProduct(){
            
        }
        
        public OrderProduct(int orderId,int costumerId,int productId, int quantity, Date date){
            this.orderId=orderId;
            this.costumerId= costumerId;
            this.productId=productId;
            this.quantity=quantity;
            this.price =ProductDAO.getProductById(productId).getPrice() * quantity;
            this.date=date;
        }


	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
        
	public int getCostumerId() {
		return costumerId;
	}

	public void setCostumerId(int costumerId) {
		this.costumerId = costumerId;
	}
        
        public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId=productId;
	}
        
        public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
        
        public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price=price;
	}
        
        public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date=date;
	}
        
        
        public Product getProductById(){
            return ProductDAO.getProductById(productId);
        }
        
         public String goToCart(){
            return "cart";
        }
         
           public String goToOrders(){
            return "orders";
        }
}
