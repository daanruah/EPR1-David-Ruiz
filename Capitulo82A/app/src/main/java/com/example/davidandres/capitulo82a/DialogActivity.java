package com.example.davidandres.capitulo82a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class DialogActivity extends Activity{

    final CharSequence[] items = {"Blue","Red","Tellow"};
    private ProgressDialog barProgressDialog;
    private Handler updateBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //showDialog();
        //showDialogWithList();
        //showDialogWithCheckBox();
        //showRingDialog();
        showBarDialog();
    }

    private void showDialog(){
        new AlertDialog.Builder(this)
            .setTitle("Titulo")
            .setMessage("Cerrar Aplicación")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            })
            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("Dialogo","Seguimos en la aplicación");
                }
            })
            .show();
        }

    private void showDialogWithList(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick a color");
        builder.setItems(items, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),items[which], Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showDialogWithCheckBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Pick a color");
        builder.setSingleChoiceItems(items, -1,new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),items[which], Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void showRingDialog(){
        final ProgressDialog ringProgressDialog = ProgressDialog.show(DialogActivity.this,"Espere","Descargando...",true);
        ringProgressDialog.setCancelable(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ringProgressDialog.dismiss();
            }
        }).start();
    }

    private void showBarDialog(){
        barProgressDialog = new ProgressDialog(DialogActivity.this);
        barProgressDialog.setTitle("Espere...");
        barProgressDialog.setMessage("Descargando....");
        barProgressDialog.setProgressStyle(barProgressDialog.STYLE_HORIZONTAL);
        barProgressDialog.setProgress(0);
        barProgressDialog.setMax(20);
        barProgressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        while ( barProgressDialog.getProgress() <= barProgressDialog.getMax()){
                        Thread.sleep(2000);
                            updateBarHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    barProgressDialog.incrementProgressBy(2);
                                }
                            });
                        if( barProgressDialog.getProgress() == barProgressDialog.getMax()){
                            barProgressDialog.dismiss();
                        }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        }).start();
    }
}
