package test;

import baseURL.TrelloBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import logger.Log;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class C02_CreateCard extends TrelloBaseURL {

    Log log = new Log();
    //63da7792c59ce5d882b0a9fd

   @Test
    public void createCard (){
       //Oluşturduğunuz board’ a iki tane kart oluşturunuz.
       log.info("Create Card testi basladi");
        cardOlustur();
        cardOlustur();
       log.info("Create Card testi basarili");

    }

    public void cardOlustur(){
        spec.pathParams("pp1",1,"pp2","cards");
//        HttpResponse<JsonNode> response = Unirest.post("https://api.trello.com/1/cards")
//                .header("Accept", "application/json")
//                .queryString("idList", "5abbe4b7ddc1b351ef961414")
//                .queryString("key", "APIKey")
//                .queryString("token", "APIToken")
//                .asJson();
        spec.queryParam("name","cards1");
        spec.queryParam("key","6de01b37f9c18b21524961ab1702a24c");
        spec.queryParam("idList","63da7792c59ce5d882b0a9fd");
        spec.queryParam("token","ATTA6f6faf0d467eb7e53d5b1bc6447a51202b1a905f5ac772e29a4afa900522b532D25301F4");

        Response response=    given().spec(spec)
                .contentType(ContentType.JSON).when().post("/{pp1}/{pp2}");

        response.prettyPrint();
    }


}




