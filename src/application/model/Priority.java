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




public class Priority {

    public String priorityId;
    public String desc;




    public static ObservableList<Priority> readFile(String filename){
        return readFile(new File(filename));
    }


    public static ObservableList<Priority> loadList(){
        ObservableList<Priority> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT  * FROM priority");

            while(result.next()){
                Priority p = new Priority();
                p.desc = result.getString("name");
                p.priorityId = result.getString("priority_id");
                list.add(p);


                //Priority a = new Priority(result.getInt("priority_id"), result.getString("name"));

            }
        }catch (SQLException throwables){

            //throwables.printStackTrace();
        }

        return list;
    }

    public static ObservableList<Priority> readFile(File file){
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
        }catch (IOException io){
            io.printStackTrace();
        }
        return list;
    }

    @Override
    public String toString() {
        return desc;
    }
}
