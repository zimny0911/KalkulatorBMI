package pl.zimny0911.kalkulatorbmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void bmiKg_isCorrect(){
        BmiKgM bmi = new BmiKgM(60.0, 180.0);
        assertEquals(18.52, bmi.calculateBmi(), 0.01);
    }

    @Test
    public void bmiLb_isCorrect(){
        BmiLbIn bmi = new BmiLbIn(170.0, 65.0);
        assertEquals(28.29, bmi.calculateBmi(), 0.01);
    }

}