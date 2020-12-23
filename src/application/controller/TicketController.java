package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TicketController {

    private Ticket ticket;
    public TextField titelTextField;
    public TextArea commentTextField;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;

    ObservableList<Ticket> list = FXCollections.observableArrayList();
    ObservableList<Ticket> searchResults = FXCollections.observableArrayList();

    private Ticket selectedItem = null;

    public void setTicket(Ticket t) {
        // zeige t.name im  entsprechenden TextField an.

        this.ticket = t;
        titelTextField.setText(t.name);
        commentTextField.setText(t.beschreibung);
        statusComboBox.setItems(Status.readFile("stati.csv"));
        priorityComboBox.setItems(Priority.readFile("priorities.csv"));


        for (Status s : statusComboBox.getItems()) {
            if (s.StatusID == t.status.StatusID) {
                statusComboBox.getSelectionModel().select(s);
                //statusComboBox.setSelectionModel(t.status.StatusName);
                break;
            }
        }
        for (Priority p : priorityComboBox.getItems()) {
            if (p.priorityId == t.prioritaet.priorityId) {
                priorityComboBox.getSelectionModel().select(p);
                break;
            }
        }

    }

    public Ticket getTicket() {
        /**
         * aktualisieren der Ticket -Daten
         */
        ticket.name = titelTextField.getText();

        return ticket;
    }

    public void newClicked(ActionEvent actionEvent) {

        selectedItem = null;

        titelTextField.setText("");
        commentTextField.setText("");
        statusComboBox.setSelectionModel(null);
        priorityComboBox.setSelectionModel(null);


        System.out.println("Neuer Artikel");
    }

    public void deleteClicked(ActionEvent actionEvent) {
        list.remove(selectedItem);
        //artikelList.refresh();
        //writeFile();
    }

    public void saveClicked(ActionEvent actionEvent) {
        if (selectedItem != null) {
            //aktualisieren

            selectedItem.name = titelTextField.getText();
            selectedItem.beschreibung = commentTextField.getText();
            //selectedItem.status = statusComboBox.setItems();
            //selectedItem.prioritaet = priorityComboBox.setItems();

            /**
             * Es wurde ein Artikel bearbeitet
             * Sag der Artikelliste dass sie sich "refresh" soll
             */


            System.out.println("Daten aktualisieren");
        } else {
            //neuer Artikel-

            Ticket a = new Ticket();
            a.name = titelTextField.getText();
            a.beschreibung = commentTextField.getText();
            //a.status = statusComboBox.getText();
            //a.prioritaet = priorityComboBox.getItems();

            System.out.println("Neuer Artikel");


            list.add(a);
        }
    }
}
