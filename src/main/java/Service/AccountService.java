package Service;

import Model.Account;
import DAO.AccountDAO;



public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account){
        Account newAddedAccount = accountDAO.createAccount(account);
        return newAddedAccount;
    }

}


    
    

   
    

    