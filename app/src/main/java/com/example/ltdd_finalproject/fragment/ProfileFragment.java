package com.example.ltdd_finalproject.fragment;
import 	android.util.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ltdd_finalproject.R;
import com.example.ltdd_finalproject.adapters.ProfileAdapter;

import androidx.fragment.app.Fragment;

import android.widget.TextView;
import android.widget.Toast;

import com.example.ltdd_finalproject.models.Customer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Customer customer;
    String username;
    private TextView customerIdTextView, fullNameTextView, emailTextView, genderTextView,
            birthDayTextView, addressTextView, phoneNumberTextView;
    private ProfileAdapter profileAdapter;

    public ProfileFragment() {
        // Required empty public constructor
    }
    ///

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void setProfile(Customer customer) {

        customerIdTextView.setText(customer.getCustomerId());
        fullNameTextView.setText(customer.getFullName());
        emailTextView.setText(customer.getEmail());
        genderTextView.setText(customer.isGender() ? "Male" : "Female");
        birthDayTextView.setText(customer.getBirthDay());
        addressTextView.setText(customer.getAddress());
        phoneNumberTextView.setText(customer.getPhoneNumber());
        Log.d("ProfileFragment", "Customer object is null");
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
    View view = inflater.inflate(R.layout.fragment_profile, container, false);
    customerIdTextView = view.findViewById(R.id.customerId);
    fullNameTextView = view.findViewById(R.id.textViewfullName);
    emailTextView = view.findViewById(R.id.textViewEmail);
    genderTextView = view.findViewById(R.id.textViewGender);
    birthDayTextView = view.findViewById(R.id.textViewbirthDay);
    addressTextView = view.findViewById(R.id.textViewaddress);
    phoneNumberTextView = view.findViewById(R.id.textViewphoneNumber);
    Bundle bundle = getArguments();


    if (bundle != null) {
        // retrieve the Customer object from the Bundle
        customer = (Customer) bundle.getSerializable("customer");
        username =  (String) bundle.getSerializable("username");
        if (customer != null) {
            // do something with the Customer object
            setProfile(customer);
        } else {
            // handle the case where the customer object is null
            Toast.makeText(getContext(), "Customer object is null", Toast.LENGTH_SHORT).show();
        }
    }
   // profileAdapter = new ProfileAdapter();
    /////Chinh sua lai sau
    //String username = customer;
    //getProfile(username);
    // Inflate the layout for this fragment
    return view;
}
}