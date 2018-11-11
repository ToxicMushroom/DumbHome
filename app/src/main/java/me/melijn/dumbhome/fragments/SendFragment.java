package me.melijn.dumbhome.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InputDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import me.melijn.dumbhome.R;
import me.melijn.dumbhome.activities.MainActivity;
import me.melijn.dumbhome.utils.HttpManager;
import me.melijn.dumbhome.utils.JSONConverter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SendFragment extends Fragment {


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;

    private Button button9;
    private Button button10;

    private CheckBox checkBox1_2;
    private CheckBox checkBox3_4;
    private CheckBox checkBox5_6;
    private CheckBox checkBox7_8;
    private View.OnClickListener onClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send, container, false);
        SharedPreferences preferences = view.getContext().getSharedPreferences("Settings", 0);
        button1 = view.findViewById(R.id.btn_local1);
        button2 = view.findViewById(R.id.btn_local2);
        button3 = view.findViewById(R.id.btn_local3);
        button4 = view.findViewById(R.id.btn_local4);
        button5 = view.findViewById(R.id.btn_local5);
        button6 = view.findViewById(R.id.btn_local6);
        button7 = view.findViewById(R.id.btn_local7);
        button8 = view.findViewById(R.id.btn_local8);
        button9 = view.findViewById(R.id.btn_local9);
        button10 = view.findViewById(R.id.btn_local10);

        checkBox1_2 = view.findViewById(R.id.checkBox1);
        checkBox3_4 = view.findViewById(R.id.checkBox2);
        checkBox5_6 = view.findViewById(R.id.checkBox3);
        checkBox7_8 = view.findViewById(R.id.checkBox4);
        checkBox1_2.setChecked(preferences.getBoolean("checked1", true));
        checkBox3_4.setChecked(preferences.getBoolean("checked2", true));
        checkBox5_6.setChecked(preferences.getBoolean("checked3", true));
        checkBox7_8.setChecked(preferences.getBoolean("checked4", false));

        final JSONObject index = new JSONConverter(getResources().openRawResource(R.raw.index)).getObj();

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int btn = -1;
                SharedPreferences preferences = view.getContext().getSharedPreferences("Settings", 0);
                String host = preferences.getString("host", "rasp.melijn.me");
                String ip = preferences.getString("local-ipaddress", "192.168.0.44");
                String route = preferences.getString("route", "chacon");
                int localPort = preferences.getInt("local-port", 8080);
                int publicPort = preferences.getInt("public-port", 80);

                String url = (MainActivity.local ? "http://" + ip + ":" + localPort : "https://" + host + (publicPort == 80 ? "" : ":" + publicPort)) + "/" + route;
                switch (view.getId()) {
                    case R.id.btn_local1:
                        btn = 1;
                        break;
                    case R.id.btn_local2:
                        btn = 2;
                        break;
                    case R.id.btn_local3:
                        btn = 3;
                        break;
                    case R.id.btn_local4:
                        btn = 4;
                        break;
                    case R.id.btn_local5:
                        btn = 5;
                        break;
                    case R.id.btn_local6:
                        btn = 6;
                        break;
                    case R.id.btn_local7:
                        btn = 7;
                        break;
                    case R.id.btn_local8:
                        btn = 8;
                        break;
                    case R.id.btn_local9:
                        int[] codes = new int[4];
                        try {
                            //Get all codes to enable the sockets
                            if (checkBox1_2.isChecked())
                                codes[0] = index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt("1");
                            if (checkBox3_4.isChecked())
                                codes[1] = index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt("3");
                            if (checkBox5_6.isChecked())
                                codes[2] = index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt("5");
                            if (checkBox7_8.isChecked())
                                codes[3] = index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt("7");
                            HttpManager.sendRequest(url, codes);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.btn_local10:
                        codes = new int[4];
                        try {
                            //Get all codes to disable the sockets
                            if (checkBox1_2.isChecked())
                                codes[0] = index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt("2");
                            if (checkBox3_4.isChecked())
                                codes[1] = index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt("4");
                            if (checkBox5_6.isChecked())
                                codes[2] = index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt("6");
                            if (checkBox7_8.isChecked())
                                codes[3] = index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt("8");
                            HttpManager.sendRequest(url, codes);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
                if (btn == -1) return;
                try {
                    HttpManager.sendRequest(url, index.getJSONObject(String.valueOf(ConfigFragment.mode)).getInt(String.valueOf(btn)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);
        button4.setOnClickListener(onClickListener);
        button5.setOnClickListener(onClickListener);
        button6.setOnClickListener(onClickListener);
        button7.setOnClickListener(onClickListener);
        button8.setOnClickListener(onClickListener);
        button9.setOnClickListener(onClickListener);
        button10.setOnClickListener(onClickListener);
        return view;
    }
}
