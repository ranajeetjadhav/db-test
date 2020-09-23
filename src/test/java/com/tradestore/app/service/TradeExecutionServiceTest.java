package com.tradestore.app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.tradestore.app.config.ServiceBeanConfiguration;
import com.tradestore.app.dao.TradeRepository;
import com.tradestore.app.entity.Trade;
import com.tradestore.app.exception.InvalidTradeException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ServiceBeanConfiguration.class, loader=AnnotationConfigContextLoader.class)
public class TradeExecutionServiceTest {

	@MockBean
	private TradeValidationService tradeValidationService;
	
	@MockBean
	private TradeRepository tradeRepository;
	
	@Autowired
	private TradeExecutionService tradeExecutionService;
	
	@Test
	public void executeTrade_success() throws InvalidTradeException {
		Trade trade = new Trade().setTradeId("T2").setVersion(2).setCounterPartyId("CP-2").setBookId("B1")
				.setMaturityDate("20/05/2021").setCreatedDate("23/09/2020").setExpired("N");
		when(tradeValidationService.validateTrade(trade)).thenReturn(true);
		Mockito.doNothing().when(tradeRepository).createTrade(trade);
		
		boolean result = tradeExecutionService.executeTrade(trade);
		
		assertEquals(true, result);
		verify(tradeValidationService, atLeast(1)).validateTrade(trade);
		verify(tradeRepository, atLeast(1)).createTrade(trade);
	}
	
	@Test
	public void executeTrade_failed() throws InvalidTradeException {
		Trade trade = new Trade().setTradeId("T1").setVersion(1).setCounterPartyId("CP-1").setBookId("B1")
				.setMaturityDate("20/05/2020").setCreatedDate("23/09/2020").setExpired("N");
		when(tradeValidationService.validateTrade(trade)).thenReturn(false);
		Mockito.doNothing().when(tradeRepository).createTrade(trade);
		
		boolean result = tradeExecutionService.executeTrade(trade);
		
		assertEquals(false, result);
		verify(tradeValidationService, atLeast(1)).validateTrade(trade);
		verify(tradeRepository, times(0)).createTrade(trade);
	}
	
	@Test(expected=InvalidTradeException.class)
	public void executeTrade_exception() throws InvalidTradeException {
		Trade trade = new Trade().setTradeId("T2").setVersion(1).setCounterPartyId("CP-1").setBookId("B1")
				.setMaturityDate("20/05/2021").setCreatedDate("23/09/2020").setExpired("N");
		when(tradeValidationService.validateTrade(trade)).thenReturn(true);
		Mockito.doThrow(InvalidTradeException.class).when(tradeRepository).createTrade(trade);
		
		boolean result = tradeExecutionService.executeTrade(trade);
		
		assertEquals(true, result);
		verify(tradeValidationService, atLeast(1)).validateTrade(trade);
		verify(tradeRepository, atLeast(0)).createTrade(trade);
	}
	
	@Test
	public void getAlltradesFromStore() {
		Trade trade = new Trade().setTradeId("T2").setVersion(2).setCounterPartyId("CP-2").setBookId("B1")
				.setMaturityDate("20/05/2021").setCreatedDate("23/09/2020").setExpired("N");
		when(tradeRepository.getAlltradesFromStore()).thenReturn(Arrays.asList(trade));
		
		List<Trade> tradeList = tradeExecutionService.getAlltradesFromStore();
		
		assertEquals(tradeList.get(0), trade);
		verify(tradeRepository, atLeast(1)).getAlltradesFromStore();
	}
	
	@Test
	public void updateExpiryFlagForMaturityTardes() {
		Mockito.doNothing().when(tradeRepository).updateExpiryFlag();
		
		tradeExecutionService.updateExpiryFlagForMaturityTardes();
		
		verify(tradeRepository, atLeast(1)).updateExpiryFlag();
	}
}
