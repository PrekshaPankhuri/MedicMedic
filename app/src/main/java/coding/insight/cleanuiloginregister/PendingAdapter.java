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
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class PendingAdapter extends FirebaseRecyclerAdapter<PendingModel,PendingAdapter.PendingViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PendingAdapter(@NonNull FirebaseRecyclerOptions<PendingModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PendingViewHolder holder, int position, @NonNull PendingModel model){


            holder.Problem.setText(model.getProblem());
            holder.ProblemDescription.setText(model.getProblemDescription());
            holder.Date.setText(model.getDate());
            holder.Time.setText(model.getTime());
            holder.DoctorType.setText(model.getDoctorType());
            holder.DoctorName.setText(model.getDoctorName());


            holder.Accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                     Intent intent=new Intent(v.getContext(),Accepting.class);
                     intent.putExtra("HospitalName",model.getHospitalName());
                     intent.putExtra("UserID",model.getUserID());
                     intent.putExtra("AppID",model.getAppID());
                     intent.putExtra("HospitalID",model.getHospitalID());
                     intent.putExtra("Problem",model.getProblem());
                     intent.putExtra("ProblemDescription",model.getProblemDescription());
                     intent.putExtra("Date",model.getDate());
                     intent.putExtra("Time",model.getTime());
                     intent.putExtra("DoctorType",model.getDoctorType());
                     intent.putExtra("DoctorName",model.getDoctorName());
                     v.getContext().startActivity(intent);

                }
            });


    }

    @NonNull
    @Override
    public PendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pendingappointments_list,parent,false);
        return new PendingViewHolder(view);
    }

    public static class PendingViewHolder extends RecyclerView.ViewHolder {

        TextView Problem,ProblemDescription,Time,Date,DoctorType,DoctorName;
        Button Accept;

        public PendingViewHolder(@NonNull View itemView) {
            super(itemView);

            Problem=itemView.findViewById(R.id.problem);
            ProblemDescription=itemView.findViewById(R.id.Description);
            Time=itemView.findViewById(R.id.Time);
            Date=itemView.findViewById(R.id.AppointmentDate);
            DoctorType=itemView.findViewById(R.id.doctortype);
            Accept=itemView.findViewById(R.id.accept);
            DoctorName=itemView.findViewById(R.id.doctor);

        }
    }
}
