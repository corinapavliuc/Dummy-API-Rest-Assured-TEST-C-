package tests;

import com.github.javafaker.Faker;
import dto.CreateUserRequest;
import dto.CreateUserResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tests.BaseTest.postRequest;
public class CreateUserTest {
    Faker faker = new Faker();

    @Test
    public void successCreateUser() {
        //Faker faker = new Faker();// dlja raddom email
        String userEmail = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();

        //CreateUserRequest requestBody = new CreateUserRequest(firstName, lastname, userEmail);
        CreateUserRequest reqBodyBuilder = CreateUserRequest.builder() // sozdajem
                .firstName(firstName)
                .lastName(lastname)
                .email(userEmail)
                .build();
        Response response = postRequest("/user/create", 200, reqBodyBuilder);

        //Check that all fields are not empty
        CreateUserResponse responseBody = response.getBody().as(CreateUserResponse.class);
        assertFalse(responseBody.getId().isEmpty());
        assertFalse(responseBody.getFirstName().isEmpty());
        assertFalse(responseBody.getLastName().isEmpty());
        assertFalse(responseBody.getEmail().isEmpty());
        assertFalse(responseBody.getRegisterDate().isEmpty());
        assertFalse(responseBody.getUpdatedDate().isEmpty());

        // Check that email value from request equal to email value from response
        assertEquals(responseBody.getEmail(), responseBody.getEmail());

        //registerDate  == updatedDate (sovpadaet)
        assertEquals(responseBody.getRegisterDate(), responseBody.getUpdatedDate());
    }
//        Faker faker = new Faker();// dlja raddom email
//        String userEmail = faker.internet().emailAddress();
//        CreateUserRequest requestBody = new CreateUserRequest(
//                "John", "Black", userEmail); //"jblack34567@gmail.com"); //kazdij raz unikalnij email dolzen bitj
//        postRequest("/user/create", 200, requestBody);
//    }


    //Check that all fields are not empty includes
    //    "gender":"male",
    //    "title" : "mr",
    //    "phone" : "849765455322"
    @Test
    public void successCreateUserAdditionalFields() {
        //Faker faker = new Faker();// dlja random email
        String userEmail = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();
        String gender = "male";
        String title = "mr";
        String phone = "849765455322";

        CreateUserRequest requestBody = CreateUserRequest.builder()
                .firstName(firstName)
                .lastName(lastname)
                .email(userEmail)
                .gender(gender)
                .title(title)
                .phone(phone)
                .build();
        Response response = postRequest("/user/create", 200, requestBody
        );

        CreateUserResponse responseBody = response.getBody().as(CreateUserResponse.class);

        assertFalse(responseBody.getId().isEmpty());
        assertFalse(responseBody.getFirstName().isEmpty());
        assertFalse(responseBody.getLastName().isEmpty());
        assertFalse(responseBody.getEmail().isEmpty());
        assertFalse(responseBody.getGender().isEmpty());
        assertFalse(responseBody.getTitle().isEmpty());
        assertFalse(responseBody.getRegisterDate().isEmpty());
        assertFalse(responseBody.getPhone().isEmpty());
        assertFalse(responseBody.getUpdatedDate().isEmpty());
    }

    //negativnie testi Path email is required 400
    @Test
    public void createUserWithoutEmail() {
        // Faker faker = new Faker();// dlja random email
        String firstName = faker.name().firstName();
        String lastname = faker.name().lastName();

        CreateUserRequest requestBody = CreateUserRequest.builder()
                .firstName(firstName)
                .lastName(lastname)
                .build();
        Response response = postRequest("/user/create", 200, requestBody
        );
    }
}