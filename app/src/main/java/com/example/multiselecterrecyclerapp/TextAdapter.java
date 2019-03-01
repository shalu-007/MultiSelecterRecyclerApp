package com.example.multiselecterrecyclerapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TextAdapter  extends MultiSelectableAdapter<TextAdapter.TextHolder> {

    public  static  final Integer ACTIVE=0;
    public  static  final Integer INACTVE=1;
    private Context mContext;
    ArrayList<SingleItem> mItems;
    //define a listener object to capture activity object in it to call onlongclick and on click methods in activity
    TextHolder.clickListener mActivityClickListener;


    public TextAdapter(ArrayList<SingleItem> pItems, TextHolder.clickListener pActivityClickListener) {
        mItems = pItems;
        mActivityClickListener=pActivityClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        boolean type=mItems.get(position).isActive();
        return type?ACTIVE:INACTVE;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        mContext=pViewGroup.getContext();
        final int vLayout=pI==INACTVE?R.layout.inactive_item_layout:R.layout.active_item_layout;
        View v= LayoutInflater.from(mContext).inflate(vLayout,pViewGroup,false);
        return new TextHolder(v,mActivityClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder pTextHolder, int pI) {
        pTextHolder.title.setText(mItems.get(pI).getTitle());
        pTextHolder.desc.setText(mItems.get(pI).getDescription()+" ,"+(mItems.get(pI).isActive()?"ACTIVE":"INActive") );
    //       final  ViewGroup.LayoutParams vLayoutParams=pTextHolder.itemView.getLayoutParams();
//        if(vLayoutParams instanceof StaggeredGridLayoutManager.LayoutParams){
//            StaggeredGridLayoutManager.LayoutParams vLayoutParams1=(StaggeredGridLayoutManager.LayoutParams) vLayoutParams;
//            vLayoutParams1.setFullSpan(true);
//            pTextHolder.itemView.setLayoutParams(vLayoutParams1);
//        }
        pTextHolder.mView.setVisibility(isSelected(pI)?View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class TextHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        CardView mCardView;
        TextView title,desc;
        View mView;
        private  clickListener mClickListener;
        TextHolder(@NonNull View itemView,clickListener pClickListener) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            desc=itemView.findViewById(R.id.description);
            mCardView=itemView.findViewById(R.id.container);
            mView=itemView.findViewById(R.id.overlay);

            mClickListener=pClickListener;
            mCardView.setOnClickListener(this);
            mCardView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mClickListener!=null){
                mClickListener.onItemClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mClickListener!=null){
                return mClickListener.onItemLongClicked(getAdapterPosition());
            }
            return true;
        }

        public interface  clickListener{
            void onItemClick(int position);
            boolean onItemLongClicked(int position);
        }
    }

}
