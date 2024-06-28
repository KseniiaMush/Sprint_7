package praktikum.sprint7;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierCredentials {
    private String login;
    private String password;

    public CourierCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public CourierCredentials() {

    }

    public CourierCredentials(String login) {
        this.login = login;
    }

    public static CourierCredentials from(Courier courier) {
        return new CourierCredentials(courier.getLogin(), courier.getPassword());
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static CourierCredentials basicCourierCredentials() {
        return  new CourierCredentials("vovannumberone", "12345");
    }

    public static CourierCredentials randomCourierCredentials() {
        return new CourierCredentials(RandomStringUtils.random(10), RandomStringUtils.random(5));
    }

    public static CourierCredentials invalidParams() {
        return new CourierCredentials(RandomStringUtils.random(10));
    }

}
