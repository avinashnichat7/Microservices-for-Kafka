package com.orderService.cotroller;

import org.apache.kafka.common.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.dto.Order;
import com.base.dto.OrderEvent;
import com.orderService.kafka.OrderProducer;

@RestController
@RequestMapping("/api")
public class OrderController {

	private OrderProducer orderProducer;

	public OrderController(OrderProducer orderProducer) {

		this.orderProducer = orderProducer;
	}

	@PostMapping("/order")
	public String placeOrder(@RequestBody Order order) {

		order.setOrderId(Uuid.randomUuid().toString());
		OrderEvent orderEvent = new OrderEvent();

		orderEvent.setStatus("pending");
		orderEvent.setMessage("order status pending");
		orderEvent.setOrder(order);

		orderProducer.sendMessage(orderEvent);
		return "order is place sucessfull";

	}
}
