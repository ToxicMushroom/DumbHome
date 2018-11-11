package me.melijn.dumbhome.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SeekBar;
import android.widget.TextView;
import me.melijn.dumbhome.R;

public class ConfigFragment extends Fragment {


    private TextView textViewMode;
    private TextView textViewPulseLength;
    public static int mode = 1;
    public static int pulseLength = 430;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);
        SharedPreferences pref = view.getContext().getSharedPreferences("Settings", 0);
        mode = pref.getInt("mode", mode);
        pulseLength = pref.getInt("pulseLength", pulseLength);
        SeekBar modeBar = view.findViewById(R.id.seekBar1);
        SeekBar pulseLengthBar = view.findViewById(R.id.seekBar2);
        modeBar.setProgress(mode);
        pulseLengthBar.setProgress(pulseLength);
        textViewMode = view.findViewById(R.id.tv_mode_title);
        textViewPulseLength = view.findViewById(R.id.tv_pulse_title);
        textViewMode.setText("Mode: " + mode);
        textViewPulseLength.setText("PulseLength: " + pulseLength);

        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if (seekBar.getId() == R.id.seekBar1) {
                    mode = progress + 1;
                    textViewMode.setText("Mode: " + mode);
                } else if (seekBar.getId() == R.id.seekBar2) {
                    pulseLength = progress+1;
                    textViewPulseLength.setText("PulseLength: " + pulseLength);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        modeBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        pulseLengthBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        return view;
    }
}
