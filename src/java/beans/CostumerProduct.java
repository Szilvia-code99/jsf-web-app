/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CartDAO;
import dao.ProductDAO;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ReferencedBean;

/**
 *
 * @author Szilvia
 */
@ManagedBean  
@ReferencedBean 
public class CostumerProduct {
        
        private int costumer_productId;
        private int costumerId;
        private int productId;
        private int quantity;
        private float price;
        
        public CostumerProduct(){
            
        }
        
        public CostumerProduct(int costumer_productId,int costumerId,int productId, int quantity){
            this.costumer_productId=costumer_productId;
            this.costumerId= costumerId;
            this.productId=productId;
            this.quantity=quantity;
            this.price =ProductDAO.getProductById(productId).getPrice() * quantity;
        }


	public int getCartProductId() {
		return costumer_productId;
	}

	public void setCartProductId(int cartProductId) {
		this.costumer_productId = cartProductId;
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
        
        public void addToCart(int productId){
            boolean valid = CartDAO.addToCart(productId);
        }
        
        public Product getProductById(){
            return ProductDAO.getProductById(productId);
        }
        
         public String goToCart(){
            return "cart";
        }
}
