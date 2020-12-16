package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TicketController {

    private Ticket ticket;
    public TextField titelTextField;
    public TextArea commentTextField;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;

    public void setTicket(Ticket t) {
        // zeige t.name im  entsprechenden TextField an.

        this.ticket = t;
        titelTextField.setText(t.Name);
        commentTextField.setText(t.Beschreibung);
        statusComboBox.setItems(Status.readFile("stati.csv"));
        priorityComboBox.setItems(Priority.readFile("priorities.csv"));


        for (Status s : statusComboBox.getItems()){
            //if(s.StatusNummer == t.Status.StatusNummer)
        }

    }
    public Ticket getTicket(){
        /**
         * aktualisieren der Ticket -Daten
         */
        ticket.Name = titelTextField.getText();

        return ticket;
    }

}
