package com.example.task_exam.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task_exam.R;
import com.example.task_exam.model.Item;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class MyAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Item> usersList = new ArrayList<>();

    public MyAdapter(Context mContext, ArrayList<Item> usersList) {
        this.mContext = mContext;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        holder.email.setText(usersList.get(i).getEmail());
        holder.number.setText(usersList.get(i).getMobile());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public void addAll(ArrayList<Item> list) {
        this.usersList.clear();
        this.usersList.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnClick {
        void onClick(Item item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView email;
        TextView number;

        public ViewHolder(View rootView) {
            super(rootView);

            this.email = rootView.findViewById(R.id.email);
            this.number = rootView.findViewById(R.id.number);
        }
    }
}



