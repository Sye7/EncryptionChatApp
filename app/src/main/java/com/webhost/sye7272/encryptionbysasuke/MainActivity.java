package com.webhost.sye7272.encryptionbysasuke;


import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // For  text keyboard   android:inputType="text|textCapCharacters|textCapWords|textCapSentences|textAutoCorrect|textAutoComplete|textMultiLine|textImeMultiLine|textNoSuggestions|textUri|textEmailAddress|textEmailSubject|textShortMessage|textLongMessage|textPersonName|textPostalAddress|textPassword|textVisiblePassword|textWebEditText|textFilter|textPhonetic|textWebEmailAddress|textWebPassword"
  // For number keyboard      android:inputType="text|textCapCharacters|textCapWords|textCapSentences|textAutoCorrect|textAutoComplete|textMultiLine|textImeMultiLine|textNoSuggestions|textUri|textEmailAddress|textEmailSubject|textShortMessage|textLongMessage|textPersonName|textPostalAddress|textPassword|textVisiblePassword|textWebEditText|textFilter|textPhonetic|textWebEmailAddress|textWebPassword|phone"






    private EditText mEditTextBinary;
    private EditText mEditTextText;
    private MainActivity mThis;

    public void bGround() {
        int a[] = new int[12];
        for (int i = 0; i < 12; i++) {
            a[i] = i;
        }
        Random rand = new Random();
        int bg = rand.nextInt(12);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.map);
        switch (bg) {
            case 0: {
                rl.setBackgroundResource(R.drawable.a);
                break;
            }
            case 1: {
                rl.setBackgroundResource(R.drawable.b);
                break;
            }
            case 2: {
                rl.setBackgroundResource(R.drawable.c);
                break;
            }
            case 3: {
                rl.setBackgroundResource(R.drawable.d);
                break;
            }
            case 4: {
                rl.setBackgroundResource(R.drawable.e);
                break;
            }
            case 5: {
                rl.setBackgroundResource(R.drawable.f);
                break;
            }
            case 6: {
                rl.setBackgroundResource(R.drawable.g);
                break;
            }
            case 7: {
                rl.setBackgroundResource(R.drawable.h);
                break;
            }
            case 8: {
                rl.setBackgroundResource(R.drawable.a);
                break;
            }
            case 9: {
                rl.setBackgroundResource(R.drawable.j);
                break;
            }
            case 10: {
                rl.setBackgroundResource(R.drawable.k);
                break;
            }
            case 11: {
                rl.setBackgroundResource(R.drawable.l);
                break;
            }

        }

    }







    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mThis = this;
        this.mEditTextBinary = (EditText) findViewById(R.id.EditTextBinary);  //found
        this.mEditTextText = (EditText) findViewById(R.id.EditTextText);
        ((Button) findViewById(R.id.CopyText)).setOnClickListener(new View.OnClickListener() {      //for copy purpose

            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 22) {
                   ((ClipboardManager) MainActivity.this.getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("text label", MainActivity.this.mEditTextText.getText().toString()));
                } else {
                    ((android.text.ClipboardManager) MainActivity.this.getSystemService(CLIPBOARD_SERVICE)).setText(MainActivity.this.mEditTextText.getText().toString());
                }
                Toast.makeText(MainActivity.this.mThis, "Success", Toast.LENGTH_LONG).show();
            }
        });
        ((Button) findViewById(R.id.CopyBinary)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {               //found
                if (Build.VERSION.SDK_INT >= 22) {
                    ((ClipboardManager) MainActivity.this.getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("text label", MainActivity.this.mEditTextBinary.getText().toString()));
                } else {
                    ((android.text.ClipboardManager) MainActivity.this.getSystemService(CLIPBOARD_SERVICE)).setText(MainActivity.this.mEditTextBinary.getText().toString());
                }
                Toast.makeText(MainActivity.this.mThis, "Success ;)",Toast.LENGTH_SHORT ).show();    //edit
            }
        });
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("code")) {
                this.mEditTextBinary.setText(savedInstanceState.getString("code"));
            }
            if (savedInstanceState.containsKey("text")) {
                this.mEditTextText.setText(savedInstanceState.getString("text"));
            }
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);              //Advertise
        int aufrufAnzahl = prefs.getInt("Aufrufe", 1);
        boolean bewertet = prefs.getBoolean("Bewertet", false);
        if (aufrufAnzahl <= 2 || bewertet) {
            aufrufAnzahl++;
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("Aufrufe", aufrufAnzahl);
            editor.commit();
            return;
        }
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("Encryption By Syed");
        dlg.setMessage("Please rate this app and share it with your friends!");
        dlg.setNegativeButton("Not yet", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this.mThis).edit();
                editor.putBoolean("Bewertet", false);
                editor.putInt("Aufrufe", -10);
                editor.commit();
            }
        });
        dlg.setNeutralButton("Rate Later", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this.mThis).edit();
                editor.putBoolean("Bewertet", false);
                editor.putInt("Aufrufe", 0);
                editor.commit();
            }
        });
        dlg.setPositiveButton("Rate Now", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this.mThis).edit();
                editor.putBoolean("Bewertet", true);
                editor.commit();
                MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://sye7272.000webhostapp.com")));
            }
        });
        dlg.show();                                                                 //Advertise
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("code", this.mEditTextBinary.getText().toString());
        outState.putString("text", this.mEditTextText.getText().toString());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear /*2131427403*/:
                EditText text = (EditText) findViewById(R.id.EditTextText);
                ((EditText) findViewById(R.id.EditTextBinary)).setText(BuildConfig.FLAVOR);
                text.setText(BuildConfig.FLAVOR);
                break;
            case R.id.exit:
                System.exit(0);
                break;
            case R.id.bg:
                bGround();
                break;
            case R.id.syed:
                Toast.makeText(mThis, "Perhaps I'm the Closest Thing to Demon ALive", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void Encode(View v) {
        this.mEditTextBinary.setText(gibAusgabeBinary(this.mEditTextText.getText().toString()));
    }

    private String gibAusgabeBinary(String input) {
        String ausgabe = BuildConfig.FLAVOR;
        for (int i = 0; i < input.length(); i++) {
            String add = Integer.toBinaryString(input.charAt(i));
            while (add.length() != 8) {
                add = "0" + add;
            }
             int int_VAlue=Integer.parseInt(add,2);
            String temp=Integer.toString(int_VAlue,36);

            ausgabe = ausgabe + temp + " ";
        }
        Log.i("Chacha",ausgabe);

        return ausgabe;
    }

    public void Decode(View v) {
        this.mEditTextText.setText(gibAusgabeText(this.mEditTextBinary.getText().toString()));
    }

    //  .replaceAll(" ", BuildConfig.FLAVOR)
    private String gibAusgabeText(String input) {
        try {
            String ausgabe = BuildConfig.FLAVOR;
            int ctr=0,inc=0;
            String tempS=new String();
            for (int i = 0; i < input.length(); i++) {
                ctr=i;
                for(int j=i;input.charAt(j)!=' '&&j<input.length()+1;j++)
                {
                            ctr++;
                            inc++;
                }
                tempS=input.substring(i,ctr);
                Log.i("temp",tempS);
                int dec_no=Integer.parseInt(tempS,36);
                Log.i("tempValue",Integer.toString(dec_no));
                if(input.charAt(i)==' ')
                    ausgabe=ausgabe+" ";
                ausgabe=ausgabe+((char)Integer.parseInt(tempS,36));
                i=i+inc;
                inc=0;
                ctr=0;
            }
            return ausgabe;
        } catch (NumberFormatException e) {
            Toast.makeText(this.mThis, "Error Your Mistake", Toast.LENGTH_SHORT).show();     //edit
            return BuildConfig.FLAVOR;
        }
    }
}
