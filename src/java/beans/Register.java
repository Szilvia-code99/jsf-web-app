package beans;
import beans.SessionUtils;
import dao.UserDAO;
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
public class Register {  
private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String msg;
	private String user;

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


        public String registerUser() {
            HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
		boolean valid = UserDAO.register(user, pwd);
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