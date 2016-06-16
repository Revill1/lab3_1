package pl.com.bottega.ecommerce.sales.domain.invoicing;

public class BookKeeperBuider {

	private InvoiceFactory invoiceFactory;
	
	public BookKeeperBuider(){}
	
	public BookKeeperBuider withInvoiceFactory(InvoiceFactory factory)
	{
		this.invoiceFactory = factory;
		return this;
	}
	
	public BookKeeper build(InvoiceFactory factory)
	{
		return new BookKeeper(factory);
	}
}
