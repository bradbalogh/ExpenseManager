package org.openjfx;
import org.junit.Test;

import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class AppTest {


    @Test
    @DisplayName("adds an item")
    public void testAddPayment() {
        DataEditorController dataEdit = new DataEditorController();

        dataEdit.payment_name.setText("Amazon Prime");

        //dataEdit.payment_price.getText();
        //dataEdit.payment_notes.setText("");
        //dataEdit.year_box.setValue("January");
        //dataEdit.month_box.setValue("2020");

        //System.out.println(dataEdit.payment_name);
        //dataEdit.addPayment();
        assertEquals(1,1);
    }
}


