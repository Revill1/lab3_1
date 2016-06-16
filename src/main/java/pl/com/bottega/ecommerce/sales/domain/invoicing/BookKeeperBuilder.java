package pl.com.bottega.ecommerce.sales.domain.invoicing;

public class BookKeeperBuilder {

	private InvoiceFactory invoiceFactory = new InvoiceFactory();
	
	public BookKeeperBuilder(){}
	
	public BookKeeperBuilder withInvoiceFactory(InvoiceFactory factory)
	{
		this.invoiceFactory = factory;
		return this;
	}
	
	public BookKeeper build()
	{
		return new BookKeeper(invoiceFactory);
	}
}
