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
public class Orders {
private static final long serialVersionUID = 1094801825228386363L;
    
      private List<OrderItem> order_items;
      private float total;
    
        @PostConstruct
        public void init() {
          this.order_items = OrderDAO.getProductsFromOrdersById();
          this.calculateTotal();
        }
     
        public List<OrderItem> getOrder_items() {
                return order_items;
        }
        
         public void calculateTotal() {
              for(int i=0;i<order_items.size();i++){
                 this.total+= order_items.get(i).getPrice() ;
              }
         }
         
          public float getTotal() {
              return total;
         }
          
}
