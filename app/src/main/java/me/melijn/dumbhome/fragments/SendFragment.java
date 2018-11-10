package me.melijn.dumbhome.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import me.melijn.dumbhome.R;
import me.melijn.dumbhome.activities.MainActivity;
import me.melijn.dumbhome.utils.HttpManager;
import me.melijn.dumbhome.utils.JSONConverter;
import org.json.JSONException;
import org.json.JSONObject;


public class SendFragment extends Fragment {


    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private View.OnClickListener onClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send, container, false);
        button1 = view.findViewById(R.id.btn_local1);
        button2 = view.findViewById(R.id.btn_local2);
        button3 = view.findViewById(R.id.btn_local3);
        button4 = view.findViewById(R.id.btn_local4);
        button5 = view.findViewById(R.id.btn_local5);
        button6 = view.findViewById(R.id.btn_local6);
        button7 = view.findViewById(R.id.btn_local7);
        button8 = view.findViewById(R.id.btn_local8);

        final JSONObject index = new JSONConverter(getResources().openRawResource(R.raw.index)).getObj();

        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int btn = -1;
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
                    default:
                        break;
                }
                if (btn == -1) return;
                try {
                    SharedPreferences preferences = view.getContext().getSharedPreferences("Settings", 0);
                    String host = preferences.getString("host", "rasp.melijn.me");
                    String ip = preferences.getString("ipaddress", "192.168.0.44");
                    String route = preferences.getString("route", "chacon");
                    int port = preferences.getInt("port", 8080);

                    String url = (MainActivity.local ? "http://" + ip : "https://" + host) + ":" + port + "/" + route;
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

        return view;
    }
}
