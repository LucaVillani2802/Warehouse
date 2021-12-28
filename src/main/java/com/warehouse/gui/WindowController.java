package com.warehouse.gui;

import com.warehouse.database.DatabaseItem;
import com.warehouse.database.DatabasePropertyItem;
import com.warehouse.items.EditableItem;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.warehouse.database.DatabaseManager;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.warehouse.database.DatabaseManager.insertIntoDB;
import static com.warehouse.database.DatabaseManager.insertIntoItemTable;

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

    @FXML
    protected Button saveItem;

    @FXML
    protected TextField itemNameTextField;

    @FXML
    protected TextField itemPriceTextField;

    @FXML
    protected TextArea itemDescriptionTextArea;

    @FXML
    protected Spinner itemQuantitySpinner;

    @FXML
    protected TableView<DatabasePropertyItem> orderListTable;

    @FXML
    protected TableColumn<DatabasePropertyItem, Integer> idTableColumn;

    @FXML
    protected TableColumn<DatabasePropertyItem, String> nameTableColumn;

    @FXML
    protected TableColumn<DatabasePropertyItem, String> surnameTableColumn;

    @FXML
    protected TableColumn<DatabasePropertyItem, String> prdnameTableColumn;

    @FXML
    protected TableColumn<DatabasePropertyItem, String> payment_methodTableColumn;
    @FXML
    protected TableColumn<DatabasePropertyItem, Double> priceTableColumn;

    @FXML
    protected TableColumn<DatabasePropertyItem, Integer> quantityTableColumn;


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

        initTable();
        configureOrderTable();

    }

    public void on_button_clicked(){
        DatabaseItem databaseItem = new DatabaseItem(nameTextField.getText(),
                                                    surnameTextField.getText(),
                                                    (String) itemComboBox.getValue(),
                                                    (Integer) quantitySpinner.getValue(),
                (EditableItem.findPrice((String) itemComboBox.getValue()))*(Integer)quantitySpinner.getValue(),
                                                    (String) paymentChoiceBox.getValue());
        insertIntoDB(databaseItem);
        configureOrderTable();
    }

    public void on_saveitembutton_clicked() {
        EditableItem editableItem = new EditableItem(itemNameTextField.getText(), itemDescriptionTextArea.toString(), (Integer) itemQuantitySpinner.getValue(), Double.parseDouble(itemPriceTextField.getText()));
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

    public void initTable(){
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        prdnameTableColumn.setCellValueFactory(new PropertyValueFactory<>("prdname"));
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityTableColumn.setCellValueFactory((new PropertyValueFactory<>("quantity")));
        orderListTable.getColumns().addAll(idTableColumn,nameTableColumn,surnameTableColumn,prdnameTableColumn, payment_methodTableColumn,priceTableColumn,quantityTableColumn);
    }

    public void configureOrderTable(){

        ObservableList<DatabasePropertyItem> data = DatabaseManager.showOrders();
        orderListTable.setItems(data);
    }


}