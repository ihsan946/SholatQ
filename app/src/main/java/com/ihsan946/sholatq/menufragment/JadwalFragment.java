package com.ihsan946.sholatq.menufragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.batoulapps.adhan.CalculationMethod;
import com.batoulapps.adhan.CalculationParameters;
import com.batoulapps.adhan.Coordinates;
import com.batoulapps.adhan.Madhab;
import com.batoulapps.adhan.PrayerTimes;
import com.batoulapps.adhan.data.DateComponents;
import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.adapter.JadwalsholatAdapter;
import com.ihsan946.sholatq.model.Sholatqmodel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JadwalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JadwalFragment extends Fragment {


    TextView lokasi,tanggal;
    String time_shubuh,time_dzuhur,time_asr,time_maghrib,time_isya,tanggal_terkini;
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

//
        getJadwalSholat();
//
//        shubuh = view.findViewById(R.id.value_shubuh);
//        dzuhur = view.findViewById(R.id.value_dzuhur);
//        asr = view.findViewById(R.id.value_ashar);
//        maghrib = view.findViewById(R.id.value_maghrib);
//        isya = view.findViewById(R.id.value_isya);

//  layout atas

        lokasi = view.findViewById(R.id.value_lokasi);
        tanggal = view.findViewById(R.id.tanggal_jadwal);
        lokasi.setText(Preference.getNamaKota(getActivity()));
        tanggal.setText(tanggal_terkini);
        ImageView image = view.findViewById(R.id.gambaratas_jadwal);
        Picasso.get().load("https://random.imagecdn.app/400/162").into(image);

//400/162

//        shubuh.setText(time_shubuh);
//        dzuhur.setText(time_dzuhur);
//        asr.setText(time_asr);
//        maghrib.setText(time_maghrib);
//        isya.setText(time_isya);

//        layout bawah

        layout_bawah_jadwal = view.findViewById(R.id.layoutbawah_jadwal);
        LinearLayoutManager layoutManager = new LinearLayoutManager(fragment, LinearLayoutManager.HORIZONTAL, false);
        layout_bawah_jadwal.setLayoutManager(layoutManager);
        Sholatqmodel model = new Sholatqmodel();

        String [] name_jadwal = {
                "Shubuh","Dzuhur","Ashar","Maghrib","Isya"
        };
        String [] jadwal = {
                time_shubuh,time_dzuhur,time_asr,time_maghrib,time_isya
        };

        model.setName_jadwalsholat(name_jadwal);
        model.setJadwal_sholat(jadwal);
        final JadwalsholatAdapter jadwalsholatAdapter = new JadwalsholatAdapter(model.getName_jadwalsholat(), model.getJadwal_sholat(), fragment);
        layout_bawah_jadwal.setAdapter(jadwalsholatAdapter);










//

//


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
        Date tanggal = new Date();

        CalculationParameters params = CalculationMethod.SINGAPORE.getParameters();
        params.madhab = Madhab.SHAFI;

        PrayerTimes prayer = new PrayerTimes(coordinates,date,params);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm ", Locale.UK);
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));

        SimpleDateFormat formatTanggal = new SimpleDateFormat("dd-MMMM-YYYY");
//        set value
        time_shubuh = String.valueOf(formatter.format(prayer.fajr));
        time_dzuhur = String.valueOf(formatter.format(prayer.dhuhr));
        time_asr = String.valueOf(formatter.format(prayer.asr));
        time_maghrib = String.valueOf(formatter.format(prayer.maghrib));
        time_isya = String.valueOf(formatter.format(prayer.isha));
        tanggal_terkini = formatTanggal.format(tanggal).toString();
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