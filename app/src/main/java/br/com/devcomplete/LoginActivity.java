package br.com.devcomplete;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.devcomplete.service.LoginService;
import br.com.devcomplete.validation.LoginValidation;


public class LoginActivity extends AppCompatActivity {

    private EditText edtLogin;
    private EditText edtPass;

    private Button btnLogInto;

    private SharedPreferences sharedPreferences;

    private LoginService loginService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginService = new LoginService();

        sharedPreferences = getSharedPreferences("prefs",MODE_PRIVATE);
        String login = sharedPreferences.getString("login", null);
        String pass = sharedPreferences.getString("pass", null);

//        if(login != null && pass != null){
//            Intent i = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(i);
//            finish();
//        }


        edtLogin = (EditText) findViewById(R.id.edt_login);
        edtPass = (EditText) findViewById(R.id.edt_pass);

        btnLogInto = (Button) findViewById(R.id.btn_logInto);

        btnLogInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = edtLogin.getText().toString();
                String pass = edtPass.getText().toString();

                LoginValidation validation = new LoginValidation();
                validation.setActivity(LoginActivity.this);
                validation.setEdtLogin(edtLogin);
                validation.setEdtpass(edtPass);
                validation.setLogin(login);
                validation.setPass(pass);

                loginService.validationLogin(validation);


            }
        });

    }

}
