package me.melijn.dumbhome.fragments;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);
        SeekBar seekBar1 = view.findViewById(R.id.seekBar1);
        SeekBar seekBar2 = view.findViewById(R.id.seekBar2);
        textViewMode = view.findViewById(R.id.tv_mode_title);
        textViewPulseLength = view.findViewById(R.id.tv_pulse_title);

        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if (seekBar.getId() == R.id.seekBar1) {
                    mode = progress + 1;
                    textViewMode.setText(String.valueOf("Mode: " + mode));
                } else if (seekBar.getId() == R.id.seekBar2) {
                    pulseLength = progress+1;
                    textViewPulseLength.setText(String.valueOf("PulseLength: " + pulseLength));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };

        seekBar1.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBar2.setOnSeekBarChangeListener(onSeekBarChangeListener);
        return view;
    }
}
