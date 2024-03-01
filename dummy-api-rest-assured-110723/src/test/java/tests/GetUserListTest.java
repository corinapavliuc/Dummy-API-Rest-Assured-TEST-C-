package tests;


import dto.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static tests.BaseTest.getRequest;
import static tests.BaseTest.getRequestWithoutAppID;

public class GetUserListTest {
    @Test
    public void getUserList() {
        //Request
        //https://dummyapi.io/data/v1/user
        //200 OK list of users
        List<User> users = getRequest("/user", 200)
                .body().jsonPath().getList("data", User.class);
        System.out.println(users.get(0).getFirstName());
        // given().baseUri("https://dummyapi.io/data/v1")
        //.header("app-id", "65438304d1f7d94308af1c97")
        //.when().log().all()
        //.get("/user")
        //.then().log().all().statusCode(200);
        //Check that id of each user is not empty
        users.forEach(user -> assertFalse(user.getId().isEmpty()));
        users.forEach(user -> assertTrue(user.getPicture().endsWith("jpg")));
        users.forEach(user -> assertTrue(user.getPicture().startsWith("https")));
        users.forEach(user -> assertFalse(user.getFirstName().isEmpty()));
        users.forEach(user -> assertFalse(user.getLastName().isEmpty()));

        int limit = getRequest("/user", 200)
                .body().jsonPath().getInt("limit");
        assertEquals(limit, users.size());

    }

    @Test
    public void getUserListWithoutAppID() {
        getRequestWithoutAppID("/user", 403);
//  given().baseUri("https://dummyapi.io/data/v1")
//                .when().log().all()
//                .get("/user")
//                .then().log().all().statusCode(403)
//            .statusLine("HTTP/1.1 403 Forbidden");
    }

    @Test
    public void getUsersListWithSpecLimit() {
        //limit =10
        //List<Users> users
        //int limit
        //Check that users quantity == limit
        int limitValue = 10;
        Response response = getRequest("user?limit=" + limitValue, 200);
        int expectedLimit = response.body().jsonPath().getInt("limit");
        List<User> users = response.body().jsonPath().getList("data");
        assertEquals(expectedLimit, users.size());
    }

    //Values of limit
    //Parametrized test for all limit checked values
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 4, 5, 10, 59, 51, 100, 1000})
    public void getUserListSpecLimit(int limitValue) {
        Response response = getRequest("/user?limit =" + limitValue, 200);
        int ecpectedLimit = response.body().jsonPath().getInt("limit");
    }

    //Parametrized test for all limit checked value
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 4, 5, 10, 50, 51, 100, 1000})
    public void getUserListWithSpecLimit(int limitValue) {
        Response response = getRequest("/user?limit=" + limitValue, 200);
        int expectedLimit = response.body().jsonPath().getInt("limit");
    }

    //Values of limit
    @ParameterizedTest @MethodSource ("validData")
    public  void getUserListWithSpecLimit(int limitValue, int expectedLimit) {
        Response response = getRequest("/user?limit=" + limitValue, 200);
        int actualLimit = response.body().jsonPath().getInt("limit");
        assertEquals(expectedLimit,actualLimit);
    }
    static Stream<Arguments> validData() {
        return Stream.of(
                Arguments.arguments(0, 5), //5- eto int expectedResult  2- eto int a, 3- int b
                Arguments.arguments(1, 5),
                Arguments.arguments(-1, 5),
                Arguments.arguments(1000, 50)
        );
    }
}
//2oj variant
//    //@ValueSource(ints = {0, -1, 4, 5, 10, 50, 51, 100, 1000})
//    public void getUserListWithSpeccialLimit(int expectedLimitValue) {
//        Response response = getRequest("/user?limit=" + expectedLimitValue, 200);// "/user?limit=10", 200 +zapominaem, chto v response
//        int actualLimit = response.body().jsonPath().getInt("limit");
//        if (expectedLimitValue < 5) {
//            expectedLimitValue = 5;
//        } else if (expectedLimitValue > 50) {
//            expec
