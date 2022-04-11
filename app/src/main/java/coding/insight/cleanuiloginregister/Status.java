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

public class Status extends AppCompatActivity {

    RecyclerView recyclerView;
    StatusAdapter statusAdapter;
    FirebaseAuth fauth;
    DatabaseReference ref;
    TextView stat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        setContentView(R.layout.activity_status);

        String HosID=getIntent().getStringExtra("HospitalID");

        recyclerView=findViewById(R.id.statusBookedList);
        stat=findViewById(R.id.stats);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fauth=FirebaseAuth.getInstance();

        String user= Objects.requireNonNull(fauth.getCurrentUser()).getUid();

        FirebaseRecyclerOptions<StatusModel> options=new FirebaseRecyclerOptions.Builder<StatusModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("PendingAppointments").orderByChild("UserID").equalTo(user),StatusModel.class)
                .build();

        statusAdapter=new StatusAdapter(options);
        recyclerView.setAdapter(statusAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        statusAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        statusAdapter.stopListening();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,HospitalAppointment.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    public void onRight(View view){
        startActivity(new Intent(getApplicationContext(),StatusBooked.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

}