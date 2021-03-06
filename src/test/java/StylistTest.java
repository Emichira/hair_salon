import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class StylistTest {

  // connectivity with the database
  @Rule
    public DatabaseRule database = new DatabaseRule();

  //  test to return all stylist instances as true
  @Test
    public void equals_returnsTrueIfDescriptionsAretheSame() {
      Stylist firstStylist = new Stylist("Janet");
      Stylist secondStylist = new Stylist("Janet");
      assertTrue(firstStylist.equals(secondStylist));
    }
  // test to return all instances of stylists as true
  @Test
   public void all_returnsAllInstancesOfStylist_true() {
     Stylist firstStylist = new Stylist("Janet");
     firstStylist.save();
     Stylist secondStylist = new Stylist("Lilian");
     secondStylist.save();
     assertEquals(true, Stylist.all().get(0).equals(firstStylist));
     assertEquals(true, Stylist.all().get(1).equals(secondStylist));
   }
     // test used for assigning id to stylists
   @Test
     public void save_assignsIdToObject() {
       Stylist myStylist = new Stylist("Janet");
       myStylist.save();
       Stylist savedStylist = Stylist.all().get(0);
       assertEquals(myStylist.getId(), savedStylist.getId());
    }
   @Test
      public void getId_stylistsInstantiateWithAnId_1() {
        Stylist testStylist = new Stylist("Janet");
        testStylist.save();
        assertTrue(testStylist.getId() > 0);
      }
    // test used to find and return stylists with the same id
    @Test
      public void find_returnsStylistWithSameId_secondStylist() {
        Stylist firstStylist = new Stylist("Sarah", "image");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Shirley", "image");
        secondStylist.save();
        assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
      }
      // retrieves all clients from the database
      @Test
      public void getClients_retrievesALlClientsFromDatabase_clientsList() {
        Stylist myStylist = new Stylist("Caroline", "image" );
        myStylist.save();
        Client firstClient = new Client("June", myStylist.getId());
        firstClient.save();
        Client secondClient = new Client("Jane", myStylist.getId());
        secondClient.save();
        Client[] clients = new Client[] { firstClient, secondClient };
        assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
      }
      // test used for updates
      @Test
      public void update_updatesStylistDescription_true() {
        Stylist myStylist = new Stylist("Sarah", "image");
        myStylist.save();
        myStylist.update("description", "myImage");
        assertEquals("description", Stylist.find(myStylist.getId()).getDescription());
      }

      // test used to delete clients
      @Test
      public void delete_deletesStylist_true() {
        Stylist myStylist = new Stylist("Sarah", "image");
        myStylist.save();
        int myStylistId = myStylist.getId();
        myStylist.delete();
        assertEquals(null, Stylist.find(myStylistId));
      }
      //test used to check the saved stylists in the database
      @Test
        public void save_insertsObjectIntoDatabase_true() {
          Stylist myStylist = new Stylist("Sarah", "image");
          myStylist.save();
          assertTrue(Stylist.all().get(0).equals(myStylist));
        }

}
