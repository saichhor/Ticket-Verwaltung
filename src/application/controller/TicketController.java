package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TicketController {

    public TextField titelTextField;
    public TextArea commentTextField;
    public ComboBox<Status> statusComboBox;
    public ComboBox<Priority> priorityComboBox;

    public void setTicket(Ticket t) {
        // zeige t.name im  entsprechenden TextField an.

        titelTextField.setText(t.Name);
        commentTextField.setText(t.Beschreibung);

        statusComboBox.getSelectionModel().select(Integer.parseInt(t.Status));
        statusComboBox.setItems(Status.readFile("stati.csv"));

        priorityComboBox.getSelectionModel().select(Integer.parseInt(t.Priorität));


    }

    @Override
    public String toString() {
        return "TicketController{" +
                "titelTextField=" + titelTextField +
                ", commentTextField=" + commentTextField +
                ", statusComboBox=" + statusComboBox +
                ", priorityComboBox=" + priorityComboBox +
                '}';
    }
}
