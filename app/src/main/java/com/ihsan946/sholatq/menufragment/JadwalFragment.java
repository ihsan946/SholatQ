package com.ihsan946.sholatq.menufragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihsan946.sholatq.R;
import com.ihsan946.sholatq.api.ApiEndpointJadwal;
import com.ihsan946.sholatq.api.ApiEndpointLokasi;
import com.ihsan946.sholatq.api.ApiServiceJadwal;
import com.ihsan946.sholatq.api.ApiServiceLokasi;
import com.ihsan946.sholatq.model.ApimodelJadwal;
import com.ihsan946.sholatq.model.ApimodelLokasi;
import com.ihsan946.sholatq.model.DatetimeModel;
import com.ihsan946.sholatq.sharedpreferenced.Preference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JadwalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JadwalFragment extends Fragment {

    private List<DatetimeModel> items = new ArrayList<>();

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
        getJadwalSholat();

        return view;
    }

    public void getJadwalSholat(){
        String nama_kota = Preference.getNamaKota(getActivity());

        ApiServiceJadwal api = ApiEndpointJadwal.getClient().create(ApiServiceJadwal.class);

        Call<ApimodelJadwal> call = api.getJadwal(nama_kota);
        call.enqueue(new Callback<ApimodelJadwal>() {
            @Override
            public void onResponse(Call<ApimodelJadwal> call, Response<ApimodelJadwal> response) {
                items = response.body().result;
                Log.d("result",items.toString());
            }

            @Override
            public void onFailure(Call<ApimodelJadwal> call, Throwable t) {

            }
        });



    }



}