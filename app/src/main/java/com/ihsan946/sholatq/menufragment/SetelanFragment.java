package com.ihsan946.sholatq.menufragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.sharedpreferenced.Preference;
import com.ihsan946.sholatq.utils.BroadcastReceiverSholat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetelanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetelanFragment extends Fragment {


    Switch status_shubuh,status_dzuhur,status_asr,status_maghrib,status_isya;
//
    int ALARM_CODE_SHUBUH = 100;
    int ALARM_CODE_DZUHUR = 101;
    int ALARM_CODE_ASR = 102;
    int ALARM_CODE_MAGHRIB = 103;
    int ALARM_CODE_ISYA = 104;
    BroadcastReceiverSholat broadcastreceiversholat;
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String tanggal_kini = formatter.format(date);

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SetelanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetelanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetelanFragment newInstance(String param1, String param2) {
        SetelanFragment fragment = new SetelanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setelan, container, false);

//
        status_shubuh = view.findViewById(R.id.status_shubuh);
        status_dzuhur = view.findViewById(R.id.status_dzuhur);
        status_asr = view.findViewById(R.id.status_asr);
        status_maghrib = view.findViewById(R.id.status_maghrib);
        status_isya = view.findViewById(R.id.status_isya);
//
        status_shubuh.setChecked(Preference.getStatusShubuhPreference(getActivity()));
        status_dzuhur.setChecked(Preference.getStatusDzuhurPreference(getActivity()));
        status_asr.setChecked(Preference.getStatusAsrPreference(getActivity()));
        status_maghrib.setChecked(Preference.getStatusMaghribPreference(getActivity()));
        status_isya.setChecked(Preference.getStatusIsyaPreference(getActivity()));
        broadcastreceiversholat = new BroadcastReceiverSholat();
//
        status_shubuh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Preference.setStatusShubuhPreference(getActivity(),true);
                Toast.makeText(getActivity(), "Setelan Shubuh" + " : " + "Sudah diaktifkan",
                        Toast.LENGTH_LONG).show();
            }
            else{
                Preference.setStatusShubuhPreference(getActivity(),false);
                broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_SHUBUH);
                Toast.makeText(getActivity(), "Setelan Shubuh" + " : " + "Sudah dimatikan",
                        Toast.LENGTH_LONG).show();
            }
        });
//
        status_dzuhur.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Preference.setStatusDzuhurPreference(getActivity(),true);
                Toast.makeText(getActivity(), "Setelan Dzuhur" + " : " + "Sudah diaktifkan",
                        Toast.LENGTH_LONG).show();
            }
            else{
                Preference.setStatusDzuhurPreference(getActivity(),false);
                broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_DZUHUR);
                Toast.makeText(getActivity(), "Setelan Dzuhur" + " : " + "Sudah dimatikan",
                        Toast.LENGTH_LONG).show();
            }
        });
//
        status_asr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Preference.setStatusAsrPreference(getActivity(),true);
                    Toast.makeText(getActivity(), "Setelan Ashar" + " : " + "Sudah diaktifkan",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Preference.setStatusAsrPreference(getActivity(),false);
                    broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_ASR);
                    Toast.makeText(getActivity(), "Setelan Ashar" + " : " + "Sudah dimatikan",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
//
        status_maghrib.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                Preference.setStatusMaghribPreference(getActivity(),true);
                Toast.makeText(getActivity(), "Setelan Maghrib" + " : " + "Sudah diaktifkan",
                        Toast.LENGTH_LONG).show();
            }
            else{
                Preference.setStatusMaghribPreference(getActivity(),false);
                broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_MAGHRIB);
                Toast.makeText(getActivity(), "Setelan Maghrib" + " : " + "Sudah dimatikan",
                        Toast.LENGTH_LONG).show();
            }
        });

        status_isya.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Preference.setStatusIsyaPreference(getActivity(),true);
                    Toast.makeText(getActivity(), "Setelan Isya" + " : " + "Sudah diaktifkan",
                            Toast.LENGTH_LONG).show();
                }else{
                    Preference.setStatusIsyaPreference(getActivity(),false);
                    broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_ISYA);
                    Toast.makeText(getActivity(), "Setelan Isya" + " : " + "Sudah dimatikan",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        broadcastreceiversholat = new BroadcastReceiverSholat();

        //set alarm
        broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_SHUBUH,
                Preference.getTimeShubuhPreference(getActivity()),"Shubuh",tanggal_kini);
        broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_DZUHUR,
                Preference.getTimeDzuhurPreference(getActivity()),"Dzuhur",tanggal_kini);
        broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_ASR,
                Preference.getTimeAsrPreference(getActivity()),"Ashar",tanggal_kini);
        broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_MAGHRIB,
                Preference.getTimeMaghribPreference(getActivity()),"Maghrib",tanggal_kini);
        broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_ISYA,
                Preference.getTimeIsyaPreference(getActivity()),"Isya",tanggal_kini);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}