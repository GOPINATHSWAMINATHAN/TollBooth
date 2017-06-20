package com.gopinath.tollbooth;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TollPay extends AppCompatActivity {
    EditText source, destination, vehicle_number;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth firebaseAuth;
    RadioGroup tripTypes;
    RadioButton findTypes;
    TextInputLayout inputsource, inputdestination, input_vehicle_type, input_vehicle_number;
    Button pay, searchplaces;
    String trippingTypes;
    String phoneNo = SignUpActivity.mobile_number;
    String source_place;
    String destination_place;
    String vehiclenos;
    int totalAmount;
    String vehicle;
    String vehicletypes;
    Spinner spin;
    List lt = new ArrayList();
    Boolean a = false;
    SaveData sd;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toll_pay);
        inputsource = (TextInputLayout) findViewById(R.id.inputsource);
        inputdestination = (TextInputLayout) findViewById(R.id.inputdestination);
        spin = (Spinner) findViewById(R.id.spin_data);
        lt.add("Two Wheeler");
        lt.add("Four Wheeler");
        lt.add("Multi-axle Vehicle");
        lt.add("Truck");
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lt);
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vehicletypes = spin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        input_vehicle_number = (TextInputLayout) findViewById(R.id.inputvehicleno);
        source = (EditText) findViewById(R.id.source);
        destination = (EditText) findViewById(R.id.destination);

        vehicle_number = (EditText) findViewById(R.id.vehiclenumber);
        tripTypes = (RadioGroup) findViewById(R.id.trip_type);
        firebaseAuth = firebaseAuth.getInstance();
        pay = (Button) findViewById(R.id.pay);
        searchplaces = (Button) findViewById(R.id.search_for_places);
        searchplaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    source_place = source.getText().toString().trim();
                    destination_place = destination.getText().toString().trim();
                    vehiclenos = vehicle_number.getText().toString().trim();
                    int selectedId = tripTypes.getCheckedRadioButtonId();
                    findTypes = (RadioButton) findViewById(selectedId);
                    trippingTypes = findTypes.getText().toString();


                    if (trippingTypes.equals("ONE-WAY") && vehicletypes.equals("Two Wheeler")) {

                        totalAmount = 200;
                    } else if (trippingTypes.equals("ONE-WAY") && vehicletypes.equals("Four Wheeler")) {

                        totalAmount = 400;
                    } else if (trippingTypes.equals("TWO-WAY") && vehicletypes.equals("Two Wheeler")) {

                        totalAmount = 300;
                    } else if (trippingTypes.equals("TWO-WAY") && vehicletypes.equals("Four Wheeler")) {

                        totalAmount = 500;

                    } else if (trippingTypes.equals("ONE-WAY") && vehicletypes.equals("Multi-axle Vehicle")) {

                        totalAmount = 1000;

                    } else if (trippingTypes.equals("TWO-WAY") && vehicletypes.equals("Multi-axle Vehicle")) {

                        totalAmount = 1500;

                    } else if (trippingTypes.equals("ONE-WAY") && vehicletypes.equals("Truck")) {

                        totalAmount = 1500;

                    } else if (trippingTypes.equals("TWO-WAY") && vehicletypes.equals("Truck")) {

                        totalAmount = 2500;
                    } else {

                        Toast.makeText(getApplicationContext(), "Please give the values properly", Toast.LENGTH_LONG).show();
                    }

                    trippingTypes = findTypes.getText().toString();

                    sd = new SaveData(source_place, destination_place, vehicletypes, vehiclenos, trippingTypes, phoneNo,totalAmount);
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    DatabaseReference databaseReference = database.getReference("vehicle").getRef();

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            flag = 0;
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                if (child.getValue().equals(vehiclenos)) {
                                    myRef.child(vehiclenos).setValue(sd);
                                    flag = 1;
                                    Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNo));
                                    intent.putExtra("sms_body", "source place is  " + source_place + "  destination place is  " + destination_place + "  vehicle type is  " + vehicletypes + "  vehicle no is  " + vehiclenos + "  Total Amount is = " + totalAmount);
                                    startActivity(intent);
                                }


                            }
                            if (flag == 0)
                                Toast.makeText(getApplicationContext(), " Invalid Vehicle no.", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please Enter all the fields", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


}
