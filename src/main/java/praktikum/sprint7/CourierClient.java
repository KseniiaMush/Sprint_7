package praktikum.sprint7;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.net.HttpURLConnection;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CourierClient {
    public static final String CREATE_PATH = "/api/v1/courier";
    public static final String LOGIN_PATH = "/api/v1/courier/login";
    static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    @Step("Compare code & text with create courier response")
    public void checkCreateSuccess(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok");

        Assert.assertTrue(created);
    }
    @Step("Send POST request to /api/v1/courier")
    public ValidatableResponse createCourier(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(courier)
                .when()
                .post(CREATE_PATH)
                .then().log().all();
    }

    @Step("Send POST request to api/v1/courier/login")
    public ValidatableResponse loginCourier(CourierCredentials creds) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(creds)
                .when()
                .post(LOGIN_PATH)
                .then().log().all();
    }
    @Step("Compare code & id  with success login response")
    public Integer checkSuccessLogin(ValidatableResponse loginResponse) {
        return loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
    }
    @Step("Send DELETE request to /api/v1/courier")
    public ValidatableResponse deleteCourier(int id) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(Map.of("id", id))
                .when()
                .delete(CREATE_PATH + "/" + id)
                .then().log().all();
    }
    @Step("Compare code & text with delete courier response")
    public void checkDeleteSuccess(ValidatableResponse deleteResponse) {
        boolean deleted = deleteResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("ok");

        Assert.assertTrue(deleted);
    }
    @Step("Compare error code & text with create clone courier response")
    public void ckeckCloneCourierError(ValidatableResponse cloneCreateResponse) {
        String cloneCreated = cloneCreateResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT)
                .extract()
                .path("message");

        Assert.assertEquals("Этот логин уже используется", cloneCreated);
    }
    @Step("Compare error code & text with invalid creds response")
    public void checkInvalidParamsError(ValidatableResponse invalidResponse) {
        String invalidCreated = invalidResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");

        Assert.assertEquals("Недостаточно данных для создания учетной записи", invalidCreated);
    }
    @Step("Compare error code & text with invalid params response")
    public void checkInvalidParamsLoginError(ValidatableResponse invalidResponse) {
        String invalidParamsLogin = invalidResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .extract()
                .path("message");

        Assert.assertEquals("Недостаточно данных для входа", invalidParamsLogin);
    }
    @Step("Compare error code & text with invalid creds response")
    public void checkInvalidCredsLoginError(ValidatableResponse invalidResponse) {
        String invalidCredsLogin = invalidResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
                .extract()
                .path("message");

        Assert.assertEquals("Учетная запись не найдена", invalidCredsLogin);
    }
}
