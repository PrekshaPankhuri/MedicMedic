package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class PendingAppointments extends AppCompatActivity {

    RecyclerView recyclerView;
    PendingAdapter pendingAdapter;
    FirebaseAuth fauth;
    String HospitalName,ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_appointments);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        HospitalName = getIntent().getStringExtra("HospitalName");

        recyclerView=findViewById(R.id.pendingapplist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<PendingModel> options=new FirebaseRecyclerOptions.Builder<PendingModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("PendingAppointments").orderByChild("HospitalName").equalTo(HospitalName),PendingModel.class)
                .build();

        pendingAdapter=new PendingAdapter(options);
        recyclerView.setAdapter(pendingAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        pendingAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pendingAdapter.stopListening();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(getApplicationContext(),AcceptAppointments.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}