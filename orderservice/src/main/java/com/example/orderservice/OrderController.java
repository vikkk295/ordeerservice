package com.example.orderservice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

@Autowired
private	OrderService orderService;
	
	@PostMapping("/bookorder")
	public TranscationResponse bookOrder(@RequestBody TransactionRequest request) {
	
		String name="vikas";
List<String>streamName=	Arrays.stream(name.split("")).collect(Collectors.toList());

System.out.println(streamName);
		
		return orderService.saveOrder(request);
	}
	
}
