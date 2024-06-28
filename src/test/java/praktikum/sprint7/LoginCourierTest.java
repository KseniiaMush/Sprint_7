package praktikum.sprint7;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class LoginCourierTest {

    private int courierId;

    CourierClient client = new CourierClient();

    @Test

    public void ValidParamsLogin() {

        Courier courier = Courier.random();

        ValidatableResponse createResponse = client.createCourier(courier);
        client.checkCreateSuccess(createResponse);

        CourierCredentials creds = CourierCredentials.from(courier);

        ValidatableResponse loginResponse = client.loginCourier(creds);

        courierId = client.checkSuccessLogin(loginResponse);

        Assert.assertNotEquals(0, courierId);
    }

    @Test

    public void InvalidParamsLogin() {
        CourierCredentials invalidParams= CourierCredentials.invalidParams();
        ValidatableResponse invalidResponse = client.loginCourier(invalidParams);
        client.checkInvalidParamsLoginError(invalidResponse);
    }

    @Test

    public void InvalidCredsLogin() {
        CourierCredentials invalidCreds = CourierCredentials.randomCourierCredentials();
        ValidatableResponse invalidResponse = client.loginCourier(invalidCreds);
        client.checkInvalidCredsLoginError(invalidResponse);
    }

    @After

    public void deleteCourier() {

        if (courierId != 0) {
            ValidatableResponse deleteResponse = client.deleteCourier(courierId);
            client.checkDeleteSuccess(deleteResponse);
        }
    }

}
