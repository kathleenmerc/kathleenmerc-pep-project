package Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.AccountDAO;
import Model.Account;
import Service.AccountService;

import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
public class SocialMediaController {

    AccountService accountService;
    public SocialMediaController() {
        accountService = new AccountService();
    }
    
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccount);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param username 
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAccount(Context ctx) throws JsonProcessingException {
        //String username = ctx.pathParam("username");
        
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account newAddedAccount = accountService.addAccount(account);
        //Account existingAccount = AccountDAO.getAccountByUsername(username);

        //if(newAddedAccount == null || existingAccount != null){
        if(newAddedAccount == null) {
            ctx.status(400);
            ctx.json(' ');
        }else{
            ctx.status(200);
            ctx.json(newAddedAccount);
            System.out.println(newAddedAccount);
        }
    }


}