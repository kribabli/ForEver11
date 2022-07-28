package com.example.yoyoiq.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yoyoiq.Model.MainViewModel;
import com.example.yoyoiq.Model.SquadsA;
import com.example.yoyoiq.R;

import java.util.ArrayList;

public class SquadsAAdapter extends RecyclerView.Adapter<SquadsAAdapter.MyViewHolder> {
    Context context;
    ArrayList<SquadsA> list;

    MainViewModel mainViewModel;
    boolean isEnable = false;
    boolean isSelectedAll = false;
    ArrayList<String> selectedList = new ArrayList<>();

    public SquadsAAdapter(Context context, ArrayList<SquadsA> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SquadsAAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selected_players, parent, false);
        mainViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(MainViewModel.class);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SquadsAAdapter.MyViewHolder holder, int position) {
        SquadsA listData = list.get(position);

        String fullPlayerName = listData.getName();
//        String playerFChar = fullPlayerName.substring(0, 1);
//        String[] separated = fullPlayerName.split(" ");
//        String separated1stChar = separated[0];
//        String separated2ndChar = separated[1];

        holder.playerName.setText(listData.getShort_namePlayers());
        holder.playerCredit.setText(listData.getFantasy_player_rating());
        holder.country.setText(listData.getMatchAB());
        holder.cardViewSelected.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isEnable) {
                    ActionMode.Callback callback = new ActionMode.Callback() {
                        @Override
                        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                            MenuInflater menuInflater = actionMode.getMenuInflater();
                            menuInflater.inflate(R.menu.menu_selected, menu);

                            return true;
                        }

                        @Override
                        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                            isEnable = true;
                            ClickItem(holder);
                            mainViewModel.getText().observe((LifecycleOwner) context, new Observer<String>() {
                                @Override
                                public void onChanged(String s) {
                                    actionMode.setTitle(String.format("%s Selected", s));
                                }
                            });
                            return true;
                        }

                        @Override
                        public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {
                            int id = menuItem.getItemId();
                            switch (id) {
                                case R.id.menu_add:
                                    for (String s : selectedList) {
                                        selectedList.add(s);
                                    }
                                    if (selectedList.size() == 0) {
                                        holder.alreadyAddedPlayer.setVisibility(View.VISIBLE);
                                    }
                                    mode.finish();
                                    break;
                                case R.id.menu_select_all:
                                    if (selectedList.size() == list.size()) {
                                        isSelectedAll = false;
                                        selectedList.clear();
                                    } else {
                                        isSelectedAll = true;
                                        selectedList.clear();
                                        selectedList.addAll(selectedList);
                                    }
                                    mainViewModel.setText(String.valueOf(selectedList.size()));
                                    notifyDataSetChanged();
                                    break;
                            }
                            return true;
                        }

                        @Override
                        public void onDestroyActionMode(ActionMode actionMode) {
                            isEnable = false;
                            isSelectedAll = false;
                            selectedList.clear();
                            notifyDataSetChanged();

                        }
                    };
//                    ((AppCompatActivity) v.getContext().startActivity((Intent) callback));
                } else {
                    ClickItem(holder);
                }
                return true;
            }

        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnable) {
                    ClickItem(holder);
                } else {

                }
            }
        });
        if (isSelectedAll) {
            holder.im_AddPlayer.setVisibility(View.VISIBLE);
        } else {
            holder.alreadyAddedPlayer.setVisibility(View.GONE);
        }
    }

    private void ClickItem(MyViewHolder holder) {
        String s = String.valueOf(list.get(holder.getAdapterPosition()));
        if (holder.im_AddPlayer.getVisibility() == View.GONE) {
            holder.im_AddPlayer.setVisibility(View.VISIBLE);
            holder.alreadyAddedPlayer.setVisibility(View.VISIBLE);
            selectedList.add(s);
        } else {
            holder.im_AddPlayer.setVisibility(View.GONE);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            selectedList.remove(s);
        }
        mainViewModel.setText(String.valueOf(selectedList.size()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView playerName, country, playerCredit;
        ImageView playerImg, alreadyAddedPlayer, im_AddPlayer;
        CardView cardViewSelected;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.playerName);
            country = itemView.findViewById(R.id.country);
            playerCredit = itemView.findViewById(R.id.tv_PlayerCredit);
            playerImg = itemView.findViewById(R.id.playerImg);
            alreadyAddedPlayer = itemView.findViewById(R.id.alreadyAddedPlayer);
            im_AddPlayer = itemView.findViewById(R.id.im_AddPlayer);
            cardViewSelected = itemView.findViewById(R.id.cardViewSelected);
        }
    }
}
