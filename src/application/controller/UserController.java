package application.controller;

import application.model.Status;
import application.model.Ticket;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

public class UserController {
    public Button cancelButton;
    public TextField userTitelTextField;
    public TextField userStrasseTextField;
    public TextField userPLZTextField;
    public TextField userStadtTextField;
    public TextField userAbteilungTextField;
    public ListView<User> userListView;
    public TextField userNummerTextField;
    public TextField userNameTextField;
    public Button createButton;

    private User selectedItem = null;
    public File file = new File("user.csv");

    ObservableList<User> list = FXCollections.observableArrayList();

    public void initialize(){
        readFile();
    }

    public void readFile () {
        String s;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
            try {
                br.readLine(); // ignoriere die erste Zeile => Überschriften

                while ((s = br.readLine()) != null) {
                    // s enthält die gesamte Zeile
                    s = s.replace("\"", ""); // ersetze alle " in der Zeile
                    User a = new User();

                    String[] words = s.split(";");

                    a.userNummer = words[0];
                    a.userTitel = words[1];
                    a.userName = words[2];
                    a.userStrasse = words[3];
                    a.userPLZ = words[4];
                    a.userStadt = words[5];
                    a.userAbteilung = words[6];


                    list.add(a); // füge Artikel zur Liste hinzu
                }
            } finally {
                br.close();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        userListView.setItems(list);

    }

    public void ListViewclicked(MouseEvent mouseEvent) {
        User s = userListView.getSelectionModel().getSelectedItem();

        if (s != null) {
            this.selectedItem = s;
        }



        userNummerTextField.setText(userListView.getSelectionModel().getSelectedItem().userNummer);
        userTitelTextField.setText(userListView.getSelectionModel().getSelectedItem().userTitel);
        userNameTextField.setText(userListView.getSelectionModel().getSelectedItem().userName);
        userStrasseTextField.setText(userListView.getSelectionModel().getSelectedItem().userStrasse);
        userPLZTextField.setText(userListView.getSelectionModel().getSelectedItem().userPLZ);
        userStadtTextField.setText(userListView.getSelectionModel().getSelectedItem().userStadt);
        userAbteilungTextField.setText(userListView.getSelectionModel().getSelectedItem().userAbteilung);





    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void createButtonClicked(ActionEvent actionEvent) {
        userTitelTextField.clear();
        userStrasseTextField.clear();
        userPLZTextField.clear();
        userStadtTextField.clear();
        userAbteilungTextField.clear();
        userNummerTextField.clear();
        userNameTextField.clear();

        // lösche die Variable, die den gewählten Artikel
        // beinhaltet
        this.selectedItem = null;
    }

    public void saveButtonClicked(ActionEvent actionEvent) {

        if (this.selectedItem != null) {

            selectedItem.userNummer = userNummerTextField.getText();
            selectedItem.userTitel = userTitelTextField.getText();
            selectedItem.userName = userNameTextField.getText();
            selectedItem.userStrasse = userStrasseTextField.getText();
            selectedItem.userPLZ = userPLZTextField.getText();
            selectedItem.userStadt = userStadtTextField.getText();
            selectedItem.userAbteilung = userAbteilungTextField.getText();


            userListView.refresh();

        } else {

            System.out.println("Neuer User");
            String s;
            try {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"))) {
                    writer.write("\n\"" + userNummerTextField.getText() + "\";\"" + userStrasseTextField.getText() + "\";\"" + userPLZTextField.getText() + "\";\"" + userStadtTextField.getText() + "\";\"" + userPLZTextField.getText() + "\";\"" + userStadtTextField.getText() + "\";\"" + userAbteilungTextField.getText() + "\";\"" + userNummerTextField.getText() + "\";\"" + userNameTextField.getText() + "\";");
                    list.clear();
                    Ticket a = new Ticket();

                }
            } catch (IOException io) {
                io.printStackTrace();
            }
            initialize();
        }
    }
}
