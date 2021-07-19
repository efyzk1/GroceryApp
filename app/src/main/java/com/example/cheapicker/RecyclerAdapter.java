package com.example.cheapicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private static final String taq="RecyclerView";
    private Context mContext;
    private ArrayList<User> userList;
    //////////////////
    private ArrayList<User> userListFull;
    //////////////

    public RecyclerAdapter(Context mContext, ArrayList<User> userList) {
        this.mContext = mContext;
        this.userListFull = userList;
        this.userList=new ArrayList<>(userListFull);
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
                            //user_item
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(userList.get(position).getName());
        holder.weightView.setText(userList.get(position).getWeight());
        holder.priceView.setText(userList.get(position).getPrice());
        holder.desView.setText(userList.get(position).getdescription());

        Glide.with(mContext)
                .load(userList.get(position).getImg_url())
                .into(holder.imageView);
        Glide.with(mContext)
                .load(userList.get(position).getMartimg_url())
                .into(holder.martimage);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
/////////////////////////
    @Override
    public Filter getFilter() {
        return userFilter;
    }

    private final Filter userFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<User> filteredUserList=new ArrayList<>();

            if(constraint==null || constraint.length()==0){
                filteredUserList.addAll(userListFull);
            }else{

                String filterPattern=constraint.toString().toLowerCase().trim();
                for(User user : userListFull){

                    if(user.name.toLowerCase().contains(filterPattern))
                        filteredUserList.add(user);
                }

            }
            FilterResults results=new FilterResults();
            results.values=filteredUserList;
            results.count=filteredUserList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            userList.clear();
            userList.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };

    public void filterList(ArrayList<User> filteredList){
        userList=filteredList;
        notifyDataSetChanged();
    }

///////////////////////////////
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        ImageView martimage;
        TextView priceView;
        TextView desView;
        TextView weightView;
        TextView textView;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            imageView=itemView.findViewById(R.id.item_imageView);
            martimage=itemView.findViewById(R.id.mart_imageView);
            textView=itemView.findViewById(R.id.name_productView);
            priceView=itemView.findViewById(R.id.price_productView);
            desView=itemView.findViewById(R.id.des_productView);
            weightView=itemView.findViewById(R.id.weight_productView);
        }



    }



}
