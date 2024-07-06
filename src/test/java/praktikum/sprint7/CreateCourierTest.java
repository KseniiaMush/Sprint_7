package praktikum.sprint7;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;


public class CreateCourierTest {
    private int courierId;
    private int courierCloneId;

   CourierClient client = new CourierClient();

    @Test

    public void createValidParamsCourier() {
        Courier courier = Courier.random();

        ValidatableResponse createResponse = client.createCourier(courier);
        client.checkCreateSuccess(createResponse);
        CourierCredentials creds = CourierCredentials.from(courier);

        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = client.checkSuccessLogin(loginResponse);
    }


    @Test

    public void createCloneCourier() {
        Courier courier = Courier.random();
        ValidatableResponse createResponse = client.createCourier(courier);
        client.checkCreateSuccess(createResponse);
        CourierCredentials creds = CourierCredentials.from(courier);

        ValidatableResponse loginResponse = client.loginCourier(creds);
        courierId = client.checkSuccessLogin(loginResponse);

        Courier cloneCourier = new Courier(courier.getLogin(), courier.getPassword(), courier.getFirstName());
        ValidatableResponse createCloneResponse = client.createCourier(cloneCourier);
        client.ckeckCloneCourierError(createCloneResponse);
    }

    @Test

    public void createInvalidParamsCourier() {
    Courier courier = Courier.invalid();
        ValidatableResponse invalidResponse = client.createCourier(courier);
        client.checkInvalidParamsError(invalidResponse);

    }

    @After
    public void deleteCourier() {

        if (courierId != 0) {
            ValidatableResponse deleteResponse = client.deleteCourier(courierId);
            client.checkDeleteSuccess(deleteResponse);
        }
    }
}
