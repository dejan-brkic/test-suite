/**
 * 
 */
package org.craftercms.studio.test.api.objects;

import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.craftercms.studio.test.utils.APIConnectionManager;
import org.craftercms.studio.test.utils.ConstantsPropertiesManager;
import org.craftercms.studio.test.utils.FilesLocations;
import org.craftercms.studio.test.utils.JsonTester;

/**
 * @author luishernandez
 *
 */
public class SecurityAPI extends BaseAPI{
	
	private String password;
	private String userName;

	public SecurityAPI(JsonTester api, APIConnectionManager apiConnectionManager) {
		super(api, apiConnectionManager);
		ConstantsPropertiesManager constantsPropertiesManager = new ConstantsPropertiesManager(
				FilesLocations.CONSTANTSPROPERTIESFILEPATH);
		userName = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.api.username");
		password = constantsPropertiesManager.getSharedExecutionConstants().getProperty("crafter.api.password");
	}
	
	public void logInIntoStudioUsingAPICall() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", userName);
		json.put("password", password);
		api.post("/studio/api/1/services/api/1/security/login.json").json(json).execute().status(200);
	}
	
	public void testLogInUnauthorized() {
		Map<String, Object> json = new HashMap<>();
		json.put("username", userName+"nonvalid");
		json.put("password", password+"nonvalid");
		api.post("/studio/api/1/services/api/1/security/login.json").json(json).execute().status(401);
		
	}

	public void logOutFromStudioUsingAPICall() {
		api.post("/studio/api/1/services/api/1/security/logout.json").execute().status(200);
	}
	
	public void loginWithOtherUser(String username, String password) {
		Map<String, Object> json = new HashMap<>();
		json.put("username", username);
		json.put("password", password);
		api.post("/studio/api/1/services/api/1/security/login.json").json(json).execute().status(200);
	}

	public void testValidateSession() {
		api.get("/studio/api/1/services/api/1/security/validate-session.json").execute().status(200)
		.json("$.message", is("OK")).debug();
	}
	
    public void testValidateSessionUnauthorized(){
		api.get("/studio/api/1/services/api/1/security/validate-session.json")
		.execute().status(401).debug();
    }
    
    public void testGetUserPermissions(String siteId){
		Map<String, Object> json = new HashMap<>();
		json.put("site", siteId);
		json.put("user", userName);
		
    	api.get("/studio/api/1/services/api/1/security/get-user-permissions.json")
    	.json(json).execute().status(200).debug();
    }
	
    public void testGetUserRoles(String siteId){
		Map<String, Object> json = new HashMap<>();
		json.put("site", siteId);
		json.put("user", userName);
		
    	api.get("/studio/api/1/services/api/1/security/get-user-roles.json")
    	.json(json).execute().status(200).debug();
    }
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
