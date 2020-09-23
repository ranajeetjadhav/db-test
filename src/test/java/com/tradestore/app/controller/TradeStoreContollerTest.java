package com.tradestore.app.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradestore.app.config.ServiceBeanConfiguration;
import com.tradestore.app.entity.Trade;
import com.tradestore.app.exception.InvalidTradeException;
import com.tradestore.app.service.TradeExecutionService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes=ServiceBeanConfiguration.class, loader=AnnotationConfigContextLoader.class)
public class TradeStoreContollerTest {
	
	private MockMvc mockMvc;
	
	@MockBean
	private TradeExecutionService tradeExecutionService;
	
	@InjectMocks
	@Spy
	private TradeStoreController tradeStoreController;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(tradeStoreController).build();
	}
	
	@Test
	public void createTrade_success() throws Exception {
		Trade trade = new Trade().setTradeId("T2").setVersion(2).setCounterPartyId("CP-2").setBookId("B1")
				.setMaturityDate("20/05/2021").setCreatedDate("23/09/2020").setExpired("N");
		
		when(tradeExecutionService.executeTrade(trade)).thenReturn(true);
		
		this.mockMvc.perform(MockMvcRequestBuilders
							.post("/api/createtrade")
							.content(asJsonString(trade))
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isCreated())
							.andExpect(jsonPath("$", is("Trade created successfully")));
	}
	
	@Test
	public void createTrade_failed() throws Exception {
		Trade trade = new Trade().setTradeId("T1").setVersion(2).setCounterPartyId("CP-2").setBookId("B1")
				.setMaturityDate("20/05/2020").setCreatedDate("23/09/2020").setExpired("N");
		
		when(tradeExecutionService.executeTrade(trade)).thenReturn(false);
		
		this.mockMvc.perform(MockMvcRequestBuilders
							.post("/api/createtrade")
							.content(asJsonString(trade))
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isNotAcceptable())
							.andExpect(jsonPath("$", is("Trade validation failed, hence trade not logged in")));
	}
	
	@Test(expected = Exception.class)
	public void createTrade_exception() throws Exception {
		Trade trade = new Trade().setTradeId("T2").setVersion(1).setCounterPartyId("CP-1").setBookId("B1")
				.setMaturityDate("20/05/2021").setCreatedDate("23/09/2020").setExpired("N");
		
		Mockito.doThrow(InvalidTradeException.class).when(tradeExecutionService).executeTrade(trade);
		
		this.mockMvc.perform(MockMvcRequestBuilders
							.post("/api/createtrade")
							.content(asJsonString(trade))
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON))
							.andExpect(status().isInternalServerError());
	}
	
	@Test
	public void showAllTradesFromStore() throws Exception {
		Trade trade = new Trade().setTradeId("T2").setVersion(2).setCounterPartyId("CP-2").setBookId("B1")
				.setMaturityDate("20/05/2021").setCreatedDate("23/09/2020").setExpired("N");
		when(tradeExecutionService.getAlltradesFromStore()).thenReturn(Arrays.asList(trade));
		
		this.mockMvc.perform(MockMvcRequestBuilders
				.get("/api/showAllTrades")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].tradeId", is("T2")))
				.andExpect(jsonPath("$[0].version", is(2)))
				.andExpect(jsonPath("$[0].counterPartyId", is("CP-2")))
				.andExpect(jsonPath("$[0].bookId", is("B1")))
				.andExpect(jsonPath("$[0].maturityDate", is("20/05/2021")))
				.andExpect(jsonPath("$[0].createdDate", is("23/09/2020")))
				.andExpect(jsonPath("$[0].expired", is("N")));
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
