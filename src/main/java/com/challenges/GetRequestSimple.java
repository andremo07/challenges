package com.challenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/*
Java REST GET Simple
In the Java file, write a program to perform a GET request on the route:
https://coderbyte.com/api/challenges/json/rest-get-simple
and then print to the console the hobbies property in the following format: ITEM1, ITEM2, ....

Example Output
running, painting
*/
public class GetRequestSimple {
    public static void main(String[] args) {
        System.setProperty("http.agent", "Chrome");

        try {
            // Set up the URL and connection
            URL url = new URL("https://coderbyte.com/api/challenges/json/rest-get-simple");
            URLConnection connection = url.openConnection();

            // Open an input stream and read the response into a StringBuilder
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }

            // Close the reader
            reader.close();

            // Convert the response to a String
            String jsonResponse = responseBuilder.toString();

            // Extract the value associated with the "hobbies" key
            String hobbies = extractHobbiesValue(jsonResponse);

            // Print the hobbies
            System.out.println(hobbies);
        } catch (IOException e) {
            // Handle exceptions related to I/O and networking
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Extracts the value associated with the "hobbies" key from a JSON string.
     * This method manually parses the JSON without using any external libraries.
     *
     * @param json The JSON string to parse.
     * @return A comma-separated list of hobbies, or an empty string if not found.
     */
    private static String extractHobbiesValue(String json) {
        String key = "\"hobbies\":";
        int keyIndex = json.indexOf(key);
        if (keyIndex == -1) {
            return "";
        }

        // Find the starting bracket of the hobbies array
        int startBracketIndex = json.indexOf("[", keyIndex);
        int endBracketIndex = json.indexOf("]", startBracketIndex);
        if (startBracketIndex == -1 || endBracketIndex == -1) {
            return "";
        }

        // Extract the hobbies array as a substring
        String hobbiesArray = json.substring(startBracketIndex + 1, endBracketIndex);

        // Split the hobbies into individual items by commas
        String[] hobbies = hobbiesArray.split(",");

        // Clean up each hobby item by removing extra quotes and whitespace
        StringBuilder formattedHobbies = new StringBuilder();
        for (String hobby : hobbies) {
            if (formattedHobbies.length() > 0) {
                formattedHobbies.append(", ");
            }
            formattedHobbies.append(hobby.trim().replaceAll("\"", ""));
        }

        return formattedHobbies.toString();
    }
}
