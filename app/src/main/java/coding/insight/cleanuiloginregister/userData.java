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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class userData extends AppCompatActivity {
    RecyclerView recyclerview;
    UserAdapter useradapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#027876"));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);


        recyclerview = (RecyclerView)findViewById(R.id.rv);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<userModel> options =
                new FirebaseRecyclerOptions.Builder<userModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users"), userModel.class).build();


        useradapter = new UserAdapter(options);
        recyclerview.setAdapter(useradapter);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(userData.this, RegisterActivity.class);
                intent.putExtra("Act",1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        useradapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        useradapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){
        FirebaseRecyclerOptions<userModel> options =
                new FirebaseRecyclerOptions.Builder<userModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("users").orderByChild("Name").startAt(str).endAt(str+"~"), userModel.class).build();

        useradapter = new UserAdapter(options);
        useradapter.startListening();
        recyclerview.setAdapter(useradapter);
    }


    public void onLoginClick(View view) {
        onBackPressed();
    }

}
