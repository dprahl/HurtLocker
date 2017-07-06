import java.io.*;

public class Solution { // for HireVue assessment for JPMorganChase
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null) {
            s = convert(Integer.parseInt(s)) + "Dollars";
            System.out.println(s);
        }
    }

    // implement zero as empty string, makes no sense to concat as a word
    private static String[] units = {
            "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    // here, zero AND ten are empty strings, makes no sense to concat as words
    private static String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety"
    };

    // use this method recursively!
    public static String convert(Integer number){
        // deal with (in order) <10 <100 <1000 <1000000
        if (number < 20) {
            return units[number];
        }
        if (number < 100) {
            return tens[number / 10] + convert(number % 10);
        }
        if (number < 1000) {
            return units[number / 100] + "Hundred" + convert(number % 100);
        }
        if (number < 1000000) {
            return convert(number / 1000) + "Thousand" + convert(number % 1000);
        }
        return convert(number / 1000000) + "Million" + convert(number % 1000000);
    }
}

