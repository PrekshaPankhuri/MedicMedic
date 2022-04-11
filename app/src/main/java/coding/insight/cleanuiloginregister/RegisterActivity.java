package coding.insight.cleanuiloginregister;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {

    EditText userName,userNumber,userEmail,userPassword;
    Button userRegister;
    FirebaseAuth fauth;
    DatabaseReference userDatabaseRef;
    ProgressDialog loader;
    String us="user";
    TextView RegisterHos,AHaveAnacc, mainLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();


        RegisterHos=findViewById(R.id.RegisterHos);
        AHaveAnacc=findViewById(R.id.AHaveAnacc);
        userName=findViewById(R.id.RegName);
        userEmail=findViewById(R.id.RegEmail);
        userNumber=findViewById(R.id.RegMobile);
        userPassword=findViewById(R.id.RegPassword);
        userRegister=findViewById(R.id.cirRegisterButtonUser);
        mainLabel = findViewById(R.id.mainLabel);
        fauth=FirebaseAuth.getInstance();
        loader=new ProgressDialog(this);

        int ActNo = getIntent().getIntExtra("Act",1);
        if(ActNo == 1){
            AHaveAnacc.setVisibility(View.GONE);
            RegisterHos.setVisibility(View.GONE);
            mainLabel.setText("Add New User");
        }

        userRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=userName.getText().toString().trim();
                String email=userEmail.getText().toString().trim();
                String number=userNumber.getText().toString().trim();
                String password=userPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    userName.setError("Field is required.");
                }
                if(TextUtils.isEmpty(email)){
                    userEmail.setError("Field is required.");
                }
                if(TextUtils.isEmpty(number)){
                    userNumber.setError("Field is required.");
                }
                if(TextUtils.isEmpty(password)){
                    userPassword.setError("Field is required.");
                }
                if(password.length()<6){
                    userPassword.setError("Should be at least 6 digits.");
                }
                else{
                    loader.setMessage("Registering You...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();
                    fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Error...!",Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }else {
                                String currentUserID = fauth.getCurrentUser().getUid();
                                userDatabaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUserID);
                                HashMap userInfo = new HashMap();
                                userInfo.put("ID", currentUserID);
                                userInfo.put("Name", name);
                                userInfo.put("Email", email);
                                userInfo.put("Number", number);
                                userInfo.put("Password", password);
                                userInfo.put("type", us);

                                userDatabaseRef.updateChildren(userInfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "User Created...!", Toast.LENGTH_SHORT).show();
                                            onBackPressed();
                                            loader.dismiss();
                                            finish();
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Error. Try again...!", Toast.LENGTH_SHORT).show();
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

    public void onLoginClick(View view){
        onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
    public void onRegisterClick(View view){
        Intent intent = new Intent(RegisterActivity.this,HospitalRegister.class);
        intent.putExtra("Act",2);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }



}
