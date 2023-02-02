package baseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TrelloBaseURL {

    protected RequestSpecification spec;

   @BeforeAll
    public void setUp(){

        spec = new RequestSpecBuilder().
                setBaseUri("https://api.trello.com").
                build();
    }
}
