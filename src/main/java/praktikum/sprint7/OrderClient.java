package praktikum.sprint7;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.net.HttpURLConnection;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class OrderClient {

    public static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    public static final String ORDER_PATH = "/api/v1/orders";
    @Step("Send POST request to /api/v1/orders")
    public ValidatableResponse createOrder(Order order) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then().log().all();
    }
    @Step("Get validate response from POST request to /api/v1/orders")
    public Integer checkSuccessCreate(ValidatableResponse createResponse) {
        return createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("track");
    }
    @Step("Send GET request to /api/v1/orders")
    public ValidatableResponse getOrders() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .when()
                .get(ORDER_PATH)
                .then().log().all();
    }
    @Step("Get validate response from GET request to /api/v1/orders")
    public ArrayList checkGetOrdersSuccess(ValidatableResponse getOrdersResponse) {
        return getOrdersResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("orders");
    }
}
