package pl.zimny0911.kalkulatorbmi;

/**
 * Created by Zimny - Asus R513C on 16.03.2018.
 */

public abstract class Bmi {
    protected Double weight, height;
    public Bmi(Double weight, Double height){
        this.weight = weight;
        this.height = height;
    }
    public abstract double calculateBmi() throws IllegalAccessException;
    protected abstract boolean dataAreValid();
}
