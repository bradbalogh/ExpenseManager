package edu.depaul.expense;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import org.assertj.core.api.Assert;
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
import org.testfx.framework.junit5.Start;

import javax.swing.*;

@ExtendWith(ApplicationExtension.class)
public class AppTest extends ApplicationTest{

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
    @DisplayName("Adds a date and payment with legal formatting")
    public void testAddLegalPayment (FxRobot robot) {
        // adds month
        clickOn("#month_box");
        String month = "June";
        clickOn(month);
        // adds year
        clickOn("#year_box");
        String year = "2020";
        clickOn(year);
        // adds payment name
        clickOn("#payment_name");
        String pname = "Amazon Prime";
        write(pname);
        // adds payment price
        clickOn("#payment_price");
        write("5.00");
        // adds payment notes
        clickOn("#payment_notes");
        write("Cancel Soon");
        clickOn("#add_payment");
        sleep(500);
        // checks to see if payment was added successfully
        String stringBuild = ("Added Payment: "+pname+" ["+month+year+"]\n");
        Assertions.assertThat(robot.lookup("#text_pane").queryAs(TextFlow.class)).hasText(stringBuild);
        //sleep(3000);
    }

    @Test
    @DisplayName("Tries to add a payment with illegal formatting")
    public void testAddIllegalPayment (FxRobot robot) {
        // adds month
        clickOn("#month_box");
        String month = "June";
        clickOn(month);
        // adds year
        clickOn("#year_box");
        String year = "2020";
        clickOn(year);
        // adds payment name
        clickOn("#payment_name");
        String pname = "Nextlix";
        write(pname);
        // adds payment price
        clickOn("#payment_price");
        write("$8.00");
        // adds payment notes
        clickOn("#payment_notes");
        write("Cancel Soon");
        clickOn("#add_payment");
        sleep(500);
        // checks to see if payment was added successfully
        String stringBuild = ("Payment Price Must Be Entered As Type Double i.e. 15.25"+ '\n');
        Assertions.assertThat(robot.lookup("#text_pane").queryAs(TextFlow.class)).hasText(stringBuild);
        //sleep(3000);
    }

    @Test
    @DisplayName("Tries to add a payment with no date selected")
    public void testMissingDate (FxRobot robot) {
        // adds payment name
        clickOn("#payment_name");
        String pname = "Nextlix";
        write(pname);
        // adds payment price
        clickOn("#payment_price");
        write("8.00");
        clickOn("#add_payment");
        sleep(500);
        // checks to see if payment was added successfully
        String stringBuild = ("Please Select A Date To Add Payment"+ '\n');
        Assertions.assertThat(robot.lookup("#text_pane").queryAs(TextFlow.class)).hasText(stringBuild);
        //sleep(3000);
    }


    @Test
    @DisplayName("Tries to add payment with no payment name inputted")
    public void testMissingPaymentName (FxRobot robot) {
        // adds month
        clickOn("#month_box");
        String month = "June";
        clickOn(month);
        // adds year
        clickOn("#year_box");
        String year = "2020";
        clickOn(year);
        // adds payment notes
        clickOn("#payment_notes");
        write("Cancel Soon");
        clickOn("#add_payment");
        sleep(500);
        // checks to see if payment was added successfully
        String stringBuild = ("Payment Name And Payment Price Must Be Filled"+ '\n');
        Assertions.assertThat(robot.lookup("#text_pane").queryAs(TextFlow.class)).hasText(stringBuild);
        //sleep(3000);
    }

    @Test
    @DisplayName("Tries to add payment with no payment price inputted")
    public void testMissingPaymentPrice (FxRobot robot) {
        // adds month
        clickOn("#month_box");
        String month = "June";
        clickOn(month);
        // adds year
        clickOn("#year_box");
        String year = "2020";
        clickOn(year);
        // adds payment name
        clickOn("#payment_name");
        String pname = "Amazon Prime";
        write(pname);
        clickOn("#add_payment");
        sleep(500);
        // checks to see if payment was added successfully
        String stringBuild = ("Payment Name And Payment Price Must Be Filled"+ '\n');
        Assertions.assertThat(robot.lookup("#text_pane").queryAs(TextFlow.class)).hasText(stringBuild);
        //sleep(3000);
    }
}


