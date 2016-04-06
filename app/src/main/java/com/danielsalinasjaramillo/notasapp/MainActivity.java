package com.danielsalinasjaramillo.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    int por_exp=0,por_prac=0,por_proy=0;

    EditText etExpo,etPrac,etProy,etNota;
    TextView tvExpo,tvPrac,tvProy;
    Button bCalc,bLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etExpo = (EditText) findViewById(R.id.etExpo);
        etPrac = (EditText) findViewById(R.id.etPrac);
        etProy = (EditText) findViewById(R.id.etProy);
        etNota = (EditText) findViewById(R.id.etFinal);

        tvExpo = (TextView) findViewById(R.id.tvExpo);
        tvPrac = (TextView) findViewById(R.id.tvPrac);
        tvProy = (TextView) findViewById(R.id.tvProy);

        bCalc = (Button) findViewById(R.id.bCalcular);
        bLimpiar = (Button) findViewById(R.id.bLimpiar);

        bLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                etExpo.setText(String.valueOf(""));
                etPrac.setText(String.valueOf(""));
                etProy.setText(String.valueOf(""));
                etNota.setText(String.valueOf(""));
            }
        });

        bCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String desborde="",overflow="",nota_string;
                double nota,nPrac,nExpo,nProy;
                boolean campos=true;

                if(desborde.equals(etExpo.getText().toString()) || desborde.equals(etPrac.getText().toString())
                        || desborde.equals(etProy.getText().toString()))
                    campos=false;

            if(campos==true)
            {
                nExpo = Double.parseDouble(etExpo.getText().toString());
                nPrac = Double.parseDouble(etPrac.getText().toString());
                nProy = Double.parseDouble(etProy.getText().toString());

                if (nExpo > 5) {
                    overflow = "Expositions ";
                    desborde = "Exposiciones ";
                }
                if (nPrac > 5) {
                    overflow = overflow + "Practices ";
                    desborde = desborde + "Prácticas ";
                }
                if (nProy > 5) {
                    overflow = overflow + "Project ";
                    desborde = desborde + "Proyecto ";
                }

                if (desborde.equals("")) {
                    DecimalFormat decimales = new DecimalFormat("0.0");
                    nota = nExpo * por_exp / 100 + nPrac * por_prac / 100 + nProy * por_proy / 100;
                    nota_string = decimales.format(nota);
                    etNota.setText(String.valueOf(nota_string));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Las siguientes notas asignadas no están en el rango permitido [0.0,5.0]: "
                            + desborde + "Corríjalas para obtener la nota.", Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "The following assigned grades are not in the allowed range [0.0,5.0]: "
                            + overflow + "correct them for getting the final grade.", Toast.LENGTH_LONG).show();
                }
            }
                else
                {
                    Toast.makeText(MainActivity.this, "Llene todos los campos correspondientes a las notas.", Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "Fill in all the fields correspondig to the grades.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id==R.id.menu_configurar)
        {
            Intent intent = new Intent(this,SettingsActivity.class);
            /*intent.putExtra("pExpo",15);
            intent.putExtra("pPrac", 50);
            intent.putExtra("pProy",35);
            startActivity(intent);*/
            startActivityForResult(intent,1234);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
       if(requestCode==1234 && resultCode==RESULT_OK)
       {
           por_exp=Integer.parseInt(data.getExtras().getString("npExp"));
           por_prac=Integer.parseInt(data.getExtras().getString("npPrac"));
           por_proy=Integer.parseInt(data.getExtras().getString("npProy"));

           tvExpo.setText(String.valueOf(por_exp)+"%");
           tvPrac.setText(String.valueOf(por_prac) + "%");
           tvProy.setText(String.valueOf(por_proy)+"%");
       }
    }
}
