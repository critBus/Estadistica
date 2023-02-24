/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadistica;

import Utiles.FX.VentanasFX;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 *
 * @author Rene
 */
public class Estadistica extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            VentanasFX.load(this, "Ventana_Estadistica.fxml").show();
//            FXMLLoader fx = new FXMLLoader(this.getClass().getResource("ventanWB.fxml"));
//        Parent root = fx.load(); 
//        Scene sc=new Scene(root);
//        primaryStage.setScene(sc);
//        primaryStage.show();
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Estadistica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
       // HTMLEditor h;
       // h.setDisable(true);
      //  h.set
        


    }
    
}
