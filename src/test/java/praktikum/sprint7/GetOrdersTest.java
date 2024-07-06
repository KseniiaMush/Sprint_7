package praktikum.sprint7;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class GetOrdersTest {

@Test
    public void getOrders() {
    OrderClient orderClient = new OrderClient();

    ValidatableResponse getOrdersResponse = orderClient.getOrders();
    ArrayList<String> ordersList = orderClient.checkGetOrdersSuccess(getOrdersResponse);

    Assert.assertNotNull(ordersList);
}
}
