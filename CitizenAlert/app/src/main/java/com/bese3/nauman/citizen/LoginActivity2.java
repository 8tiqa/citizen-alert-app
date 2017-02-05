package com.bese3.nauman.citizen;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bese3.nauman.citizen.Unused.User;
import com.bese3.nauman.citizen.Unused.UserService;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request.Method;
import com.bese3.nauman.citizen.data.AppController;
import com.bese3.nauman.citizen.data.CustomRequest;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity2 extends Activity {
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private CheckBox rememberMeCheckBox;
    private TextView forgotPasswordTextView;
    private Button loginButton;
    private Button signUpButton;
    private ProgressDialog progressDialog;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    public void vollry() {
        CustomRequest jsonObjReq = new CustomRequest(Method.POST,
                "http://citizenemergency.web44.net/response.php", null,//ip
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            int t=response.getInt("query");
                            String s=response.getString("data");
                            pref=getSharedPreferences("MyPrefs",MODE_PRIVATE);
                            editor=pref.edit();
                            editor.putString("userid",s);
                            editor.commit();
                            if (s != "null")
                            {

                                Toast.makeText(LoginActivity2.this,
                                        "Login Successfull",
                                        Toast.LENGTH_LONG).show();

                                goToMainMenuActivity();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity2.this,
                                        "Invalid Username Or Password",
                                        Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(LoginActivity2.this,
                                    "connected but not working",
                                    Toast.LENGTH_LONG).show();

                        }
						/*try {

							}
						/*else {
								Toast.makeText(MainActivity.this,
										response.getString("message"),
										Toast.LENGTH_LONG).show();
							}
						 catch (JSONException e) {
							Log.d("checking", e.toString());
							Toast.makeText(MainActivity.this, e.toString(),
									Toast.LENGTH_LONG).show();

						}*/
//						pDialog.hide();
//						load_to_view();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("ddddd", "Error: " + error.getMessage());
                Toast.makeText(LoginActivity2.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {



                Map<String, String> params = new HashMap<String, String>();
                params.put("myqueery", "9");
                params.put("email",  mEmailView.getText().toString());
                params.put("password", mPasswordView.getText().toString());
				//params.put("password","no/or");
                //params.put("email","umairmindfreak");
                //params.put("password","1234");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "application/x-www-form-urlencoded");
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity2);

        /*if(UserService.getInstance(this).getCurrentUser()!=null)
        {
            goToMainMenuActivity();
            return;
        }
*/

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.checkBox_Remember);
        forgotPasswordTextView =  (TextView) findViewById(R.id.forgotpassword);
        loginButton =  (Button) findViewById(R.id.loginbtn);
        signUpButton =  (Button) findViewById(R.id.signupbtn);

        loginButton.setOnClickListener(onClickLoginButtonListener);
        signUpButton.setOnClickListener(onClickSignUpButtonListener);
        forgotPasswordTextView.setOnClickListener(onClickForgotPasswordTextViewListener);


        SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
        boolean remembered = sharedPreferences.getBoolean("remembered",false);
        if(remembered)
        {
            rememberMeCheckBox.setChecked(true);
            String username = sharedPreferences.getString("username", null);
            String password = sharedPreferences.getString("password", null);
            //login(username,password);
        }
    }

   /* private void login(String username,String password)
    {
        progressDialog.show();
        UserService.getInstance(LoginActivity2.this).login(username, password, loginListener);
    }*/

    private void goToSignupActivity()
    {
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

    private void goToMainMenuActivity()
    {
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    View.OnClickListener onClickLoginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           String username = mEmailView.getText().toString();
           String password = mPasswordView.getText().toString();

            vollry();
        }
    };

    View.OnClickListener onClickSignUpButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goToSignupActivity();
        }
    };

    View.OnClickListener onClickForgotPasswordTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity2.this);
            builder.setTitle("Warning");
            builder.setIcon(R.drawable.error);
            builder.setMessage("Not implemented");
            builder.setPositiveButton("OK",null);
            builder.show();
        }
    };

    UserService.LoginListener loginListener = new UserService.LoginListener() {
        @Override
        public void onResponce(boolean loggedin, String message, User user) {
            progressDialog.dismiss();
            Toast.makeText(LoginActivity2.this, message, Toast.LENGTH_SHORT).show();
            if(loggedin)
            {
                SharedPreferences sharedPreferences = getSharedPreferences("user_data",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(rememberMeCheckBox.isChecked())
                {
                    editor.putBoolean("remembered", true);
                    editor.putString("username", user.getUsername());
                    editor.putString("password", user.getPassword());
                }
                else
                {
                    editor.putBoolean("remembered",false);
                    editor.remove("username");
                    editor.remove("password");
                }
                editor.commit();
                goToMainMenuActivity();
            }
        }
    };
}



