package org.example.apispringfron.requestHandler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class getHandler {
    private static OkHttpClient client = new OkHttpClient();
    private String defaultApiBody = "http://localhost:8080/api/v1/employees";

    public String getAllEmployees(){
        String responseBody;
        String url = defaultApiBody;

        Request request = new Request.Builder()
                .url(url)
                .get()
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

    public String getEmployeeById(int employeeId){
        String responseBody;
        String url = defaultApiBody + "/" + employeeId;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected error: " + response);
            }
            responseBody = response.body().string();
        } catch (IOException error) {
            responseBody = null;
            System.err.println(error.toString());
            error.printStackTrace();
        }

        return responseBody;
    }
}
