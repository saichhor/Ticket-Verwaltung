package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class User {

    public String userNummer;
    public String userTitel;
    public String userName;
    public String userStrasse;
    public String userPLZ;
    public String userStadt;
    public String userAbteilung;


    public static ObservableList<User> readFile(String filename) {
        return readFile(new File(filename));
    }

    public static ObservableList<User> readFile(File file) {
        ObservableList<User> list = FXCollections.observableArrayList();
        String s;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {

                while ((s = br.readLine()) != null) {
                    s = s.replace("\"", "");
                    String[] words = s.split(";");
                    User a = new User();
                    a.userNummer = words[0];
                    a.userTitel = words[1];
                    a.userName = words[2];
                    a.userStrasse = words[3];
                    a.userPLZ = words[4];
                    a.userStadt = words[5];
                    a.userAbteilung = words[6];


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
        return userName;
    }
}


