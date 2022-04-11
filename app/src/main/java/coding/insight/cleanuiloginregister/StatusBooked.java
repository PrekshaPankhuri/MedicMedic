package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class StatusBooked extends AppCompatActivity {

    RecyclerView recyclerView;
    StatusBookedAdapter statusBookedAdapter;
    FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_booked);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        recyclerView=findViewById(R.id.statusBookedList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FAuth=FirebaseAuth.getInstance();

        String user= Objects.requireNonNull(FAuth.getCurrentUser()).getUid();

        FirebaseRecyclerOptions<StatusBookedModel> options=new FirebaseRecyclerOptions.Builder<StatusBookedModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference("BookedAppointments").orderByChild("UserID").equalTo(user),StatusBookedModel.class)
                .build();

        statusBookedAdapter=new StatusBookedAdapter(options);
        recyclerView.setAdapter(statusBookedAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        statusBookedAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        statusBookedAdapter.stopListening();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,Status.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}