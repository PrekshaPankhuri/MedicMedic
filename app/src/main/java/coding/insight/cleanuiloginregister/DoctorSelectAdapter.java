package coding.insight.cleanuiloginregister;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class DoctorSelectAdapter extends FirebaseRecyclerAdapter<DoctorSelectModel,DoctorSelectAdapter.DoctorSelectViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DoctorSelectAdapter(@NonNull FirebaseRecyclerOptions<DoctorSelectModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DoctorSelectViewHolder holder, int position, @NonNull DoctorSelectModel model) {

        holder.Name.setText(model.getName());
        holder.Specification.setText(model.getSpecification());
        holder.DoctorTime.setText(model.getDoctorTime());
        holder.Number.setText(model.getNumber());
        holder.Expertise.setText(model.getExpertise());
        holder.Hospital.setText(model.getHospital());

        holder.fillForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),Form.class);
                intent.putExtra("ID",model.getID());
                intent.putExtra("Name",model.getName());
                intent.putExtra("Hospital",model.getHospital());
                v.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public DoctorSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list,parent,false);
        return new DoctorSelectViewHolder(view);
    }

    public static class DoctorSelectViewHolder extends RecyclerView.ViewHolder {

        TextView Name,Hospital,DoctorTime,Expertise,Specification,Number;
        Button fillForm;

        public DoctorSelectViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.DoctorName);
            Hospital=itemView.findViewById(R.id.hospital);
            DoctorTime=itemView.findViewById(R.id.doctorTime);
            Expertise=itemView.findViewById(R.id.expertise);
            Specification=itemView.findViewById(R.id.specification);
            Number=itemView.findViewById(R.id.DoctorNumber);
            fillForm=itemView.findViewById(R.id.fillForm);

        }
    }
}
