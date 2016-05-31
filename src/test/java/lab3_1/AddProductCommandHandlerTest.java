package lab3_1;

import static org.mockito.Mockito.mock;

import java.util.regex.Matcher;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.mockito.Mockito;
import org.hamcrest.Matchers;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.application.api.handler.AddProductCommandHandler;
import pl.com.bottega.ecommerce.sales.domain.client.Client;
import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import pl.com.bottega.ecommerce.sales.domain.equivalent.SuggestionService;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import pl.com.bottega.ecommerce.system.application.SystemContext;

public class AddProductCommandHandlerTest {
	@Test
	public void addProductCommandHandlerTest() {
		ReservationRepository reservationRepository = mock(ReservationRepository.class);
		ProductRepository productRepository = mock(ProductRepository.class);
		SuggestionService suggestionService = mock(SuggestionService.class);
		ClientRepository clientRepository = mock(ClientRepository.class);
		SystemContext systemContext = mock(SystemContext.class);
		AddProductCommand addProductCommand = mock(AddProductCommand.class);
		
	//	AddProductCommand addProductCommand = new AddProductCommand(new Id("1"), new Id("1"), 23);
		AddProductCommandHandler handler = new AddProductCommandHandler();
	//	handler.handle(addProductCommand);
		
//		Mockito.when(handler.handle(addProductCommand)).thenReturn(null);
		verify(reservationRepository,times(0)).load(new Id("2"));
	}

}
