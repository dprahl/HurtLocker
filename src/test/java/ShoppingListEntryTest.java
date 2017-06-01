import org.junit.Assert;
import org.junit.Test;

/**
 * Created by danielprahl on 6/1/17.
 */
public class ShoppingListEntryTest {

    @Test
    public void addPriceFirstTest(){
        //given;
        ShoppingListEntry entry = new ShoppingListEntry("Test");
        int expected = 2;

        //when;
        entry.addPrice("99.99");
        entry.addPrice("99.99");
        int actual = entry.getFirstPriceCount();

        //then;
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void addPriceSecondTest(){
        //given;
        ShoppingListEntry entry = new ShoppingListEntry("Test");
        String expected = "0.99";

        //when;
        entry.addPrice("99.99");
        entry.addPrice("0.99");
        String actual = entry.getSecondPrice();

        //then;
        Assert.assertEquals(expected, actual);

    }
}
