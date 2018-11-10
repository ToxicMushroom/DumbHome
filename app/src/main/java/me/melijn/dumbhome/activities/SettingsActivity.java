package me.melijn.dumbhome.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.melijn.dumbhome.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_settings);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Settings", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (pref.getAll().size() == 0) {
            editor.putString("ipaddress", "192.168.0.44");
            editor.putString("remote-ipaddress", "84.198.44.242");
            editor.putString("host", "rasp.melijn.me");
            editor.putString("route", "chacon");
            editor.putInt("port", 8080);
            editor.apply();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
