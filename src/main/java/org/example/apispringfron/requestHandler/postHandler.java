package org.example.apispringfron.requestHandler;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class postHandler {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client = new OkHttpClient();
    private String defaultApiBody = "http://localhost:8080/api/v1/employees";

    public String addEmployee(String firstName, String lastName, String emailId) {

        String responseBody;
        String url = defaultApiBody + "/add/user";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", firstName);
        jsonObject.put("lastName", lastName);
        jsonObject.put("emailId", emailId);

        System.out.println(jsonObject);

        System.out.println();

        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected error: " + response);
            }
            responseBody = response.body().string();
        } catch (IOException error) {
            responseBody = "Error fetching employees: " + error.getMessage();
            System.err.println(error.toString());
            error.printStackTrace();
        }

        return responseBody;
    }
}
