package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class RequestItemBuilder {

	private ProductData productData;

	private int quantity;

	private Money totalCost;
	
	public RequestItemBuilder(){}
	
	public RequestItemBuilder withProductData(ProductData data)
	{
		this.productData = data;
		return this;
	}
	
	public RequestItemBuilder withQuantity(int quantity)
	{
		this.quantity = quantity;
		return this;
	}
	
	public RequestItemBuilder withTotalCost(Money totalCost)
	{
		this.totalCost = totalCost;
		return this;
	}
	
	public RequestItem build(ProductData data, int quantity, Money totalCost)
	{
		return new RequestItem(data, quantity, totalCost);
	}
}
