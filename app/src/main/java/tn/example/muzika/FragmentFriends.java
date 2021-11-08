package tn.example.muzika;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import tn.example.muzika.models.AppDataBase;
import tn.example.muzika.models.Track;
import tn.example.muzika.models.friendships;
import tn.example.muzika.models.user;
import tn.example.muzika.utils.SessionManager;

import static android.content.Context.MODE_PRIVATE;
import static tn.example.muzika.Login.FILE_NAME;

public class FragmentFriends extends Fragment {
    private ArrayList<user> users;

    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fav, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.featuredRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.show();
        progressDialog.setContentView(R.layout.custom_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        friendsAdapter adapter = new friendsAdapter(this.getContext(), progressDialog);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}

class friendsAdapter extends RecyclerView.Adapter<friendsAdapter.MyViewHolder> implements Runnable {
    private ArrayList<user> users = new ArrayList<>();
    private List<friendships> friends;
    Context context;
    friendsAdapter adapter = this;

    public friendsAdapter(Context context, ProgressDialog progressDialog) {
        this.context = context;
        getData(context, progressDialog);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_adapter, parent, false);

        return new friendsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.username.setText(users.get(position).getUsername());
        holder.email.setText(users.get(position).getEmail());
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME,MODE_PRIVATE);

                user u = AppDataBase.getAppDatabase(context).userDao().findbyusername(sharedPreferences.getString("USERNAME",""));
                friendships f = AppDataBase.getAppDatabase(context).friendsDao().findtheId(String.valueOf(u.getId()),String.valueOf(users.get(position).getId()));


                AppDataBase.getAppDatabase(context).friendsDao().delete(f);
                Toast.makeText(context,"Friend Deleted !",Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return users.isEmpty() ? 0 : users.size();
    }


    @Override
    public void run() {

    }

    void getData(Context cntx, ProgressDialog progressDialog) {

        SharedPreferences sharedPreferences = cntx.getSharedPreferences(FILE_NAME,MODE_PRIVATE);

        user u = AppDataBase.getAppDatabase(cntx).userDao().findbyusername(sharedPreferences.getString("USERNAME",""));
        friends = AppDataBase.getAppDatabase(cntx).friendsDao().findfriends(String.valueOf(u.getId()));

        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i).getIdfriend());
            user uu = AppDataBase.getAppDatabase(cntx).userDao().findbyId(friends.get(i).getIdfriend());
            Log.d("user",uu.toString());

            users.add(uu);
        }
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView email;
        private final TextView username;
        private final ImageButton addButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.user_name);
            email = (TextView) itemView.findViewById(R.id.email);
            ImageView img = itemView.findViewById(R.id.imageView5);
            img.setImageResource(R.drawable.userprofile);
            addButton = itemView.findViewById(R.id.imageButton3);
        }

        public TextView getTextView() {
            return username;
        }

        public TextView getDescription() {
            return email;
        }
    }
}
