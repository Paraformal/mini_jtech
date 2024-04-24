package org.example.apispringfron.requestHandler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class getHandler {


    public String getAllEmployees(){
        String responseBody;
        String url = "http://localhost:8080/api/v1/employees";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try(Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) {
                throw new IOException("Unexpected error " + response);
            }

            responseBody = response.body().string();
            System.out.printf(responseBody);
        } catch (IOException error){
            responseBody = error.toString();
            error.printStackTrace();
        }

        return responseBody;
    }

    public String getEmployeeById(int employeeId){
        String responseBody;
        String url = "http://localhost:8080/api/v1/employees/" + employeeId;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try(Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) {
                throw new IOException("Unexpected error " + response);
            }

            responseBody = response.body().string();
            System.out.printf(responseBody);
        } catch (IOException error){
            responseBody = error.toString();
            error.printStackTrace();
        }

        return responseBody;
    }
}
