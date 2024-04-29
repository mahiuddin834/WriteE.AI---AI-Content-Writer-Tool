package com.itnation.writee_aicontentwritertool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.itnation.writee_aicontentwritertool.MVVM.Content;
import com.itnation.writee_aicontentwritertool.R;

public class ContentListAdapter extends ListAdapter<Content, ContentListAdapter.ViewHolder> {


    Context context;

    public ContentListAdapter(){

        super(CALBACK);
    }

    private static final DiffUtil.ItemCallback<Content> CALBACK = new DiffUtil.ItemCallback<Content>() {
        @Override
        public boolean areItemsTheSame(@NonNull Content oldItem, @NonNull Content newItem) {
           return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Content oldItem, @NonNull Content newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getParagraph().equals(newItem.getParagraph());
        }
    };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Content content = getItem(position);

        holder.title.setText(content.getTitle());
        holder.paragraphTxt.setText(content.getParagraph());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



    }

    public Content getContent(int position){

        return getItem(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, paragraphTxt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.contentTitle);
            paragraphTxt = itemView.findViewById(R.id.contentParagraph);
        }
    }
}

