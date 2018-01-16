/**
 * 
 */
package code.challenge.service.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import code.challenge.service.dao.SubscriberDao;
import code.challenge.service.model.Subscriber;

/**
 * @author Juanjo
 *
 */
@RequestScoped
@Path("/subscribers")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class SubscriberEndpoint {

	/**
	* @param subscriber
	* @return
	*/
	@POST
	public Response create(@HeaderParam("X-API-Key-Token") String apikeytoken , final Subscriber subscriber) {
		

		if (isValidToken(apikeytoken)) {
			SubscriberDao dao = new SubscriberDao();
			Subscriber subscriberPersisted = dao.create(subscriber, (short) 1, (short) 1);
			if (subscriberPersisted != null && subscriberPersisted.getId() == -1) {
				this.sendEmail(subscriberPersisted);
				this.sendEvent(subscriberPersisted);
			
			}else {
				return Response.status(Response.Status.CONFLICT)
				        .type(MediaType.TEXT_PLAIN_TYPE)
				        .entity("email duplicated")
				        .build();
			}
			return Response.ok(subscriberPersisted).build();
		}else {
			return Response.status(Response.Status.UNAUTHORIZED)
			        .type(MediaType.TEXT_PLAIN_TYPE)
			        .entity("Unauthorized")
			        .build();
		}
		

	}
	
	/**
	 * Mock method
	 * 
	 * @param apikeytoken
	 * @return
	 */
	private boolean  isValidToken(String apikeytoken) {
		if (apikeytoken != null && apikeytoken.equals("01234567890ABCDEFGH")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/**
	 * mock
	 * @param subscriber
	 */
	private void sendEmail(final Subscriber subscriber) {
	
		/**
		 * 	this call must be asynchronous given the two SLA
		 * this service SLA 300ms
		 * email service SLA 2 secconds
		 * 
		 * option 1 
		 * 
		 * client.target(REST_SERVICE_URL)
			.request()
			.async()
			.post(Entity.entity(subscriber, MediaType.APPLICATION_JSON),
					new InvocationCallback<Subscriber>() {
						@Override
						public void completed(final Book bookPersisted) {
							//ok
						}
 
						@Override
						public void failed(final Throwable throwable) {
							//retry or backlog of failed for batch processing
						}
					}).get();
					
			option 2: send to queue
		 * 
		 * 
		 */
	}
	
	/**
	 * mock
	 * @param subscriber
	 */
	private void sendEvent(final Subscriber subscriber) {
		// sync call
	}

}
