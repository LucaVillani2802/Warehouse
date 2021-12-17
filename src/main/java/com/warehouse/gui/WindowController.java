package com.warehouse.gui;

import com.warehouse.database.DatabaseItem;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;
import com.warehouse.database.DatabaseManager;

import static com.warehouse.database.DatabaseManager.insertIntoDB;

public class WindowController {
    @FXML
    protected Button saveOrderButton;

    @FXML
    protected TextField nameTextField;

    @FXML
    protected TextField surnameTextField;

    @FXML
    protected Spinner quantitySpinner;

    @FXML
    protected ComboBox itemComboBox;

    @FXML
    protected ChoiceBox paymentChoiceBox;

    public void initialize(){
        // this configures the PAYMENT ChoiceBox
        paymentChoiceBox.getItems().setAll("Cash Payment","Electronic Payment");
        paymentChoiceBox.selectionModelProperty();

        // this item configures the ITEM ComboBox
        itemComboBox.getItems().addAll();
        itemComboBox.setVisibleRowCount(3);
        itemComboBox.setEditable(true);
        itemComboBox.setPromptText("Choose the item");

        // configure the spinner for the values 0 to 100, QUANTITY Spinner
        SpinnerValueFactory<Integer> quantityValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        this.quantitySpinner.setValueFactory(quantityValueFactory);

    }

    public void on_button_clicked(){
        DatabaseItem databaseItem = new DatabaseItem(nameTextField.getText(), surnameTextField.getText(), (String) itemComboBox.getValue(), (Integer) quantitySpinner.getValue(),0.0,(String) paymentChoiceBox.getValue());
        insertIntoDB(databaseItem);
    }

}