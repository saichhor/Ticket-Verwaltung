package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ticket {

    public String ID;
    public String Name;
    public String Beschreibung;
    public String Status;
    public String Priorität;

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
                    a.ID = words[0];
                    a.Name = words[1];
                    a.Beschreibung = words[2];
                    a.Status = words[3];
                    a.Priorität = words[4];
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




