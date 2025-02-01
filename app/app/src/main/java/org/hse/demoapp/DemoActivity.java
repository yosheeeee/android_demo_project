package org.hse.demoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DemoActivity extends AppCompatActivity {

    private EditText number;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_demo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result =findViewById(R.id.result_text);
        number = findViewById(R.id.number_input);
        var button1 = findViewById(R.id.button1);
        var button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton1Click();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButton2Click();
            }
        });

    }

    private int GetInputValue() throws Exception {
        var numberVal = number.getText().toString();
        if (numberVal.isEmpty()){
            numberVal = "0";
        }
        int integerVal = Integer.parseInt(numberVal);
        if (integerVal > 500){
            Toast.makeText(this, "Value is greater than 500", Toast.LENGTH_SHORT).show();
            result.setText("");
            throw new Exception("");
        }
        if (integerVal < 0){
            Toast.makeText(this, "Value is lower than 0", Toast.LENGTH_SHORT).show();
            result.setText("");
            throw new Exception("");
        }
        return integerVal;
    }

    private void onButton1Click(){
        int integerVal;
        try{
            integerVal = GetInputValue();
        }catch (Exception e){
            return;
        }
        int counter = 0;
        for(int i=0; i< integerVal; i++){
            counter += i +1;
        }
        result.setText(String.format("Result: %d", counter));
    }

    private void onButton2Click(){
        int integerVal;
        try{
            integerVal = GetInputValue();
        }catch (Exception e){
            return;
        }
        var counter = 0;
        for (int i=0; i<integerVal; i+=2){
            counter += i;
        }
        result.setText(String.format("Result: %d", counter));
    }
}