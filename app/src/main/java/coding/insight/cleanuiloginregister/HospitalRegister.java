package coding.insight.cleanuiloginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Locale;

public class HospitalRegister extends AppCompatActivity {

    EditText HospitalName, HospitalEmail, HospitalNumber, HospitalPassword, HospitalAddress, HospitalCity, HospitalBeds, HospitalCylinders, HospitalBlood, HospitalVaccine;
    Button HospitalRegister;
    FirebaseAuth fauth;
    DatabaseReference userDatabaseRef;
    ProgressDialog loader;
    String us="hospital";
    TextView Haveanacc;
    String low;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_hospital_register);
        changeStatusBarColor();

        HospitalName=findViewById(R.id.HospitalRegName);
        HospitalEmail=findViewById(R.id.HospitalRegEmail);
        HospitalPassword=findViewById(R.id.HospitalRegPassword);
        HospitalNumber=findViewById(R.id.HospitalRegMobile);
        HospitalAddress=findViewById(R.id.HospitalRegAddress);
        HospitalCity=findViewById(R.id.HospitalRegCity);
        HospitalBeds=findViewById(R.id.HospitalRegBeds);
        HospitalBlood=findViewById(R.id.HospitalRegBlood);
        HospitalCylinders=findViewById(R.id.HospitalRegCylinders);
        HospitalVaccine=findViewById(R.id.HospitalRegVaccine);
        HospitalRegister=findViewById(R.id.cirRegisterButtonHospital);
        Haveanacc = findViewById(R.id.Haveanacc);
        fauth=FirebaseAuth.getInstance();
        loader=new ProgressDialog(this);

        int ActNo = getIntent().getIntExtra("Act",1);
        if(ActNo == 1){
            Haveanacc.setVisibility(View.GONE);
        }

        HospitalRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=HospitalName.getText().toString().trim();
                String email=HospitalEmail.getText().toString().trim();
                String password=HospitalPassword.getText().toString().trim();
                String number=HospitalNumber.getText().toString().trim();
                String address=HospitalAddress.getText().toString().trim();
                String city=HospitalCity.getText().toString().trim();
                String beds=HospitalBeds.getText().toString().trim();
                String cylinders=HospitalCylinders.getText().toString().trim();
                String blood=HospitalBlood.getText().toString().trim();
                String vaccine=HospitalVaccine.getText().toString().trim();
                low=city.toLowerCase();

                if(TextUtils.isEmpty(name)){
                    HospitalName.setError("Field is required.");
                }
                if(TextUtils.isEmpty(email)){
                    HospitalEmail.setError("Field is required.");
                }
                if(TextUtils.isEmpty(password)){
                    HospitalPassword.setError("Field is required.");
                }
                if(TextUtils.isEmpty(number)){
                    HospitalNumber.setError("Field is required.");
                }
                if(TextUtils.isEmpty(address)){
                    HospitalAddress.setError("Field is required.");
                }
                if(TextUtils.isEmpty(city)){
                    HospitalCity.setError("Field is required.");
                }
                if(TextUtils.isEmpty(beds)){
                    HospitalBeds.setError("Field is required.");
                }
                if(TextUtils.isEmpty(cylinders)){
                    HospitalCylinders.setError("Field is required.");
                }
                if(TextUtils.isEmpty(blood)){
                    HospitalBlood.setError("Field is required.");
                }
                if(TextUtils.isEmpty(vaccine)){
                    HospitalVaccine.setError("Field is required.");
                }
                if(password.length()<6){
                    HospitalPassword.setError("Should be at least 6 digits.");
                }
                else{
                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                loader.dismiss();
                                Toast.makeText(HospitalRegister.this,"Error occurred.",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                String currentUserId=fauth.getCurrentUser().getUid();
                                userDatabaseRef=FirebaseDatabase.getInstance().getReference().child("hospital").child(currentUserId);
                                HashMap userInfo=new HashMap();
                                userInfo.put("ID",currentUserId);
                                userInfo.put("Name",name);
                                userInfo.put("Email",email);
                                userInfo.put("Password",password);
                                userInfo.put("Address",address);
                                userInfo.put("City",city);
                                userInfo.put("Number",number);
                                userInfo.put("Beds",beds);
                                userInfo.put("Cylinders",cylinders);
                                userInfo.put("BloodBank",blood);
                                userInfo.put("VaccineCentre",vaccine);
                                userInfo.put("type",us);
                                userInfo.put("Search",low);

                                userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(HospitalRegister.this,"User Created.",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),HospitalScreen.class));
                                            loader.dismiss();
                                            finish();
                                        }else{
                                            Toast.makeText(HospitalRegister.this,"Try again.",Toast.LENGTH_SHORT).show();
                                            loader.dismiss();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View View){
        onBackPressed();
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
}
