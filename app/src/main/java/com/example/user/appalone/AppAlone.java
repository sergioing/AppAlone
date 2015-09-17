package com.example.user.appalone;

import android.bluetooth.BluetoothAssignedNumbers;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class AppAlone extends ActionBarActivity {

    private static String url1 = "http://localhost:8080/mueblesdelosalpes.servicios/webresources/mobibus/mobibus/";
    private EditText txtNombre;
    private Button btnAceptar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_alone);

        txtNombre = (EditText)findViewById(R.id.TxtNombre);
        btnAceptar = (Button)findViewById(R.id.BtnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Creamos la información a pasar entre actividades

                double numero = (Math.random()*0.41)+4.43;
                double numero2=(Math.random()*(-0.213))-74.001;
                String lon=String.valueOf(numero);
                String lat=String.valueOf(numero2);

                String id="2";

                String ur=url1+id+"/"+lon+"_"+lat;
                txtNombre.setText(ur);

                try
                {
                    enviar(ur);
                }
                catch(Exception e)
                {
                    System.out.println(ur);
                }


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_app_alone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void enviar(String purl) throws java.net.ProtocolException, IOException
    {
        URL url = new URL(purl);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());
        out.write("Resource content");
        out.close();
        httpCon.getInputStream();
    }
}
