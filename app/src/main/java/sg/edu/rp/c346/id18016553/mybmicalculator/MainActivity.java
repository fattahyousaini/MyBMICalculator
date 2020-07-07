package sg.edu.rp.c346.id18016553.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvDate, tvBMI, tvResult;
    EditText etWeight, etHeight;
    Button btnCalculate, btnReset;

    public String BMI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate = findViewById(R.id.textViewDate);
        tvBMI = findViewById(R.id.textViewBMI);
        tvResult = findViewById(R.id.textViewResult);
        etWeight = findViewById(R.id.editTextWeight);
        etHeight = findViewById(R.id.editTextHeight);
        btnCalculate = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strWeight = etWeight.getText().toString();
                String strHeight = etHeight.getText().toString();
                if (strWeight.isEmpty() && strHeight.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter your Weight and Height.", Toast.LENGTH_SHORT).show();

                    return;

                }

                float w = Float.parseFloat(etWeight.getText().toString());
                float h = Float.parseFloat(etHeight.getText().toString());
                float bmi = w / (h * h);
                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);
                tvDate.setText(datetime);
                tvBMI.setText(String.format("%.3f", bmi));
                etHeight.setText("");
                etWeight.setText("");

                // Enhancement
                if (bmi == 0.0){
                    tvResult.setText("");
                } else if (bmi < 18.5) {
                    tvResult.setText("You are underweight!");
                } else if (bmi >= 18.5 && bmi < 25 ) {
                    tvResult.setText("You BMI is normal.");
                } else if (bmi >= 25 && bmi < 30 ) {
                    tvResult.setText("You are overweight.");
                } else if (bmi >= 30) {
                    tvResult.setText("You are Obese!");
                } else {
                    tvResult.setText("Error in calculation");
                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvDate.setText("");
                tvBMI.setText("");
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.clear();
                prefEdit.commit();
            }
        });

    }
}
