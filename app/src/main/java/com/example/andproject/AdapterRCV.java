package com.example.andproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRCV extends RecyclerView.Adapter<AdapterRCV.MyViewHolder> {
    private Context context;
    private ArrayList item_id, item_name, item_date;
    LayoutInflater inflater;
    private Cursor cursor;



    AdapterRCV(Context context, ArrayList item_id, ArrayList item_name, ArrayList item_date){
        this.context =context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_date = item_date;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rows, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_id.setText(String.valueOf(item_id.get(position)));
        holder.item_name.setText(String.valueOf(item_name.get(position)));
        holder.item_date.setText(String.valueOf(item_date.get(position)));

        //int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
        //holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_id, item_name, item_date;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id = itemView.findViewById(R.id.id_textView);
            item_name = itemView.findViewById(R.id.name_textView);
            item_date =itemView.findViewById(R.id.date_textView);


            View.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position2 = getAdapterPosition();

                }
            };

        }
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }
        cursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }



    public void getItemAt(int position){
        position = getItemCount();
    }
}
