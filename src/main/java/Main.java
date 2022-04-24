import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import org.json.JSONObject;

public class Main {

    public static String getCountryDataFromAPI (String country, String phoneNumber) throws Exception {
        String url = "https://jsonmock.hackerrank.com/api/countries?name="
                + country;

        URL API_URL = new URL(url);
        URLConnection yc = API_URL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String jsonString = in.readLine();

        if (jsonString == null) {
            return "-1";
        }

        in.close();
        return jsonString;
    }
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter country name:");
        String country = scanner.nextLine();

        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();

        System.out.println(getCountryDataFromAPI(country, phoneNumber));
    }
}