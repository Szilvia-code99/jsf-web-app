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
        
        public int getCostumer_productId() {
		return costumer_productId;
	}

	public void setCostumer_productId(int costumer_productId) {
		this.costumer_productId = costumer_productId;
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
        
        public void addToCart(int productId) throws IOException{
            boolean valid = CartDAO.addToCart(productId);
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
             ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }
        
         public void placeOrder(List<CostumerProduct> costumer_products) throws IOException{
             if (OrderDAO.placeOrder(costumer_products)){
                 CartDAO.emptyCart(costumer_products);
             };
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
             ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }
        
        public Product getProductById(){
            return ProductDAO.getProductById(productId);
        }
        
         public String goToCart(){
            return "cart";
        }
}
