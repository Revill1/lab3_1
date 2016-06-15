package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.math.BigDecimal;

import pl.com.bottega.ecommerce.sharedkernel.Money;

public class TaxBuilder {

	private Money money = new Money(new BigDecimal("100"),"EUR");
	private String description = "";
	
	public TaxBuilder()	{}
	
	public TaxBuilder withMoney(Money money)
	{
		this.money = money;
		return this;
	}
	
	public TaxBuilder withDescription(String description)
	{
		this.description = description;
		return this;
	}
	
	public Tax build(Money money, String description)
	{
		return new Tax(money,description);
	}
}
