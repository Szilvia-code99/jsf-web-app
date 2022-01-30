package beans;
 
import beans.SessionUtils;
import dao.CostumerDAO;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ReferencedBean;  
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
  
@ManagedBean  
@ReferencedBean  
public class Costumer {  
private static final long serialVersionUID = 1094801825228386363L;
	
        private int id;
	private String pwd;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	//validate login
	public String validateUsernamePassword() {
		boolean valid = CostumerDAO.validate(user, pwd);
		if (valid) {
                  id =  CostumerDAO.getId(user,pwd);
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			session.setAttribute("id",id);
			return "products";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login";
		}
	}

    
	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
        
        public String goToRegister(){  
         return "register.xhtml";  
        }
        
         public String goToCart(){  
         return "cart";  
        }
        
         public String registerUser() {
            HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
		boolean valid = CostumerDAO.register( user,  pwd);
		if (valid) {
                    FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Succesfull",
							"Yey"));
                    return "login";
			
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Username already exists",
							"Please enter correct username and Password"));
			
                        return "register";
		}
	}
  
}