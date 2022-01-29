/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CartDAO;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ReferencedBean;

/**
 *
 * @author Szilvia
 */
@ManagedBean  
@ReferencedBean 
public class CartProduct {
        
        private int cartProductId;
        private Costumer costumer;
        private Product product;
        private int quantity;
        
        public CartProduct(){
            
        }

	public int getCartProductId() {
		return cartProductId;
	}

	public void setCartProductId(int cartProductId) {
		this.cartProductId = cartProductId;
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}
        
        public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product=product;
	}
        
        public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
        
        public void addToCart(Product product){
            boolean valid = CartDAO.addToCart(costumer,product,1);
        }

    
}
