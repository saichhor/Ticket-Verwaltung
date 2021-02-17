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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketController {

    public ComboBox orderComboBox;
    private Ticket ticket;
    public TextField titelTextField;
    public TextArea commentTextField;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;

    ObservableList<Ticket> list = FXCollections.observableArrayList();


    public void setTicket(Ticket t) {
        // zeige t.name im  entsprechenden TextField an.

        this.ticket = t;
        statusComboBox.setItems(Status.loadList());
        priorityComboBox.setItems(Priority.loadList());


        if (t != null) {
            titelTextField.setText(t.name);
            commentTextField.setText(t.beschreibung);


            for (Status s : statusComboBox.getItems()) {
                if (s.StatusID.equals(t.status.StatusID)) {
                    statusComboBox.getSelectionModel().select(s);
                    //statusComboBox.setSelectionModel(t.status.StatusName);
                    break;
                }
            }
            for (Priority p : priorityComboBox.getItems()) {
                if (p.priorityId == (t.prioritaet.priorityId)) {
                    priorityComboBox.getSelectionModel().select(p);
                    break;
                }
            }

        }
    }

    public Ticket getTicket() {
        /**
         * aktualisieren der Ticket -Daten
         */
        ticket.name = titelTextField.getText();
        ticket.beschreibung = commentTextField.getText();
        ticket.status = statusComboBox.getValue();
        ticket.prioritaet = priorityComboBox.getValue();

        return ticket;
    }


}
