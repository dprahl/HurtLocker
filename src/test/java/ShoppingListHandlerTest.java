import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by danielprahl on 5/31/17.
 */
public class ShoppingListHandlerTest {
    ShoppingListHandler handler;

    @Before
    public void init(){
        handler = new ShoppingListHandler();
    }

    @Test
    public void getEntryTestMilk(){
        //given;
        String expected = "Milk";

        //when;
        String actual = handler.getEntry("MILK").getName();

        //then;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEntryTestBread(){
        //given;
        String expected = "Bread";

        //when;
        String actual = handler.getEntry("BREAD").getName();

        //then;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEntryTestCookies(){
        //given;
        String expected = "Cookies";

        //when;
        String actual = handler.getEntry("COOKIES").getName();

        //then;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEntryTestApples(){
        //given;
        String expected = "Apples";

        //when;
        String actual = handler.getEntry("aPPLES").getName();

        //then;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getEntryTestNull(){
        //given;
        Object expected = null;

        //when;
        Object actual = handler.getEntry("");

        //then;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void allEntriesToStringTest(){
        //given;
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        handler.populateItems(input);
        handler.populateEntries();

        //then;
        String actual = handler.allEntriesToString();
        System.out.println(actual);

        //when;

    }
}
