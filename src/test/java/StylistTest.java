import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest {
  //  test to check whether similar objects are returned true
  @Test
      public void equals_returnsTrueIfDescriptionsAretheSame() {
        Stylist firstStylist = new Stylist("Janet");
        Stylist secondStylist = new Stylist("Janet");
        assertTrue(firstStylist.equals(secondStylist));
      }

}
