package br.com.devcomplete.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import br.com.devcomplete.R;
import br.com.devcomplete.entity.Person;
import br.com.devcomplete.util.Constantes;
import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by ricks on 14/07/2016.
 */
public class FragmentProfile extends Fragment{

    private Button btnRegister;
    private TextInputLayout lytTxtName;
    private TextView txtName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false); //Inflate First, everything in my fragment are hear!

        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        lytTxtName = (TextInputLayout) view.findViewById(R.id.lytTxtName);
        txtName = (TextView) view.findViewById(R.id.edtName);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateName()){
                    return;
                }

                final Gson gson = new Gson();
                String json = gson.toJson(createPerson()); //GSON

                /* Put Json elements on Json object
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("id", 0);
                    jsonObject.put("descricao", "aaa");
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                try {
                    //StringEntity stringEntity = new StringEntity(jsonObject.toString()); //Using Json Object
                    StringEntity stringEntity = new StringEntity(json);
                    new AsyncHttpClient().post(null, Constantes.URL_WS_BASE + "user/add", stringEntity, "application/json", new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("response", response.toString());
                            gson.fromJson(response.toString(), Person.class);
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }

        private boolean validateName(){
            if(txtName.getText().toString().trim().isEmpty()){
                lytTxtName.setError("The name field is required!");
                return false;
            } else {
                lytTxtName.setErrorEnabled(false);
            }
                return true;
        }

    private Person createPerson(){
        Person person = new Person();
        person.setBio("It's me!");
        person.setCodOccupation(1);
        person.setEmail("henrique@test.com");
        person.setGender('M');
        person.setName("Henrique Souza");
        return person;
    }

    }
