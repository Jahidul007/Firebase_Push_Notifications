package com.jahid.firebasepushnotifications;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private List<Notifications> mNotifList;

    private FirebaseFirestore firebaseFirestore;
    private Context context;

    private FirebaseAuth mAuth;

    public NotificationsAdapter(Context context, List<Notifications> mNotifList) {

        this.mNotifList = mNotifList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.single_notification, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        firebaseFirestore = FirebaseFirestore.getInstance();

        //String from_id = mNotifList.get(position).getFrom();

        String from_id = mNotifList.get(position).getFrom();

        System.out.println("from Id: "+ from_id);

     //   String dataFrom = getIntent().getStringExtra("from_user_id");

        mAuth = FirebaseAuth.getInstance();

        String mUserId;

        mUserId = mAuth.getCurrentUser().getUid();



        holder.mNotifMessage.setText(mNotifList.get(position).getMessage());

        firebaseFirestore.collection("Users").document(from_id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if(documentSnapshot.exists()) {
                    String user_name = documentSnapshot.getString("name");
                    String user_image = documentSnapshot.getString("image");

                    holder.mNotifName.setText(user_name);

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.placeholder(R.mipmap.default_image);

                    Glide.with(context).setDefaultRequestOptions(requestOptions).load(user_image).into(holder.mNotifImage);
                }
                else {
                    System.out.println("This document doesn't exist");
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mNotifList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public CircleImageView mNotifImage;
        public TextView mNotifName;
        public TextView mNotifMessage;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            mNotifImage = (CircleImageView) mView.findViewById(R.id.notif_image);
            mNotifMessage = (TextView) mView.findViewById(R.id.notif_message);
            mNotifName = (TextView) mView.findViewById(R.id.notif_name);


        }
    }
}
