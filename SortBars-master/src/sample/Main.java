package sample;


import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    private static PropArrayInt pai;
    private static ChartModel cm;
    public static IntegerProperty[] listener;
    public static MergeSort ms;
    public static QuickSort qs;

    private final static int RECTMIN = 3;
    private final static int RECTMAX = 500;
    private final static int RECTTIC = 25;

    private final static int DELAYMIN = 0;
    private final static int DELAYMAX = 50;
    private final static int DELAYTIC = 10;


    //Werte der Dimensionen
    double dPaneWidth = 1200, dPaneHeigth = 650;
    String mystic = "-fx-background-color: rgba(232, 236, 241, 1);";
    String gallery = "-fx-background-color: rgba(238, 238, 238, 1);";
    String red = "-fx-background-color: rgba(255, 0, 0, 0.5);";

    @Override
    public void start(Stage primaryStage) throws Exception{

        PropArrayInt.setBarsSettings(10,417,RECTMIN,RECTMAX);
        pai = new PropArrayInt(RECTMIN);
        listener = pai.addListener(listener);
        ChartModel.setCMStettings(1176,419,red);

        cm = new ChartModel(pai.getSizeValidValues(),pai.getValidArrayAsDouble());

        ms = new MergeSort(pai.arrayProperty(),pai.getIndexProperty(),pai.getValidSize());

        qs = new QuickSort(pai.arrayProperty(),pai.getIndexProperty(),pai.getValidSize());
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (Exception e) {
            System.out.println("Fehler beim Lesen der Layout-Spezifikation");
            System.exit(1);
       }

        primaryStage.setTitle("Sort Bars");
        primaryStage.setScene(new Scene(parent, dPaneWidth, dPaneHeigth));
        primaryStage.show();



    }

    static public PropArrayInt getPaiModel(){return pai;}
    static public ChartModel getCmModel(){return cm;}
    static public IntegerProperty[] getListener(){return listener;}
    static public MergeSort getMergeSort(){return ms;}
    static public QuickSort getQuickSort(){return qs;}
    static public Integer getBarMin(){return RECTMIN;}
    static public Integer getBarMax(){return RECTMAX;}
    static public Integer getBarTic(){return RECTTIC;}
    static public Integer getDelayMin(){return DELAYMIN;}
    static public Integer getDelayMax(){return DELAYMAX;}
    static public Integer getDelayTic(){return DELAYTIC;}

    public static void main(String[] args) {
        launch(args);
    }
}
