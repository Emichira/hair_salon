import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {
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

}
