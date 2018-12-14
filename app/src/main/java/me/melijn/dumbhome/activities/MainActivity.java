package me.melijn.dumbhome.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import me.melijn.dumbhome.R;
import me.melijn.dumbhome.fragments.ConfigFragment;
import me.melijn.dumbhome.fragments.RulesFragment;
import me.melijn.dumbhome.fragments.SendFragment;
import me.melijn.dumbhome.utils.HelpersKt;

public class MainActivity extends AppCompatActivity {

    private SendFragment sendFragment;
    private ConfigFragment configFragment;
    private RulesFragment rulesFragment;
    public static boolean local = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        sendFragment = new SendFragment();
        configFragment = new ConfigFragment();
        rulesFragment = new RulesFragment();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_bottom_send:
                        setFragment(sendFragment);
                        return true;
                    case R.id.nav_bottom_config:
                        setFragment(configFragment);
                        return true;
                    case R.id.nav_bottom_rules:
                        setFragment(rulesFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Settings", 0);
        HelpersKt.getIp(pref.getString("public-ipaddress", "84.198.44.242"));
        ConfigFragment.mode = pref.getInt("mode", ConfigFragment.mode);
        ConfigFragment.pulseLength = pref.getInt("pulseLength", ConfigFragment.pulseLength);
        setFragment(sendFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_bar_nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();
    }
}
