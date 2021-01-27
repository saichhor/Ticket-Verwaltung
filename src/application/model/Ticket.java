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

public class Ticket {

    public String id;
    public String name;
    public String beschreibung;
    public Status status;
    public Priority prioritaet;

    public Ticket(String ticket_id, String name, String desc, int priotity_id, int status_id) {
        this.id = ticket_id;
        this.name = name;
        this.beschreibung = desc;
        this.status = Status.getById(status_id);
        this.prioritaet = Priority.getById(priotity_id);
    }
/**
    public static ObservableList<Ticket> readFile(String filename){
        return readFile(new File(filename));
    }
 **/

    public static ObservableList<Ticket> loadList(){
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT  * FROM tickets");

            while(result.next()){
                Ticket t = new Ticket(result.getString("ticket_id"), result.getString("name"),result.getString("desc"), result.getInt("priority_id"), result.getInt("status_id"));


                list.add(t);
            }
        }catch (SQLException throwables){

            throwables.printStackTrace();
        }

        return list;
    }
    /**

    public static ObservableList<Ticket> readFile(File file){
        ObservableList<Ticket> list = FXCollections.observableArrayList();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {

                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");
                    Ticket a = new Ticket();
                    a.id = words[0];
                    a.name = words[1];
                    a.beschreibung = words[2];
                    Status status = new Status();
                    status.StatusID = words[3];
                    a.status = status;
                    Priority priority = new Priority();
                    priority.priorityId = words[4];

                    a.prioritaet = priority;
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
     **/

    public static  Ticket getById(int id) {
        Ticket obj = null;
        try {
            Connection connection = AccessDb.getConnection();

            Statement statement = null;
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM ticktes WHERE ticket_id = " + id);

            if(result.next()) {
                obj = new Ticket(result.getString("ticket_id"),
                        result.getString("name"),
                        result.getString("desc"),
                        result.getInt("priority_id"),
                        result.getInt("status_id"));



            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return obj;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}


