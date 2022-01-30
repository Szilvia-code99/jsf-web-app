package beans;
 
import beans.SessionUtils;
import dao.CostumerDAO;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ReferencedBean;  
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
  
@ManagedBean  
@ReferencedBean  
public class Product {  
private static final long serialVersionUID = 1094801825228386363L;
	
        private int productId;
	private String name;
	private float price;
	private String image;
        private String description;
        
        public Product(){
            
        }
        
        public Product(int productId,String name,float price,String image,String description){
            this.productId=productId;
            this.name = name;
            this.price=price;
            this.image=image;
            this.description=description;
        }

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
        public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}