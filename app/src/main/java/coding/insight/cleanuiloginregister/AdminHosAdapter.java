package coding.insight.cleanuiloginregister;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class AdminHosAdapter extends FirebaseRecyclerAdapter<AdminHodpitalModel, AdminHosAdapter.Ahosviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdminHosAdapter(@NonNull FirebaseRecyclerOptions<AdminHodpitalModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Ahosviewholder holder, int position, @NonNull AdminHodpitalModel model) {
        holder.Name.setText(model.getName());
        holder.Email.setText(model.getEmail());
        holder.Number.setText(model.getNumber());
        holder.Address.setText(model.getAddress());
        holder.City.setText(model.getCity());
        holder.Beds.setText(model.getBeds());
        holder.Cylinders.setText(model.getCylinders());
        holder.BloodBank.setText(model.getBloodBank());

        holder.EditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.popup_update_hospital))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                EditText HospitalName = v.findViewById(R.id.HosName);
                EditText HospitalNumber = v.findViewById(R.id.HosNumber);
                EditText HospitalBeds = v.findViewById(R.id.HosBed);
                EditText HospitaCylinders = v.findViewById(R.id.HosCyl);
                EditText HospitalBB = v.findViewById(R.id.HosBB);
                EditText HospitalAddress = v.findViewById(R.id.HosAddr);
                EditText HospitalCity = v.findViewById(R.id.HosCity);

                Button btnUpdate = v.findViewById(R.id.btnuupdate);
                HospitalName.setText(model.getName());
                HospitalNumber.setText(model.getNumber());
                HospitalBeds.setText(model.getBeds());
                HospitaCylinders.setText(model.getCylinders());
                HospitalBB.setText(model.getBloodBank());
                HospitalAddress.setText(model.getAddress());
                HospitalCity.setText(model.getCity());



                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Name",HospitalName.getText().toString());
                        map.put("Number",HospitalNumber.getText().toString());
                        map.put("Address",HospitalAddress.getText().toString());
                        map.put("City",HospitalCity.getText().toString());
                        map.put("Beds",HospitalBeds.getText().toString());
                        map.put("Cylinders",HospitaCylinders.getText().toString());
                        map.put("BloodBank",HospitalBB.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("hospital")
                                .child(getRef(holder.getLayoutPosition()).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.Name.getContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.Name.getContext(),"Error while Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });
            }
        });

        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.Name.getContext());
                builder.setTitle("Are You Sure???");
                builder.setMessage("Deleted Data can't be Undo!!");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("hospital").child(getRef(holder.getLayoutPosition()).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.Name.getContext(),"Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();

            }
        });
    }

    @NonNull
    @Override
    public Ahosviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adminhos_item,parent,false);
        return new Ahosviewholder(view);

    }

    class Ahosviewholder extends RecyclerView.ViewHolder{
        TextView Name, Email, Number, Address, City, Beds, Cylinders, BloodBank;
        Button EditButton, DeleteButton;

        public Ahosviewholder(@NonNull View itemView) {
            super(itemView);

            Name = (TextView)itemView.findViewById(R.id.AHosName);
            Email = (TextView)itemView.findViewById(R.id.AHosEmail);
            Number = (TextView)itemView.findViewById(R.id.AHosNumber);
            Address = (TextView)itemView.findViewById(R.id.AHosAddress);
            City = (TextView)itemView.findViewById(R.id.AHosCity);
            Beds = (TextView)itemView.findViewById(R.id.AHosBeds);
            Cylinders = (TextView)itemView.findViewById(R.id.AHosCylinder);
            BloodBank = (TextView)itemView.findViewById(R.id.AHosBB);
            EditButton=itemView.findViewById(R.id.btnEdit);
            DeleteButton=itemView.findViewById(R.id.btnDelete);




        }
    }
}
