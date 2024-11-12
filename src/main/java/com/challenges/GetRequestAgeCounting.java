package com.challenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/*
Java Age Counting
In the Java file, write a program to perform a GET request on the route:
https://coderbyte.com/api/challenges/json/age-counting

which contains a data key and the value is a string which contains items in the format: key=STRING, age=INTEGER.
Your goal is to count how many items exist that have an age equal to or greater than 50, and print this final value.

Example Input
{"data":"key=IAfpK, age=58, key=WNVdi, age=64, key=jp9zt, age=47"}

Example Output
2
*/
public class GetRequestAgeCounting {

    public static void main(String[] args) {
        // Set the user agent to mimic a browser request
        System.setProperty("http.agent", "Chrome");

        try {
            // Define the URL for the GET request
            URL url = new URL("https://coderbyte.com/api/challenges/json/age-counting");
            URLConnection connection = url.openConnection();

            // Open a connection and get the input stream
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Read the entire response into a StringBuilder
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }

            // Close the reader
            reader.close();

            // Convert the response to a String
            String jsonResponse = responseBuilder.toString();

            // Extract the value associated with the "data" key
            String dataValue = extractDataValue(jsonResponse);

            // If dataValue is empty, no valid data was found
            if (dataValue.isEmpty()) {
                System.out.println("0");
                return;
            }

            // Split the data string into individual items
            String[] items = dataValue.split(", ");

            int count = 0; // Counter for ages >= 50

            // Iterate through the items and count qualifying ages
            for (String item : items) {
                if (item.startsWith("age=")) {
                    String ageStr = item.substring(4); // Extract the age value
                    try {
                        int age = Integer.parseInt(ageStr);
                        if (age >= 50) {
                            count++;
                        }
                    } catch (NumberFormatException e) {
                        // If age is not a valid integer, skip this item
                        // Optionally, you can handle this case differently
                    }
                }
            }

            // Print the final count
            System.out.println(count);
        } catch (IOException e) {
            // Handle exceptions related to I/O and networking
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Extracts the value associated with the "data" key from a JSON string.
     * This method manually parses the JSON without using any external libraries.
     *
     * @param json The JSON string to parse.
     * @return The value of the "data" key, or an empty string if not found.
     */
    private static String extractDataValue(String json) {
        String key = "\"data\":";
        int keyIndex = json.indexOf(key);
        if (keyIndex == -1) {
            return "";
        }

        // Find the starting quote of the data value
        int startQuoteIndex = json.indexOf("\"", keyIndex + key.length());
        if (startQuoteIndex == -1) {
            return "";
        }

        // Find the ending quote of the data value
        int endQuoteIndex = json.indexOf("\"", startQuoteIndex + 1);
        if (endQuoteIndex == -1) {
            return "";
        }

        // Extract and return the data value
        return json.substring(startQuoteIndex + 1, endQuoteIndex);
    }
}
