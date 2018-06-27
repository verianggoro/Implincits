package com.devlat.veri.intentimplicits;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText EditTextWeb, EditTextGps, EditTextShare;
    Button OpenWeb, OpenMaps, OpenShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditTextWeb = (EditText) findViewById(R.id.websiteUri);
        OpenWeb = (Button) findViewById(R.id.OpenWeb);
        EditTextGps = (EditText) findViewById(R.id.LocInput);
        OpenMaps = (Button) findViewById(R.id.OpenLoc);
        EditTextShare = (EditText) findViewById(R.id.shareIn);
        OpenShare = (Button) findViewById(R.id.shareBtn);


        OpenWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = EditTextWeb.getText().toString(); //Mengambil data pada edit Text dan mengubah ke string
                Uri website = Uri.parse(url); //Mengenkode dan parse String ke dalam obyek Uri
                Intent moveweb = new Intent(Intent.ACTION_VIEW, website); //Membuat Inten(perpindahan activity) dengan ACTION_VIEW sebagai tindakannya.
                /*
                Terdapat beberapa tindakan di Intent yaitu:
                ACTION_VIEW = Untuk melihat data yang diberikan
                ACTION_EDIT =  Untk Mengubah data yang diberikan
                ACTION_DIAL = Untuk menhubungi nomor telepon
                 */
                if (moveweb.resolveActivity(getPackageManager()) != null) {       //berfungsi untuk memfilter aplikasi yang dipasang dan memastikan menjalakan aplikasi tersebut dapat di jalankan
                    startActivity(moveweb);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this");
                }
            }
        });

        OpenMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lok = EditTextGps.getText().toString();
                Uri adrress = Uri.parse("geo:0,0?q=" + lok); //memparse string ke dalam objek Uri dengan kueri penelusuran geo
                Intent moveMaps = new Intent(Intent.ACTION_VIEW, adrress);
                if (moveMaps.resolveActivity(getPackageManager()) != null) {
                    startActivity(moveMaps);
                } else {
                    Log.d("ImplicitIntents", "Can't handle this");
                }
            }
        });

        OpenShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtShare = EditTextShare.getText().toString();
                String thisTxt = "text/plain";
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)             //Activity yang akan meluncurkan
                        .setType(thisTxt)       //tipe text yang akan diambil dengan mengambil class thisTxt
                        .setChooserTitle("Share this text with : ")     //Mengatur judul pada sistem yang akan dipilih
                        .setText(txtShare)  //text yang akan diambil berasal dari class txtShare
                        .startChooser(); //menampilkan opsi aplikasi yang akan di share
            }
        });
    }
}
