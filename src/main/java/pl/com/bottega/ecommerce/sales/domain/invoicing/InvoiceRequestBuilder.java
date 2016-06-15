package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;

public class InvoiceRequestBuilder {

	private ClientData client;	
	
	public InvoiceRequestBuilder(){}
	
	public InvoiceRequestBuilder withClientData(ClientData client)
	{
		this.client = client;
		return this;
	}
	
	public InvoiceRequest build (ClientData client)
	{
		return new InvoiceRequest(client);
	}
}
