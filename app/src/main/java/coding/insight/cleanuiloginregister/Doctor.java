package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Doctor extends AppCompatActivity {

    String Id,Name;
    RecyclerView recyclerView;
    DoctorSelectAdapter doctorSelectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        Id=getIntent().getStringExtra("ID");
        Name=getIntent().getStringExtra("Name");
        recyclerView=findViewById(R.id.DoctorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<DoctorSelectModel> options=new FirebaseRecyclerOptions.Builder<DoctorSelectModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("doctors").orderByChild("Hospital").equalTo(Name),DoctorSelectModel.class)
                .build();

        doctorSelectAdapter=new DoctorSelectAdapter(options);
        recyclerView.setAdapter(doctorSelectAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        doctorSelectAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        doctorSelectAdapter.stopListening();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,Booking.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}