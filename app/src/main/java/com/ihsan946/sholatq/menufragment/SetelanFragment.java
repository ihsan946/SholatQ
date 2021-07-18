package com.ihsan946.sholatq.menufragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetelanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetelanFragment extends Fragment {


    Switch status_shubuh,status_dzuhur,status_asr,status_maghrib,status_isya;
//
    final int ALARM_CODE_SHUBUH = 100;
    final int ALARM_CODE_DZUHUR = 101;
    final int ALARM_CODE_ASR = 102;
    final int ALARM_CODE_MAGHRIB = 103;
    final int ALARM_CODE_ISYA = 104;
//    BroadcastReceiverSholat broadcastreceiversholat;

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
//        broadcastreceiversholat = new BroadcastReceiverSholat();
//
//        status_shubuh.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if(isChecked){
//                if(Preference.getStatusShubuhPreference(getActivity()) == true){
//                    Preference.setStatusShubuhPreference(getActivity(),true);
//                }
//                else{
//                    broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_SHUBUH,
//                            Preference.getTimeShubuhPreference(getActivity()),"Shubuh");
//                    Preference.setStatusShubuhPreference(getActivity(),true);
//                }
//
//            }
//            else{
//                broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_SHUBUH);
//                Preference.setStatusShubuhPreference(getActivity(),false);
//            }
//        });
//
//        status_dzuhur.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if(isChecked){
//                if(Preference.getStatusDzuhurPreference(getActivity()) == true){
//                    Preference.setStatusDzuhurPreference(getActivity(),true);
//                }
//                else{
//                    broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_DZUHUR,
//                            Preference.getTimeDzuhurPreference(getActivity()),"Dzuhur");
//                    Preference.setStatusDzuhurPreference(getActivity(),true);
//                }
//            }
//            else{
//                broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_DZUHUR);
//                Preference.setStatusDzuhurPreference(getActivity(),false);
//            }
//        });
//
//        status_asr.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if(isChecked){
//                if(Preference.getStatusAsrPreference(getActivity()) == true){
//                    Preference.setStatusAsrPreference(getActivity(),true);
//                }
//                else{
//                    broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_ASR,
//                            Preference.getTimeAsrPreference(getActivity()),"Ashar");
//                    Preference.setStatusAsrPreference(getActivity(),true);
//                }
//            }
//            else{
//                broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_ASR);
//                Preference.setStatusAsrPreference(getActivity(),false);
//            }
//        });
//
//        status_maghrib.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if(isChecked){
//                if(Preference.getStatusMaghribPreference(getActivity()) == true){
//                    Preference.setStatusMaghribPreference(getActivity(),true);
//                }
//                else{
//                    broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_MAGHRIB,
//                            Preference.getTimeMaghribPreference(getActivity()),"Maghrib");
//                    Preference.setStatusMaghribPreference(getActivity(),true);
//                }
//
//            }
//            else{
//                broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_MAGHRIB);
//                Preference.setStatusMaghribPreference(getActivity(),false);
//            }
//        });
//
//        status_isya.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if(isChecked){
//                if(Preference.getStatusIsyaPreference(getActivity()) == true){
//                    Preference.setStatusIsyaPreference(getActivity(),true);
//                }
//                else{
//                    broadcastreceiversholat.setRepeatingAlarm(getActivity(),ALARM_CODE_ISYA,
//                            Preference.getTimeIsyaPreference(getActivity()),"Isya");
//                    Preference.setStatusIsyaPreference(getActivity(),true);
//                }
//
//            }
//            else{
//                broadcastreceiversholat.cancelAlarm(getActivity(),ALARM_CODE_ISYA);
//                Preference.setStatusIsyaPreference(getActivity(),false);
//            }
//        });

        return view;
    }
}