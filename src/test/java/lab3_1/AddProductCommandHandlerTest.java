package lab3_1;

import static org.mockito.Mockito.mock;

import java.util.Date;
import java.util.regex.Matcher;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.mockito.Mockito;
import org.hamcrest.Matchers;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.application.api.handler.AddProductCommandHandler;
import pl.com.bottega.ecommerce.sales.domain.client.Client;
import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import pl.com.bottega.ecommerce.sales.domain.equivalent.SuggestionService;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sales.domain.reservation.Reservation;
import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import pl.com.bottega.ecommerce.sharedkernel.Money;
import pl.com.bottega.ecommerce.sharedkernel.exceptions.DomainOperationException.DomainOperationException;
import pl.com.bottega.ecommerce.system.application.SystemContext;
import pl.com.bottega.ecommerce.system.application.SystemUser;

public class AddProductCommandHandlerTest {

	private AddProductCommandHandler productCommandHandler;
	private AddProductCommand productCommand;
	private ProductRepository productRepository;
	private ReservationRepository reservationRepository;
	private SuggestionService suggestionService;
	private ClientRepository clientRepository;
	private SystemContext systemContext;
	private Product product = new Product(new Id("3"),new Money(20),"prod1",ProductType.STANDARD );
	private Product problematicProduct = new Product(new Id("4"),new Money(50),"prod2",ProductType.DRUG );
	private Reservation reservation = new Reservation(new Id("3"),Reservation.ReservationStatus.OPENED,new ClientData(new Id("1"),"client"),new Date());

	@Before
	public void init() {
		productRepository = Mockito.mock(ProductRepository.class);
		Mockito.when(productRepository.load(new Id("3"))).thenReturn(product);
		clientRepository = Mockito.mock(ClientRepository.class);
		Mockito.when(clientRepository.load(new Id("5"))).thenReturn(new Client());
		suggestionService = Mockito.mock(SuggestionService.class);
		Mockito.when(suggestionService.suggestEquivalent(problematicProduct, new Client())).thenReturn(product);
		productCommand = Mockito.mock(AddProductCommand.class);
		Mockito.when(productCommand.getProductId()).thenReturn(product.getId());
		reservationRepository = Mockito.mock(ReservationRepository.class);
		Mockito.when(reservationRepository.load(new Id("3"))).thenReturn(reservation);
		systemContext = Mockito.mock(SystemContext.class);
		Mockito.when(systemContext.getSystemUser()).thenReturn(new SystemUser(new Id("1")));
		productCommand = Mockito.mock(AddProductCommand.class);
		Mockito.when(productCommand.getQuantity()).thenReturn(5);
		Mockito.when(productCommand.getProductId()).thenReturn(product.getId());
		Mockito.when(productCommand.getOrderId()).thenReturn(product.getId());
	}

	@Test
	public void BehavioralTests()
    {
		productCommandHandler = new AddProductCommandHandler(reservationRepository, productRepository, suggestionService, clientRepository, systemContext);
		productCommandHandler.handle(productCommand);
		productCommandHandler.loadClient();
    	
    	Mockito.verify(productCommand,Mockito.times(1)).getProductId();
    	Mockito.verify(productCommand,Mockito.times(1)).getProductId();
    	Mockito.verify(systemContext,Mockito.times(1)).getSystemUser();
    	
    }
	
	@Test
	public void SateTest()
    {
		productCommandHandler = new AddProductCommandHandler(reservationRepository, productRepository, suggestionService, clientRepository, systemContext);
		productCommandHandler.handle(productCommand);
    	
    	assertEquals(productCommand.getQuantity(), 5);
    }
    	
    }

