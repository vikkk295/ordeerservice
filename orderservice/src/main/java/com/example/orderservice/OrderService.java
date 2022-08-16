package com.example.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

	
	@Autowired
	private OdrderRepository odrderRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public TranscationResponse saveOrder(TransactionRequest request) {
		String response="";
		Order order=request.getOrder();
		Payment payment=request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
Payment	paymentresponse=restTemplate.postForObject("http://PAYMENT-SERVICE/payment/dopayment", payment,Payment.class);
	    
response=paymentresponse.getPaymentStatus().equalsIgnoreCase("success")?"payment processing successfull and order is placed":"there is failure in payment api,order added to cart";
odrderRepository.save(order);
return new TranscationResponse(order,payment.getAmount(),paymentresponse.getTranscationId(),response);
	}
	
	
}
