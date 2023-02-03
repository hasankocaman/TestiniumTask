package test;

import baseURL.TrelloBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import logger.Log;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;





public class C01_CreateABoardTrello extends TrelloBaseURL {

    Log log = new Log();

   @Test
    public void createBoard (){
       // Trello üzerinde bir board oluşturunuz.
   createBoardMethod();

    }
    public void createBoardMethod (){
        // Trello üzerinde bir board oluşturunuz.
        spec.pathParams("pp1",1,"pp2","boards");
//        HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/")
//                .queryString("name", "{name}")
//                .queryString("key", "APIKey")
//                .queryString("token", "APIToken")
//                .asString();
//
//        System.out.println(response.getBody());
        log.info("Create Board testi basladi");
        spec.queryParam("name","deneme2");
        spec.queryParam("key","6de01b37f9c18b21524961ab1702a24c");
        spec.queryParam("token","ATTA6f6faf0d467eb7e53d5b1bc6447a51202b1a905f5ac772e29a4afa900522b532D25301F4");

        Response response=    given().spec(spec)
                .contentType(ContentType.JSON).when().post("/{pp1}/{pp2}");

        response.prettyPrint();
        log.info("Create Board testi basarili");


    }

}
