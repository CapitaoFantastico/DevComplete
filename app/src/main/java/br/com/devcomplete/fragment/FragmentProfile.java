package br.com.devcomplete.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.com.devcomplete.R;

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
    }
