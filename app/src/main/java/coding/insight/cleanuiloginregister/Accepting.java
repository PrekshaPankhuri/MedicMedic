package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Accepting extends AppCompatActivity {

    String Name,AppID,UserID,HospitalID,Problem,ProblemDescription,Date,Time,DoctorType,DoctorName;
    EditText problemdata,descriptiondata,datedata,timedata,doctortypedata;
    Button confirm;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        setContentView(R.layout.activity_accepting);
        Name=getIntent().getStringExtra("HospitalName");
        AppID=getIntent().getStringExtra("AppID");
        UserID=getIntent().getStringExtra("UserID");
        HospitalID=getIntent().getStringExtra("HospitalID");
        Problem=getIntent().getStringExtra("Problem");
        ProblemDescription=getIntent().getStringExtra("ProblemDescription");
        Date=getIntent().getStringExtra("Date");
        Time=getIntent().getStringExtra("Time");
        DoctorType=getIntent().getStringExtra("DoctorType");
        DoctorName=getIntent().getStringExtra("DoctorName");

        problemdata=findViewById(R.id.problemdata);
        descriptiondata=findViewById(R.id.descriptiondata);
        datedata=findViewById(R.id.datedata);
        timedata=findViewById(R.id.timedata);
        doctortypedata=findViewById(R.id.doctortypedata);
        confirm=findViewById(R.id.buttonConfirm);

        problemdata.setText(Problem);
        descriptiondata.setText(ProblemDescription);
        datedata.setText(Date);
        timedata.setText(Time);
        doctortypedata.setText(DoctorType);
        
        String stat="Booked";

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref= FirebaseDatabase.getInstance().getReference("BookedAppointments").child(AppID);
                HashMap book=new HashMap();
                book.put("HospitalName", Name);
                book.put("DoctorName",DoctorName);
                book.put("AppId",AppID);
                book.put("UserID",UserID);
                book.put("HospitalID",HospitalID);
                book.put("Problem",Problem);
                book.put("ProblemDescription",ProblemDescription);
                book.put("Date",Date);
                book.put("Time",Time);
                book.put("DoctorType",DoctorType);
                book.put("Status",stat);

                ref.updateChildren(book).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        deletePending(AppID);
                        Intent intent=new Intent(getApplicationContext(),BookedAppointments.class);
                        intent.putExtra("HospitalID",HospitalID);
                        startActivity(intent);
                    }
                });
            }
        });


    }

    private void deletePending(String appID) {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("PendingAppointments")
                .child(AppID);
        databaseReference.removeValue();
    }

    public void onLoginClick(View view){
        startActivity(new Intent(Accepting.this,PendingAppointments.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

}