package utils;
import app.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.EditText;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.sae1.raisehand.R;
/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {
    private String TAG= LoginActivity.class.getSimpleName();
    private Button buttonLogin;
    private TextView msgResponse;
    private ProgressBar pBar;
    private String tag_string_req= "string_req";
    private StringRequest strReq;
    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pBar =  (ProgressBar) findViewById(R.id.loginProgressBar);
        pBar.setVisibility(View.INVISIBLE);
        buttonLogin= (Button) findViewById(R.id.buttonLogin);
        msgResponse = (TextView) findViewById(R.id.msgResponse);

        pBar =  (ProgressBar) findViewById(R.id.loginProgressBar);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pBar.setVisibility(View.VISIBLE);
                userLogin();
            }
        });
    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

<<<<<<< HEAD
        String urlSuffix= "?username="+username+"&password="+password;
        String url_final= URLS.URL_STRING_REQ+urlSuffix;
        showProgressDialog();
        strReq= new StringRequest(Method.GET, url_final, new Response.Listener<String>(){
=======
        strReq= new StringRequest(Method.GET, URLS.URL_STRING_REQ, new Response.Listener<String>(){
>>>>>>> e0bcda278aef3ec5687060298409aed5dd817f67
                    @Override
                    public void onResponse(String response){
                        Log.d(TAG, response.toString());
                        msgResponse.setText(response.toString());
                        pBar.setVisibility(View.INVISIBLE);
                    }}, new Response.ErrorListener(){
                        @Override
                            public void onErrorResponse(VolleyError error){
                                msgResponse.setText("unable to read");
                                Log.d(TAG, "unable to read");
                                VolleyLog.d(TAG, "Error: "+ error.getMessage());
                            pBar.setVisibility(View.INVISIBLE);
                            }
        });




        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        MainActivity.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}