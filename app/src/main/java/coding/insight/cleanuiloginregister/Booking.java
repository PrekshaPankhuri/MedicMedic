package coding.insight.cleanuiloginregister;

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
import com.google.firebase.database.FirebaseDatabase;

public class Booking extends AppCompatActivity {

    RecyclerView recyclerView;
    BookingAdapter bookingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#027876"));
        actionBar.setBackgroundDrawable(colorDrawable);

        recyclerView=findViewById(R.id.HospitalList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<BookingModel> options=new FirebaseRecyclerOptions.Builder<BookingModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hospital"),BookingModel.class)
                .build();

        bookingAdapter=new BookingAdapter(options);
        recyclerView.setAdapter(bookingAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        bookingAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookingAdapter.stopListening();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search,menu);

        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("Search City...");

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

        FirebaseRecyclerOptions<BookingModel> options=new FirebaseRecyclerOptions.Builder<BookingModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("hospital").orderByChild("Search").startAt(q).endAt(q+"\uf8ff"),BookingModel.class)
                .build();

        bookingAdapter=new BookingAdapter(options);
        bookingAdapter.startListening();
        recyclerView.setAdapter(bookingAdapter);

    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,HospitalAppointment.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}