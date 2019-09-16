package com.example.anotherapi.controller;
import org.springframework.web.bind.annotation.*;

import com.example.anotherapi.models.PostRequest;
import com.example.anotherapi.models.PostResponse;
import com.example.anotherapi.models.SampleResponse;

import java.util.Random;

import static java.lang.Thread.*;

@RestController

public class WebController {

	@CrossOrigin
	@RequestMapping("/Getfruits")
	public SampleResponse Sample(@RequestParam(value = "name",
	defaultValue = "<You did not mention Vendor name in URL!>") String name) {

		SampleResponse response = new SampleResponse();
		response.setId(1);
		response.setMessage("Apple $" + (int)(Math.random()*5 +1) + " Banana $" +  (int)(Math.random()*5 + 1) + " Orange $" + (int)(Math.random()*5 +1) + " at Vendor "+name ) ;
		//response.setMessage("Apple $1 Banana $2 Carrot $3 at Vendor "+name);
		return response;

	}

	@CrossOrigin
	@RequestMapping("/Sleep")
	public SampleResponse Sample() {
		SampleResponse response = new SampleResponse();
		try {
			for (int i =0; i< 3000; i++) {
				sleep(10000);
			}
		} catch (InterruptedException e) {
			//Do nothing for now.. TBD
			currentThread().interrupt();
		}
		response.setMessage("This thread slept for sometime to demonstrate load balancer");
		return response;
	}

	@CrossOrigin
	@RequestMapping(value = "/UpdateFruits", method = RequestMethod.POST)
	public PostResponse Test(@RequestBody PostRequest inputPayload) {
		PostResponse response = new PostResponse();
		response.setId(inputPayload.getId()*100);
		response.setMessage("Fruits updated " + inputPayload.getName());
		response.setExtra("Some Extra Text");
		return response;
	}
}