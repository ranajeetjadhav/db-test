package com.tradestore.app.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.tradestore.app.constants.ErrorEnum;
import com.tradestore.app.dao.TradeRepository;
import com.tradestore.app.entity.Trade;
import com.tradestore.app.exception.InvalidTradeException;
import com.tradestore.app.utils.DateUtils;

@Repository
public class TradeRepositoryImpl implements TradeRepository {

	/**
	 * we can use in-memory DB instead of Hashmap
	 */
	private Map<String, Trade> tradeMap = new HashMap<String, Trade>();
	
	public void createTrade(Trade trade) throws InvalidTradeException {
		Trade existingTrade = tradeMap.get(trade.getTradeId());
		if(existingTrade != null && trade.getVersion() < existingTrade.getVersion()) {
			throw new InvalidTradeException().setErrorCode(ErrorEnum.INVALIDTRADE.getErrorCode()).setErrorDescription(ErrorEnum.INVALIDTRADE.getErrorDescription());
		} else {
			tradeMap.put(trade.getTradeId(), trade);
		} 
	}

	public void updateExpiryFlag() {
		tradeMap.values().stream()
			.filter(trade -> (DateUtils.isMaturityDateExpired(trade.getMaturityDate()) && "N".equalsIgnoreCase(trade.getExpired())))
			.forEach(trade -> trade.setExpired("Y"));
	}

	public List<Trade> getAlltradesFromStore() {
		return tradeMap.values().stream().collect(Collectors.toCollection(ArrayList::new));
	}
}
