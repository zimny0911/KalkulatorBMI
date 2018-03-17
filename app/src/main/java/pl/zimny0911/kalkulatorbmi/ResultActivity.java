package pl.zimny0911.kalkulatorbmi;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Double myBmi;
    private TextView result, bmi, status;
    ConstraintLayout layout;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setTitle(getApplicationContext().getResources().getString(R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        layout = findViewById(R.layout.activity_result);
        layout = findViewById(R.id.activity_result);

        myBmi = getIntent().getDoubleExtra("bmiValue", 0.0);

        result = findViewById(R.id.resultView);
        result.setText(myBmi.toString());
        bmi = findViewById(R.id.bmiText);
        bmi.setText(getString(R.string.bmiText));

        status = findViewById(R.id.statusText);

        if (myBmi <= 18.5){
            layout.setBackgroundColor(Color.rgb(186,233,253));
            status.setText(getString(R.string.underwieght));
        }
        else if (myBmi <= 25){
            layout.setBackgroundColor(Color.rgb(174,253,105));
            status.setText(getString(R.string.normal));
        }
        else if (myBmi <= 30) {
            layout.setBackgroundColor(Color.rgb(244,237,105));
            status.setText(getString(R.string.overweight));
        }
        else if (myBmi <= 35) {
            layout.setBackgroundColor(Color.rgb(255,145,65));
            status.setText(getString(R.string.obese));
        }
        else if (myBmi >35) {
            layout.setBackgroundColor(Color.rgb(198,17,37));
            status.setText(getString(R.string.severelyObese));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
