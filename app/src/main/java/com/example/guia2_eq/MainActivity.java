package com.example.guia2_eq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnMisDatos;
    Button btnEmpezar;
    EditText Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = findViewById(R.id.txtEmail);
        Password = findViewById(R.id.txtPassword);
        btnMisDatos = findViewById(R.id.btnMisDatos);
        btnEmpezar = findViewById(R.id.btnEmpezar);


        btnMisDatos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MisDatos.class);
                startActivityForResult(i, 0);
            }
        });

        btnEmpezar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(Validacion()) {
                Intent i = new Intent(v.getContext(), FormFavoritos.class);
                startActivityForResult(i, 0);
                }
            }
        });
    }


    public boolean Validacion(){
        boolean valor=false;
        if (Email.getText().toString().isEmpty() && Password.getText().toString().isEmpty()){
            Toast.makeText(this,"Los Campos no pueden quedar vacios",Toast.LENGTH_SHORT).show();
        }else{
            valor=true;
        }

        return valor;
    }
}

