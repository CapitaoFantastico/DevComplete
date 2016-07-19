package br.com.devcomplete.async;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ricks on 18/07/2016.
 */
public class AsyncUser extends AsyncTask<String, String, String> {

    private Activity activity;

    public AsyncUser(Activity activity){
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... url) {

        StringBuilder result = new StringBuilder("");

        try {
            URL urlNet = new URL(url[0]);
            HttpURLConnection con = (HttpURLConnection) urlNet.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.connect();

            InputStream inp = con.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inp));

            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                result.append(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(activity, result, Toast.LENGTH_LONG).show();
    }
}
