package com.tradestore.app.service;

import java.util.List;

import com.tradestore.app.entity.Trade;
import com.tradestore.app.exception.InvalidTradeException;

public interface TradeExecutionService {
	public boolean executeTrade(Trade trade) throws InvalidTradeException;
	public void updateExpiryFlagForMaturityTardes();
	public List<Trade> getAlltradesFromStore();
}
