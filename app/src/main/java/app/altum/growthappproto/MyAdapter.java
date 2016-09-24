package app.altum.growthappproto;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Child> childList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, height, weight;

        public ViewHolder(final View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            height = (TextView) view.findViewById(R.id.height);
            weight = (TextView) view.findViewById(R.id.weight);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(view.getContext(), GraphActivity.class);
                    myIntent.putExtra(GraphActivity.CHILD_ID, childList.get(getAdapterPosition()).getName());
                    view.getContext().startActivity(myIntent);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Child> childList) {
        this.childList = childList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Child child = childList.get(position);
        holder.name.setText(child.getName());
        holder.height.setText(child.getHeight() + " cm");
        holder.weight.setText(child.getWeight() + " kg");


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return childList.size();
    }
}
