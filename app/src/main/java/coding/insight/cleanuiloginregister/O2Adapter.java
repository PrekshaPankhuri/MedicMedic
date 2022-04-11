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

public class O2Adapter extends FirebaseRecyclerAdapter<OxygenModel,O2Adapter.oxygenViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public O2Adapter(@NonNull FirebaseRecyclerOptions<OxygenModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull oxygenViewHolder holder, int position, @NonNull OxygenModel model) {
        holder.Name.setText(model.getName());
        holder.Address.setText(model.getAddress());
        holder.City.setText(model.getCity());
        holder.Number.setText(model.getNumber());
        holder.Cylinders.setText(model.getCylinders());
    }

    @NonNull
    @Override
    public oxygenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.oxygenlist,parent,false);
        return new oxygenViewHolder(view);
    }

    public class oxygenViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Address,City,Number,Cylinders;

        public oxygenViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Name);
            Address=itemView.findViewById(R.id.Address);
            City=itemView.findViewById(R.id.City);
            Number=itemView.findViewById(R.id.Number);
            Cylinders=itemView.findViewById(R.id.Cylinder);

        }
    }
}
