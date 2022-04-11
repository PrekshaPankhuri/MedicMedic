package coding.insight.cleanuiloginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FinalAdapter extends FirebaseRecyclerAdapter<FinalModel,FinalAdapter.FinalViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FinalAdapter(@NonNull FirebaseRecyclerOptions<FinalModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FinalAdapter.FinalViewHolder holder, int position, @NonNull FinalModel model) {

        holder.Date.setText(model.getDate());
        holder.DoctorType.setText(model.getDoctorType());
        holder.Problem.setText(model.getProblem());
        holder.ProblemDescription.setText(model.getProblemDescription());
        holder.Time.setText(model.getTime());
        holder.DoctorName.setText(model.getDoctorName());

    }

    @NonNull
    @Override
    public FinalAdapter.FinalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.final_list,parent,false);
        return new FinalViewHolder(view);
    }

    public static class FinalViewHolder extends RecyclerView.ViewHolder {

        TextView Problem,ProblemDescription,Time,Date,DoctorType,DoctorName;

        public FinalViewHolder(@NonNull View itemView) {
            super(itemView);

            Problem=itemView.findViewById(R.id.Problem1);
            ProblemDescription=itemView.findViewById(R.id.Description1);
            Time=itemView.findViewById(R.id.Time1);
            Date=itemView.findViewById(R.id.AppointmentDate1);
            DoctorType=itemView.findViewById(R.id.doctortype1);
            DoctorName=itemView.findViewById(R.id.doctorName);

        }
    }
}
