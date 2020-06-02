package edu.depaul.expense;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.assertions.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.api.FxRobot;
import javafx.scene.control.Button;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ExtendWith(ApplicationExtension.class)
public class DataViewerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(App.class.getResource("dataViewer.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    // function to create a csv file with the given inputs as strings
    public void createDataFile(String date, String paymentName, String paymentPrice, String paymentNotes){
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
    }

    @Test
    @DisplayName("Clears data file, creates a new one, and checks to see if it was successful")
    public void testLoadedData (FxRobot robot) {
        // clears data file
        clickOn("#clear_data");
        // creates new data file
        String date = "June2020";
        String paymentName = "Netflix";
        String paymentPrice = "8.00";
        String paymentNotes = "";
        createDataFile(date, paymentName, paymentPrice, paymentNotes);
        sleep(1500);
        // loads data
        clickOn("#loadData");
        // builds expected string
        String stringBuild = (paymentName + " - " + "$"+paymentPrice+" ["+date+"]\n");
        // checks to see if the expected string is in the text pane
        Assertions.assertThat(robot.lookup("#data_text_pane").queryAs(TextFlow.class)).hasText(stringBuild);
    }

    @Test
    @DisplayName("Deletes data file and tries to load data")
    public void testEmptyData (FxRobot robot) {
        // clears data file
        clickOn("#clear_data");
        // loads data
        clickOn("#loadData");
        // builds expected string
        String stringBuild = ("Please Add Payment Data First");
        Assertions.assertThat(robot.lookup("#data_text_pane").queryAs(TextFlow.class)).hasText(stringBuild);
    }

}