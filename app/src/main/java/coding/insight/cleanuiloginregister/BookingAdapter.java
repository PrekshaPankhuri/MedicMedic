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

public class BookingAdapter extends FirebaseRecyclerAdapter<BookingModel,BookingAdapter.BookingViewHolder> {
    String ID;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BookingAdapter(@NonNull FirebaseRecyclerOptions<BookingModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BookingViewHolder holder, int position, @NonNull BookingModel model) {
         holder.Name.setText(model.getName());
         holder.Address.setText(model.getAddress());
         holder.City.setText(model.getCity());
         holder.Number.setText(model.getNumber());
         holder.HosID.setText(model.getID());
         holder.HosID2.setText(model.getID());

         holder.Book.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(v.getContext(),Doctor.class);
                 intent.putExtra("ID",model.getID());
                 intent.putExtra("Name",model.getName());
                 v.getContext().startActivity(intent);
             }
         });
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_list,parent,false);
        return new BookingViewHolder(view);
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        TextView Name,Address,Number,City,HosID,HosID2;
        Button Book;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);

            Name=itemView.findViewById(R.id.Name);
            Address=itemView.findViewById(R.id.Address);
            Number=itemView.findViewById(R.id.City);
            City=itemView.findViewById(R.id.Number);
            Book=itemView.findViewById(R.id.select);
            HosID=itemView.findViewById(R.id.HosID);
            HosID2=itemView.findViewById(R.id.HosID2);


        }
    }
}
