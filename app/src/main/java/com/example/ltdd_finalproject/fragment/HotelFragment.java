package com.example.ltdd_finalproject.fragment;

        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.ltdd_finalproject.R;
        import com.example.ltdd_finalproject.adapters.HotelAdapter;
        import com.example.ltdd_finalproject.models.Hotel;

        import java.util.List;

public class HotelFragment extends Fragment {

    private final List<Hotel> hotelList;
    private RecyclerView recyclerView;
    private HotelAdapter adapter;

    public HotelFragment(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

        // Thiết lập RecyclerView
        recyclerView = view.findViewById(R.id.doanh_thu_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new HotelAdapter(requireContext(), hotelList, R.id.hotel_item1);
//        recyclerView.setAdapter(adapter);

        return view;
    }

}
