package pl.testng.ch5;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class RaceResultsService {

	private Map<String, Collection<Client>> subsciriptions = new HashMap<String, Collection<Client>>();
	
	private Logger logger;

	public void addSubscriber(String category, Client client) {

		if (subsciriptions.get(category) == null) {
			subsciriptions.put(category, new HashSet<Client>());
		}

		subsciriptions.get(category).add(client);

	}

	public void send(Message message) {

		Collection<Client> clients = subsciriptions.get(message.getCategory());
		if (clients != null) {
			for (Client client : clients) {
				client.receive(message);
				
				logger.log(client.getName(),message.getText(),message.getDate());
			}
		}

	}

	public void removeSubsciber(String category, Client client) throws UnknowClientException {
		
		if(subsciriptions.get(category) ==null || !subsciriptions.get(category).contains(client)) {
			throw new UnknowClientException();
		} else {
		subsciriptions.get(category).remove(client);
		}
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
		
	}

}
