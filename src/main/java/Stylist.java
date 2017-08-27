// imports
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String description;

  public Stylist(String description) {
    this.description = description;
  }

  //returns the description of the stylist
    public String getDescription() {
     return description;
   }
}
