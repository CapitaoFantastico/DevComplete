package br.com.devcomplete.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import br.com.devcomplete.MainActivity;
import br.com.devcomplete.async.AsyncUser;
import br.com.devcomplete.async.AsyncUserHttpClient;
import br.com.devcomplete.util.Constantes;
import br.com.devcomplete.util.Util;
import br.com.devcomplete.validation.LoginValidation;
import cz.msebera.android.httpclient.Header;


/**
 * Created by ricks on 16/05/2016.
 */
public class LoginService {

    public void validationLogin(final LoginValidation validation){

        final Activity activity = validation.getActivity();

        boolean result = true;

        if(validation.getLogin() == null || "".equals(validation.getLogin())){
            validation.getEdtLogin().setError("This field is required!");
            result = false;
        } else if(validation.getLogin().length() < 3){
            validation.getEdtLogin().setError("Minimum 3 characters");
        }

        if(validation.getPass()== null || "".equals(validation.getPass())){
            validation.getEdtpass().setError("This field is required");
            result = false;
        }

        if(result){
            RequestParams params = new RequestParams();
            params.put("user", validation.getLogin());
            params.put("pass", validation.getPass());

            AsyncUserHttpClient.post("user/login", params, new TextHttpResponseHandler() {
                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Log.e(LoginService.class.getName(), "Erro in Login method! Http Code: " + statusCode, throwable);
                            Util.showMsgSimpleToast(activity, "Erro in Login method!");
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, String responseString) {

                            if (Boolean.valueOf(responseString)){
                                SharedPreferences.Editor editor = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE).edit();
                                editor.putString("login", validation.getLogin());
                                editor.putString("pass", validation.getPass());
                                editor.commit();

                                Intent i = new Intent(activity, MainActivity.class);
                                activity.startActivity(i);
                                activity.finish();

                            } else{
                                Util.showMsgSimpleToast(activity, "User/Pass Invalid!");
                            }
                        }
                    });
                    //new AsyncUser(validation).execute(Constantes.URL_WS_LOGIN);
        }

    }

    public void logout()
    {

    }

}
