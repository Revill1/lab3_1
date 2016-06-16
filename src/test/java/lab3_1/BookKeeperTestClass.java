package lab3_1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import pl.com.bottega.ecommerce.sales.domain.equivalent.SuggestionService;
import pl.com.bottega.ecommerce.sales.domain.invoicing.BookKeeper;
import pl.com.bottega.ecommerce.sales.domain.invoicing.ClientDataBuilder;
import pl.com.bottega.ecommerce.sales.domain.invoicing.Invoice;
import pl.com.bottega.ecommerce.sales.domain.invoicing.InvoiceFactory;
import pl.com.bottega.ecommerce.sales.domain.invoicing.InvoiceRequest;
import pl.com.bottega.ecommerce.sales.domain.invoicing.InvoiceRequestBuilder;
import pl.com.bottega.ecommerce.sales.domain.invoicing.RequestItem;
import pl.com.bottega.ecommerce.sales.domain.invoicing.Tax;
import pl.com.bottega.ecommerce.sales.domain.invoicing.TaxBuilder;
import pl.com.bottega.ecommerce.sales.domain.invoicing.TaxPolicy;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import pl.com.bottega.ecommerce.sharedkernel.Money;
import pl.com.bottega.ecommerce.system.application.SystemContext;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BookKeeperTestClass {

	@Test
	public void bookKeeperTestOneInvoice() {
		TaxPolicy policy = mock(TaxPolicy.class);
		ClientData client = new ClientDataBuilder().build();
		ProductData productData = new ProductData(new Id("1"), new Money(
				new BigDecimal(50)), "NAME", ProductType.DRUG, new Date());

		InvoiceRequest request = new InvoiceRequestBuilder().build();
		InvoiceFactory factory = new InvoiceFactory();
		BookKeeper bookKeeper = new BookKeeper(factory);

		request.add(new RequestItem(productData, 1, new Money(new BigDecimal(
				255))));
		when(policy.calculateTax(Mockito.any(ProductType.class),
						Mockito.any(Money.class))).thenReturn(
				new TaxBuilder().build());
			Invoice invoice = bookKeeper.issuance(request, policy);

		assertThat(invoice.getItems().size(), is(1));
	}

	@Test
	public void bookKeeperTestTwoInvoice() {
		TaxPolicy policy = mock(TaxPolicy.class);
		ClientData client = new ClientData(new Id("1"), "klient1");
		ProductData productData = new ProductData(new Id("1"), new Money(
				new BigDecimal(50)), "NAME", ProductType.DRUG, new Date());
		ProductData productData2 = new ProductData(new Id("2"), new Money(
				new BigDecimal(50)), "NAME2", ProductType.STANDARD, new Date());

		InvoiceRequest request = new InvoiceRequest(client);
		InvoiceFactory factory = new InvoiceFactory();
		BookKeeper bookKeeper = new BookKeeper(factory);

		request.add(new RequestItem(productData, 1, new Money(new BigDecimal(
				255))));
		request.add(new RequestItem(productData2, 2, new Money(new BigDecimal(
				200))));
		when(
				policy.calculateTax(Mockito.any(ProductType.class),
						Mockito.any(Money.class))).thenReturn(
				new Tax(new Money(new BigDecimal(40)), "test"));

		Invoice invoice = bookKeeper.issuance(request, policy);

		verify(policy, times(2)).calculateTax(any(ProductType.class),
				any(Money.class));
		assertThat(invoice.getItems().size(), is(2));
	}

	@Test
	public void bookKeeperTestNoInvoice() {
		TaxPolicy policy = mock(TaxPolicy.class);
		ClientData client = new ClientData(new Id("1"), "klient1");

		InvoiceRequest request = new InvoiceRequest(client);
		InvoiceFactory factory = new InvoiceFactory();
		BookKeeper bookKeeper = new BookKeeper(factory);

		when(
				policy.calculateTax(Mockito.any(ProductType.class),
						Mockito.any(Money.class))).thenReturn(
				new Tax(new Money(new BigDecimal(40)), "test"));
		Invoice invoice = bookKeeper.issuance(request, policy);

		assertThat(invoice.getItems().size(), is(0));
	}

	@Test
	public void bookKeeperTestIfGetItemsMethodWasExecuted() {
		TaxPolicy policy = mock(TaxPolicy.class);
		InvoiceRequest request = mock(InvoiceRequest.class);

		ClientData client = new ClientData(new Id("1"), "klient1");
		ProductData productData1 = new ProductData(new Id("1"), new Money(
				new BigDecimal(500)), "NAME1", ProductType.FOOD, new Date());
		ProductData productData2 = new ProductData(new Id("2"), new Money(
				new BigDecimal(50)), "NAME2", ProductType.STANDARD, new Date());

		// InvoiceRequest request = new InvoiceRequest(client);
		InvoiceFactory factory = new InvoiceFactory();
		BookKeeper bookKeeper = new BookKeeper(factory);

		request.add(new RequestItem(productData1, 1, new Money(new BigDecimal(
				255))));
		request.add(new RequestItem(productData2, 2, new Money(new BigDecimal(
				255))));
		when(
				policy.calculateTax(Mockito.any(ProductType.class),
						Mockito.any(Money.class))).thenReturn(
				new Tax(new Money(new BigDecimal(40)), "test"));
		Invoice invoice = bookKeeper.issuance(request, policy);

		verify(request, times(1)).getItems();
	}
}
