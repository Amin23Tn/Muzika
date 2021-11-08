package tn.example.muzika;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;
import tn.example.muzika.models.AppDataBase;
import tn.example.muzika.models.user;
import tn.example.muzika.utils.SessionManager;

public class Login extends AppCompatActivity {

    Button Register, Login, spotifyLogin;
    EditText username, password;
    SharedPreferences pref;
    SessionManager sessionManager;
    //Dialog
    Dialog dialog;
    private Activity app = this;

    SharedPreferences sharedPreferences ;
    public static String FILE_NAME = "com.exemple.muzika.sp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final LoadingDialog loadingDialog = new LoadingDialog(Login.this);

        sessionManager = new SessionManager(getApplicationContext());
        Login = findViewById(R.id.loginbutton);
        Register = findViewById(R.id.registerbutton);
        spotifyLogin = findViewById(R.id.spotifyLogin);
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        Log.d("Spotify App remote", "appRemote.isConnected()");
        sharedPreferences = getSharedPreferences(FILE_NAME,MODE_PRIVATE);

        if(!sharedPreferences.getString("LOGIN","").isEmpty())
        {
            Intent intent = new Intent(Login.this,HomePage.class);
            startActivity(intent);
            finish();
        }

        //dialog
        dialog = new Dialog(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startLoadingDialog();

                if(AppDataBase.getAppDatabase(getApplicationContext()).userDao().login(username.getText().toString(),password.getText().toString())==null)
                {
                    loadingDialog.dismissDialog();
                    OpenErreurDialog("Invalide");
                }
                else
                {
                    loadingDialog.startLoadingDialog();

                    SharedPreferences.Editor editor;
                    editor = sharedPreferences.edit();
                    editor.putString("USERNAME",username.getText().toString());
                    editor.apply();
                    Log.d("Username",sharedPreferences.getString("USERNAME",""));

                    success(loadingDialog);
                }

                //login(loadingDialog);

            }
        });

        spotifyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    void success(LoadingDialog loadingDialog) {

        Intent intent = new Intent(Login.this, HomePage.class);
        startActivity(intent);
        finish();
    }


    //dialog
    private void OpenErreurDialog(String errorResponse) {
        dialog.setContentView(R.layout.erreur_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button tryagain = dialog.findViewById(R.id.tryagainbutton);
        TextView text = dialog.findViewById(R.id.Ereurtext);

        tryagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(Login.this, "OUPS!", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}