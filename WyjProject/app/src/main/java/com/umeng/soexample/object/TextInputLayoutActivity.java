package com.umeng.soexample.object;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.umeng.soexample.R;
import com.umeng.soexample.backLayout.BaseActivity;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextInputLayoutActivity extends BaseActivity {

    @BindView(R.id.accountinput)
    TextInputLayout accountinput;
    @BindView(R.id.password)
    EditText passwordEt;
    @BindView(R.id.passwordinput)
    TextInputLayout passwordinput;
    @BindView(R.id.account)
    EditText accountEt;

    String account, passwd;
    @BindView(R.id.account_sign_in_button)
    Button accountSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        ButterKnife.bind(this);
        accountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(accountinput.getError()!=null){
                    accountinput.setError(null);
                }
            }
        });
        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(passwordinput.getError()!=null){
                    passwordinput.setError(null);
                }
            }
        });


    }


    private boolean isAccountValid(String name) {
        //TODO: Replace this with your own logic
        Pattern pattern = Pattern.compile("^(13[0-9]|14[5|7]|15\\d|17[6|7]|18[\\d])\\d{8}$");
        return pattern.matcher(name).matches();
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    @OnClick(R.id.account_sign_in_button)
    public void onClick() {
        account = accountEt.getText().toString().trim();
        passwd = passwordEt.getText().toString().trim();
        if (TextUtils.isEmpty(account) || !isAccountValid(account)) {
            accountinput.setError("无效手机号");
        }
        if (TextUtils.isEmpty(passwd) || !isPasswordValid(passwd)) {
            passwordinput.setError("密码不少于6位");
        }
    }
}
