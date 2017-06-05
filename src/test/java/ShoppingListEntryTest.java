import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by danielprahl on 6/1/17.
 */
public class ShoppingListEntryTest {

    ShoppingListHandler handler;

    @Before
    public void setup(){
        handler = new ShoppingListHandler();
    }

    @Test
    public void addPriceFirstTest(){
        //given;
        ShoppingListHandler.ShoppingListEntry entry = handler.new ShoppingListEntry("Test");
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
        ShoppingListHandler.ShoppingListEntry entry = handler.new ShoppingListEntry("Test");
        String expected = "0.99";

        //when;
        entry.addPrice("99.99");
        entry.addPrice("0.99");
        String actual = entry.getSecondPrice();

        //then;
        Assert.assertEquals(expected, actual);

    }
}
