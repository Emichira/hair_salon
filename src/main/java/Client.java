// imports
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String description;
  private boolean completed;
  private int id;
  private int stylistId;


// constructor client
public Client (String description, int stylistId) {
  this.description = description;
  completed = true;
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

  // to hold and return all client database entries
  public static List<Client> all() {
    String sql = "SELECT id, description, stylistId  FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  // to override the methods to prevent data redundancy
    @Override
    public boolean equals(Object otherClient) {
      if (!(otherClient instanceof Client)) {
        return false;
      } else {
        Client newClient = (Client) otherClient;
        return this.getDescription().equals(newClient.getDescription()) &&
             this.getId() == newClient.getId() &&
             this.getStylistId() == newClient.getStylistId();
      }
    }
    // save method for storing our client data
    public void save() {
      try(Connection con = DB.sql2o.open()) {
        String sql = "INSERT INTO clients (description, stylistId) VALUES (:description, :stylistId)";
        this.id = (int) con.createQuery(sql, true)
          .addParameter("description", this.description)
          .addParameter("stylistId", this.stylistId)
          .executeUpdate()
          .getKey();
      }
    }

  // to find clients based on id
    public static Client find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM clients where id=:id";
        Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
        return client;
      }
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
