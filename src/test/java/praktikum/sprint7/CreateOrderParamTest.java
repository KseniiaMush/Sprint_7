package praktikum.sprint7;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CreateOrderParamTest {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;

    public CreateOrderParamTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getSumData() {
        return new Object[][]{
                {"Анатолий", "Иванов", "г. Москва", "Чкаловская", "89999999999", 1, "2020-06-06", "Когда это всё закончится", new String[]{"GREY"}},
                {"Максим", "Петров", "г. Нижний Новгород", "Ленинская", "89999999999", 1, "2020-06-06", "Когда это всё закончится", new String[]{"BLACK"}},
                {"Иван", "Максимов", "г. СПБ", "Московская", "89999999999", 2, "2020-06-06", "Когда это всё закончится", new String[]{"GREY", "BLACK"}},
                {"Петр", "Ананьев", "г. Екатеринбург", "Стрелка", "89999999999", 2, "2020-06-06", "Когда это всё закончится", new String[]{}},
        };
    }

        @Test
        public void createOrder() {

        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

        OrderClient orderClient = new OrderClient();
            ValidatableResponse createOrderResponse = orderClient.createOrder(order);
            int id = orderClient.checkSuccessCreate(createOrderResponse);

            Assert.assertNotEquals(0, id);
        }
}
