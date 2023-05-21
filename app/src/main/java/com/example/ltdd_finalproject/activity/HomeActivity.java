package com.example.ltdd_finalproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.Button;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.databinding.ActivityMain2Binding;
import com.example.ltdd_finalproject.fragment.BookingFragment;
import com.example.ltdd_finalproject.fragment.HomeFragment;
import com.example.ltdd_finalproject.fragment.ProfileFragment;
import com.example.ltdd_finalproject.models.Customer;
import com.example.ltdd_finalproject.adapters.ProfileAdapter;

public class HomeActivity extends AppCompatActivity implements ProfileAdapter.ProfileCallback {
    ActivityMain2Binding binding;
    Button allTourBtn, buttonHotel;
    Button allVehicleBtn;
    String username;
    Bundle bundle = new Bundle();
    Customer customer;
    private ProfileAdapter profileAdapter;

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        username = getIntent().getStringExtra("username");

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        profileAdapter = new ProfileAdapter(); // Initialize the profileAdapter object
        getProfile(username);

        // binding.bottomnavigation.setOnItemSelectedListener(item -> {
        //     switch (item.getItemId()) {
        //         case R.id.navigation_home:
        //             HomeFragment homefragment = new HomeFragment();
        //             homefragment.setArguments(bundle);
        //             getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homefragment).commit();
        //             break;
        //         case R.id.navigation_profile:
        //             ProfileFragment profilefragment= new ProfileFragment();
        //             profilefragment.setArguments(bundle);
        //             customer = profileAdapter.getCustomer();
        //             Log.d("customerIDa", customer.getCustomerId());
        //             SharedPreferences.getInstance(getApplicationContext()).saveCustomer(customer);
        //             getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, profilefragment).commit();

        //             break;
        //         case R.id.navigation_booking:
        //             BookingFragment bookingfragment = new BookingFragment();
        //             bookingfragment.setArguments(bundle);
        //             getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bookingfragment).commit();
        //             break;
        //     }
        //     return true;
        // });

        Intent intent = getIntent();
        String fragmentName = intent.getStringExtra("fragment");
        if (fragmentName != null && fragmentName.equals("booking_fragment")) {
            BookingFragment fragment = new BookingFragment();
            fragment.setArguments(intent.getExtras());
            replaceFragment(fragment);
        }
    }

    private void getProfile(String username) {
        profileAdapter.profile(username, this);
        Log.d("getProfile", "Customer object is null");
    }

    @Override
    public void onProfileSuccess(String message, String customerId, String fullName, String email,
                                 String phoneNumber, String imageLink, String address, boolean gender, String birthDay) {
        customer = new Customer(customerId, fullName, email, phoneNumber, imageLink, address, gender, birthDay);
        bundle.putSerializable("customer", customer);
        bundle.putSerializable ("username",username);
        bundle.putSerializable("customer_id", customerId);
        //Bundle bundle = new Bundle();
        //bundle.putSerializable("customer", customer);

        // create a new instance of the ProfileFragment
        //ProfileFragment profileFragment = new ProfileFragment();

        // set the arguments of the ProfileFragment to the Bundle
        //profileFragment.setArguments(bundle);

        // replace the current fragment with the ProfileFragment
        //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, profileFragment).commit();
        binding.bottomnavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homefragment = new HomeFragment();
                    homefragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homefragment).commit();
                    break;
                case R.id.navigation_profile:
                    ProfileFragment profilefragment= new ProfileFragment();
                    profilefragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, profilefragment).commit();
                    break;
                case R.id.navigation_booking:
                    BookingFragment bookingfragment = new BookingFragment();
                    bookingfragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, bookingfragment).commit();
                    break;
            }
            return true;
        });
        Log.d("onProfileSuccess", "Customer object is null");
    }

    @Override
    public void onProfileFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        Log.d("onProfileFailure", "Customer object is null");
    }
}