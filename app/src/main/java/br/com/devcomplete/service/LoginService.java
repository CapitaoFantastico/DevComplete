package br.com.devcomplete.service;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import br.com.devcomplete.async.AsyncUser;
import br.com.devcomplete.async.AsyncUserHttpClient;
import br.com.devcomplete.util.Constantes;
import br.com.devcomplete.validation.LoginValidation;
import cz.msebera.android.httpclient.Header;


/**
 * Created by ricks on 16/05/2016.
 */
public class LoginService {

    public void validationLogin(LoginValidation validation){

        boolean result = true;

        if(validation.getLogin() == null || "".equals(validation.getLogin())){
            validation.getEdtLogin().setError("Campo obrigatório!");
            result = false;
        } else if(validation.getLogin().length() < 3){
            validation.getEdtLogin().setError("Campo deve ter no minímo 3 caracteres");
        }

        if(validation.getPass()== null || "".equals(validation.getPass())){
            validation.getEdtpass().setError("Campo obrigatório!");
            result = false;
        }

        if(result){
            RequestParams params = new RequestParams();
            params.put("user", validation.getLogin());
            params.put("pass", validation.getPass());

            AsyncUserHttpClient.post("user/login", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("Sucesso", String.valueOf(statusCode));
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });

            //new AsyncUser(validation).execute(Constantes.URL_WS_LOGIN);
        }

    }

    public void logout()
    {

    }

}
