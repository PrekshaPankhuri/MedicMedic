package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HospitalScreen extends AppCompatActivity {
    Button hosinfo,book,Logout;
    String HospitalID, HospitalName,ID;
    Button DoctorInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_screen);

        hosinfo = findViewById(R.id.buttonhosinfo);
        DoctorInfo = findViewById(R.id.buttondoct);
        Logout=findViewById(R.id.buttonlogout);


        HospitalID = getIntent().getStringExtra("ID");
        HospitalName = getIntent().getStringExtra("Name");
        book=findViewById(R.id.bookings);

        hosinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HospitalScreen.this, HospitalInfoPop.class);
                intent.putExtra("ID",HospitalID);
                startActivity(intent);
            }
        });


        DoctorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HospitalScreen.this, HosDoctorInfoPop.class);
                Log.e("HospitalName1",HospitalName);
                intent.putExtra("HosName",HospitalName);
                startActivity(intent);
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalScreen.this, AcceptAppointments.class);
                intent.putExtra("HospitalName",HospitalName);
                intent.putExtra("ID",HospitalID);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

    }



}