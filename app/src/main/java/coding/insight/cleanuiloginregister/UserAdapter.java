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

public class UserAdapter extends FirebaseRecyclerAdapter<userModel,UserAdapter.userviewholder>{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirebaseRecyclerOptions<userModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(userviewholder holder, int position, userModel model) {
        holder.UserName.setText(model.getName());
        holder.UserEmail.setText(model.getEmail());
        holder.UserPhone.setText(model.getNumber());


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();

                View v = dialogPlus.getHolderView();
                EditText Name = v.findViewById(R.id.txtName);
                EditText Number = v.findViewById(R.id.txtNumber);

                Button btnUpdate = v.findViewById(R.id.btnuupdate);
                Name.setText(model.getName());
                Number.setText(model.getNumber());

                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Name",Name.getText().toString());
                        map.put("Number",Number.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("users")
                                .child(getRef(holder.getLayoutPosition()).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.UserName.getContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.UserName.getContext(),"Error while Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.UserName.getContext());
                builder.setTitle("Are You Sure???");
                builder.setMessage("Deleted Data can't be Undo!!");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(getRef(holder.getLayoutPosition()).getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.UserName.getContext(),"Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();

            }
        });

    }

    @NonNull
    @Override
    public userviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        return new userviewholder(view);
    }

    class userviewholder extends RecyclerView.ViewHolder{
        TextView UserName,UserEmail,UserPhone;
        Button btnEdit, btnDelete;
        public userviewholder(@NonNull View itemView) {
            super(itemView);
            UserName = (TextView)itemView.findViewById(R.id.UserName);
            UserEmail = (TextView)itemView.findViewById(R.id.UserEmail);
            UserPhone = (TextView)itemView.findViewById(R.id.UserPhone);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button)itemView.findViewById(R.id.btnDelete);
        }
    }

}
