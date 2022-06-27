package com.example.fragmentapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentapp.R;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ListViewHolder> {

    ArrayList<String> stringArrayList;

    public MyListAdapter(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.item_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.tvItem.setText(stringArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
        }
    }
}
