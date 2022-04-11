package coding.insight.cleanuiloginregister;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class VaccineAdapter extends FirebaseRecyclerAdapter<VaccineModel,VaccineAdapter.vaccineViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public VaccineAdapter(@NonNull FirebaseRecyclerOptions<VaccineModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull vaccineViewHolder holder, int position, @NonNull VaccineModel model) {
        holder.Name.setText(model.getName());
        holder.Address.setText(model.getAddress());
        holder.City.setText(model.getCity());
        holder.Number.setText(model.getNumber());
        holder.VaccineCentre.setText(model.getVaccineCentre());
    }

    @NonNull
    @Override
    public vaccineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.vacclist,parent,false);
        return new VaccineAdapter.vaccineViewHolder(view);
    }

    public static class vaccineViewHolder extends RecyclerView.ViewHolder {

        TextView Name,Number,Address,City,VaccineCentre;

        public vaccineViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Name);
            Address=itemView.findViewById(R.id.Address);
            City=itemView.findViewById(R.id.City);
            Number=itemView.findViewById(R.id.Number);
            VaccineCentre=itemView.findViewById(R.id.VaccineCentre);

        }
    }
}
