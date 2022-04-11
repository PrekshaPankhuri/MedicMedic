package coding.insight.cleanuiloginregister;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class HospitalInfoPop extends AppCompatActivity {

    Button btnhosupdate;
    private DatabaseReference mDatabase;
    String hospitalId;
    TextView HospitalName;
    EditText mbedit;
    EditText oxedit;
    EditText bbedits;
    EditText covidedit;
    EditText vacedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_info_pop);


        btnhosupdate = findViewById(R.id.btnhosupdate);
        HospitalName = findViewById(R.id.HospitalName);
        mbedit = findViewById(R.id.mbedit);
        oxedit = findViewById(R.id.oxedit);
        bbedits = findViewById(R.id.bbedits);
        covidedit = findViewById(R.id.covidedit);
        vacedit = findViewById(R.id.vacedit);
        hospitalId = getIntent().getStringExtra("ID");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("hospital").child(hospitalId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.e("firebase", String.valueOf(task.getResult().getValue()));
                    HospitalName.setText(Objects.requireNonNull(task.getResult().child("Name").getValue()).toString());
                    mbedit.setText(Objects.requireNonNull(task.getResult().child("Number").getValue()).toString());
                    oxedit.setText(Objects.requireNonNull(task.getResult().child("Cylinders").getValue()).toString());
                    bbedits.setText(Objects.requireNonNull(task.getResult().child("BloodBank").getValue()).toString());
                    covidedit.setText(Objects.requireNonNull(task.getResult().child("Beds").getValue()).toString());
                    vacedit.setText(Objects.requireNonNull(task.getResult().child("VaccineCentre").getValue()).toString());


                }
            }
        });
      btnhosupdate.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Map<String,Object> map = new HashMap<>();
              map.put("Name",HospitalName.getText().toString());
              map.put("Number",mbedit.getText().toString());
              map.put("Beds",covidedit.getText().toString());
              map.put("Cylinders",oxedit.getText().toString());
              map.put("BloodBank",bbedits.getText().toString());
              map.put("VaccineCentre",vacedit.getText().toString());

              mDatabase.child("hospital").child(hospitalId).updateChildren(map, new DatabaseReference.CompletionListener() {
                  @Override
                  public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                      Toast toast = Toast.makeText(HospitalInfoPop.this, "Successfully Updated", LENGTH_SHORT );
                      toast.show();
                  }
              });
          }
      });

    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,HospitalScreen.class));
        overridePendingTransition(R.anim.slide_in_left,R.anim.stay);

    }
}