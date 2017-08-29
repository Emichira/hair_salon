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

// to return the list of clients
   public List<Client> getClients() {
     try(Connection con = DB.sql2o.open()) {
       String sql = "SELECT * FROM clients where stylistId=:id";
       return con.createQuery(sql)
       .addParameter("id", this.id)
       .executeAndFetch(Client.class);
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

   // to find stylists based on their id
   public static Stylist find(int id) {
           try(Connection con = DB.sql2o.open()) {
             String sql = "SELECT * FROM stylists where id=:id";
             Stylist stylist = con.createQuery(sql)
               .addParameter("id", id)
               .executeAndFetchFirst(Stylist.class);
             return stylist;
           }
         }


}
