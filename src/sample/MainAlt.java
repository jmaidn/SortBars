package sample;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainAlt extends Application {

   private static PropArrayInt pai;

    //Werte der Dimensionen
    double dPaneWidth = 1200, dPaneHeigth = 650;
    String mystic = "-fx-background-color: rgba(232, 236, 241, 1);";
    String gallery = "-fx-background-color: rgba(238, 238, 238, 1);";

    @Override
    public void start(Stage primaryStage) throws Exception{
        int dist = 10;

        AnchorPane root = new AnchorPane();
        AnchorPane apChart = new AnchorPane();
        FlowPane fpSldrBars = new FlowPane();
        FlowPane fpSldrDelay = new FlowPane();
        FlowPane fpProgrss = new FlowPane();
        Slider sldrBars = new Slider();
        Slider sldrDelay = new Slider();
        Label lblSldrBars = new Label("Anzahl Balken");
        Label lblSlldrBarsOP = new Label("");
        Label lblSldrDelay = new Label("Delay");
        Label lblSldrDelayOP = new Label(" [ms]");
        ButtonBar btnBar = new ButtonBar();
        Button btnMergeSort = new Button("MergeSort");
        Button btnQuikSort = new Button("QuickSort");
        Button btnRegenerate = new Button("regenerate");
        ProgressBar progBar = new ProgressBar();
        Label lblProgBarName = new Label("Progress");
        Label lblProgress = new Label("%");


        Insets insfp = new Insets(30,10,0,0);
        root.setPadding(new Insets(dist,dist,dist,dist));

        fpSldrBars.setPrefSize(300,80);
        fpSldrDelay.setPrefSize(300,80);
        apChart.setPrefSize(1176,419);
        fpSldrBars.setLayoutX(14);fpSldrBars.setLayoutY(14);
        fpSldrDelay.setLayoutX(14);fpSldrDelay.setLayoutY(94);
        apChart.setLayoutX(14);apChart.setLayoutY(217);
        btnBar.setLayoutX(500);btnBar.setLayoutY(24);
        fpProgrss.setPrefSize(300,50);
        fpProgrss.setLayoutX(750);fpProgrss.setLayoutY(14);

        btnRegenerate.setLayoutX(400);btnRegenerate.setLayoutY(45);
        btnBar.setPrefSize(209,61);
        sldrBars.setPrefSize(166,24);
        fpSldrBars.setHgap(20);

        fpSldrBars.setStyle(mystic);
        fpSldrDelay.setStyle(mystic);
        apChart.setStyle(mystic);
        fpProgrss.setStyle(mystic);


        btnBar.getButtons().addAll(btnMergeSort,btnQuikSort);
        fpSldrBars.getChildren().addAll(lblSldrBars,sldrBars,lblSlldrBarsOP);

        fpSldrDelay.getChildren().addAll(lblSldrDelay,sldrDelay,lblSldrDelayOP);
        fpProgrss.getChildren().addAll(lblProgBarName,progBar,lblProgress);

        fpSldrBars.setMargin(lblSldrBars,insfp);
        fpSldrBars.setMargin(sldrBars,insfp);
        fpSldrBars.setMargin(lblSldrDelayOP,insfp);
        fpSldrDelay.setMargin(lblSldrDelay,insfp);
        fpSldrDelay.setMargin(sldrDelay,insfp);
        fpSldrDelay.setMargin(lblSldrDelayOP,insfp);

        fpProgrss.setMargin(lblProgBarName,insfp);
        fpProgrss.setMargin(progBar,insfp);
        fpProgrss.setMargin(lblProgress,insfp);


        root.getChildren().addAll(apChart,fpSldrBars,fpSldrDelay,btnBar,btnRegenerate,fpProgrss);

        primaryStage.setTitle("Sort Bars");
        primaryStage.setScene(new Scene(root, dPaneWidth, dPaneHeigth));
        primaryStage.show();

    }

    static public PropArrayInt getModel(){return pai;}

    public static void main(String[] args) {
        launch(args);
    }
}
