package coding.insight.cleanuiloginregister;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class DoctorsUpdate extends AppCompatActivity {
    ImageView adddoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_update);

        adddoc = findViewById(R.id.imageadd);
        adddoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorsUpdate.this, RegisterDoctor.class);
                startActivity(intent);
            }
        });
    }
}