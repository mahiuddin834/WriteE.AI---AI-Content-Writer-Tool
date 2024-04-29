package com.itnation.writee_aicontentwritertool.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itnation.writee_aicontentwritertool.Activity.ViewContentActivity;
import com.itnation.writee_aicontentwritertool.MVVM.Content;
import com.itnation.writee_aicontentwritertool.R;

import java.util.List;

public class ContentRVAdapter extends RecyclerView.Adapter<ContentRVAdapter.ViewHolder> {

    Context context;
    List<Content> contentList;

    public ContentRVAdapter(Context context, List<Content> contentList) {
        this.context = context;
        this.contentList = contentList;
    }

    @NonNull
    @Override
    public ContentRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.content_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentRVAdapter.ViewHolder holder, int position) {

        Content content= contentList.get(position);
        holder.title.setText(content.getTitle());
        holder.paragraphTxt.setText(content.getParagraph());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ViewContentActivity.class);
                intent.putExtra("paragraph", content.getParagraph());
                intent.putExtra("id", content.getId());
                intent.putExtra("title", content.getTitle());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return contentList.size();
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
