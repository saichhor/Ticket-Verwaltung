package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Priority {

    public String priorityId;



    public static ObservableList<Priority> readFile(String filename){
        return readFile(new File(filename));
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
        return priorityId;
    }
}
