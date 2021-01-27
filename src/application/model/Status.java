package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Status {

    public String StatusName;
    public String StatusID;

    public Status(String status_id, String name) {
        this.StatusName = status_id;
        this.StatusID = name;

    }

    public static ObservableList<Status> loadList(){
        ObservableList<Status> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT  * FROM stati");

            while(result.next()){
                Status s = new Status(result.getString("name"), result.getString("status_id"));

                list.add(s);

            }
        }catch (SQLException throwables){

            throwables.printStackTrace();
        }

        return list;
    }
    


    public static ObservableList<Status> readFile(String filename){
        return readFile(new File(filename));
    }

    public static ObservableList<Status> readFile(File file){
        ObservableList<Status> list = FXCollections.observableArrayList();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {

                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");
                    Status a = new Status();
                    a.StatusID = words[0];
                    a.StatusName = words[1];
                    list.add(a);
                }
            } finally {
                br.close();
            }
        }catch (IOException io){
            io.printStackTrace();
        }
        return list;
    }

    @Override
    public String toString() {
        return StatusName;
    }

    public void delete() {
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM stati WHERE status_id = " + StatusID);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try{
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE stati SET name = ? WHERE status_id = ?");
            statement.setString(1, StatusName);
            statement.setInt(2, Integer.parseInt(StatusID));

            statement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public static  Status getById(int id) {
        Status obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM stati WHERE status_id = " + id);

            if(result.next()) {
                obj.StatusID = result.getString("status_id");
                obj.StatusName = result.getString("name");



            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }
}
