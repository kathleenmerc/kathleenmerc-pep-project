package DAO;

import Model.Message;
import Util.ConnectionUtil;

import java.sql.*;

public class MessageDAO {

    public Message createMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        
        try {
            String sql = "INSERT INTO message (message_text, posted_by) VALUES (?, ?)";


            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, message.getMessage_text());
            preparedStatement.setInt(2, message.getPosted_by());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            

            if (generatedKeys.next()) {
                int messageId = generatedKeys.getInt(1);
                message.setMessage_id(messageId);
            }

            return message;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
        
    }
}
