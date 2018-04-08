package com.example.user.cg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.cg.Model.User;
import com.example.user.cg.common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Login extends AppCompatActivity {


    Button SignInbtn;
    TextView txtNoAccount;
    EditText edtPhone, edtPassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        SignInbtn = (Button)findViewById(R.id.SignInbtn);




       //initial firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        SignInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(Login.this);
                mDialog.setMessage("Please waiting....");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {


                            //Get user information
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            user.setPhone(edtPhone.getText().toString()); //set phone
                            if (user.getPassword().equals(edtPassword.getText().toString())) {


                                {
                                    Intent homeIntent = new Intent(Login.this, Drawer.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();
                                }
                            }else
                        {
                                Toast.makeText(Login.this, "Sign in failed!", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(Login.this, "user not exist in Database", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        //set font for the text for register
        txtNoAccount = (TextView)findViewById(R.id.txtNoAccount);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Lato-Hairline.ttf");
        txtNoAccount.setTypeface(face);





    }
}
