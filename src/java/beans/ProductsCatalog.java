package beans;
 
import beans.SessionUtils;
import dao.ProductDAO;
import dao.CostumerDAO;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ReferencedBean;  
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
  
@ManagedBean  
@ReferencedBean  
public class ProductsCatalog {  
private static final long serialVersionUID = 1094801825228386363L;
	
        private List<Product> products;

        @PostConstruct
        public void init() {
          this.products  = ProductDAO.getAllProducts();
        }
     
        public List<Product> getProducts() {
                return products;
        }
        
        public String goToCart(){  
         return "cart";  
        }
        
}