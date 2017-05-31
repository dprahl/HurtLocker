import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

/**
 * Created by danielprahl on 5/31/17.
 */
public class JerkSONParserTest {

    @Test
    public void separateInputTest(){
        //given;
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        int expected = 28;

        //then;
        String[] separated = JerkSONParser.separateInput(input);
        int actual = separated.length;

        //when;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void separateLinesTest(){
        //given;
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##naMe:MilK;priCe:;type:Food;expiration:4/25/2016##naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food^expiration:1/04/2016##";
        int expected = 28;

        //then;
        ArrayList<String> seperated = JerkSONParser.separateLines(input);
        int actual = seperated.size();

        //when;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getValueNameTest(){
        //given;
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";
        String expected = "Milk";

        //then;
        String actual = JerkSONParser.getValue(input, JerkSONParser.NAME);

        //when;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getValuePriceTest(){
        //given;
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";
        String expected = "3.23";

        //then;
        String actual = JerkSONParser.getValue(input, JerkSONParser.PRICE);

        //when;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getValueTypeTest(){
        //given;
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";
        String expected = "Food";

        //then;
        String actual = JerkSONParser.getValue(input, JerkSONParser.TYPE);

        //when;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getValueExpirationTest(){
        //given;
        String input = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##";
        String expected = "1/25/2016";

        //then;
        String actual = JerkSONParser.getValue(input, JerkSONParser.EXPIRATION);

        //when;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = PatternSyntaxException.class)
    public void getValueTestException(){
        //given;
        String input = "naMe:Milk;price:;type:Food;expiration:1/25/2016##";
        String expected = "3.23";

        //then;
        String actual = JerkSONParser.getValue(input, JerkSONParser.PRICE);

        //when;
        Assert.assertEquals(expected, actual);
    }

}
