package praktikum.sprint7;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderParamTest {
    private final String[] color;

    public CreateOrderParamTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {new String[]{"GREY"}},
                {new String[]{"BLACK"}},
                {new String[]{"GREY", "BLACK"}},
                {new String[]{}},
        };
    }

        @Test
        public void createOrder() {

        Order order = new Order("Анатолий", "Иванов", "г. Москва", "Чкаловская", "89999999999", 1, "2020-06-06", "Когда это всё закончится", color);

        OrderClient orderClient = new OrderClient();
            ValidatableResponse createOrderResponse = orderClient.createOrder(order);
            int id = orderClient.checkSuccessCreate(createOrderResponse);

            Assert.assertNotEquals(0, id);
        }
}
