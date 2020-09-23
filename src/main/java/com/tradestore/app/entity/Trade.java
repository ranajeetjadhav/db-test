package com.tradestore.app.entity;

import java.io.Serializable;

public class Trade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// add validation
	private String tradeId;
	private int version;
	private String counterPartyId;
	private String bookId;
	private String maturityDate;
	private String createdDate;
	private String expired;
	
	public Trade() {
		super();
	}
	
	public String getTradeId() {
		return tradeId;
	}

	public Trade setTradeId(String tradeId) {
		this.tradeId = tradeId;
		return this;
	}

	public int getVersion() {
		return version;
	}

	public Trade setVersion(int version) {
		this.version = version;
		return this;
	}

	public String getCounterPartyId() {
		return counterPartyId;
	}

	public Trade setCounterPartyId(String counterPartyId) {
		this.counterPartyId = counterPartyId;
		return this;
	}

	public String getBookId() {
		return bookId;
	}

	public Trade setBookId(String bookId) {
		this.bookId = bookId;
		return this;
	}

	public String getMaturityDate() {
		return maturityDate;
	}

	public Trade setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
		return this;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public Trade setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public String getExpired() {
		return expired;
	}

	public Trade setExpired(String expired) {
		this.expired = expired;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		result = prime * result + ((counterPartyId == null) ? 0 : counterPartyId.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((expired == null) ? 0 : expired.hashCode());
		result = prime * result + ((maturityDate == null) ? 0 : maturityDate.hashCode());
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		if (counterPartyId == null) {
			if (other.counterPartyId != null)
				return false;
		} else if (!counterPartyId.equals(other.counterPartyId))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (expired == null) {
			if (other.expired != null)
				return false;
		} else if (!expired.equals(other.expired))
			return false;
		if (maturityDate == null) {
			if (other.maturityDate != null)
				return false;
		} else if (!maturityDate.equals(other.maturityDate))
			return false;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", version=" + version + ", counterPartyId=" + counterPartyId + ", bookId="
				+ bookId + ", maturityDate=" + maturityDate + ", createdDate=" + createdDate + ", expired=" + expired
				+ "]";
	}
}
