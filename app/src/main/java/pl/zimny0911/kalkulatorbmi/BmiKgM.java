package pl.zimny0911.kalkulatorbmi;

/**
 * Created by Zimny - Asus R513C on 16.03.2018.
 */

public class BmiKgM extends Bmi{

    public BmiKgM(Double weight, Double height){
        super(weight, height);
    }

    @Override
    public double calculateBmi() throws IllegalArgumentException {
        if(dataAreValid()){
            return Math.round((weight / (height / 100.0 * height / 100.0) * 100.0)) / 100.0;
        }
        else {
            throw new IllegalArgumentException("Incorrect data");
        }
    }

    @Override
    protected boolean dataAreValid() {
        return weight > 0 && weight < 300 && height > 0 && height < 250;
    }


}