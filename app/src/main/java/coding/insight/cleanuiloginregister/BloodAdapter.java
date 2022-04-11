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

public class BloodAdapter extends FirebaseRecyclerAdapter<BloodModel,BloodAdapter.bloodViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BloodAdapter(@NonNull FirebaseRecyclerOptions<BloodModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull bloodViewHolder holder, int position, @NonNull BloodModel model) {
        holder.Name.setText(model.getName());
        holder.Address.setText(model.getAddress());
        holder.City.setText(model.getCity());
        holder.Number.setText(model.getNumber());
        holder.BloodBank.setText(model.getBloodBank());
    }

    @NonNull
    @Override
    public bloodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.bloodlist,parent,false);
        return new bloodViewHolder(view);
    }

    public static class bloodViewHolder extends RecyclerView.ViewHolder{

        TextView Name,Address,City,Number,BloodBank;

        public bloodViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Name);
            Address=itemView.findViewById(R.id.Address);
            City=itemView.findViewById(R.id.City);
            Number=itemView.findViewById(R.id.Number);
            BloodBank=itemView.findViewById(R.id.BloodBank);

        }
    }
}
