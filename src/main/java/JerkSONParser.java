import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by danielprahl on 5/31/17.
 */
public class JerkSONParser {

    // name       =  4 letters:possibly(some number of letters);
    // price      =  5 letters:possibly(1 digit.2 digits);
    // type       =  type:Food[:^%*!@;]
    // expiration =  expiration:1 digit/2 digits/2016
    // delimiter  =  ##
    //  regex to match individual entry (entire line)
    //    \D{4}:\w*;\D{5}:(\d{1}\.\d{2})?;type:Food[:^%*!@;]\D{10}:\d{1}\/\d{2}\/2016##

    // pattern constants
    public static final String NAME = "\\D{4}:\\w*";
    public static final String PRICE = "\\D{5}:(\\d\\.\\d{2})?";
    public static final String TYPE = "type:Food";
    public static final String EXPIRATION = "\\D{10}:\\d\\/\\d{2}\\/2016";

    public static String[] separateInput(String rawInput){
        Pattern splitter = Pattern.compile("##");
        return splitter.split(rawInput);
    }

    public static ArrayList<String> separateLines(String rawInput){
        ArrayList<String> results = new ArrayList<String>();
        String patternString =
                "\\D{4}:\\w*;\\D{5}:(\\d\\.\\d{2})?;type:Food[:^%*!@;]\\D{10}:\\d/\\d{2}/2016##";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(rawInput);
        while(matcher.find()){
            results.add(matcher.group());
        }
        return results;
    }

    public static String getValue(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            String match = matcher.group();
            Pattern splitter = Pattern.compile(":");
            String[] splitten = splitter.split(match);
            if (splitten.length == 2) {
                return splitten[1];
            } else {
                throw new PatternSyntaxException("No value found.", regex, 0);
            }
        }else{
            throw new PatternSyntaxException("No key found.", regex, 0);
        }
    }


}
