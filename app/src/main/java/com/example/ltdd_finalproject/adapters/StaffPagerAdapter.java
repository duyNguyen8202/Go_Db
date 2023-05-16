package com.example.ltdd_finalproject.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ltdd_finalproject.Fragment.DoanhThuFragment;
import com.example.ltdd_finalproject.Fragment.HotelFragment;
import com.example.ltdd_finalproject.Fragment.PlaceFragment;
import com.example.ltdd_finalproject.Fragment.RoomFragment;
import com.example.ltdd_finalproject.Fragment.TourFragment;
import com.example.ltdd_finalproject.Fragment.VehicleFragment;

public class StaffPagerAdapter extends FragmentPagerAdapter {

    private final String[] tabTitles = new String[]{"Doanh thu", "Tour", "Hotel", "Room", "Vehicle", "Place"};
//    private final String[] tabTitles = new String[]{"Doanh thu"};

    public StaffPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DoanhThuFragment();
            case 1:
                return new TourFragment();
            case 2:
                return new HotelFragment();
            case 3:
                return new RoomFragment();
            case 4:
                return new VehicleFragment();
            case 5:
                return new PlaceFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
