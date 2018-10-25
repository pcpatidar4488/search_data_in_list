package app_lock.project.beryl.com.searchinlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {


    public static List<Detail> detailList;
    Context context;
    public static List<Detail> mFilteredList;

    public MyAdapter(Context context, List detailList) {
        this.context = context;
        this.detailList = detailList;

    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int position) {
        viewHolder.name.setText(detailList.get(position).getName());
        viewHolder.mobile.setText(detailList.get(position).getMobile());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView mobile;


        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            mobile = view.findViewById(R.id.mobile);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = detailList;
                } else {
                    ArrayList<Detail> filteredList = new ArrayList<>();
                    for (Detail androidVersion : detailList) {
                        if (androidVersion.getName().toLowerCase().startsWith(charString) || androidVersion.getMobile().startsWith(charString)) {
                            filteredList.add(androidVersion);
                        } else if (androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getMobile().contains(charString)) {
                            filteredList.add(androidVersion);
                        }
                    }
                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Detail>) filterResults.values;
                detailList=mFilteredList;
                notifyDataSetChanged();
            }
        };
    }
}
