package com.eveningoutpost.dexdrip;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;

import com.eveningoutpost.dexdrip.Models.JoH;
import com.google.android.gms.common.GoogleApiAvailability;


public class LicenseAgreementActivity extends AppCompatActivity {
    boolean IUnderstand;
    boolean appended = false;
    CheckBox agreeCheckBox;
    Button saveButton;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        IUnderstand = prefs.getBoolean("I_understand", false);
        setContentView(R.layout.activity_license_agreement);
        JoH.fixActionBar(this);
        findViewById(R.id.googlelicenses).setAlpha(0.5f);
        agreeCheckBox = (CheckBox) findViewById(R.id.agreeCheckBox);
        agreeCheckBox.setChecked(IUnderstand);
        saveButton = (Button) findViewById(R.id.saveButton);
        addListenerOnButton();
    }

    public void viewGoogleLicenses(View myview) {
        try {
            if (!appended) {
                String googleLicense = GoogleApiAvailability.getInstance().getOpenSourceSoftwareLicenseInfo(getApplicationContext());
                if (googleLicense != null) {
                    String whiteheader = "<font size=-2 color=white><pre>";
                    String whitefooter = "</font></pre>";
                    WebView textview = (WebView) findViewById(R.id.webView);
                    textview.setBackgroundColor(Color.TRANSPARENT);
                    textview.getSettings().setJavaScriptEnabled(false);
                    textview.loadDataWithBaseURL("", whiteheader + googleLicense + whitefooter, "text/html", "UTF-8", "");
                    appended = true;
                    findViewById(R.id.googlelicenses).setVisibility(View.INVISIBLE);
                    findViewById(R.id.webView).setVisibility(View.VISIBLE);

                }
            }
        } catch (Exception e) {
        }
    }
    public void viewWarning(View myview)
    {
    startActivity(new Intent(getApplicationContext(),Agreement.class));
    }

    public void addListenerOnButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                prefs.edit().putBoolean("I_understand", agreeCheckBox.isChecked()).apply();

                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }

        });
    }
}
