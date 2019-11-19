package sample;

import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.VBox;

public class ChartModel {

    private static double heigthChart;
    private static double widthChart;
    private static int countSetFields;
    private static String styleRGBA;

    private final static String STYLEACTION = "-fx-background-color: rgba(0, 190, 0, 0.5);";
    VBox[] arrVB;

    ChartModel(int size,Double[] arrIP){
        arrVB = new VBox[size];
        if( countSetFields == 1) {
            double widthRect = widthChart / size, heigthRect = heigthChart;

            for (int i = 0; i < size; i++) {
                arrVB[i] = new VBox();
                arrVB[i].setPrefWidth(widthRect);
                arrVB[i].setPrefHeight(arrIP[i]);
                arrVB[i].setLayoutX(i * widthRect);
                arrVB[i].setStyle(styleRGBA);
            }
        }else
            throw new IllegalArgumentException("Die Klassenfelder müssen vor dem ersten Instanzieren gesetzt werden!");
    }
    public VBox[] getVB(){
        return arrVB;
    }

    public void setVBoxIndivHeight(int ndx,int value){
        arrVB[ndx].setPrefHeight(value);
    }

    public static void setCMStettings(double width,double heigth,String style ){
        if (countSetFields == 0) {
            widthChart = width;
            heigthChart = heigth;
            countSetFields++;
            styleRGBA = style;
        }else
            throw new  IllegalArgumentException("Die Klassenfelder dürfen nur einmalig gesetzt werden!");

    }

    public void setStyle(int ndx){
        arrVB[ndx].setStyle(STYLEACTION);
    }
    public void setStyleOld(int ndx){
        arrVB[ndx].setStyle(styleRGBA);
    }

}
