package org.example.apispringfron.requestHandler;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class postHandler {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public String addEmployee(String firstName, String lastName, String emailId) {

        String responseBody;
        String url = "http://localhost:8080/api/v1/employees/add/user";

        OkHttpClient client = new OkHttpClient();

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
                throw new IOException("Unexpected error " + response);
            }

            responseBody = response.body().string();
            System.out.printf(responseBody);
        } catch (IOException error) {
            responseBody = error.toString();
            error.printStackTrace();
        }

        return responseBody;
    }
}
