package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.util.List;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class InvoiceBuilder {

	private ClientData client;
	private Id id;
	private List<InvoiceLine> items;

	public InvoiceBuilder(){}
	
	public InvoiceBuilder withClienData(ClientData client)
	{
		this.client = client;
		return this;
	}
	
	public InvoiceBuilder withId(Id id)
	{
		this.id = id;
		return this;
	}
	
	public Invoice build(Id id, ClientData client)
	{
		return new Invoice(id,client);
	}
}
