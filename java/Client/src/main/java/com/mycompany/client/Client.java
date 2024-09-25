/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.mycompany.client.rest.Response;
import com.mycompany.client.rest.Service;

public class Client {
    private static final MenuItem[] items = {
        new CreateUserMenuItem(),
        new CreateTownMenuItem(),
        new CreateCategoryMenuItem(),
        new CreateBelongsMenuItem(),
        new CreatePackageMenuItem(),
        new CreateRatingMenuItem(),
        new CreateSubMenuItem(),
        new CreateVideoMenuItem(),
        new CreateWatchedMenuItem(),
        new DeleteRatingMenuItem(),
        new DeleteVideoMenuItem(),
        new GetCategoriesMenuItem(),
        new GetCategoriesOfVideoMenuItem(),
        new GetPackagesMenuItem(),
        new GetRatingsOfVideoMenuItem(),
        new GetSubsOfUserMenuItem(),
        new GetTownsMenuItem(),
        new GetUsersMenuItem(),
        new GetVideosMenuItem(),
        new GetWatchesOfVideo(),
        new UpdatePackagePriceMenuItem(),
        new UpdateRatingRateMenuItem(),
        new UpdateUserEmailMenuItem(),
        new UpdateUserTownMenuItem(),
        new UpdateVideoNameMenuItem(),
        new ExitMenuItem()
    };
    
     public static void main(String[] arguments) {
        Scanner scanner = new Scanner(System.in);
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:8080/Server/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        Gson gson = new Gson();
        Type errorResponseType = new TypeToken<Response>() {}.getType();
        Service service = retrofit.create(Service.class);
        while (true) {
            try {
                System.out.println("Choose an option:");
                IntStream.range(0, items.length).forEach(i -> {
                    System.out.format("\t%02d. %s\n", i, items[i].name());
                });
                int option = Integer.parseInt(scanner.nextLine());
                if (option > items.length || option < 0) {
                    System.err.println("Invalid option number!");
                    continue;
                }
                retrofit2.Response response = items[option]
                    .execute(scanner, service)
                    .execute();
                Object responseBody = response.body();
                if (!response.isSuccessful() || responseBody == null) {
                    System.err.println("Request failed!");
                    System.err.println("Error code: " + response.code());
                    ResponseBody errorBody = response.errorBody();
                    if (errorBody != null) {
                        Response errorResponse = gson.fromJson(errorBody.string(), errorResponseType);
                        System.err.println(errorResponse.reason);
                    }
                } else {
                    System.out.println(responseBody);
                }
            } catch (Exception ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
