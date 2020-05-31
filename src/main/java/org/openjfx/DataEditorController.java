package org.openjfx;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
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
    public void switchToSecondary() throws IOException {
        App.setRoot("dataViewer");
    }

    @FXML
    public String getDate() {
        String date = "";
        try {
            String month = (month_box.getValue()).toString();
            String year = (year_box.getValue()).toString();
            date = (month + year);
        } catch (NullPointerException ex) {
            date = ("missingValue");
        }
        return date;
    }

    @FXML
    public void addPayment() {
        String date = getDate();
        String paymentName = payment_name.getText();
        String paymentPrice = payment_price.getText();
        String paymentNotes = payment_notes.getText();
        if(date.equals("missingValue")){
            Text paymentError = new Text("Please Select A Date To Add Payment"+ '\n');
            paymentError.setFill(Color.YELLOW);
            paymentError.setFont(Font.font("Verdana", 13));
            text_pane.getChildren().add(paymentError);
        }
        else if(paymentName.equals("") || paymentPrice.equals("")) {
            Text paymentError = new Text("Payment Name And Payment Price Must Be Filled"+ '\n');
            paymentError.setFill(Color.YELLOW);
            paymentError.setFont(Font.font("Verdana", 13));
            text_pane.getChildren().add(paymentError);
        }
        else {
            try {
                Float.parseFloat(paymentPrice);
                Text paymentError = new Text("Added Payment: " + paymentName + " [" + date + "]" + '\n');
                paymentError.setFill(Color.GREEN);
                paymentError.setFont(Font.font("Verdana", 13));
                text_pane.getChildren().add(paymentError);
                writeToFile();
            } catch (NumberFormatException e) {
                Text paymentError = new Text("Payment Price Must Be Entered As Type Double i.e. 15.25"+ '\n');
                paymentError.setFill(Color.YELLOW);
                paymentError.setFont(Font.font("Verdana", 13));
                text_pane.getChildren().add(paymentError);
            }
        }
    }

    @FXML
    private void writeToFile() {
        String date = getDate();
        String paymentName = payment_name.getText();
        String paymentPrice = payment_price.getText();
        String paymentNotes = payment_notes.getText();

        try {
            // create a list of objects
            List<List<String>> records = Arrays.asList(
                    Arrays.asList(date, paymentName, paymentPrice, paymentNotes)
            );
            // create a writer
            FileWriter fileWritter = new FileWriter("payments.csv",true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            // write all records
            for (List<String> record : records) {
                bufferWritter.write(String.join(",", record));
                bufferWritter.newLine();
            }
            //close the writer
            bufferWritter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        fileToArray();
    }

    @FXML
    public List<List<String>> fileToArray() {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("payments.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (IOException ex){
            System.out.println("File Not Found");
        }
        return records;
    }

    @FXML
    private void clearFields() {
        payment_name.setText("");
        payment_price.setText("");
        payment_notes.setText("");
    }

}
