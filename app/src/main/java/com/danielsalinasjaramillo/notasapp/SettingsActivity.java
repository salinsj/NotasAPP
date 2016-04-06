package com.danielsalinasjaramillo.notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SettingsActivity extends AppCompatActivity {

    EditText epExpo,epPrac,epProy;
    Button bGuardar,bBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        epPrac = (EditText) findViewById(R.id.epPrac);
        epProy = (EditText) findViewById(R.id.epProy);
        epExpo = (EditText) findViewById(R.id.epExpo);

        bGuardar = (Button) findViewById(R.id.bGuardar);
        bBorrar = (Button) findViewById(R.id.bBorrar);

        /*Bundle extras = getIntent().getExtras();

        epExpo.setText(String.valueOf(extras.getInt("pExpo")));
        epPrac.setText(String.valueOf(extras.getInt("pPrac")));
        epProy.setText(String.valueOf(extras.getInt("pProy")));*/

        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epExpo.setText(String.valueOf(""));
                epPrac.setText(String.valueOf(""));
                epProy.setText(String.valueOf(""));
            }
        });

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nExpo, nPrac, nProy;
                boolean campos = true;
                String desborde = "";

                if (desborde.equals(epExpo.getText().toString()) || desborde.equals(epPrac.getText().toString())
                        || desborde.equals(epProy.getText().toString()))
                    campos = false;

                if (campos == true)
                {
                        nExpo = Double.parseDouble(epExpo.getText().toString());
                        nPrac = Double.parseDouble(epPrac.getText().toString());
                        nProy = Double.parseDouble(epProy.getText().toString());

                        if (nExpo + nPrac + nProy == 100) {
                            Intent intent = new Intent();
                            intent.putExtra("npExp", epExpo.getText().toString());
                            intent.putExtra("npPrac", epPrac.getText().toString());
                            intent.putExtra("npProy", epProy.getText().toString());
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(SettingsActivity.this, "La suma de los porcentajes no es igual a 100, verifique y reasígnelos.", Toast.LENGTH_LONG).show();
                            Toast.makeText(SettingsActivity.this, "The sum of the percentages is not equal to 100, check it and reassign them", Toast.LENGTH_LONG).show();
                        }
                }
                 else
                {
                    Toast.makeText(SettingsActivity.this, "Llene todos los campos correspondientes a los porcentajes", Toast.LENGTH_LONG).show();
                    Toast.makeText(SettingsActivity.this, "Fill in all the fields corresponding to the percentages", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
