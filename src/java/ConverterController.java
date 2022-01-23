
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.component.UIPanel;
import javax.faces.context.FacesContext;


@ManagedBean(name = "converterController")
public class ConverterController {

   private Converter converter = new Converter();
   private UIPanel resultPanel;
   private UIInput numberInput;

   public Converter getConverter() {
      return converter;
   }

   public void setConverter(Converter converter) {
      this.converter = converter;
   }

   public UIPanel getResultPanel() {
      return resultPanel;
   }

   public void setResultPanel(UIPanel resultPanel) {
      this.resultPanel = resultPanel;
   }

   public UIInput getNumberInput() {
      return numberInput;
   }

   public void setNumberInput(UIInput numberInput) {
      this.numberInput = numberInput;
   }

   public String toBinary() {
      FacesContext ctx = FacesContext.getCurrentInstance();
      try {
         converter.toBinary();
         resultPanel.setRendered(true);
         ctx.addMessage(null, new
               FacesMessage(FacesMessage.SEVERITY_INFO,
            "Decimal number converted to binary", null));
      } catch (Exception ex) {
         resultPanel.setRendered(false);
         ctx.addMessage(null, new
               FacesMessage(FacesMessage.SEVERITY_ERROR,
            ex.getMessage(), null));
      }
      return null;
   }

   public String toHex() {
      FacesContext ctx = FacesContext.getCurrentInstance();
      try {
         converter.toHex();
         resultPanel.setRendered(true);
         ctx.addMessage(null, new
               FacesMessage(FacesMessage.SEVERITY_INFO,
            "Decimal number converted to Hexadecimal",
               null));
      } catch (Exception ex) {
         resultPanel.setRendered(false);
         ctx.addMessage(null, new
               FacesMessage(FacesMessage.SEVERITY_ERROR,
            ex.getMessage(), null));
      }
      return null;
   }

   public String toOct() {
      FacesContext ctx = FacesContext.getCurrentInstance();
      try {
         converter.toOctal();
         resultPanel.setRendered(true);
         ctx.addMessage(null,
               new FacesMessage(FacesMessage.SEVERITY_INFO,
            "Decimal number converted to Octal",
               null));
      } catch (Exception ex) {
         resultPanel.setRendered(false);
         ctx.addMessage(null, new
               FacesMessage(FacesMessage.SEVERITY_ERROR,
            ex.getMessage(), null));
      }
      return null;
   }

   public String clear() {
      FacesContext ctx = FacesContext.getCurrentInstance();
      try {
         converter.clear();
         resultPanel.setRendered(false);
         ctx.addMessage(null, new
               FacesMessage(FacesMessage.SEVERITY_INFO,
            "Results Cleared", null));
      } catch (Exception ex) {
         resultPanel.setRendered(false);
         ctx.addMessage(null, new
               FacesMessage(FacesMessage.SEVERITY_ERROR,
            ex.getMessage(), null));
         }
         return null;
   }
}