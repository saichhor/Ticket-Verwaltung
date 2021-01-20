package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Department {

    public String Department1;
    public String DepartmentNumber;


    public static ObservableList<Department> readFile(String filename){
        return readFile(new File(filename));
    }

    public static ObservableList<Department> loadList(){
        ObservableList<Department> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT  * FROM priorities");

            while(result.next()){
                Department d = new Department();
                d.Department1 = result.getString("department");
                d.DepartmentNumber = result.getString("department_number");

                list.add(d);
            }
        }catch (SQLException throwables){

            throwables.printStackTrace();
        }

        return list;
    }

    public static ObservableList<Department> readFile(File file){
        ObservableList<Department> list = FXCollections.observableArrayList();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {

                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");
                    Department a = new Department();
                    a.DepartmentNumber = words[0];
                    a.Department1 = words[1];
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
        return Department1;
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
}
