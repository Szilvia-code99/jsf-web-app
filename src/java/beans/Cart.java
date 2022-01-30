/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CartDAO;
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
    
      private List<CostumerProduct> costumer_products;
      private float total;
    
        @PostConstruct
        public void init() {
          this.costumer_products = CartDAO.getProductsFromCartById();
          this.calculateTotal();
        }
     
        public List<CostumerProduct> getCostumer_products() {
                return costumer_products;
        }
        
         public void calculateTotal() {
              for(int i=0;i<costumer_products.size();i++){
                 this.total+= costumer_products.get(i).getPrice() ;
              }
         }
         
          public float getTotal() {
              return total;
         }
          
         public void delete(int productId) throws IOException {
              CartDAO.deleteProduct(productId);
              ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
              ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
         }
}
