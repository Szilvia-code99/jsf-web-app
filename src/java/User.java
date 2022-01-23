 
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ReferencedBean;  
  
@ManagedBean  
@ReferencedBean  
public class User {  
String userName;  
String email;  
public String getUserName() {  
return userName;  
}  
public void setUserName(String userName) {  
this.userName = userName;  
}  
public String getEmail() {  
return email;  
}  
public void setEmail(String email) {  
this.email = email;  
}  
  
  
public boolean save(){  
int result = 0;  
try{  
Class.forName("com.mysql.jdbc.Driver");     
Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/login?allowPublicKeyRetrieval=true&useSSL=false","root","sabrina");  
PreparedStatement stmt = con.prepareStatement("insert into user(uname,password) values(?,?);");  
stmt.setString(1, this.getUserName());  
stmt.setString(2, this.getEmail());  
result = stmt.executeUpdate();  
}catch(Exception e){  
System.out.println(e);  
}  
if(result == 1){  
return true;  
}else return false;  
}  
  
public String submit(){  
if(this.save()){  
return "response.xhtml";  
}else return "index.xhtml";  
}     
}  