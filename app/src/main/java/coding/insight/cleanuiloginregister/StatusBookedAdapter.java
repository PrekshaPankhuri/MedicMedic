package coding.insight.cleanuiloginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class StatusBookedAdapter extends FirebaseRecyclerAdapter<StatusBookedModel,StatusBookedAdapter.BookedViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public StatusBookedAdapter(@NonNull FirebaseRecyclerOptions<StatusBookedModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookedViewHolder holder, int position, @NonNull StatusBookedModel model) {
        holder.ProblemDescription.setText(model.getProblemDescription());
        holder.Problem.setText(model.getProblem());
        holder.Date.setText(model.getDate());
        holder.Time.setText(model.getTime());
        holder.DoctorType.setText(model.getDoctorType());
        holder.NameHospital2.setText(model.getHospitalName());
        holder.DoctorName2.setText(model.getDoctorName());
    }

    @NonNull
    @Override
    public BookedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.statusbooked_list,parent,false);
        return new BookedViewHolder(view);
    }


    public static class BookedViewHolder extends RecyclerView.ViewHolder {

        TextView Problem,ProblemDescription,DoctorType,Date,Time,NameHospital2,DoctorName2;

        public BookedViewHolder(@NonNull View itemView) {
            super(itemView);

            Problem=itemView.findViewById(R.id.Problem2);
            ProblemDescription=itemView.findViewById(R.id.description2);
            DoctorType=itemView.findViewById(R.id.doctorType2);
            Date=itemView.findViewById(R.id.date2);
            Time=itemView.findViewById(R.id.timeBook2);
            NameHospital2=itemView.findViewById(R.id.NameHospital2);
            DoctorName2=itemView.findViewById(R.id.NameDoctor2);

        }
    }
}
