package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Oxygen extends AppCompatActivity {

    RecyclerView recyclerView;
    O2Adapter o2Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#027876"));
        actionBar.setBackgroundDrawable(colorDrawable);

        recyclerView=findViewById(R.id.oxygenList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<OxygenModel> options=new FirebaseRecyclerOptions.Builder<OxygenModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hospital"),OxygenModel.class)
                .build();

        o2Adapter=new O2Adapter(options);
        recyclerView.setAdapter(o2Adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        o2Adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        o2Adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("Seach City...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String query) {

        String q=query.toLowerCase();

        FirebaseRecyclerOptions<OxygenModel> options=new FirebaseRecyclerOptions.Builder<OxygenModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hospital").orderByChild("Search").startAt(q).endAt(q+"\uf8ff"),OxygenModel.class)
                .build();

        o2Adapter=new O2Adapter(options);
        o2Adapter.startListening();
        recyclerView.setAdapter(o2Adapter);
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,CovidHelp.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}