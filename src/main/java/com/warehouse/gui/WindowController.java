package com.warehouse.gui;

import com.warehouse.database.DatabaseItem;
import com.warehouse.items.EditableItem;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.warehouse.database.DatabaseManager.*;

public class WindowController {

    @FXML
    protected Button saveOrderButton;

    @FXML
    protected TextField nameTextField;

    @FXML
    protected TextField surnameTextField;

    @FXML
    protected Spinner<Integer> quantitySpinner;

    @FXML
    protected ComboBox<String> itemComboBox;

    @FXML
    protected ChoiceBox<String> paymentChoiceBox;

    @FXML
    protected Button saveItem;

    @FXML
    protected TextField itemNameTextField;

    @FXML
    protected TextField itemPriceTextField;

    @FXML
    protected TextArea itemDescriptionTextArea;

    @FXML
    protected Spinner<Integer> itemQuantitySpinner;

    @FXML
    protected TableView<DatabaseItem> orderListTable;

    @FXML
    protected TableColumn idTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> nameTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> surnameTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> prdnameTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> payment_methodTableColumn;
    @FXML
    protected TableColumn<DatabaseItem, Double> priceTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, Integer> quantityTableColumn;


    public void initialize(){
        // this configures the PAYMENT ChoiceBox
        paymentChoiceBox.getItems().setAll("Cash Payment","Electronic Payment");
        paymentChoiceBox.selectionModelProperty();

        // this item configures the ITEM ComboBox
        configureComboBox();
        itemComboBox.getItems().addAll(EditableItem.findnames());
        itemComboBox.setVisibleRowCount(3);
        itemComboBox.setEditable(true);
        itemComboBox.setPromptText("Choose the item");

        // configure the spinner for the values 0 to 100, QUANTITY Spinner
        SpinnerValueFactory<Integer> quantityValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        this.quantitySpinner.setValueFactory(quantityValueFactory);

        this.itemQuantitySpinner.setValueFactory(quantityValueFactory);

        initTableView();
    }

    public void on_button_clicked(){
        DatabaseItem databaseItem = new DatabaseItem(
                nameTextField.getText(),
                surnameTextField.getText(),
                itemComboBox.getValue(),
                quantitySpinner.getValue(),
                (EditableItem.findPrice(itemComboBox.getValue()))* quantitySpinner.getValue(),
                paymentChoiceBox.getValue()
        );
        insertIntoDB(databaseItem);
        orderListTable.setItems(showOrders());
    }

    public void on_saveitembutton_clicked() {
        EditableItem editableItem = new EditableItem(itemNameTextField.getText(), itemDescriptionTextArea.toString(), itemQuantitySpinner.getValue(), Double.parseDouble(itemPriceTextField.getText()));
        insertIntoItemTable(editableItem);
        configureComboBox();
    }

    public void configureComboBox(){
        ObservableList<String> arrayList = EditableItem.findnames();
        itemComboBox.setItems(arrayList);
        itemComboBox.setVisibleRowCount(3);
        itemComboBox.setEditable(true);
        itemComboBox.setPromptText("Choose the item");

    }

    public void initTableView()
    {
        idTableColumn.setCellValueFactory(data -> new SimpleIntegerProperty());
        nameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPersonName()));
        surnameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPersonSurname()));
        prdnameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProductName()));
        payment_methodTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPaymentMethod()));
        //priceTableColumn.setCellValueFactory(data -> data.getValue().getTotalCost());
        //quantityTableColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantity()));

        orderListTable.setItems(showOrders());
    }


    //TODO showing database and refresh when a new order is added

}