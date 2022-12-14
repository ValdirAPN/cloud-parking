package br.com.vpn.parking.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest extends AbstractContainerBase {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .auth()
                .basic("admin", "adminPass")
                .when()
                .get("/parking")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
//
//    @Test
//    void whenCreateThenCheckIsCreated() {
//        var createDTO = new ParkingFormDTO();
//        createDTO.setColor("AMARELO");
//        createDTO.setLicense("WRT-5555");
//        createDTO.setModel("VW POLO");
//        createDTO.setState("SP");
//
//        RestAssured.given()
//                .auth()
//                .basic("admin", "adminPass")
//                .when()
//                .body(createDTO)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .post("/parking")
//                .then()
//                .statusCode(HttpStatus.CREATED.value())
//                .body("license", Matchers.equalTo("WRT-5555"))
//                .body("color", Matchers.equalTo("AMARELO"));
//    }
}