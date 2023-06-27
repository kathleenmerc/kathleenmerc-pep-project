package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;

public class AccountDAO {

    public Account createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        
        try {
            String sql = "INSERT INTO account(username, password) VALUES (?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if (account.getPassword().length() < 4) {
                return null; 
            }

            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
        
    }



    public static Account getAccountByUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try {
            
            String sql = "SELECT * from account WHERE username = ?;";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}