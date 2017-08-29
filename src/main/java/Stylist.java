// imports
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String description;
  private int id;
  private String image;


  public Stylist(String description, String image) {
    this.description = description;
    this.image = image;
  }

  //returns the description of the stylist
    public String getDescription() {
     return description;
   }
   // to return the ids of the stylists
    public int getId() {
      return id;
    }

    // returns the image of stylist
   public String getImage() {
    return image;
    }

    // returns list of stylists within the database
    public static List<Stylist> all() {
      String sql = "SELECT id, description, image FROM stylists";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Stylist.class);
      }
    }

// to return all client objects
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
         String sql = "INSERT INTO stylists(description, image) VALUES (:description, :image)";
         this.id = (int) con.createQuery(sql, true)
           .addParameter("description", this.description)
           .addParameter("image", this.image)
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
     // override method used to prevent data redundancy
   @Override
     public boolean equals(Object otherStylist) {
       if (!(otherStylist instanceof Stylist)) {
         return false;
       } else {
         Stylist newStylist = (Stylist) otherStylist;
         return this.getDescription().equals(newStylist.getDescription()) &&
               this.getImage().equals(newStylist.getImage()) &&
               this.getId() == newStylist.getId();
       }
     }

 //update methods
     public void update(String description, String image) {
       try(Connection con = DB.sql2o.open()) {
         String sql = "UPDATE stylists SET (description, image)= (:description, :image) WHERE id = :id";
         con.createQuery(sql)
         .addParameter("description", description)
         .addParameter("image", image)
         .addParameter("id", id)
         .executeUpdate();
       }
     }

 // delete methods
     public void delete() {
       try(Connection con = DB.sql2o.open()) {
         String sql = "DELETE FROM stylists WHERE id = :id;";
         con.createQuery(sql)
         .addParameter("id", id)
         .executeUpdate();
       }
     }



}
