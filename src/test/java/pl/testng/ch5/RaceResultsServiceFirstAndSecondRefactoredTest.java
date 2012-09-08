package pl.testng.ch5;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



@Test
public class RaceResultsServiceFirstAndSecondRefactoredTest {

	private static Date TEST_DATE = new Date();
	
	private RaceResultsService raceResults;
	private Message messageACategory;
	private Message messageBCategory;
	private Client clientA;
	private Client clientB;
	private Logger logger; 
	
	@BeforeMethod
	protected void setUp() throws Exception {
		raceResults = new RaceResultsService();
		
		logger = mock(Logger.class);
		raceResults.setLogger(logger);
		messageACategory = mock(Message.class);
		when(messageACategory.getCategory()).thenReturn("CategoryA");
		when(messageACategory.getText()).thenReturn("CategoryAText");
		when(messageACategory.getDate()).thenReturn(TEST_DATE);
		
		messageBCategory = mock(Message.class);
		when(messageBCategory.getCategory()).thenReturn("CategoryB");
		when(messageBCategory.getText()).thenReturn("CategoryBText");
		when(messageBCategory.getDate()).thenReturn(TEST_DATE);
		
		clientA = mock(Client.class);
		when(clientA.getName()).thenReturn("clientA");
		
		clientB = mock(Client.class);
		when(clientB.getName()).thenReturn("clientB");
	}
	public void subscribedClientShouldReveiveMessage() {
		raceResults.addSubscriber("CategoryA",clientA);
		raceResults.send(messageACategory);
		verify(clientA).receive(messageACategory);
		verify(logger).log("clientA", "CategoryAText", TEST_DATE);
	}
	public void allSubscribedClientsShouldReceiveMessages() {
		raceResults.addSubscriber("CategoryA",clientA);
		raceResults.addSubscriber("CategoryA",clientB);
		raceResults.send(messageACategory);
		verify(clientA).receive(messageACategory);
		verify(clientB).receive(messageACategory);
		verify(logger).log("clientA", "CategoryAText", TEST_DATE);
		verify(logger).log("clientB", "CategoryAText", TEST_DATE);
		
	}
	public void notSubscribedClientShouldNotReceiveMessage() {
		raceResults.send(messageACategory);
		verify(clientA,never()).receive(messageACategory);
		verify(clientB,never()).receive(messageACategory);
		verify(logger,never()).log(anyString(), anyString(), any(Date.class));
		
	}
	public void shouldSendOnlyOneMessageToMultiSubscriber() {
		raceResults.addSubscriber("CategoryA",clientA);
		raceResults.addSubscriber("CategoryA",clientA);
		raceResults.send(messageACategory);
		verify(clientA,times(1)).receive(messageACategory);
		verify(logger).log("clientA", "CategoryAText", TEST_DATE);

	}
	public void unsubsribedClientShouldNotReceiveMessages() throws UnknowClientException {
		raceResults.addSubscriber("CategoryA",clientA);
		raceResults.removeSubsciber("CategoryA",clientA);
		raceResults.send(messageACategory);
		verify(clientA,never()).receive(messageACategory);
		verify(logger,never()).log(anyString(), anyString(), any(Date.class));
			
	}
	public void shoudlRecivedMessageFromSubscribedCategory() {
		raceResults.addSubscriber("CategoryA",clientA);
		raceResults.addSubscriber("CategoryB",clientB);
		raceResults.send(messageACategory);
		verify(clientA,times(1)).receive(messageACategory);
		verify(clientB,never()).receive(messageACategory);
		verify(logger).log("clientA", "CategoryAText", TEST_DATE);
		verify(logger,never()).log("clientB", "CategoryBText", TEST_DATE);
	}

	public void oneSubscribedShouldBeAbledToSubscibeToManyCategory() {
		raceResults.addSubscriber("CategoryA",clientA);
		raceResults.addSubscriber("CategoryB",clientA);
		raceResults.addSubscriber("CategoryC",clientB);
		
		raceResults.send(messageACategory);
		raceResults.send(messageBCategory);
		verify(clientA,times(2)).receive(any(Message.class));
		verify(clientB,never()).receive(any(Message.class));
		verify(logger,times(2)).log(eq("clientA"), anyString(), eq(TEST_DATE));
		verify(logger,never()).log("clientB", "CategoryBText", TEST_DATE);

	}
	public void shouldRecivedOnlySubscribedCategoriesMessages() {

		raceResults.addSubscriber("CategoryB",clientA);
		raceResults.send(messageACategory);

		verify(clientA,never()).receive(any(Message.class));

	}
	public void shouldFinishedWitoutAnyException() {

	
		raceResults.send(messageACategory);

	}
	@Test(expectedExceptions=UnknowClientException.class)
	public void removedNotSubscribedClient() throws UnknowClientException {

		
		raceResults.removeSubsciber("CategoryA", clientA);

	}
	public void anyUserShouldNotRecievedMessage() {
		
		raceResults.addSubscriber("CategoryB",clientA);
		raceResults.send(mock(Message.class));

		verify(clientA,never()).receive(any(Message.class));
	}
	
}
