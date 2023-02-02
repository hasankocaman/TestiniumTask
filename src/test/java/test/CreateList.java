package test;

import baseURL.TrelloBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CreateList extends TrelloBaseURL {


    @Test
    public void createList (){
     spec.pathParams("pp1",1,"pp2","lists");
//        HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/")
//                .queryString("name", "{name}")
//                .queryString("key", "APIKey")
//                .queryString("token", "APIToken")
//                .asString();
//
//        System.out.println(response.getBody());

        spec.queryParam("name","liste1");
        spec.queryParam("key","6de01b37f9c18b21524961ab1702a24c");
        spec.queryParam("idBoard", "63da759455d4fc60d79e548f");
        spec.queryParam("token","ATTA6f6faf0d467eb7e53d5b1bc6447a51202b1a905f5ac772e29a4afa900522b532D25301F4");

    Response response=    given().spec(spec)
            .contentType(ContentType.JSON).when().post("/{pp1}/{pp2}");

response.prettyPrint();


}


}
