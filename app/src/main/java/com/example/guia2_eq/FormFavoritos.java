package com.example.guia2_eq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FormFavoritos extends AppCompatActivity implements View.OnLongClickListener {

    AutoCompleteTextView Fruta, Animal, LengProgram;
    Button Procesar;
    int Progreso=0;
    ProgressBar ProgressBar;
    boolean ValidacionClick = false;
    Handler h = new Handler();

    String [] listFrutas = {"Mangos","Fresas","Peras","Manzanas"};
    String [] listAnimales = {"Lobos","Leones","Tigres","Leopardos"};
    String [] listLengProgram = {"C#","C++","Java","JavasScript"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_favoritos);


        Procesar = findViewById(R.id.btnProcesar);
        Fruta = findViewById(R.id.txtFruta);
        Animal = findViewById(R.id.txtAnimal);
        LengProgram = findViewById(R.id.txtLengProgram);
        ProgressBar = findViewById(R.id.ProgressBar);

        ArrayAdapter<String> AdapterFruta = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listFrutas);
        Fruta.setThreshold(1);
        Fruta.setAdapter(AdapterFruta);

        ArrayAdapter<String> AdapterAnimal = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listAnimales);
        Animal.setThreshold(1);
        Animal.setAdapter(AdapterAnimal);

        ArrayAdapter<String> AdapterLengProgram = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, listLengProgram);
        LengProgram.setThreshold(1);
        LengProgram.setAdapter(AdapterLengProgram);

        Procesar.setOnLongClickListener(this);
    }

    public void MostrarToast(){
        Toast.makeText(this,"Fruta Seleccionada: " + Fruta.getText()+"\n"+
                "Animal Seleccionado: " + Animal.getText()+"\n"+"Lenguaje Seleccionado: " + LengProgram.getText(),Toast.LENGTH_SHORT).show();
    }
    /*private void setProgressValue(final int progress){
        ProgressBar.setProgress(progress);
        Progreso = progress;
        Thread  thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                setProgressValue(progress+10);
            }
        });
        thread.start();
    }*/

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.btnProcesar: {
                Thread carga = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (Progreso <= 100) {
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    ProgressBar.setProgress(Progreso);
                                }
                            });
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(Progreso==100)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        MostrarToast();
                                        Progreso=0;
                                    }
                                });
                            }
                            Progreso = Progreso+20;
                        }
                    }
                });  carga.start();
            }
            break;
        }
        return false;
    }
}
