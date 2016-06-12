package memoryGame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    public void buttonClicked(ActionEvent actionEvent) {
        System.out.printf("Clicked %n");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
