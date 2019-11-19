package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class PropArrayInt {
    private static int valueMax;
    private static int valueMin;
    private static int indexMin;
    private static int indexMax;
    private static int countSetSettings;

    private IntegerProperty index = new SimpleIntegerProperty(0);
    private IntegerProperty[] array;
    private IntegerProperty sizeValidValues = new SimpleIntegerProperty();

    public static void setBarsSettings(int iValueMin,int iValueMax,int iIndexMin, int iIndexMax){
        if(countSetSettings > 0)
            throw new IllegalArgumentException("Die Klassenvariablen dürfen nur einmalig eingestellt werden");
        else {
            if (iValueMax < iValueMin)
                throw new IllegalArgumentException("Der maxinmale Wert darf nicht kleiner als der minimale Wert sein!\nEingabe(Min: " + iValueMin + "\tMax: " + iValueMax);
            else {
                valueMax = iValueMax;
                valueMin = iValueMin;
            }
            if (iIndexMin < 0)
                throw new IllegalArgumentException("Die minimale Arraygröße darf nicht kleiner 0 annehmen!");
            else
                indexMin = iIndexMin;

            if (iIndexMax < iIndexMin)
                throw new IllegalArgumentException("Die maximale Arraygröße darf nicht kleiner als die minimale Arraygröße sein!");
            else
                indexMax = iIndexMax;

            countSetSettings++;
        }
    }

    public PropArrayInt(int size) {
        if (size<indexMin)
            throw new IllegalArgumentException("Der initilaisierte Wert darf nicht kleiner als der eingestellt Minimalwert sein!\n"+
            "Eingabe :" + size + " (Einstelung: Min: " + indexMin + ")");
        else
            sizeValidValues.set(size);
        if (countSetSettings == 1){
            array = new SimpleIntegerProperty[indexMax];
            for (int i = 0; i < indexMax; i++) {
                if (i < size)
                    array[i] = new SimpleIntegerProperty(this,String.valueOf(i),determineRandomValue());
                else
                    array[i] = new SimpleIntegerProperty(this,String.valueOf(i), 0);
            }
        }else
            throw new IllegalArgumentException("Vor dem ersten Instanzieren, müssen die Klassenvaribalen mit der Methode setBarsSettings() initialisiert werden!");

    }

    public void setArray(int size){
        if(size<indexMin || size>indexMax)
            throw new IllegalArgumentException("Die zu setzende Arraygröße darf nicht kleiner/größer als die minimale/maximale Arraygröße sein!\n" +
                    "Eingabe: " + size + "\"t Einstellung(Min: " + indexMin + "\tMax: " + indexMax);
        else{
            for(int i=0;i<indexMax;i++){
                if(i<size)
                    array[i].set(determineRandomValue());
                else
                    array[i].set(0);
            }
            sizeValidValues.set(size);
        }
    }
    public void setArraySinlgeElement(int ndx){
        if(ndx > sizeValidValues.getValue() - 1)
            throw new IllegalArgumentException("Der zu setzende Indexwert ist außerhalb des gültigen Arraybereiches!\n Eingabe Index: " + ndx + "\tArraybereich: " + sizeValidValues.get());
        else
            array[ndx].set(determineRandomValue());
    }

    public int getIndex() {
        return index.get();
    }
    public IntegerProperty getIndexProperty(){
        return index;
    }

    public Integer getSizeValidValues() {
        return sizeValidValues.getValue();
    }
    public Integer getSizeArray(){
        return indexMax;
    }
    public IntegerProperty getSizeValidValuesProperty() {
        return sizeValidValues;
    }
    public Integer getValidSize(){
        return sizeValidValues.getValue();
    }

    public void setIndex(Integer value){
        index.set(value);
    }

    public IntegerProperty getInidivBarPropery(int ndx) {
        return array[ndx];
    }

    public Integer getIndivBarValue(int ndx){
        return array[ndx].getValue();
    }

    public static int getIndexMax() {
        return indexMax;
    }

    public IntegerProperty IndexProperty() {
        return index;
    }

    public Double[] getValidArrayAsDouble(){
        Double[] db = new Double[sizeValidValues.getValue()];
        for(int i=0;i<sizeValidValues.getValue();i++)
            db[i] = Double.valueOf (array[i].getValue());
        return db;
    }

    public IntegerProperty[] arrayProperty(){
        return array;
    }


    public IntegerProperty[] addListener(IntegerProperty[] listener){
        listener = new SimpleIntegerProperty[indexMax];
        for(int i=0;i<indexMax;i++){
            listener[i] = new SimpleIntegerProperty(this,String.valueOf(i),array[i].getValue());
            listener[i].bind(array[i]);
        }
        return listener;
    }

    private Integer determineRandomValue(){
        Random r = new Random();
        return r.nextInt((valueMax - valueMin) + 1) + valueMin;
    }

    public void printArray(){
        System.out.print("[");

        for(int i=0;i<sizeValidValues.getValue();i++) {
            if (i + 1 < sizeValidValues.getValue())
                System.out.print(array[i].get() + ",");
            else
                System.out.print(array[i].get());
        }
        System.out.println("]");
    }

}
