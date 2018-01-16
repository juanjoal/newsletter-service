package code.challenge.service.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import code.challenge.service.model.Subscriber;

public class SubscriberDao {
	
	private static Logger logger = LoggerFactory.getLogger(SubscriberDao.class);
	
	
	private String insert_sp = "{call insert_subscriptor(?,?, ?, ?, ?, ?, ?) }";
	


	public Subscriber create(Subscriber subscriber, short idNewsletter, short idCampaing) {

		int outputValue = -1;
		Context initContext;
		try {
			initContext = new InitialContext();	
			DataSource ds = (DataSource)initContext.lookup("java:jboss/MySqlDS");
			
			//autoclose
			try (Connection conn = ds.getConnection();
					CallableStatement insertSubscriptor = conn.prepareCall(insert_sp)){
				
				insertSubscriptor.setString(1, subscriber.getEmail());
				insertSubscriptor.setString(2, subscriber.getFirstname());
				insertSubscriptor.setString(3, subscriber.getGender());
				insertSubscriptor.setDate(4, new java.sql.Date(subscriber.getDateOfBirth().getTime()));
				insertSubscriptor.setShort(5, idNewsletter);
				insertSubscriptor.setShort(6, idCampaing);
				insertSubscriptor.registerOutParameter(7, Types.SMALLINT);
				
				boolean resultado = insertSubscriptor.execute();
				
				outputValue = insertSubscriptor.getShort(7);
				subscriber.setId(outputValue);
				logger.debug("outputValue: " + outputValue);
				logger.debug("resultado: " + resultado);
			}catch (SQLException e) {
				logger.error("", e);
			}


		} catch (NamingException e) {
			logger.error("", e);
		} 
		return subscriber;
	}
	
}
