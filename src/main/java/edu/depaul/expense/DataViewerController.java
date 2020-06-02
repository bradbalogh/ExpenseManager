package edu.depaul.expense;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class DataViewerController {
    DataEditorController DE_Controller = new DataEditorController();

    // set ID's
    @FXML
    TextFlow data_text_pane;
    @FXML
    TextFlow total_text_pane;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("DataEditor");
    }

    @FXML
    private void deleteFile() {
        File file = new File("payments.csv");
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
        loadData();
    }

    @FXML
    private float getToal() {
        List<List<String>> data = DE_Controller.fileToArray();
        float total = 0;
        for(int i = 0; i < data.size(); i++){
            try {
                total += Float.parseFloat(data.get(i).get(2));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Skipping Empty Row...");
            }
        }
        return total;
    }

    @FXML
    private void loadData() {
        // Resets data page first
        total_text_pane.getChildren().clear();
        data_text_pane.getChildren().clear();

        // Now loads data to page
        // Sets Total
        float total = getToal();
        // If data file is empty
        if(total == 0.0){
            // Empty total
            String formattedTotal = String.format("%.02f", total);
            Text totalText = new Text("Total: $" + formattedTotal);
            totalText.setFill(Color.YELLOW);
            totalText.setFont(Font.font("Verdana", 13));
            total_text_pane.getChildren().add(totalText);
            // Error in text pane
            Text paymentText = new Text("Please Add Payment Data First");
            paymentText.setFill(Color.YELLOW);
            paymentText.setFont(Font.font("Verdana", 13));
            data_text_pane.getChildren().add(paymentText);
        }
        else {
            String formattedTotal = String.format("%.02f", total);
            Text totalText = new Text("Total: $" + formattedTotal);
            totalText.setFill(Color.WHITESMOKE);
            totalText.setFont(Font.font("Verdana", 13));
            total_text_pane.getChildren().add(totalText);

            // Loads Payment Data
            List<List<String>> data = DE_Controller.fileToArray();
            String note;
            for (int i = 0; i < data.size(); i++) {
                try {
                    // checks for note
                    if (data.get(i).size() == 4) {
                        note = ("(" + data.get(i).get(3) + ")");
                    } else {
                        note = "";
                    }
                    ;
                    String currentText = (data.get(i).get(1) + note + " - $" + data.get(i).get(2) + " [" + data.get(i).get(0) + "]");
                    Text paymentText = new Text(currentText + '\n');
                    paymentText.setFill(Color.WHITESMOKE);
                    paymentText.setFont(Font.font("Verdana", 13));
                    data_text_pane.getChildren().add(paymentText);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Skipping Empty Row...");
                }
            }
        }
    }

}