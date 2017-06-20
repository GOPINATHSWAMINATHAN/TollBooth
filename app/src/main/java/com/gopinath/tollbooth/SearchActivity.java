package com.gopinath.tollbooth;

import android.content.Context;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.view.View.VISIBLE;

public class SearchActivity extends AppCompatActivity {
    AutoCompleteTextView actv, actv1;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ArrayList<SaveData> places = new ArrayList<SaveData>();
    TextView td1, td2, details, destinations, triptype, vehiclenos, vehicletyps, totalamount;
    EditText ed1;
    Button search;
    String source, destination, phoneno, triptypes, vehicleno, vehicletypes;
    private final String TAG = "MYTAG";
    String name;
    TextView t1, t2, t3, t4, t5, t6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final List<SaveData> tolldetails = new ArrayList<SaveData>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = (Button) findViewById(R.id.button2);

//        td1=(TextView)findViewById(R.id.source_text);
//        td2=(TextView)findViewById(R.id.destination_text);
        ed1 = (EditText) findViewById(R.id.vehicle_nos);
        details = (TextView) findViewById(R.id.details);
        t1 = (TextView) findViewById(R.id.t1);
        t2 = (TextView) findViewById(R.id.t2);
        t3 = (TextView) findViewById(R.id.t3);
        t4 = (TextView) findViewById(R.id.t4);
        t5 = (TextView) findViewById(R.id.t5);
        t6 = (TextView) findViewById(R.id.t6);
        destinations = (TextView) findViewById(R.id.destination);
        triptype = (TextView) findViewById(R.id.triptype);
        vehiclenos = (TextView) findViewById(R.id.vehicleno_textviews);
        vehicletyps = (TextView) findViewById(R.id.vehicletypes_textviews);
        totalamount = (TextView) findViewById(R.id.totalamount_textviews);
        details.setVisibility(View.INVISIBLE);
        destinations.setVisibility(View.INVISIBLE);
        triptype.setVisibility(View.INVISIBLE);
        vehiclenos.setVisibility(View.INVISIBLE);
        vehicletyps.setVisibility(View.INVISIBLE);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference(ed1.getText().toString()).getRef();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Toast.makeText(getApplicationContext(), "C" + dataSnapshot.child("source").getValue(), Toast.LENGTH_LONG).show();
                        try {


                            details.setText(dataSnapshot.child("source").getValue().toString());
                            destinations.setText(dataSnapshot.child("destination").getValue().toString());
                            triptype.setText(dataSnapshot.child("tripTypes").getValue().toString());
                            vehiclenos.setText(dataSnapshot.child("vehicleno").getValue().toString());
                            vehicletyps.setText(dataSnapshot.child("vehicletype").getValue().toString());
                            totalamount.setText(dataSnapshot.child("totalamount").getValue().toString());
                            t1.setVisibility(View.VISIBLE);
                            t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.VISIBLE);
                            t5.setVisibility(View.VISIBLE);
                            t6.setVisibility(View.VISIBLE);

                            details.setVisibility(View.VISIBLE);
                            destinations.setVisibility(View.VISIBLE);
                            triptype.setVisibility(View.VISIBLE);
                            vehiclenos.setVisibility(View.VISIBLE);
                            vehicletyps.setVisibility(View.VISIBLE);
                            totalamount.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Records are not available for given vehicle no", Toast.LENGTH_LONG).show();
                            t1.setVisibility(View.INVISIBLE);
                            t2.setVisibility(View.INVISIBLE);
                            t3.setVisibility(View.INVISIBLE);
                            t4.setVisibility(View.INVISIBLE);
                            t5.setVisibility(View.INVISIBLE);
                            t6.setVisibility(View.INVISIBLE);
                            details.setVisibility(View.INVISIBLE);
                            destinations.setVisibility(View.INVISIBLE);
                            triptype.setVisibility(View.INVISIBLE);
                            vehiclenos.setVisibility(View.INVISIBLE);
                            vehicletyps.setVisibility(View.INVISIBLE);
                            totalamount.setVisibility(View.INVISIBLE);

                        }


                        // Toast.makeText(getApplicationContext(), "C" + p.child("destination").getValue(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}


