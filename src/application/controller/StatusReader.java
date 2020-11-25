package application.controller;

import application.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.text.html.ListView;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StatusReader {

    ObservableList<Status> list = FXCollections.observableArrayList();
    private String s;


    public ObservableList readFile(String filename){

        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            try {

                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");
                    Status a = new Status();
                    a.StatusNummer = words[0];
                    a.Status1 = words[1];
                    list.add(a);
                }
            } finally {
                br.close();
            }
        }catch (IOException io){
        }
        return list;
    }

}
