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
	public Response create(@HeaderParam("X-My-API-Key-Token") String apikeytoken , final Subscriber subscriber) {
		
		if (isValidToken(apikeytoken)) {
			SubscriberDao dao = new SubscriberDao();
			Subscriber subscriberPersisted = dao.create(subscriber, (short) 1, (short) 1);
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

}
