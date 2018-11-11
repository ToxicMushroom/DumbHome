package me.melijn.dumbhome.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import me.melijn.dumbhome.R;

public class SettingsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Settings");
        setContentView(R.layout.activity_settings);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Settings", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        if (!pref.contains("local-ipaddress")) editor.putString("local-ipaddress", "192.168.0.44");
        if (!pref.contains("public-ipaddress")) editor.putString("public-ipaddress", "84.198.44.242");
        if (!pref.contains("local-port")) editor.putInt("local-port", 8080);
        if (!pref.contains("public-port")) editor.putInt("public-port", 80);
        if (!pref.contains("host")) editor.putString("host", "rasp.melijn.me");
        if (!pref.contains("route")) editor.putString("route", "chacon/");
        if (!pref.contains("pulseLength")) editor.putInt("pulseLength", 215);
        if (!pref.contains("mode")) editor.putInt("mode", 1);
        if (!pref.contains("checked1")) editor.putBoolean("checked1", true);
        if (!pref.contains("checked2")) editor.putBoolean("checked2", true);
        if (!pref.contains("checked3")) editor.putBoolean("checked3", true);
        if (!pref.contains("checked4")) editor.putBoolean("checked4", false);
        editor.apply();

        final EditText editTextLocalIp = findViewById(R.id.etext_local_ip);
        final EditText editTextPublicIp = findViewById(R.id.etext_public_ip);
        editTextLocalIp.setText(pref.getString("local-ipaddress", "192.168.0.44"));
        editTextPublicIp.setText(pref.getString("public-ipaddress", "84.198.44.242"));

        final EditText editTextLocalPort = findViewById(R.id.etext_local_port);
        final EditText editTextPublicPort = findViewById(R.id.etext_public_port);
        editTextLocalPort.setText("" + pref.getInt("local-port", 8080));
        editTextPublicPort.setText("" + pref.getInt("public-port", 80));

        final EditText editTextHost = findViewById(R.id.etext_host);
        final EditText editTextRoute = findViewById(R.id.etext_route);
        editTextHost.setText(pref.getString("host", "rasp.melijn.me"));
        editTextRoute.setText(pref.getString("route", "chacon/"));

        final EditText editTextPulseLength = findViewById(R.id.etext_pulseLength);
        final EditText editTextMode = findViewById(R.id.etext_mode);
        editTextPulseLength.setText("" + pref.getInt("pulseLength", 215));
        editTextMode.setText("" + pref.getInt("mode", 1));

        final CheckBox checkBox1 = findViewById(R.id.checkBoxSetting1);
        final CheckBox checkBox2 = findViewById(R.id.checkBoxSetting2);
        final CheckBox checkBox3 = findViewById(R.id.checkBoxSetting3);
        final CheckBox checkBox4 = findViewById(R.id.checkBoxSetting4);
        checkBox1.setChecked(pref.getBoolean("checked1", true));
        checkBox2.setChecked(pref.getBoolean("checked2", true));
        checkBox3.setChecked(pref.getBoolean("checked3", true));
        checkBox4.setChecked(pref.getBoolean("checked4", false));

        Button saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Settings", 0);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("local-ipaddress", editTextLocalIp.getText().toString());
                editor.putString("public-ipaddress", editTextPublicIp.getText().toString());
                editor.putInt("local-port", Integer.parseInt(editTextLocalPort.getText().toString()));
                editor.putInt("public-port", Integer.parseInt(editTextPublicPort.getText().toString()));
                editor.putString("host", editTextHost.getText().toString());
                editor.putString("route", editTextRoute.getText().toString());
                editor.putInt("pulseLength", Integer.parseInt(editTextPulseLength.getText().toString()));
                editor.putInt("mode", Integer.parseInt(editTextMode.getText().toString()));
                editor.putBoolean("checked1", checkBox1.isChecked());
                editor.putBoolean("checked2", checkBox2.isChecked());
                editor.putBoolean("checked3", checkBox3.isChecked());
                editor.putBoolean("checked4", checkBox4.isChecked());
                editor.apply();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }
}
