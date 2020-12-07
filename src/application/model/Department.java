package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Department {

    public String Department1;
    public String DepartmentNumber;


    public static ObservableList<Department> readFile(String filename){
        return readFile(new File(filename));
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
}
