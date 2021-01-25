package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class User {

    public String userNummer;
    public String userTitel;
    public String userName;
    public String userStrasse;
    public String userPLZ;
    public String userStadt;
    public String userAbteilung;



    public static ObservableList<User> readFile(String filename) {
        return readFile(new File(filename));
    }

    public static ObservableList<User> loadList(){
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT  * FROM user");

            while(result.next()){
                User u = new User();
                /**
                (result.getInt("user_id"),
                        result.getInt("name"),
                        result.getInt("title"),
                        result.getInt("street"),
                        result.getInt("zip"),
                        result.getInt("city"),
                        result.getInt("department"));
                 das oben nur wenn man construktor macht
                 **/
                u.userNummer = result.getString("user_id");
                u.userTitel = result.getString("title");
                u.userAbteilung = result.getString("department");
                u.userStrasse = result.getString("street");
                u.userStadt = result.getString("city");
                u.userPLZ = result.getString("zip");
                u.userName = result.getString("name");
                list.add(u);
            }
        }catch (SQLException throwables){

            throwables.printStackTrace();
        }

        return list;
    }

    public static ObservableList<User> readFile(File file) {
        ObservableList<User> list = FXCollections.observableArrayList();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {

                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");
                    User a = new User();
                    a.userNummer = words[0];
                    a.userTitel = words[1];
                    a.userName = words[2];
                    a.userStrasse = words[3];
                    a.userPLZ = words[4];
                    a.userStadt = words[5];
                    a.userAbteilung = words[6];


                    list.add(a);
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return list;
    }

    @Override
    public String toString() {
        return userName;
    }

    public void delete() {
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM user WHERE user_id = " + userNummer);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try{
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE user SET name = ? WHERE user_id = ?");
            statement.setString(1, userName);
            statement.setInt(2, Integer.parseInt(userNummer));

            statement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}


