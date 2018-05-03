package com.mobile.iglo.training;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.iglo.training.ServiceModel.Authentication.Auth;
import com.mobile.iglo.training.services.APIClient;
import com.mobile.iglo.training.services.APIInterfacesRest;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText txtUsername, txtPass;
    Button btnLogin, btnCancel;

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data){
    if(requestCode==111){
        if(resultCode==69){
            Toast.makeText(LoginActivity.this,data.getStringExtra("data"),Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LoginActivity.this,data.getStringExtra("data"),Toast.LENGTH_SHORT).show();
        }
    }
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (EditText)findViewById(R.id.user_edit);
        txtPass = (EditText)findViewById(R.id.password_edit);
        btnLogin=(Button)findViewById(R.id.btn_login);
        btnCancel=(Button)findViewById(R.id.btn_cancel);
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(LoginActivity.this,"Hello Coy", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onClick(View v) {

        if(v==btnLogin){
////            Toast.makeText(LoginActivity.this,"Ini Button Login",Toast.LENGTH_LONG).show();
////            Intent intent = new Intent(LoginActivity.this, WelcomeScreen.class);
////            intent.putExtra("username", txtUsername.getText().toString());
////            //startActivity(intent);
////            startActivityForResult(intent, 111);
            callAuthentication(txtUsername.getText().toString(),txtPass.getText().toString());
        } else{
//            Toast.makeText(LoginActivity.this,"Ini Button Cancel",Toast.LENGTH_LONG).show();
            Toast.makeText(LoginActivity.this,"Ini Button Login",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, DetailScreen.class);
            intent.putExtra("username", txtUsername.getText().toString());
            //startActivity(intent);
            startActivityForResult(intent, 111);
        }
    }
    ProgressDialog progressDialog;
    APIInterfacesRest apiInterface;
    public void callAuthentication(final String username, String password){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<Auth> call3 = apiInterface.getAuthentication(username,password);
        call3.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(Call<Auth> call, Response<Auth> response) {
                progressDialog.dismiss();
                Auth userList = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (userList !=null) {
//                    sharedPreferencesUtil.setUsername(username);
//                    AppController.setUsername(username);

                    startActivity(new Intent(LoginActivity.this, WelcomeScreen.class));
                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Auth> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }
}
