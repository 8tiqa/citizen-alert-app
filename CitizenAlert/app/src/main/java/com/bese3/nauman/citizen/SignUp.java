package com.bese3.nauman.citizen;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.bese3.nauman.citizen.data.AppController;
import com.bese3.nauman.citizen.data.CustomRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class SignUp extends Activity {

    private ImageView profileImageView;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private EditText cnicEditText;
    private EditText cellnoEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private Button signupButton;
    private ProgressDialog progressDialog;
    private Bitmap bitmap;

    public void vollby() {
        CustomRequest jsonObjReq = new CustomRequest(Request.Method.POST,
                "http://citizenemergency.web44.net/response.php", null,//ip
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            int t=response.getInt("query");
                            String s=response.getString("data");
                            if (s != "null")
                            {
                                Toast.makeText(SignUp.this,
                                        "Sign Up Succesful",
                                        Toast.LENGTH_LONG).show();

                                goToLoginActivity2();
                            }
                            else
                            {
                                Toast.makeText(SignUp.this,
                                        "Invalid Username,Password,Email,CNIC or Cell No",
                                        Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            Toast.makeText(SignUp.this,
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
                Toast.makeText(SignUp.this, error.toString(),
                        Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("myqueery", "0");
                params.put("username",  usernameEditText.getText().toString());
                //params.put("username", "admin");
                params.put("password", passwordEditText.getText().toString());
                //params.put("password", "admin");
                params.put("email",emailEditText.getText().toString());
                //params.put("email","admin@example.com");
                params.put("gender","male");
                //params.put("gender", femaleRadioButton.getText().toString());
                params.put("cnic",cnicEditText.getText().toString());
                //params.put("cnic","32432");
                params.put("phone",cellnoEditText.getText().toString());
                //params.put("phone","123123");
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

    private static final int SELECT_PICTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

       /* if(UserService.getInstance(this).getCurrentUser()!=null)
        {
            goToMainMenuActivity();
        }*/

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);

        profileImageView = (ImageView) findViewById(R.id.imageview_profile);
        usernameEditText = (EditText) findViewById(R.id.edittext_username);
        passwordEditText = (EditText) findViewById(R.id.edittext_password);
        emailEditText = (EditText) findViewById(R.id.edittext_email);
        cnicEditText = (EditText) findViewById(R.id.edittext_cnic);
        cellnoEditText = (EditText) findViewById(R.id.edittext_cellno);
        genderRadioGroup = (RadioGroup) findViewById(R.id.radiogroup_gender);
        maleRadioButton = (RadioButton) findViewById(R.id.radiobutton_male);
        femaleRadioButton = (RadioButton) findViewById(R.id.radiobutton_female);
        signupButton = (Button) findViewById(R.id.btn_signup);

        maleRadioButton.setChecked(true);
        signupButton.setOnClickListener(onClickSignUpButtonListener);
        profileImageView.setOnClickListener(onClickProfileImageViewListener);
    }

    View.OnClickListener onClickProfileImageViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            chooseImage();
        }
    };

    private void chooseImage()
    {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK)
            try {
                // We need to recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }
                InputStream stream = getContentResolver().openInputStream(data.getData());
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize=2;
                bitmap = BitmapFactory.decodeStream(stream, null, options);
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                double scale = 100.0/height;
                height = (int)(height*scale);
                width = (int)(width*scale);
                bitmap = Bitmap.createScaledBitmap(bitmap, width,height, false);
                stream.close();
                profileImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        super.onActivityResult(requestCode, resultCode, data);
    }


    View.OnClickListener onClickSignUpButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                 vollby();

               /* String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String cnic = cnicEditText.getText().toString();
                String cellno = cellnoEditText.getText().toString();

                User.Gender gender;
                if (genderRadioGroup.getCheckedRadioButtonId() == R.id.radiobutton_male) {
                    gender = User.Gender.MALE;
                } else {
                    gender = User.Gender.FEMALE;
                }*/
        }


    };

/*    private void register(String username,String password, String email, String cnic, String cellno, User.Gender gender)
    {
        progressDialog.show();
        UserService.getInstance(SignUp.this).register(username,password,email,cnic,cellno,gender,registerListener,bitmap);
    }*/

 /*   UserService.RegisterListener registerListener = new UserService.RegisterListener() {
        @Override
        public void onResponce(boolean registered, String message, User user) {
            progressDialog.dismiss();
            Toast.makeText(SignUp.this, message, Toast.LENGTH_SHORT).show();
            if(registered)
            {
                goToMainMenuActivity();
            }
        }
    };
*/
    private void goToMainMenuActivity()
    {
        Intent intent = new Intent(this,MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void goToLoginActivity2()
    {
        Intent intent = new Intent(this,LoginActivity2.class);
        startActivity(intent);
        //finish();
    }
}
