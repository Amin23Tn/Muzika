package tn.example.muzika;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import okhttp3.Headers;
import tn.example.muzika.models.AppDataBase;
import tn.example.muzika.models.user;

public class Register extends AppCompatActivity {

    Button Register;
    EditText username, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.usernamereg);
        email = (EditText) findViewById(R.id.emailreg);
        password = (EditText) findViewById(R.id.passwordreg);

        Register = findViewById(R.id.registerb);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppDataBase.getAppDatabase(getApplicationContext()).userDao().findByEmail(email.getText().toString())==null)
                {
                    user usr = new user(username.getText().toString(),email.getText().toString(),password.getText().toString());
                    AppDataBase.getAppDatabase(getApplicationContext()).userDao().insertOne(usr);

                    Intent intent = new Intent(Register.this, Login.class);

                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Success,Login please !",Toast.LENGTH_SHORT).show();

                    finish();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"EXISTE",Toast.LENGTH_SHORT).show();
                }
                /*
                Intent intent = new Intent(Register.this, HomePage.class);
                user loggedUser = register(intent);

                 */
            }
        });

    }
    
}