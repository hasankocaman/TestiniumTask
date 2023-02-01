//package test;
//
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.junit.Test;
//import pojos.PojoHerokuappBooking;
//import pojos.PojoHerokuappBookingDates;
//import pojos.PojoHerokuappExpectedBody;
//
//import static io.restassured.RestAssured.given;
//import static org.junit.Assert.assertEquals;
//
//public class C28_Post_Pojo extends HerokuAppBaseUrl {
///*
//    https://restful-booker.herokuapp.com/booking url’ine
//    asagidaki body'ye sahip bir POST request gonderdigimizde
//    donen response’un id disinda asagidaki gibi oldugunu test edin.
//    	                Request body
//    	           {
//    	                "firstname" : "Ahmet",
//    	                "lastname" : “Bulut",
//    	                "totalprice" : 500,
//    	                "depositpaid" : false,
//    	                "bookingdates" : {
//    	                         "checkin" : "2021-06-01",
//    	                         "checkout" : "2021-06-10"
//    	                                  },
//    	                "additionalneeds" : "wi-fi"
//    	            }
//
//
//    	            	Response Body = Expected Data
//    	            	{
//                    "bookingid":24,
//                    "booking":{
//                        "firstname":"Ahmet",
//                        "lastname":"Bulut",
//                        "totalprice":500,
//                        "depositpaid":false,
//                        "bookingdates":{
//                            "checkin":"2021-06-01",
//                            "checkout":"2021-06-10"
//                                        }
//                        ,
//                        "additionalneeds":"wi-fi"
//                              }
//                    }
//         */
//
//    @Test
//    public void post01(){
//
//        // 1 - Request icin gerekli URL ve Body hazirla
//
//        specHerokuApp.pathParam("pp1","booking");
//
//        // Pojo class'i olusturalim
//
//        PojoHerokuappBookingDates pojoHerokuappBookingDates = new PojoHerokuappBookingDates("2021-06-01","2021-06-10");
//
//        PojoHerokuappBooking reqBody = new PojoHerokuappBooking
//                ("Ahmet","Bulut",500,false,pojoHerokuappBookingDates,"wi-fi");
//
//
//        // 2 - Expected Data hazirla
//
//        PojoHerokuappExpectedBody expData = new PojoHerokuappExpectedBody(24,reqBody);
//
//        // 3 - Response'i kaydet
//
//        Response response = given().
//                                spec(specHerokuApp).
//                                contentType(ContentType.JSON).
//                            when().
//                                body(reqBody).
//                                post("/{pp1}");
//
//        // Assertion
//
//        PojoHerokuappExpectedBody respPojo = response.as(PojoHerokuappExpectedBody.class);
//
//        assertEquals(expData.getBooking().getFirstname(), respPojo.getBooking().getFirstname());
//        assertEquals(expData.getBooking().getLastname(), respPojo.getBooking().getLastname());
//        assertEquals(expData.getBooking().getTotalprice(), respPojo.getBooking().getTotalprice());
//        assertEquals(expData.getBooking().isDepositpaid(), respPojo.getBooking().isDepositpaid());
//        assertEquals(expData.getBooking().getBookingdates().getCheckin(), respPojo.getBooking().getBookingdates().getCheckin());
//        assertEquals(expData.getBooking().getBookingdates().getCheckout(), respPojo.getBooking().getBookingdates().getCheckout());
//        assertEquals(expData.getBooking().getAdditionalneeds(), respPojo.getBooking().getAdditionalneeds());
//
//    }
//
//}
