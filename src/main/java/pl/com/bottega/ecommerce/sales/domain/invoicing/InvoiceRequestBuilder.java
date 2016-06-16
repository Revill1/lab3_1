package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;

public class InvoiceRequestBuilder {

	private ClientData client = new ClientData(new Id("1"),"Client");	
	
	public InvoiceRequestBuilder(){}
	
	public InvoiceRequestBuilder withClientData(ClientData client)
	{
		this.client = client;
		return this;
	}
	
	public InvoiceRequest build ()
	{
		return new InvoiceRequest(client);
	}
}
