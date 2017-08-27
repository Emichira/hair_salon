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
    //  test to check whether can recognize similar objects in true manner
      @Test
      public void equals_returnsTrueIfDescriptionsAretheSame() {
        Client firstClient = new Client("Jessicah", 1);
        Client secondClient = new Client("Jessicah", 1);
        assertTrue(firstClient.equals(secondClient));
      }
      //assign and access IDs
      @Test
      public void getId_clientInstantiateWithAnID() {
        Client myClient = new Client("Jessicah", 1);
        myClient.save();
        assertTrue(myClient.getId() > 0);
      }
      //locating specific Tasks using their unique ID
      @Test
      public void find_returnsClientWithSameId_secondClient() {
        Client firstClient = new Client("Jessicah", 1);
        firstClient.save();
        Client secondClient = new Client("Edna", 1);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
      }

}
