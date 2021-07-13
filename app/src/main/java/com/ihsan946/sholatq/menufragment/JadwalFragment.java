package com.ihsan946.sholatq.menufragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.batoulapps.adhan.CalculationMethod;
import com.batoulapps.adhan.CalculationParameters;
import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.Madhab;
import com.batoulapps.adhan.PrayerTimes;
import com.batoulapps.adhan.data.DateComponents;
import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.api.ApiEndpointJadwal;
import com.ihsan946.sholatq.api.ApiEndpointLokasi;
import com.ihsan946.sholatq.api.ApiServiceJadwal;
import com.ihsan946.sholatq.api.ApiServiceLokasi;
import com.ihsan946.sholatq.model.ApimodelJadwal;
import com.ihsan946.sholatq.model.ApimodelLokasi;
import com.ihsan946.sholatq.model.DatetimeModel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JadwalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JadwalFragment extends Fragment {


    TextView shubuh,dzuhur,asr,maghrib,isya,lokasi;
    String time_shubuh,time_dzuhur,time_asr,time_maghrib,time_isya;


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
//

//
        getJadwalSholat();
//
        shubuh = view.findViewById(R.id.value_shubuh);
        dzuhur = view.findViewById(R.id.value_dzuhur);
        asr = view.findViewById(R.id.value_ashar);
        maghrib = view.findViewById(R.id.value_maghrib);
        isya = view.findViewById(R.id.value_isya);
        lokasi = view.findViewById(R.id.value_lokasi);

        shubuh.setText(time_shubuh);
        dzuhur.setText(time_dzuhur);
        asr.setText(time_asr);
        maghrib.setText(time_maghrib);
        isya.setText(time_isya);
        lokasi.setText(Preference.getNamaKota(getActivity()));


//
        return view;
    }
//

    public void getJadwalSholat(){
//        latitude and longitude
        double latitude,longitude;
        latitude = Double.parseDouble(Preference.getLatitudePreferences(getActivity()));
        longitude = Double.parseDouble(Preference.getLongitudePreferences(getActivity()));

        Coordinates coordinates = new Coordinates(latitude,longitude);


        DateComponents date = DateComponents.from(new Date());

        CalculationParameters params = CalculationMethod.SINGAPORE.getParameters();
        params.madhab = Madhab.SHAFI;

        PrayerTimes prayer = new PrayerTimes(coordinates,date,params);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm ", Locale.UK);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

//        set value
        time_shubuh = String.valueOf(formatter.format(prayer.fajr));
        time_dzuhur = String.valueOf(formatter.format(prayer.dhuhr));
        time_asr = String.valueOf(formatter.format(prayer.asr));
        time_maghrib = String.valueOf(formatter.format(prayer.maghrib));
        time_isya = String.valueOf(formatter.format(prayer.isha));
//






    }



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