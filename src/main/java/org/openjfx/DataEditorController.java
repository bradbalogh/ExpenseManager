package org.openjfx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class DataEditorController {

    //Setting ID's :
    @FXML TextField payment_name;
    @FXML TextField payment_price;
    @FXML TextField payment_notes;
    @FXML TextFlow text_pane;
    @FXML ChoiceBox year_box;
    @FXML ChoiceBox month_box;


    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("dataViewer");
    }

    @FXML
    public void getDate() {
        System.out.println(month_box.getValue());
        System.out.println(year_box.getValue());
    }

    @FXML
    private void addPayment() {
        String paymentName = payment_name.getText();
        String paymentPrice = payment_price.getText();
        String paymentNotes = payment_notes.getText();
        if(paymentName != "" && paymentPrice != "" && paymentNotes != "") {
            Text payment = new Text("Payment Name: " + paymentName + "   " + "Payment Price:  " + paymentPrice + "   " + "Payment Notes: " + paymentNotes + '\n');
            payment.setFill(Color.WHITE);
            payment.setFont(Font.font("Verdana", 13));
            text_pane.getChildren().add(payment);
        }
    }

    @FXML
    private void clearFields() {
        payment_name.setText("");
        payment_price.setText("");
        payment_notes.setText("");
    }

}
