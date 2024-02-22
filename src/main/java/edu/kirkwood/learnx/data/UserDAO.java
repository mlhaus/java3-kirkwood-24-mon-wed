package edu.kirkwood.learnx.data;

import edu.kirkwood.learnx.model.User;
import edu.kirkwood.shared.CommunicationService;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Database {

    public static void main(String[] args) {
        getAll().forEach(System.out::println);
    }
    
    public static List<User> getAll() {
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection()) {
            if(connection != null) {
                try(CallableStatement statement = connection.prepareCall("{CALL sp_get_all_users()}")) {
                    try(ResultSet resultSet = statement.executeQuery()) {
                        while(resultSet.next()) {
                            int id = resultSet.getInt("id");
                            String firstName = resultSet.getString("first_name");
                            String lastName = resultSet.getString("last_name");
                            String email = resultSet.getString("email");
                            String phone = resultSet.getString("phone");
                            char[] password = resultSet.getString("password").toCharArray();
                            String language = resultSet.getString("language");
                            String status = resultSet.getString("status");
                            String privileges = resultSet.getString("privileges");
                            Instant created_at = resultSet.getTimestamp("created_at").toInstant();
                            Instant last_logged_in = resultSet.getTimestamp("last_logged_in").toInstant();
                            Instant updated_at = resultSet.getTimestamp("updated_at").toInstant();
                            User user = new User(id, firstName, lastName, email, phone, password, language, status, privileges, created_at, last_logged_in, updated_at);
                            users.add(user);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Likely error with stored procedure");
            System.out.println(e.getMessage());
        }
        return users;
    }
    public static User get(String email) {
        try(Connection connection = getConnection();
        CallableStatement statement = connection.prepareCall("{CALL sp_get_user(?)}");
        ) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                char[] password = resultSet.getString("password").toCharArray();
                String language = resultSet.getString("language");
                String status = resultSet.getString("status");
                String privileges = resultSet.getString("privileges");
                Instant created_at = resultSet.getTimestamp("created_at").toInstant();
                Instant last_logged_in = resultSet.getTimestamp("last_logged_in").toInstant();
                Instant updated_at = resultSet.getTimestamp("updated_at").toInstant();
                return new User(id, firstName, lastName, email, phone, password, language, status, privileges, created_at, last_logged_in, updated_at);
            }
        } catch(SQLException e) {
            System.out.println("Check your stored procedures");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String add(User user) {
        try(Connection connection = getConnection();
        CallableStatement statement = connection.prepareCall("{CALL sp_add_user(?, ?)}")
        ) {
            statement.setString(1, user.getEmail());
            String hashedPassword = BCrypt.hashpw(String.valueOf(user.getPassword()), BCrypt.gensalt(12));
            statement.setString(2, hashedPassword);
            int rowsAffected = statement.executeUpdate();
            if(rowsAffected == 1) {
                try(CallableStatement statement2 = connection.prepareCall("{CALL sp_get_2fa_code(?)}");) {
                    statement2.setString(1, user.getEmail());
                    ResultSet resultSet = statement2.executeQuery();
                    if(resultSet.next()) {
                         String code = resultSet.getString("code");
                         String method = resultSet.getString("method");
                         if(method.equals("email")) {
                             String subject = "LearnX New User";
                             String message = "<h2>Welcome to LearnX</h2>";
                             message += "<p>Please enter code <b>" + code + "</b> on the website to activate your account</p>";
                             boolean sent = CommunicationService.sendEmail(user.getEmail(), subject, message);
                             // To do: If the email is not send, delete the user by email and delete the 2fa
                             return sent ? code : "";
                         }
                    }
                }
            }
        } catch(SQLException e) {
            System.out.println("Likely error with stored procedure");
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public static void update(User user) {
        try(Connection connection = getConnection();
        CallableStatement statement = connection.prepareCall("{CALL sp_update_user(?,?,?,?,?,?,?,?,?)}")
        ) {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getLanguage());
            statement.setString(7, user.getStatus());
            statement.setString(8, user.getPrivileges());
            statement.setTimestamp(9, Timestamp.from(user.getLast_logged_in()));
            statement.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Likely error with stored procedure");
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
