package com.example.getfit.Workout;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.getfit.Nutrition.ViewMeals;
import com.example.getfit.R;
import com.example.getfit.Supplement.ViewSupplement;
import com.example.getfit.ToDo_List.To_Do_List;

public class WeightConverter extends AppCompatActivity {

    EditText weight;
    RadioGroup radioGroup;
    RadioButton weight_radioBtn1;
    RadioButton weight_radioBtn2;
    RadioButton weight_radioBtn3;
    float weightValue;
    TextView weight_tv2;

    //navigation bar buttons
    Button navigate_todo,navigate_workout,navigate_nutrition,navigate_supplement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        weight =findViewById(R.id.weight_etnd1);
        weight_tv2 =findViewById(R.id.weight_tv2);
        radioGroup =findViewById(R.id.weight_radioGrp);
        weight_radioBtn1 =findViewById(R.id.weight_radioBtn1);
        weight_radioBtn2 =findViewById(R.id.weight_radioBtn2);
        weight_radioBtn3 =findViewById(R.id.weight_radioBtn3);

        navigate_nutrition = findViewById(R.id.navigate_nutrition);
        navigate_supplement = findViewById(R.id.navigate_supplement);
        navigate_todo =findViewById(R.id.navigate_todo);
        navigate_workout = findViewById(R.id.navigate_workout);

    }


    public void calculateWeight(View view){
        try {
            if(TextUtils.isEmpty(weight.getText().toString()) && radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(getApplicationContext(),"Please enter a weight value and check option ",Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(weight.getText().toString())){
                //Toast.makeText(getApplicationContext(),"Please enter a weight value to continue ",Toast.LENGTH_SHORT).show();
                    weight.setError("Please enter a weight value to continue");
                    weight.requestFocus();
                    return;
            }else if(radioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(getApplicationContext(),"Please select a weight conversion option ",Toast.LENGTH_SHORT).show();
            }else{

                    weightValue =  Float.parseFloat(weight.getText().toString());

                    if(weight_radioBtn1.isChecked()){
                        //float value = (float) (weightValue * 2.205f);
                        float result = kiloGramsToPounds(weightValue);
                        String formatValue = String.format("%.02f",result);
                        weight_tv2.setText("Your Weight is "+formatValue+" pounds");
                    }else if(weight_radioBtn2.isChecked()){
                        //float value = (float) (weightValue / 2.205f);
                        float result = PoundsToKiloGrams(weightValue);
                        String formatValue = String.format("%.02f",result);
                        weight_tv2.setText("Your Weight is "+formatValue+" kilograms");
                    }else if (weight_radioBtn3.isChecked()){
                        //float value = (float) (weightValue / 6.34f);
                        float result = kiloGramsToStones(weightValue);
                        String formatValue = String.format("%.02f",result);
                        weight_tv2.setText("Your Weight is "+formatValue+" stones");
                    }

                    emptyForm();
            }
        }catch(Exception e){
            Toast.makeText(this, "Please input correct number format", Toast.LENGTH_SHORT).show();
        }
    }

    public void emptyForm(){
        //weight.setText("");
        weight_radioBtn1.setChecked(false);
        weight_radioBtn2.setChecked(false);
        weight_radioBtn3.setChecked(false);
    }

    //Kilograms to Pounds conversion Calculation
    public float kiloGramsToPounds(double weightValue){
        float value = (float) (weightValue * 2.205);
        return value;
    }

    //Pounds to Kilograms conversion Calculation
    public float PoundsToKiloGrams(float weightValue){
        float value = (float) (weightValue / 2.205);
        return value;
    }

    //Kilograms to Stones conversion Calculation
    public float kiloGramsToStones(float weightValue){
        float value = (float) (weightValue / 6.34);
        return value;
    }


    //lower navigation bar button page directions
    protected void onResume() {
        super.onResume();

        navigate_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightConverter.this, To_Do_List.class);
                startActivity(intent);
            }
        });

        navigate_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WeightConverter.this, WorkOuts.class);
                startActivity(intent);

            }
        });

        navigate_nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WeightConverter.this, ViewMeals.class);
                startActivity(intent);

            }
        });

        navigate_supplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WeightConverter.this, ViewSupplement.class);
                startActivity(intent);

            }
        });

    }



}