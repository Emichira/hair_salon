// imports
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String description;
  private int id;

  public Stylist(String description) {
    this.description = description;
  }

  //returns the description of the stylist
    public String getDescription() {
     return description;
   }
 // to return the ids of the stylists
    public int getId() {
      return id;
    }

// to return list of the stylists within the database
    public static List<Stylist> all() {
     String sql = "SELECT id, description FROM stylists";
     try(Connection con = DB.sql2o.open()) {
       return con.createQuery(sql).executeAndFetch(Stylist.class);
     }
   }

   //method for saving stylists in the database
   public void save() {
         try(Connection con = DB.sql2o.open()) {
           String sql = "INSERT INTO stylists(description) VALUES (:description)";
           this.id = (int) con.createQuery(sql, true)
             .addParameter("description", this.description)
             .executeUpdate()
             .getKey();
         }
       }


}
