package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class Priority {

    public String priorityId;
    public String desc;

    public Priority(String priority_id, String name){
        this.priorityId = priority_id;
        this.desc = name;

    }


    public static ObservableList<Priority> readFile(String filename) {
        return readFile(new File(filename));
    }


    public static ObservableList<Priority> loadList() {
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities");

            while (result.next()) {
                Priority p = new Priority(result.getString("priority_id"),result.getString("name"));


                list.add(p);


                //Priority a = new Priority(result.getInt("priority_id"), result.getString("name"));

            }
        } catch (SQLException throwables) {

            //throwables.printStackTrace();
        }

        return list;
    }

    public static ObservableList<Priority> readFile(File file) {
        ObservableList<Priority> list = FXCollections.observableArrayList();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {

                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");
                    Priority a = new Priority();
                    a.priorityId = words[0];
                    a.priorityId = words[1];
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
        return desc;
    }

    public void delete() {
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM priorities WHERE priority_id = " + priorityId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(){
        try{
            Connection connection = AccessDb.getConnection();

            PreparedStatement statement = null;
            statement = connection.prepareStatement("UPDATE priorities SET name = ? WHERE priority_id = ?");
            statement.setString(1, desc);
            statement.setInt(2, Integer.parseInt(priorityId));

            statement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static  Priority getById(int id) {
        Priority obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM priorities WHERE priotity_id = " + id);

            if(result.next()) {
                obj.Department1 = result.getString("name");
                obj.DepartmentNumber = result.getString("department");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }

}
