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
import java.util.Locale;

public class COVIDBeds extends AppCompatActivity {

    RecyclerView recyclerView;
    BedAdapter bedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidbeds);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#027876"));
        actionBar.setBackgroundDrawable(colorDrawable);

        recyclerView=findViewById(R.id.bedsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BedModel> options=new FirebaseRecyclerOptions.Builder<BedModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hospital"),BedModel.class)
                .build();

        bedAdapter=new BedAdapter(options);
        recyclerView.setAdapter(bedAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        bedAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bedAdapter.stopListening();
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

        FirebaseRecyclerOptions<BedModel> options=new FirebaseRecyclerOptions.Builder<BedModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hospital").orderByChild("Search").startAt(q).endAt(q+"\uf8ff"),BedModel.class)
                .build();

        bedAdapter=new BedAdapter(options);
        bedAdapter.startListening();
        recyclerView.setAdapter(bedAdapter);

    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,CovidHelp.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}