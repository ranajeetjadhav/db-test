package com.tradestore.app.dao;

import java.util.List;

import com.tradestore.app.entity.Trade;
import com.tradestore.app.exception.InvalidTradeException;

public interface TradeRepository {
	public void createTrade(Trade trade) throws InvalidTradeException;
	public void updateExpiryFlag();
	public List<Trade> getAlltradesFromStore(); 
}
