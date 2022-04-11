package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BookedAppointments extends AppCompatActivity {

    String HospitalName,ID;
    RecyclerView recyclerView;
    FinalAdapter finalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_appointments);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        ID=getIntent().getStringExtra("HospitalID");

        recyclerView=findViewById(R.id.finalList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FinalModel> options=new FirebaseRecyclerOptions.Builder<FinalModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("BookedAppointments").orderByChild("HospitalID").equalTo(ID),FinalModel.class)
                .build();

        finalAdapter=new FinalAdapter(options);
        recyclerView.setAdapter(finalAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        finalAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finalAdapter.stopListening();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(getApplicationContext(),AcceptAppointments.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}