package coding.insight.cleanuiloginregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class BedAdapter extends FirebaseRecyclerAdapter<BedModel,BedAdapter.bedViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BedAdapter(@NonNull FirebaseRecyclerOptions<BedModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull bedViewHolder holder, int position, @NonNull BedModel model) {
        holder.Name.setText(model.getName());
        holder.Number.setText(model.getNumber());
        holder.Address.setText(model.getAddress());
        holder.City.setText(model.getCity());
        holder.Beds.setText(model.getBeds());
    }

    @NonNull
    @Override
    public bedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.bedslist,parent,false);
        return new bedViewHolder(view);
    }

    static class bedViewHolder extends RecyclerView.ViewHolder{

        TextView Name, Address, City, Number, Beds;

        public bedViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Name);
            Address=itemView.findViewById(R.id.Address);
            City=itemView.findViewById(R.id.City);
            Number=itemView.findViewById(R.id.Number);
            Beds=itemView.findViewById(R.id.Beds);
        }
    }

}
