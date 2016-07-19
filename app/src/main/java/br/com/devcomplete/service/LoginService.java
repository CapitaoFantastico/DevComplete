package br.com.devcomplete.service;

import br.com.devcomplete.async.AsyncUser;
import br.com.devcomplete.util.Constantes;
import br.com.devcomplete.validation.LoginValidation;


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
            new AsyncUser(validation).execute(Constantes.URL_WS_LOGIN);
        }

    }

    public void logout()
    {

    }

}
