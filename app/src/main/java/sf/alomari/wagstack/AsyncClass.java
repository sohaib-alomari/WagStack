package sf.alomari.wagstack;


//AsynTask to get the Jsondata in Background so the app will stay responsive at all times


import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

public class AsyncClass extends AsyncTask<URL,Void ,String>
        {

            private String URL="https://api.stackexchange.com/2.2/users?site=stackoverflow";




            @Override
            protected String doInBackground(URL... urls) {
                String result="";
                try {
                    result= NetworkUtils.run(URL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {

                    RecyclerView_Adapter.ExtractFeaturesFromJson(s);
                   MainActivity.updateUI();


                }
                catch (Exception e)
                {

                    e.printStackTrace();
                }
            }
        }
