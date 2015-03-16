package com.nkdroid.getwebservice;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(true);
        progressDialog.show();
        callPostWebservice();
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("HOME");
            setSupportActionBar(toolbar);
        }
    }

    private void callPostWebservice() {


        JSONObject userObject = new JSONObject();
        try {
            userObject.put("FirstName", "value");
            userObject.put("LastName", "value");
        }catch (Exception e){
            e.printStackTrace();
        }


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, AppConstants.URL, userObject, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jobj) {
                progressDialog.dismiss();
                String response = jobj.toString();
                Log.e("Response Addrecipient: ", "" + response);



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
            }
        });
        RetryPolicy policy = new DefaultRetryPolicy(0,0,0);
        req.setRetryPolicy(policy);
        MyApplication.getInstance().addToRequestQueue(req);


    }

}
