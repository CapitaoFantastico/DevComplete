package br.com.devcomplete.validation;

import android.app.Activity;
import android.widget.EditText;

/**
 * Created by ricks on 17/05/2016.
 */
public class LoginValidation {

    private String login;
    private String pass;

    private EditText edtLogin;
    private EditText edtpass;

    private Activity activity;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String senha) {
        this.pass = senha;
    }

    public EditText getEdtLogin() {
        return edtLogin;
    }

    public void setEdtLogin(EditText edtLogin) {
        this.edtLogin = edtLogin;
    }

    public EditText getEdtpass() {
        return edtpass;
    }

    public void setEdtpass(EditText edtpass) {
        this.edtpass = edtpass;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
