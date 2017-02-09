package com.tacoshop.model;

/**
 * Purchase POJO, data model for our Purchase object
 * 
 * @author <a href="mailto:jeremy.ary@gmail.com">jary</a>
 * @since Dec. 2012
 */
import java.math.BigDecimal;

public class Purchase {

	/** total cost of purchase */
	private BigDecimal total;
	
	/** number of tacos included in purchase */
	private int tacoCount;
	
	/** purchase contains drink */
	private boolean drinkIncluded;
	
	/** total discount percentage on purchase, in decimal form */
	private BigDecimal discount;

	public Purchase() {
		this.total = BigDecimal.valueOf(0);
		this.tacoCount = 0;
		this.drinkIncluded = false;
		this.discount = BigDecimal.valueOf(0);
	}

	/**
	 * Constructor allowing us to initialize some values in testing. Always start with no discount.
	 * 
	 * @param total puchase cost
	 * @param tacoCount number of tacos in purchase
	 * @param drinkIncluded if drink is included in purchase
	 */
	public Purchase(BigDecimal total, int tacoCount, boolean drinkIncluded) {
		this.total = total;
		this.tacoCount = tacoCount;
		this.drinkIncluded = drinkIncluded;
		this.discount = BigDecimal.valueOf(0);
	}

	/**
	 * getter for purchase total
	 * 
	 * @return total cost of purchase in BigDecimal format, good practice for currency to avoid floating point issues
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * setter for purchase total
	 * 
	 * @param total total cost of purchase to be set
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * getter for taco count included in purchase
	 * 
	 * @return int number of tacos
	 */
	public int getTacoCount() {
		return tacoCount;
	}

	/**
	 * setter for taco count in purchase
	 * 
	 * @param tacoCount number of tacos
	 */
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

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
