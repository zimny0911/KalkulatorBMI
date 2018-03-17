package pl.zimny0911.kalkulatorbmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText height, weight;
    private TextView weightText, heightText;
    private Switch switchUnits;

    public void setReferences() {
        height = findViewById(R.id.heightRead);
        weight = findViewById(R.id.weightRead);
        weightText = findViewById(R.id.weightText);
        heightText = findViewById(R.id.heightText);
        switchUnits = findViewById(R.id.switchUnits);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setReferences();

        try {
            readData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setUnits();
    }

    public void calculateOnClick(android.view.View view) {
        Double myBmi = 0.0;
        try {
            if(switchUnits.isChecked()){
                BmiLbIn bmi = new BmiLbIn(Double.parseDouble(weight.getText().toString()), Double.parseDouble(height.getText().toString()));
                myBmi = bmi.calculateBmi();
            }
            else {
                BmiKgM bmi = new BmiKgM(Double.parseDouble(weight.getText().toString()), Double.parseDouble(height.getText().toString()));
                myBmi = bmi.calculateBmi();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if(myBmi == 0.0) {
            Toast.makeText(getApplicationContext(), "Error - incorrect data", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(view.getContext(), ResultActivity.class);
            intent.putExtra("bmiValue", myBmi);
            startActivity(intent);
        }
    }

    public void aboutMeOnClick(MenuItem item){
        Intent intent = new Intent(getApplicationContext(),AuthorActivity.class);
        startActivity(intent);
    }

    public void changeUnits(android.view.View view){
        if(switchUnits.isChecked()){
            weightText.setText(getString(R.string.weightText2));
            heightText.setText(getString(R.string.heightText2));
        }
        else {
            weightText.setText(getString(R.string.weightText));
            heightText.setText(getString(R.string.heightText));
        }
    }

    public void setUnits(){
        if(switchUnits.isChecked()){
            weightText.setText(getString(R.string.weightText2));
            heightText.setText(getString(R.string.heightText2));
        }
        else {
            weightText.setText(getString(R.string.weightText));
            heightText.setText(getString(R.string.heightText));
        }
    }


    public void saveOnClick(MenuItem item) throws IOException {
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        File path = getApplicationContext().getFilesDir();
        File file = new File(path, "save_bmi_file.txt");
        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.write((weight.getText().toString() + System.getProperty("line.separator")).getBytes());
            stream.write((height.getText().toString() + System.getProperty("line.separator")).getBytes());
            stream.write(Boolean.toString(switchUnits.isChecked()).getBytes());
        } catch (IOException e){}
        finally {
            stream.close();
        }

    }

    public void readData() throws IOException {
        File path = getApplicationContext().getFilesDir();
        File file = new File(path, "save_bmi_file.txt");

        FileInputStream in = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        if((line = br.readLine()) != null) weight.setText(line);
        if((line = br.readLine()) != null) height.setText(line);
        if((line = br.readLine()) != null) switchUnits.setChecked(Boolean.parseBoolean(line));

        in.close();
        isr.close();
        br.close();
    }
}
