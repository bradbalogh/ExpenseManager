package org.openjfx;

import java.io.IOException;
import javafx.fxml.FXML;

public class DataEditorController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("dataViewer");
    }
}
