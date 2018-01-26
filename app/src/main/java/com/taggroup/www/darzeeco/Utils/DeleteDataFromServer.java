package com.taggroup.www.darzeeco.Utils;

import java.util.HashMap;

import org.json.JSONObject;

import android.os.AsyncTask;
import android.widget.Toast;


import org.json.JSONException;

import android.content.Context;

import com.taggroup.www.darzeeco.UsersContent.RequestHandler;

/**
 * Created by muhammad.sufwan on 1/23/2018.
 */

public class DeleteDataFromServer {

    public DeleteDataFromServer(Context mContext) {
        this.mContext = mContext;
    }

    private Context mContext;

    public void DeleteSize(final String sizeId) {


        final String Url = "http://192.168.2.41/darzee/delete_size_list.php";
        class deleteSize extends AsyncTask<Void, Void, String> {


            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("size_id", sizeId);

                //returing the response
                return requestHandler.sendPostRequest(Url, params);
            }

            protected void onPreExecute() {
                super.onPreExecute();
                //displaying the progress bar while user registers on the server

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion


                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);
                    boolean error = obj.getBoolean("error");


                    //if no error in response
                    if (error == false) {
                        Toast.makeText(mContext, obj.getString("message"), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(mContext, obj.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, e.getMessage().toUpperCase(), Toast.LENGTH_LONG).show();
                }
            }
        }

        //executing the async task
        deleteSize ru = new deleteSize();
        ru.execute();

    }

}
