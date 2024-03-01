package tests;

import com.github.javafaker.Faker;
import dto.CreateUserRequest;
import dto.CreateUserResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.BaseTest.deleteRequest;
import static tests.BaseTest.postRequest;

public class DeleteUserTest {
    Faker faker = new Faker();

    @Test
    public void deleteExistingUser() {
        //Pre-steps
        String userEmail = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        CreateUserRequest reqBodyBuilder = CreateUserRequest.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(userEmail)
                .build();
        Response response = postRequest("/user/create", 200, reqBodyBuilder);
        CreateUserResponse responseBody = response.getBody().as(CreateUserResponse.class);
        String userId = responseBody.getId(); //estj ID
//uze sam Test idet
        String deleteEndpoint = "/user/" + userId;
        Response deleteUserResponse = deleteRequest(deleteEndpoint, 200); //200 OK
        assertEquals(userId, deleteUserResponse.getBody().jsonPath().getString("id")); //Return id of deleted user
        //String deletedUserIdFromResponse =
        //                deleteResponse.body().jsonPath().getString("id");
        //        assertEquals(userID, deletedUserIdFromResponse);
        //    }
    }
    //1. Delete deleted user
    @Test
    public void deleteDeletedUser(){
        //Pre-Steps
        String userEmail = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        CreateUserRequest reqBodyBuilder = CreateUserRequest.builder()
                .firstName(firstName)
                .lastName(lastname)
                .email(userEmail).build();
        Response response = postRequest("/user/create", 200, reqBodyBuilder);
        CreateUserResponse createdUser = response.getBody().as(CreateUserResponse.class);
        String userID = createdUser.getId();
        deleteRequest("/user/" + userID, 200);
        Response deletedUserResponse =  deleteRequest("/user/" + userID, 404);
        String errorMessage = deletedUserResponse.body().jsonPath().getString("error");
        assertEquals("RESOURCE_NOT_FOUND", errorMessage);
    }
    //2. Delete invalid user
    @Test
    public void deleteInvalidUser(){
        String invalidUserId = "213426";
        Response deletedUserResponse =  deleteRequest("/user/" + invalidUserId, 400);
        String errorMessage = deletedUserResponse.body().jsonPath().getString("error");
        assertEquals("PARAMS_NOT_VALID", errorMessage);
    }
}
