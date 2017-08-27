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
      //locating specific Clients using their unique ID
      @Test
      public void find_returnsClientWithSameId_secondClient() {
        Client firstClient = new Client("Jessicah", 1);
        firstClient.save();
        Client secondClient = new Client("Edna", 1);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
      }

      @Test
      public void isCompleted_isFalseAfterInstantiation_false() {
        Client myClient = new Client("Jessicah", 1);
        assertEquals(false, myClient.isCompleted());
      }

      @Test
      public void getCreatedAt_instantiatesWithCurrentTime_today() {
        Client myClient = new Client("Jessicah", 1);
        assertEquals(LocalDateTime.now().getDayOfWeek(), myClient.getCreatedAt());
      }
      // test used to enhance relationship between parent-class stylist and child-class
      @Test
      public void save_savesStylistIdIntoDB_true() {
        Stylist myStylist = new Stylist("Janet");
        myStylist.save();
        Client myClient = new Client("Jessicah", myStylist.getId());
        myClient.save();
        Client savedClient = Client.find(myClient.getId());
        assertEquals(savedClient.getStylistId(), myStylist.getId());
      }
      // test used for updates
      @Test
      public void update_updatesClientDescription_true() {
        Client myClient = new Client("Jessicah", 1);
        myClient.save();
        myClient.update("Winnie");
        assertEquals("Winnie", Client.find(myClient.getId()).getDescription());
      }
      // test used to delete clients
      @Test
      public void delete_deletesClient_true() {
        Client myClient = new Client("Jessicah", 1);
        myClient.save();
        int myClientId = myClient.getId();
        myClient.delete();
        assertEquals(null, Client.find(myClientId));
      }

}
