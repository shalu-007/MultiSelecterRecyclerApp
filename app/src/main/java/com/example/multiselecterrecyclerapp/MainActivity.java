package com.example.multiselecterrecyclerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements TextAdapter.TextHolder.clickListener {

    private ActionModeCallBack mCallback = new ActionModeCallBack();
    private ActionMode mActionMode;
    private TextAdapter mTextAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView vRecyclerView = findViewById(R.id.mRecycler);
        ArrayList<SingleItem> vSingleItems = new ArrayList<>();
        Random vRandom = new Random();
        for (int i = 0; i < 50; i++) {
            vSingleItems.add(new SingleItem("Item number " + (i + 1), "This is item number" + (i + 1), vRandom.nextBoolean()));
        }
        mTextAdapter = new TextAdapter(vSingleItems, (TextAdapter.TextHolder.clickListener) this);
        vRecyclerView.setAdapter(mTextAdapter);
        vRecyclerView.setItemAnimator(new DefaultItemAnimator());
        vRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void toggleSelectionMethod(int position) {
        mTextAdapter.toggleSelection(position);
        int count = mTextAdapter.getSelectedItemCount();
        if (count == 0) {
            mActionMode.finish();
        } else {
            mActionMode.setTitle(String.valueOf(count));
            mActionMode.invalidate();
        }
    }

    @Override
    public void onItemClick(int position) {
        if (mActionMode != null) {
            toggleSelectionMethod(position);
        }
    }

    @Override
    public boolean onItemLongClicked(int position) {
        if (mActionMode == null) {
            mActionMode = startActionMode(mCallback);
        }
        toggleSelectionMethod(position);
        return true;
    }

    class ActionModeCallBack implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.selected_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.delete:
                    mTextAdapter.DeleteSecleted();
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mTextAdapter.clearSelection();
            mActionMode=null;
        }
    }

}
