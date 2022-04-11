package coding.insight.cleanuiloginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class StatusAdapter extends FirebaseRecyclerAdapter<StatusModel,StatusAdapter.StatusViewHolder> {
    String userID,hospitalID,ID;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public StatusAdapter(@NonNull FirebaseRecyclerOptions<StatusModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StatusViewHolder holder, int position, @NonNull StatusModel model) {
        holder.Problem.setText(model.getProblem());
        holder.ProblemDescription.setText(model.getProblemDescription());
        holder.DoctorType.setText(model.getDoctorType());
        holder.Date.setText(model.getDate());
        holder.Time.setText(model.getTime());
        holder.HospitalName.setText(model.getHospitalName());
        holder.Name.setText(model.getDoctorName());


    }

    @NonNull
    @Override
    public StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.status_list,parent,false);
        return new StatusViewHolder(view);
    }

    public static class StatusViewHolder extends RecyclerView.ViewHolder {

        TextView Problem,ProblemDescription,DoctorType,Date,Time,HospitalName,Name;

        public StatusViewHolder(@NonNull View itemView) {
            super(itemView);

            Problem=itemView.findViewById(R.id.Problem);
            ProblemDescription=itemView.findViewById(R.id.description);
            DoctorType=itemView.findViewById(R.id.doctorType);
            Date=itemView.findViewById(R.id.date);
            Time=itemView.findViewById(R.id.timeBook);
            HospitalName=itemView.findViewById(R.id.NameHospital);
            Name=itemView.findViewById(R.id.NameDoctor);

        }
    }
}
