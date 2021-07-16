package com.ihsan946.sholatq.menufragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.utils.NotificationUtils;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DzikirFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DzikirFragment extends Fragment{


    String banyak_dzikir;
    TextView dzikir;
    Button tambah_dzikir, reset_dzikir;
    int count = 0;
    private NotificationUtils mNotificationUtils;


    private final String CHANNEL_ID = "notif_sholat";
    private final int NOTIFICATION_ID = 999;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DzikirFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DzikirFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DzikirFragment newInstance(String param1, String param2) {
        DzikirFragment fragment = new DzikirFragment();
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

        View view = inflater.inflate(R.layout.fragment_dzikir, container, false);

        dzikir = view.findViewById(R.id.tampil_hitung_dzikir);
        tambah_dzikir = view.findViewById(R.id.tambah_dzikir);
        reset_dzikir = view.findViewById(R.id.reset_dzikir);

        tambah_dzikir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitungDzikir();
            }
        });

        reset_dzikir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetDzikir();
                ;
            }
        });
        return view;
    }

    public void hitungDzikir() {

        count++;
        banyak_dzikir = String.valueOf(count);
        dzikir.setText(banyak_dzikir);

//
        String name = "SholatQ";
        String description = "Yuk Sholat";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
//
        mNotificationUtils = new NotificationUtils(getActivity());
        Notification.Builder nb = mNotificationUtils.
                getAndroidChannelNotification(name,description);
        mNotificationUtils.getManager().notify(101, nb.build());






    }

    public void resetDzikir() {
        count = 0;
        banyak_dzikir = String.valueOf(count);
        dzikir.setText(banyak_dzikir);
    }


}