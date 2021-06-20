package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller extends Thread implements Initializable{

    private Label name, qty, price, total;

    public Button half1ChikenBiryani, regChikenBiryani;
    public Pane halfChikenBiryani, backpane,cover,order, ppp;
    public Pane cbpaneh, cbpanef, cbpaner, cbpanes;
    public Pane cppaneh, cppanef, cppaner, cppanes;
    public Pane sbpaneh, sbpanef;
    public Pane sppaneh, sppanef;
    public Pane dpane, rpane, shamipane, saladpane;
    public ImageView invoice;
    public TextArea area;
    public GridPane voice,nGrid;
    public GridPane item;
    public Label iName, finaltotal;

    private int grandtotal = 0;

    private ItemPrice itemPrice;
    @FXML
    VBox nameBox,qtyBox,priceBox,totalBox;

    public Controller() {
        itemPrice = new ItemPrice();
    }


    @Override
    public void run() {

    }

    public void addItem(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == cbpaneh) {
            addNewItem("Half Chicken Biryani");
        } else if(mouseEvent.getSource() == cbpanef) {
            addNewItem("Full Chicken Biryani");
        } else if(mouseEvent.getSource() == cbpaner) {
            addNewItem("Regular Chicken Biryani");
        } else if(mouseEvent.getSource() == cbpanes) {
            addNewItem("Special Chicken Biryani");
        } else if(mouseEvent.getSource() == cppaneh) {
            addNewItem("Half Chicken Pulao");
        } else if(mouseEvent.getSource() == cppanef) {
            addNewItem("Full Chicken Pulao");
        } else if(mouseEvent.getSource() == cppaner) {
            addNewItem("Regular Chicken Pulao");
        } else if(mouseEvent.getSource() == cppanes) {
            addNewItem("Special Chicken Pulao");
        } else if(mouseEvent.getSource() == sbpaneh) {
            addNewItem("Half Simple Biryani");
        } else if(mouseEvent.getSource() == sbpanef) {
            addNewItem("Full Simple Biryani");
        } else if(mouseEvent.getSource() == sppaneh) {
            addNewItem("Half Simple Pulao");
        } else if(mouseEvent.getSource() == sppanef) {
            addNewItem("Full Simple Pulao");
        } else if(mouseEvent.getSource() == dpane) {
            addNewItem("Cold-Drink");
        } else if(mouseEvent.getSource() == rpane) {
            addNewItem("Raita");
        } else if(mouseEvent.getSource() == shamipane) {
            addNewItem("Shaami");
        } else if(mouseEvent.getSource() == saladpane) {
            addNewItem("Salad");
        }
    }

    private void addNewItem(String itemName) {



        name = new Label(itemName);
        name.setTextFill(Color.WHITE);
        name.setFont(Font.font(14));
        qty = new Label("1");
        qty.setTextFill(Color.WHITE);
        qty.setFont(Font.font(14));

        int p = getPrice(itemName);

        price = new Label(Integer.toString(p));
        price.setTextFill(Color.WHITE);
        price.setFont(Font.font(14));


        int q = Integer.parseInt(qty.getText());
        int t = q*p;
        total =  new Label(Integer.toString(t));
        total.setTextFill(Color.WHITE);
        total.setFont(Font.font(14));

        nameBox.getChildren().add(name);
        qtyBox.getChildren().add(qty);
        priceBox.getChildren().add(price);
        totalBox.getChildren().add(total);

        grandtotal += t;
        finaltotal.setText(Integer.toString(grandtotal));

    }

    private int getPrice(String itemName) {
        if(itemName.equals("Half Chicken Biryani")) {
            return itemPrice.getHcb();
        } else if(itemName.equals("Full Chicken Biryani")) {
            return itemPrice.getFcb();
        } else if(itemName.equals("Regular Chicken Biryani")) {
            return itemPrice.getRcb();
        } else if(itemName.equals("Special Chicken Biryani")) {
            return itemPrice.getScb();
        } else if(itemName.equals("Half Chicken Pulao")) {
            return itemPrice.getHcp();
        } else if(itemName.equals("Full Chicken Pulao")) {
            return itemPrice.getFcp();
        } else if(itemName.equals("Regular Chicken Pulao")) {
            return itemPrice.getRcp();
        } else if(itemName.equals("Special Chicken Pulao")) {
            return itemPrice.getScp();
        } else if(itemName.equals("Half Simple Biryani")) {
            return itemPrice.getHsb();
        } else if(itemName.equals("Full Simple Biryani")) {
            return itemPrice.getFsb();
        } else if(itemName.equals("Half Simple Pulao")) {
            return itemPrice.getHsp();
        } else if(itemName.equals("Full Simple Pulao")) {
            return itemPrice.getFsp();
        } else if(itemName.equals("Cold-Drink")) {
            return itemPrice.getColdDrink();
        } else if(itemName.equals("Raita")) {
            return itemPrice.getRaita();
        } else if(itemName.equals("Shaami")) {
            return itemPrice.getShami();
        } else if(itemName.equals("Salad")) {
            return itemPrice.getSalad();
        } else {
            return 0;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        invoice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                ObservableSet<Printer> printers = Printer.getAllPrinters();
//
//                for(Printer printer : printers)
//                {
//                    area.appendText(printer.getName()+"\n");
//                }
                PrinterJob job = PrinterJob.createPrinterJob();
                if(job!= null) {
                    if(job.printPage(ppp)) {

                        job.endJob();
                        grandtotal = 0;
                        finaltotal.setText(Integer.toString(grandtotal));
                        nameBox.getChildren().clear();
                        qtyBox.getChildren().clear();
                        priceBox.getChildren().clear();
                        totalBox.getChildren().clear();
                    }
                }

            }
        });

    }
}
