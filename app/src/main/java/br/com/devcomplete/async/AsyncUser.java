package br.com.devcomplete.async;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.devcomplete.MainActivity;
import br.com.devcomplete.util.Util;
import br.com.devcomplete.validation.LoginValidation;

/**
 * Created by ricks on 18/07/2016.
 */
public class AsyncUser extends AsyncTask<String, String, String> {

    private LoginValidation loginValidation;

    private Activity activity;

    public AsyncUser(LoginValidation loginValidation){
        this.loginValidation = loginValidation;
        this.activity = loginValidation.getActivity();
    }

    @Override
    protected String doInBackground(String... url) {

        StringBuilder result = new StringBuilder("");

        try {

            String path = url[0];
            path += "?user=" + loginValidation.getLogin() + "&pass=" + loginValidation.getPass();

            URL urlNet = new URL(path);
            HttpURLConnection con = (HttpURLConnection) urlNet.openConnection();
            con.setRequestMethod("POST");
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

        if (Boolean.valueOf(result)){
            SharedPreferences.Editor editor = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE).edit();
            editor.putString("login", loginValidation.getLogin());
            editor.putString("pass", loginValidation.getPass());
            editor.commit();


                Intent i = new Intent(activity, MainActivity.class);
                activity.startActivity(i);
                activity.finish();

        } else{
            Util.showMsgToast(activity, "Login/Senha inválidos!");
        }

    }
}
