// imports
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String description;
  private int id;

// constructor client
public Client (String description) {
  this.description = description;
}

//to get description
public String getDescription() {
  return description;
}

// to get ids of the clients
    public int getId() {
      return id;
    }
}
