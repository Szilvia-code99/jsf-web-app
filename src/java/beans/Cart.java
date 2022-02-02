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
import java.util.ArrayList;
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
public class Cart {
private static final long serialVersionUID = 1094801825228386363L;
    
      private List<CartItem> cart_items;
      private float total;
    
        @PostConstruct
        public void init() {
          this.cart_items = CartDAO.getProductsFromCartById();
          this.calculateTotal();
        }
     
        public List<CartItem> getCart_items() {
                return cart_items;
        }
        
         public void calculateTotal() {
              for(int i=0;i<cart_items.size();i++){
                 this.total+= cart_items.get(i).getPrice() ;
              }
         }
         
          public float getTotal() {
              return total;
         }
          
         public void delete(int productId) throws IOException {
              CartDAO.deleteProductFromCart(productId);
              ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
              ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
         }
         
          public void placeOrder() throws IOException{
             if (OrderDAO.placeOrder(cart_items)){
                 CartDAO.emptyCart(cart_items);
             };
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
             ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }
}
