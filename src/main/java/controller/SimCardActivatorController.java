package controller;

import model.SimCardDetails;
import model.SimCardPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class SimCardActivatorController {
       @Autowired
       RestTemplate restTemplate;

        @PostMapping(path="/",consumes = "application/json", produces = "application/json")
        public void activateSim(@RequestBody SimCardDetails simCardDetails) {
                System.out.println(simCardDetails.customerEmail());
            System.out.println(simCardDetails.iccid());
            SimCardPost simCardPost = new SimCardPost(simCardDetails.iccid());

            HttpHeaders eaders = new HttpHeaders();
            eaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<SimCardPost> entity = new HttpEntity<>(simCardPost,eaders);
            ResponseEntity<String> a = restTemplate.postForEntity("http://localhost:8444/actuate", entity, String.class);
            System.out.println(a.getBody().toString());
        }
}
