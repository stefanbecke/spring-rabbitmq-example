package com.kn.amqp.testapplication.sender.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kn.amqp.common.model.events.BookingId;
import com.kn.amqp.testapplication.sender.booking.command.CreateBookingCommand;
import com.kn.amqp.testapplication.sender.booking.service.BookingService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/booking/")
public class BookingController {

	private BookingService bookingService;

	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<?> createBooking(
			@ApiParam(required = true) //
			@RequestParam(value = "consigneeName") final String consigneeName){

		BookingId bookingId = bookingService.createBooking(CreateBookingCommand.of(consigneeName));
		return ResponseEntity.ok("Booking " + bookingId + " created successfully");
	}
}