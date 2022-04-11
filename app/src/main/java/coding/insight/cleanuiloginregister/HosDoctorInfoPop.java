package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class HosDoctorInfoPop extends AppCompatActivity {
    RecyclerView recyclerview;
    DoctorAdapter doctorAdapter;
    FloatingActionButton addDoc;
    String HosName="Regency Hospital";
    TextView HospitalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hos_doctor_info_pop);
        HosName=getIntent().getStringExtra("HosName");


        HospitalText = findViewById(R.id.HospitalName);
        HospitalText.setText(HosName);



        recyclerview = (RecyclerView)findViewById(R.id.rv_doctor);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        addDoc = findViewById(R.id.floatingActionButton);

        FirebaseRecyclerOptions<DoctorModel> options =
                new FirebaseRecyclerOptions.Builder<DoctorModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doctors").orderByChild("Hospital").equalTo(HosName), DoctorModel.class).build();

        doctorAdapter = new DoctorAdapter(options);
        recyclerview.setAdapter(doctorAdapter);


        addDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HosDoctorInfoPop.this, RegisterDoctor.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        doctorAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        doctorAdapter.stopListening();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(HosDoctorInfoPop.this,HospitalScreen.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}