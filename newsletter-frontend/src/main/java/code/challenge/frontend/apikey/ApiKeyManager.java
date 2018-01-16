package code.challenge.frontend.apikey;

/**
 * Mock apikey manager. The api key must be place in a file with read permissions only for the
 * process user
 * 
 * @author Juanjo
 *
 */
public class ApiKeyManager {
	
	private static String API_KEY = "01234567890ABCDEFGH";
	
	public static String getAPIKey() {
		return API_KEY;
	}

}
