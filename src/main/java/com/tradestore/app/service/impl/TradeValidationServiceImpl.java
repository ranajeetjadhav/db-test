package com.tradestore.app.service.impl;

import org.springframework.stereotype.Service;

import com.tradestore.app.entity.Trade;
import com.tradestore.app.service.TradeValidationService;
import com.tradestore.app.utils.DateUtils;

@Service
public class TradeValidationServiceImpl implements TradeValidationService {

	public boolean validateTrade(Trade trade) {
		return !DateUtils.isMaturityDateOld(trade.getMaturityDate());
	}
}
