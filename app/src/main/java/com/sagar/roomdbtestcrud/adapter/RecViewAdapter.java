package com.sagar.roomdbtestcrud.adapter;

import android.content.Context;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.sagar.roomdbtestcrud.MainActivity;
import com.sagar.roomdbtestcrud.R;
import com.sagar.roomdbtestcrud.entity.Tbl_Contact;

import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.Viewholder> {


    Context context;
    List<Tbl_Contact> tblContactList;


    Tbl_Contact contact;


    //Constructor
    public RecViewAdapter( Context context,List<Tbl_Contact> tblContactList){

        this.context = context;
        this.tblContactList = tblContactList;

    }



    //3 override methods

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        contact = tblContactList.get(position);

        holder.txt_id.setText("ID : " + contact.id);
        holder.txt_name.setText("Name : " + contact.name);
        holder.txt_email.setText("Email : " + contact.email);
        holder.txt_phone.setText("Phone : " + contact.phone);

    }

    @Override
    public int getItemCount() {
        return tblContactList.size();
    }




    public void dialogeItemCallback(int itemPos){
        MainActivity.contactDao.delete(tblContactList.get(itemPos));
    }



    class Viewholder extends RecyclerView.ViewHolder {


        TextView txt_id,txt_name,txt_email,txt_phone;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            txt_id = itemView.findViewById(R.id.txt_id);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_email = itemView.findViewById(R.id.txt_email);
            txt_phone = itemView.findViewById(R.id.txt_phone);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(context);
                    builderSingle.setIcon(R.drawable.ic_warning_black);
                    builderSingle.setTitle("Select the operations you wanna perform:-");

                    final ArrayAdapter<String> arrayAdapter =
                            new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);

                    arrayAdapter.add("ADD Item");
                    arrayAdapter.add("Delete Item");
                    arrayAdapter.add("Update Item");

                    builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String strName = arrayAdapter.getItem(which);
                            AlertDialog.Builder builderInner = new AlertDialog.Builder(context);
                            builderInner.setMessage(strName);
                            builderInner.setTitle("Your Selected Item is :");
                            builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,int which) {

                                    //dialogeItemCallback(which);

                                    //Log.d(TAG, "onClick: ");
                                    dialogeItemCallback(getAdapterPosition());
                                    RecViewAdapter adapter = new RecViewAdapter(context,tblContactList);
                                    notifyDataSetChanged();
                                    dialog.dismiss();
                                }
                            });
                            builderInner.show();


                        }
                    });
                    builderSingle.show();
                }
            });

        }
    }

}
