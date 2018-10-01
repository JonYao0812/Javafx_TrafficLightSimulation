/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Javafx_TrafficLightSimulation;

import csu_java2.TrafficLight10;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author chengzhongito
 */
public class TrafficLight extends Application{
  //Create circle representing traffic lights  
    Circle redCircle = new Circle(40,50,25, Color.GRAY);
    Circle yellowCircle = new Circle(105,50,25, Color.GRAY);
    Circle greenCircle = new Circle(170,50,25, Color.GRAY);
    TextField greenText = new TextField("3");
    TextField yellowText = new TextField("3");
    TextField redText = new TextField("3");
    Thread greenLight,yellowLight,redLight;
    Button startBtn = new Button("Start");
    Button stopBtn = new Button("Stop");
    Runnable cl= new changeLight();
    Thread tcl;
    boolean lightRun;
    int whichL = 1;
    
    public static void main(String[] args){
        Application.launch(args); }
    
    public void start(Stage primaryStage){
        BorderPane backPane = new BorderPane();
        backPane.setPadding(new Insets(10,10,10,10));
        
        Pane lightPane = new Pane();
        lightPane.setPadding(new Insets(10,0,0,0));
        Rectangle rectangle1 = new Rectangle(0,0,210, 100);
        rectangle1.setFill(Color.BLACK);
        rectangle1.setArcWidth(10);
        rectangle1.setArcHeight(10);
        
        
        greenText.setPrefWidth(40);
        greenText.setAlignment(Pos.BASELINE_RIGHT);
        yellowText.setPrefWidth(40);
        yellowText.setAlignment(Pos.BASELINE_RIGHT);
        redText.setPrefWidth(40);
        redText.setAlignment(Pos.BASELINE_RIGHT);
        GridPane inputPane = getBtnPane();
        lightPane.getChildren().addAll(rectangle1,redCircle,yellowCircle,greenCircle);
        
        startBtn.setOnAction(e->{
            lightRun = true;
            tcl = new Thread(cl);
            tcl.start();
        
        });
        
        stopBtn.setOnAction(e ->{
            lightRun = false;
            tcl.stop();
        });
        
        backPane.setTop(lightPane);
        backPane.setBottom(inputPane);
        Scene scene = new Scene(backPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public GridPane getBtnPane(){
        GridPane inputPane = new GridPane();
        inputPane.setHgap(5);
        inputPane.setVgap(5);
        
        inputPane.add(new Label("Green"),0,0);
        inputPane.add(new Label("Yellow"),0,1);
        inputPane.add(new Label("Red"),0,2);
        inputPane.add(greenText,1,0);
        inputPane.add(yellowText,1,1);
        inputPane.add(redText,1,2);
        inputPane.add(startBtn,3,2);
        inputPane.add(stopBtn,5,2);
        return inputPane;
    }
    
    
    class changeLight implements Runnable{
        changeLight(){
        }
        
        public void run(){
            while(lightRun){
                switch(whichL){
                    case(1):{
                        greenLight();
                        whichL = 2;
                    }
                    case(2):{
                        yellowLight();
                        whichL = 3;
                    }
                    case(3):{
                        redLight();
                        whichL = 1;
                    }
                }
            }
        }
    }
    
    public void greenLight(){
        int greenS = Integer.parseInt(greenText.getText());
        greenCircle.setFill(Color.GREEN);
        System.out.println("Change light colour");
        pause();
        while (greenS > 0){
                    greenS--;
                    System.out.println("Now light: 1 after "+greenS+" seconds will change colour");
                    pause();
        }
        greenCircle.setFill(Color.GRAY);
    }
    public void yellowLight(){
        int yellowS = Integer.parseInt(yellowText.getText());
        yellowCircle.setFill(Color.YELLOW);
        System.out.println("Change light colour");
        pause();
        while (yellowS > 0){
                    yellowS--;
                    System.out.println("Now light: 2 after "+yellowS+" seconds will change colour");
                    pause();
        }
        yellowCircle.setFill(Color.GRAY);
    }
    public void redLight(){
        int redS = Integer.parseInt(redText.getText());
        redCircle.setFill(Color.RED);
        System.out.println("Change light colour");
        pause();
        while (redS > 0){
                    redS--;
                    System.out.println("Now light: 3 after "+redS+" seconds will change colour");
                    pause();
        }
        redCircle.setFill(Color.GRAY);
    }
    
    
    public void pause(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TrafficLight10.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
