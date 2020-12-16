package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ticket {

    public String id;
    public String name;
    public String beschreibung;
    public Status status;
    public Priority prioritaet;

    public static ObservableList<Ticket> readFile(String filename){
        return readFile(new File(filename));
    }

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

    @Override
    public String toString() {
        return Name;
    }
}




