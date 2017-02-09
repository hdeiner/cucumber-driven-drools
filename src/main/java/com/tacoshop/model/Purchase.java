package com.tacoshop.model;

import java.math.BigDecimal;

public class Purchase {

	private BigDecimal total;
	
	private int tacoCount;
	
	private boolean drinkIncluded;
	
	private BigDecimal discount;

	public Purchase() {
		this.total = BigDecimal.valueOf(0);
		this.tacoCount = 0;
		this.drinkIncluded = false;
		this.discount = BigDecimal.valueOf(0);
	}

	public Purchase(BigDecimal total, int tacoCount, boolean drinkIncluded) {
		this.total = total;
		this.tacoCount = tacoCount;
		this.drinkIncluded = drinkIncluded;
		this.discount = BigDecimal.valueOf(0);
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getTacoCount() { return tacoCount; }

	public void setTacoCount(int tacoCount) {
		this.tacoCount = tacoCount;
	}

	public boolean isDrinkIncluded() {
		return drinkIncluded;
	}

	public void setDrinkIncluded(boolean drinkIncluded) {
		this.drinkIncluded = drinkIncluded;
	}
	
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) { this.discount = discount; }
}
