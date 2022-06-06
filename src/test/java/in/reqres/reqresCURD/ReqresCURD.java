package in.reqres.reqresCURD;

import in.reqres.reqresinfo.ReqresStep;
import in.reqres.testbase.TestBase;
import in.reqres.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ReqresCURD extends TestBase {

    static String name = "John" + TestUtils.getRandomValue();
    static String job = "Programmer" + TestUtils.getRandomValue();
    static String email = "michael.lawson1@reqres.in";
    static String password = "test123";
    static String token;

    static String userId;

    @Steps
    ReqresStep reqresStep;

    @Title("This will create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = reqresStep.createUser(name, job);
        response.log().all().statusCode(201);

    }

    @Title("Verify that new user is added")
    @Test
    public void test002() {
        HashMap<String, Object> userMap =  reqresStep.getUserInfoByName(userId);
        Assert.assertThat(userMap, hasValue(name));
        System.out.println(userId);
    }

    @Title("This will login a user")
    @Test
    public void test003() {
        HashMap<String, Object> tokenMap = reqresStep.loginUser(email, password);
        Assert.assertThat(tokenMap, hasKey("token"));
        System.out.println(tokenMap);
    }

    @Title("This will update a user by PUT")
    @Test
    public void test004() {
        name = name + "_updatedbyPut";
        ValidatableResponse response = reqresStep.updateUserByPut(userId, name, job);
        response.log().all().statusCode(200);

    }

    @Title("This will update a user by Patch")
    @Test
    public void test005() {
        name = name + "_updatedbyPatch";
        ValidatableResponse response = reqresStep.updateUserByPatch(userId, name, job);
        response.log().all().statusCode(200);
    }
    @Title("This will update a user by Patch")
    @Test
    public void test006() {
        ValidatableResponse response = reqresStep.deleteUser(userId);
        response.log().all().statusCode(204);
    }
}






