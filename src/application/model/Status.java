package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Status {

    public String Status1;
    public String StatusNummer;



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
                    a.StatusNummer = words[0];
                    a.Status1 = words[1];
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

}
