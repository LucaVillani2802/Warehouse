package com.warehouse.gui;

import com.warehouse.database.DatabaseItem;
import com.warehouse.items.EditableItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    //-----TABLE VIEW ORDERS LIST-----

    @FXML
    protected TableView<DatabaseItem> orderListTable;

    @FXML
    protected TableColumn<DatabaseItem,String> idTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> nameTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> surnameTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> prdnameTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> payment_methodTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> priceTableColumn;

    @FXML
    protected TableColumn<DatabaseItem, String> quantityTableColumn;

    //-----TABLE VIEW ITEMS LIST-----

    @FXML
    protected TableView<EditableItem> itemsTableList;

    @FXML
    protected TableColumn<EditableItem, String> idItemTableColumn;

    @FXML
    protected TableColumn<EditableItem, String> itemNameTableColumn;

    @FXML
    protected TableColumn<EditableItem, String> descriptionTableColumn;

    @FXML
    protected TableColumn<EditableItem, String> quantityItemTableColumn;

    @FXML
    protected TableColumn<EditableItem, String> priceItemTableColumnl;

    //REFILL FORM

    @FXML
    protected ComboBox<String> itemRefillComboBox;

    @FXML
    protected Spinner<Integer> itemRefillSpinner;

    @FXML
    protected Button itemRefillButton;

    public void initialize(){
        // this configures the PAYMENT ChoiceBox
        paymentChoiceBox.getItems().setAll("Cash Payment","Electronic Payment");
        paymentChoiceBox.selectionModelProperty();

        // this item configures the ITEM ComboBox
        configureComboBox(itemComboBox);
        configureComboBox(itemRefillComboBox);

        // configure the spinner for the values 0 to 100, QUANTITY Spinner
        SpinnerValueFactory<Integer> quantityValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0);
        this.quantitySpinner.setValueFactory(quantityValueFactory);
        this.itemRefillSpinner.setValueFactory(quantityValueFactory);
        this.itemQuantitySpinner.setValueFactory(quantityValueFactory);

        initTableView();
        initItemTableView();
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
        //TODO creating controls where if the quantity_avaiable is less than the quantity ordered a dialog window is showed
        clearOrderForm();
        insertIntoDB(databaseItem);
        orderListTable.setItems(showOrders());
        modifyQuantityItem(databaseItem);
        modifyItemView();

    }

    public void on_saveitembutton_clicked() {
        EditableItem editableItem = new EditableItem(itemNameTextField.getText(), itemDescriptionTextArea.getText(), itemQuantitySpinner.getValue(), Double.parseDouble(itemPriceTextField.getText()));
        insertIntoItemTable(editableItem);
        configureComboBox(itemComboBox);
        configureComboBox(itemRefillComboBox);
        modifyItemView();
    }

    public void on_itemrefillbutton_clicked() {
        Integer quantityToRefill = itemRefillSpinner.getValue();
        String itemNameToRefill = itemRefillComboBox.getValue();

        refillInItemsTable(itemNameToRefill, quantityToRefill);
        modifyItemView();
    }

    public void configureComboBox(ComboBox itemCombo){
        ObservableList<String> arrayList = EditableItem.findnames();
        itemCombo.setItems(arrayList);
        itemCombo.setVisibleRowCount(4);
        itemCombo.setEditable(true);
        itemCombo.setPromptText("Choose the item");

    }

    public void initTableView(){
        idTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrderId().toString()));
        nameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPersonName()));
        surnameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPersonSurname()));
        prdnameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProductName()));
        payment_methodTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPaymentMethod()));
        priceTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTotalCost().toString()));
        quantityTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuantity().toString()));

        orderListTable.setItems(showOrders());
    }

    public void initItemTableView(){
        idItemTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId().toString()));
        itemNameTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProductName()));
        descriptionTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        quantityItemTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getQuantity().toString()));
        priceItemTableColumnl.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPrice().toString()));

        modifyItemView();
    }

    public void modifyItemView(){
        itemsTableList.setItems(showItems());
    }

    public void clearOrderForm(){
        nameTextField.clear();
        surnameTextField.clear();
    }

}