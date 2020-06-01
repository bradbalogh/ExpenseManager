package edu.depaul.expense;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

public class AppTest extends ApplicationTest {

    @Override
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(App.class.getResource("dataEditor.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @BeforeEach
    public void setUp () throws Exception {
    }

    @AfterEach
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    @DisplayName("Adds a date adn payment with legal formatting")
    public void testAddLegalPayment () {
        // adds month
        clickOn("#month_box");
        type(KeyCode.ENTER);
        // adds year
        clickOn("#year_box");
        type(KeyCode.ENTER);
        // adds payment name
        clickOn("#payment_name");
        write("Amazon Prime");
        // adds payment price
        clickOn("#payment_price");
        write("5.00");
        // adds payment notes
        clickOn("#payment_notes");
        write("Cancel Soon");
        clickOn("#add_payment");
        try {Thread.sleep(500);} catch (InterruptedException e) {}

    }
}


