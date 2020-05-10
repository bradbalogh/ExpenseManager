package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;

public class DataViewer {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("DataEditor");
    }
}