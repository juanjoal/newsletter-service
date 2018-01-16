package code.challenge.frontend;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import code.challenge.frontend.apikey.ApiKeyManager;
import code.challenge.frontend.model.Subscriber;



/**
 * 
 * @author Juanjo
 *
 */
@ManagedBean
@ViewScoped
public class SubscriptionBackingBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Subscriber subscriber;

	//TODO this must be placed as external properties as is environment dependient
	private static String SERVICE_URL = "http://localhost:8080/newsletter-service/rest/subscribers";

	public void subscribe() {

		Client cliente = ClientBuilder.newClient();
		Subscriber persistedSubscriber = cliente.target(SERVICE_URL).
				request().header("X-API-Key-Token", ApiKeyManager.getAPIKey()).
				post(Entity.entity(subscriber, MediaType.APPLICATION_JSON),
				Subscriber.class);
		
		if (persistedSubscriber != null && persistedSubscriber.getId() == -1) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "An account with this email address already exists."));
	    
		}
	}

	

	public Subscriber getSubscriber() {
		return subscriber;
	}


	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}
	
}
