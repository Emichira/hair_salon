// imports
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest {
  //  test to return all client instances as true
    @Test
    public void all_returnsAllInstancesOfClient_true() {
      Client firstClient = new Client("Jessicah", 1);
      firstClient.save();
      Client secondClient = new Client("Edna", 1);
      secondClient.save();
      assertEquals(true, Client.all().get(0).equals(firstClient));
      assertEquals(true, Client.all().get(1).equals(secondClient));
    }

}
