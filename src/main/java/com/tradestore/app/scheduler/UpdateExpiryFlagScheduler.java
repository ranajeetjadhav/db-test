package com.tradestore.app.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tradestore.app.service.TradeExecutionService;

@Component
public class UpdateExpiryFlagScheduler {

	@Autowired
	private TradeExecutionService tradeExecutionService;
	
	@Scheduled(cron = "update_expiry_flag_expression")
	public void updateExpiryFlagForMaturityTrades() {
		tradeExecutionService.updateExpiryFlagForMaturityTardes();
	}
}
