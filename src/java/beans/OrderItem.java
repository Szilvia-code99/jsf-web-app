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
public class OrderItem {
        
        private int order_itemId;
        private int costumerId;
        private int productId;
        private int quantity;
        private float price;
        private Date date;
        
        public OrderItem(){
            
        }
        
        public OrderItem(int order_itemId,int costumerId,int productId, int quantity, Date date){
            this.order_itemId=order_itemId;
            this.costumerId= costumerId;
            this.productId=productId;
            this.quantity=quantity;
            this.price =ProductDAO.getProductById(productId).getPrice() * quantity;
            this.date=date;
        }


	public int getOrder_itemId() {
		return order_itemId;
	}

	public void setOrder_itemId(int order_itemId) {
		this.order_itemId = order_itemId;
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
