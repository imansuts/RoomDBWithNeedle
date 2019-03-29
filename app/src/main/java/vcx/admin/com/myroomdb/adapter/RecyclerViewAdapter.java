package vcx.admin.com.myroomdb.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import vcx.admin.com.myroomdb.R;
import vcx.admin.com.myroomdb.db.User;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Activity activity;
    List<User> userLinkedList = new LinkedList<>();

    public RecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.single_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_id.setText(""+userLinkedList.get(position).getId());
        holder.textView_name.setText(userLinkedList.get(position).getFullname());
    }

    @Override
    public int getItemCount() {
        return userLinkedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_id, textView_name;

        public ViewHolder(View itemView) {
            super(itemView);


            textView_id =  itemView.findViewById(R.id.tv_id);
            textView_name =  itemView.findViewById(R.id.tv_name);
        }
    }

    public void setUserLinkedList(List<User> userLinkedList){
        this.userLinkedList.addAll(userLinkedList);
        notifyDataSetChanged();
    }


    public void clearData(){
        this.userLinkedList.clear();
        notifyDataSetChanged();
    }
}
