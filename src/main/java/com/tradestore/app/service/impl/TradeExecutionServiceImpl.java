package com.tradestore.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradestore.app.dao.TradeRepository;
import com.tradestore.app.entity.Trade;
import com.tradestore.app.exception.InvalidTradeException;
import com.tradestore.app.service.TradeExecutionService;
import com.tradestore.app.service.TradeValidationService;

@Service
public class TradeExecutionServiceImpl implements TradeExecutionService {

	@Autowired
	private TradeValidationService tradeValidationService;
	
	@Autowired
	private TradeRepository tradeRepository;
	
	public boolean executeTrade(Trade trade) throws InvalidTradeException {
		boolean isValidTrade = tradeValidationService.validateTrade(trade);
		if(isValidTrade)
			tradeRepository.createTrade(trade);
		return isValidTrade;
	}

	public void updateExpiryFlagForMaturityTardes() {
		tradeRepository.updateExpiryFlag();
	}

	public List<Trade> getAlltradesFromStore() {
		return tradeRepository.getAlltradesFromStore();
	}
}
