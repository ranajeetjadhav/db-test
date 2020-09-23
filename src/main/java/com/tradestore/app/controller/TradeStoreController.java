package com.tradestore.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradestore.app.constants.TradeConstants;
import com.tradestore.app.entity.Trade;
import com.tradestore.app.exception.InvalidTradeException;
import com.tradestore.app.service.TradeExecutionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value="tradeStore", description="Online Trade Store")
public class TradeStoreController {
 
	@Autowired
	private TradeExecutionService tradeExecutionService;
	
	@PostMapping(value = "/createtrade", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Create new trade in store", response = String.class)
	public ResponseEntity<String> createTradeToStore(@RequestBody Trade trade) throws InvalidTradeException {
		boolean isCreated = false;
		try {
			isCreated = tradeExecutionService.executeTrade(trade);
		} catch (InvalidTradeException e) {
			e.printStackTrace();
			throw new InvalidTradeException(e.getErrorCode(), e.getErrorDescription());
		}
		
		if(isCreated) {
			return new ResponseEntity<String>(TradeConstants.TRADE_SUCCESS, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(TradeConstants.TRADE_FAILED, HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@GetMapping(value = "/showAllTrades", produces = "application/json")
	@ApiOperation(value = "View a list of available trades", response = List.class)
	public ResponseEntity<List<Trade>> showAllTradesFromStore() {
		List<Trade> tradeList = tradeExecutionService.getAlltradesFromStore();
		return new ResponseEntity<List<Trade>>(tradeList, HttpStatus.OK);
	}
	
	/**
	 *To-DO
	 *	add bean validation
	 *	AOP for logging
	 *	H2 DB to store data
	 *	log4j
	 *	builder pattern for entity
	*/
}
