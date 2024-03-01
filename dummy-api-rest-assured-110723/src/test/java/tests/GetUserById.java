package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.BaseTest.getRequest;
import static tests.BaseTest.postRequestWithoutBody;

public class GetUserById {
    String requestUserId = "65d72a70f27345e02d37f6a0";
    String invalidUserId = "60d0f";

    @Test
    public void getValidUserById() {
        //check that id, firstName,lastName,gender,email, registerDate , updateDate are
        //not empty
    }
    //1.Invalid user ID

    @Test
    public void invalidUserId(){

        Response response = getRequest("/user/"+invalidUserId, 400);
        assertEquals("PARAMS_NOT_VALID", response.body().jsonPath().getString("error"));
    }

    //2.Invalid http method (POST)

    @Test
    public void inCorrectMethod(){
        Response response = postRequestWithoutBody("/user/"+ requestUserId, 404);
        assertEquals("PATH_NOT_FOUND", response.body().jsonPath().get("error"));
    }
}
