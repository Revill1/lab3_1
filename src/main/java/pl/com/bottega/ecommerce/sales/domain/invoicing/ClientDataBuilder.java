package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;

public class ClientDataBuilder {

	private Id id;
	private String name;
	
	public ClientDataBuilder (){}
	
	public ClientDataBuilder withId(Id id)
	{
		this.id = id;
		return this;
	}
	
	public ClientDataBuilder withName(String name)
	{
		this.name = name;
		return this;
	}
	
	public ClientData build(Id id, String name)
	{
		return new ClientData(id, name);
	}
	
	
}
