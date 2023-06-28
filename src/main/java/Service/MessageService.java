package Service;

import DAO.MessageDAO;
import Model.Message;
import Service.AccountService;

public class MessageService {
    MessageDAO messageDAO;
    private AccountService accountService;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountService = new AccountService();
    }

    public MessageService(MessageDAO messageDAO, AccountService accountService){
        this.messageDAO = messageDAO;
        this.accountService = accountService;
    }


    public Message addMessage(Message message, AccountService accountService) {
        // Check if the message is valid
        if (message.getMessage_text().length() == 0 || message.getMessage_text().length() > 255) {
            return null; // Invalid message length
        }
    
        // Check if the posted_by user exists
        Account postedBy = accountService.getAccount(message.getPosted_by());
        if (postedBy == null) {
            return null; // User does not exist
        }
    
        // Create the message
        Message newAddedMessage = messageDAO.createMessage(message);
    
        return newAddedMessage;
    }
    
}
