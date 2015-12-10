/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thjoe14.gop_3_assignment;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextArea gameField;

    @FXML
    public ComboBox<String> playersPicker;
    
    int numberofPlayers = 2;
        
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        if(playersPicker.getSelectionModel().getSelectedIndex() > 0){
            numberofPlayers = 2 + (playersPicker.getSelectionModel().getSelectedIndex());
        }
        else{
            numberofPlayers = 2;
        }
        Driver driver = new Driver(numberofPlayers);
        driver.fillFields();
        driver.playGame();//Run game
        label.setText(playersPicker.getSelectionModel().getSelectedItem());
    }
    
    public void appendText(String str) {
        Platform.runLater(() -> gameField.appendText(str));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true)); 
        
        playersPicker.getItems().clear();
        playersPicker.getItems().addAll(
            "2",
            "3",
            "4",
            "5");
    }    
    
}
