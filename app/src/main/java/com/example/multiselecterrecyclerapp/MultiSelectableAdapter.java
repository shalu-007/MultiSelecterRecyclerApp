package com.example.multiselecterrecyclerapp;


import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;

import java.util.ArrayList;
import java.util.List;

public abstract  class MultiSelectableAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

   private SparseBooleanArray mSelectedItems;

   MultiSelectableAdapter(){
       mSelectedItems=new SparseBooleanArray();
   }

   boolean isSelected(int position){
       return  getSelectedItems().contains(position);
   }

    public int getSelectedItemCount() {
        return mSelectedItems.size();
    }

    private List<Integer> getSelectedItems(){
       List<Integer> items=new ArrayList<Integer>(mSelectedItems.size());
      for(int i=0;i<mSelectedItems.size();i++){
          items.add(mSelectedItems.keyAt(i));
      }
      return items;
   }

   public void DeleteSecleted(){
       List<Integer> items=getSelectedItems();
       for (Integer i:items){
           g
           notifyItemChanged(i);
       }
   }
   public void  clearSelection(){
       List<Integer> items=getSelectedItems();
       mSelectedItems.clear();
       for (Integer i:items){
           notifyItemChanged(i);
       }
   }

   public  void toggleSelection(int position){
       if(mSelectedItems.get(position,false)){
           mSelectedItems.delete(position);
       }
       else{
           mSelectedItems.put(position,true);
       }
       notifyItemChanged(position);
   }

}
