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
import pl.com.bottega.ecommerce.sales.application.api.handler.AddProductComandHandlerMockClass;
import pl.com.bottega.ecommerce.sales.application.api.handler.AddProductCommandHandler;
import pl.com.bottega.ecommerce.sales.domain.client.Client;
import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import pl.com.bottega.ecommerce.sales.domain.equivalent.SuggestionService;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.Product;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import pl.com.bottega.ecommerce.sales.domain.reservation.Reservation;
import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import pl.com.bottega.ecommerce.sharedkernel.exceptions.DomainOperationException.DomainOperationException;
import pl.com.bottega.ecommerce.system.application.SystemContext;

public class AddProductCommandHandlerTest {

    private AddProductCommandHandler productCommandHandler;
    private AddProductCommand productCommand;
    private ProductRepository productRepository;
    private ReservationRepository reservationRepository;
    private SuggestionService suggestionService;
    private ClientRepository clientRepository;
    private SystemContext systemContext;

    @Before
    public void init()
    {
        reservationRepository = AddProductComandHandlerMockClass.reservationRepositoryMock();
        productRepository = AddProductComandHandlerMockClass.productRepositoryMock();
        suggestionService = AddProductComandHandlerMockClass.suggestionServiceMock();
        clientRepository = AddProductComandHandlerMockClass.clientRepositoryMock();
        systemContext = new SystemContext();

        productCommandHandler = new AddProductCommandHandler(reservationRepository, productRepository, suggestionService, clientRepository, systemContext);
        productCommand = new AddProductCommand(new Id("4"),new Id("4"),123);
        productCommandHandler.handle(productCommand);
    }

    
    public void Test()
    {
    	Mockito.verify(clientRepository, Mockito.times(1)).load(new Id("4"));

    }
}
