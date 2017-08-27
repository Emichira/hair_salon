// imports
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String description;
  private boolean completed;
  private LocalDateTime createdAt;
  private int id;
  private int stylistId;


// constructor client
public Client (String description, int stylistId) {
  this.description = description;
  completed = true;
  createdAt = LocalDateTime.now();
  this.stylistId = stylistId;
}

//to get description
  public String getDescription() {
    return description;
  }

// to get ids of the clients
  public int getId() {
    return id;
  }
  public int getStylistId() {
    return stylistId;
  }

  //updating  methods
      public void update(String description) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "UPDATE clients SET description = :description WHERE id = :id";
          con.createQuery(sql)
          .addParameter("description", description)
          .addParameter("id", id)
          .executeUpdate();
        }
      }
  // deleting  methods
      public void delete() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "DELETE FROM clients WHERE id = :id;";
          con.createQuery(sql)
          .addParameter("id", id)
          .executeUpdate();
        }
      }
}
