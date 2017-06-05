import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by danielprahl on 5/31/17.
 */
public class JerkSONParser {

    // regex pattern constants --> maybe switch to enums?
    public static final String NAME = "[Nn]\\D{3}:\\w*";
    public static final String PRICE = "[Pp]\\D{4}:(\\d\\.\\d{2})?";
    public static final String TYPE = "[Tt]\\D{3}:\\w*[:^%*!@;]";
    public static final String EXPIRATION = "[Ee]\\D{9}:\\d{1,2}\\/\\d{2}\\/\\d{4}";

    public static String[] separateInput(String rawInput){
        Pattern splitter = Pattern.compile("##");
        return splitter.split(rawInput);
    }

    public static String getValue(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if(matcher.find()) {
            String match = matcher.group();
            Pattern splitter = Pattern.compile("[:^%*!@;]");
            String[] splitten = splitter.split(match);
            if (splitten.length >= 2) {
                return splitten[1];
            } else {
                throw new PatternSyntaxException("No value found.", regex, 0);
            }
        }else{
            throw new PatternSyntaxException("No key found.", regex, 0);
        }
    }
}
