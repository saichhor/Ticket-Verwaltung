package application.controller;

import application.model.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.text.html.ListView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StatusReader {

    ObservableList<Status> list = FXCollections.observableArrayList();
    private String s;


    public ObservableList<Status> readFile(String filename){

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

    public ObservableList<Status> readeFile(File file){

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                br.readLine();
                while ((s = br.readLine()) != null) {
                    //s enthält die gesamte Zeile
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
        } catch (IOException io) {
        }
        return list;
    }

}
