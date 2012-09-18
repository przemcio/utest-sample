package pl.testng.ch9;

import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Transaction test
 */

public class TransactionTest {


    public void shouldCreateANewObject() {

        Transaction transaction = new TransactionTestDataBuilder().seMessage("message").setId(1).setRellyAllowed(true).setState("state").build();


        Assert.assertNotNull(transaction);

    }


}
