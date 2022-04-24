import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {

    public static String getCountryDataFromAPI(String country, String phoneNumber) throws Exception {

        // If country contains spaces, they are replaced by %20
        if (country.contains(" ")) {
            String[] pieces = country.split(" ");
            country = "";

            // Adds all the pieces except the last one
            for (int i = 0; i < pieces.length - 1; i++) {
                country += pieces[i] + "%20";
            }
            // Add last piece without the trailing %20
            country += pieces[pieces.length - 1];
        }

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

        JSONObject json = new JSONObject(jsonString);
        JSONArray data = new JSONArray(json.opt("data").toString());

        if (data.isEmpty()) {
            return "-1";
        }

        json = new JSONObject(data.get(0).toString());

        String capital = json.opt("capital").toString();

        JSONArray callingCodesArray = new JSONArray(json.opt("callingCodes").toString());
        String callingCode = callingCodesArray.get(callingCodesArray.length() - 1).toString();

        if (capital.equals("null") || callingCodesArray.isEmpty()) {
            return "-1";
        }

        String response = "The capital is " + capital
                + " and the phone number is: +"
                + callingCode + " " + phoneNumber + ".";

        in.close();
        return response;
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