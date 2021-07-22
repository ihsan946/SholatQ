package com.ihsan946.sholatq.menufragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.adapter.JadwalsholatAdapter;
import com.ihsan946.sholatq.model.Sholatqmodel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JadwalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JadwalFragment extends Fragment {


    TextView lokasi,tanggal;
    String tanggal_terkini;
    String time_shubuh,time_dzuhur,time_asr,time_maghrib,time_isya;
    RecyclerView layout_bawah_jadwal;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public JadwalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JadwalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JadwalFragment newInstance(String param1, String param2) {
        JadwalFragment fragment = new JadwalFragment();
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
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);
        final FragmentActivity fragment = getActivity();
//

//  layout atas
        lokasi = view.findViewById(R.id.value_lokasi);
        tanggal = view.findViewById(R.id.tanggal_jadwal);
        lokasi.setText(Preference.getNamaKota(getActivity()));
        Date tanggal_kini = new Date();
        SimpleDateFormat formatTanggal = new SimpleDateFormat("dd-MMMM-YYYY");
        tanggal_terkini = formatTanggal.format(tanggal_kini);
        tanggal.setText(tanggal_terkini);

        //
        time_shubuh = Preference.getTimeShubuhPreference(getActivity());
        time_dzuhur = Preference.getTimeDzuhurPreference(getActivity());
        time_asr = Preference.getTimeAsrPreference(getActivity());
        time_maghrib = Preference.getTimeMaghribPreference(getActivity());
        time_isya = Preference.getTimeIsyaPreference(getActivity());

//        layout bawah

        layout_bawah_jadwal = view.findViewById(R.id.layout_bawah_jadwal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragment, LinearLayoutManager.HORIZONTAL, false);
        layout_bawah_jadwal.setLayoutManager(layoutManager);
        Sholatqmodel model = new Sholatqmodel();

        String [] name_jadwal = {
                "Shubuh","Dzuhur","Ashar","Maghrib","Isya"
        };
        String [] jadwal = {
                time_shubuh,time_dzuhur,time_asr,time_maghrib,time_isya
        };
        int background = R.drawable.shubuh;

        model.setName_jadwalsholat(name_jadwal);
        model.setJadwal_sholat(jadwal);
        model.setBackground_jadwal(background);
        final JadwalsholatAdapter jadwalsholatAdapter = new JadwalsholatAdapter(model.getName_jadwalsholat(), model.getJadwal_sholat(), model.getBackground_jadwal(), fragment);
        layout_bawah_jadwal.setAdapter(jadwalsholatAdapter);
//
        return view;
    }
//





//







//    public void getJadwal(){
//        String nama_kota = Preference.getNamaKota(getActivity());
//        Log.d("namaKota", nama_kota);
//        ApiServiceJadwal api = ApiEndpointJadwal.getClient().create(ApiServiceJadwal.class);
//
//        Call<ApimodelJadwal> call = api.getJadwal(Preference.getIpDevice(getActivity()));
//        call.enqueue(new Callback<ApimodelJadwal>() {
//            @Override
//            public void onResponse(Call<ApimodelJadwal> call, Response<ApimodelJadwal> response) {
////                List<DatetimeModel> results = response.body().result;
//                int code = response.body().getCode();
//                Log.d("Hasil", String.valueOf(code));
//
//
//
//
//
//
////                Log.d("result", response.body().result.toString());
//
//
//
//
//
////                Log.d("result",items.get(0).getDatetime().get(0).getTimes().toString());
////                datetimeModel = new DatetimeModel();
////                datetimeModel.setDatetime(items.get(0).getDatetime());
//
//            }
//
//            @Override
//            public void onFailure(Call<ApimodelJadwal> call, Throwable t) {
//
//            }
//        });
//
//    }




}