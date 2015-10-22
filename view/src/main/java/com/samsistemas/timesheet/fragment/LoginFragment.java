package com.samsistemas.timesheet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.service.LoginService;
import com.samsistemas.timesheet.service.NetworkStateService;
import com.samsistemas.timesheet.util.InputUtil;
import com.samsistemas.timesheet.util.TypefaceUtil;
import com.samsistemas.timesheet.validation.EmailValidator;
import com.samsistemas.timesheet.validation.PasswordValidator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Class that represents a Login UI.
 *
 * @author jonatan.salas
 */
public class LoginFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener, NetworkStateService.OnNetworkStateReceived {
    private NetworkStateService mNetworkStateService;
    private LoginService mLoginService;

    @Bind(R.id.username_layout)
    TextInputLayout mUsernameInput;

    @Bind(R.id.password_layout)
    TextInputLayout mPasswordInput;

    @Bind(R.id.username)
    EditText mUsernameEditText;

    @Bind(R.id.password)
    EditText mPasswordEditText;

    @Bind(R.id.domain)
    TextView mDomainTextView;

    @Bind(R.id.login_button)
    Button mLoginButton;

    /**
     * Default LoginFragment constructor.
     */
    public LoginFragment() {
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        final Typeface robotoRegular = TypefaceUtil.getCustomTypeface(
                getApplicationContext(),
                R.string.roboto_regular
        );

        mUsernameInput.requestFocus();
        mUsernameEditText.requestFocus();

        //Styling views..
        mUsernameInput.setTypeface(robotoRegular);
        mPasswordInput.setTypeface(robotoRegular);
        mUsernameEditText.setTypeface(robotoRegular);
        mPasswordEditText.setTypeface(robotoRegular);

        //Setting listeners..
        mUsernameEditText.setOnFocusChangeListener(this);
        mPasswordEditText.setOnFocusChangeListener(this);
        mLoginButton.setOnFocusChangeListener(this);
        mLoginButton.setOnClickListener(this);

        mNetworkStateService = new NetworkStateService(this);
        final IntentFilter filter = new IntentFilter(NetworkStateService.CONNECTIVITY_CHANGE_ACTION);
        getActivity().registerReceiver(mNetworkStateService, filter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUsernameEditText.setOnFocusChangeListener(null);
        mPasswordEditText.setOnFocusChangeListener(null);
        mLoginButton.setOnFocusChangeListener(null);
        mLoginButton.setOnClickListener(null);

        getActivity().unregisterReceiver(mNetworkStateService);

        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(@NonNull View view) {
        final int id = view.getId();

        switch (id) {
            case R.id.login_button:
                final String domain = mDomainTextView.getText().toString().trim();
                final String username = mUsernameEditText.getText().toString().trim();
                final String password = mPasswordEditText.getText().toString().trim();
                final String email = username.concat(domain);

                final String[] credentials = new String[] {
                        email,
                        password
                };

                doLogin(getActivity(), credentials, view);

                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        final int id = v.getId();

        switch (id) {
            case R.id.username:
                if(hasFocus)
                    InputUtil.showKeyboard(getActivity());
                else
                    InputUtil.hideKeyboard(getActivity());

                break;
            case R.id.password:
                if(hasFocus)
                    InputUtil.showKeyboard(getActivity());
                else
                    InputUtil.hideKeyboard(getActivity());

                break;
            case R.id.login_button:
                if(hasFocus)
                    InputUtil.hideKeyboard(getActivity());
                break;
        }
    }

    /***
     * Method that checks email and password. After checking it performs login.
     *
     * @param context - the activity where this method is going to be used.
     * @param credentials - the email and password as a String array.
     * @param view - the view used for animations.
     */
    protected void doLogin(@NonNull Activity context,
                           @NonNull final String[] credentials,
                           @NonNull final View view) {

        final String email = credentials[0];
        final String password = credentials[1];

        final EmailValidator emailValidator = EmailValidator.newInstance();
        final PasswordValidator passwordValidator = PasswordValidator.newInstance();

        if(!emailValidator.validate(email) && !passwordValidator.validate(password)) {
            Snackbar.make(view, context.getString(R.string.email_password_error), Snackbar.LENGTH_LONG).show();
            mUsernameEditText.setText("");
            mPasswordEditText.setText("");
            mUsernameEditText.requestFocus();
        } else if(!emailValidator.validate(email)) {
            Snackbar.make(view, context.getString(R.string.email_error), Snackbar.LENGTH_LONG).show();
            mUsernameEditText.setText("");
            mUsernameEditText.requestFocus();
        } else if(!passwordValidator.validate(password)) {
            Snackbar.make(view, context.getString(R.string.password_error), Snackbar.LENGTH_LONG).show();
            mPasswordEditText.setText("");
            mPasswordEditText.requestFocus();
        } else {
            //Do login with service task.
            mLoginService = LoginService.newInstance(context, view);
            mLoginService.execute(credentials);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if((null != mLoginService) && (mLoginService.getStatus() == AsyncTask.Status.RUNNING)) {
            mLoginService.cancel(true);
        }
    }

    /**
     * Method that gets the applicationContext.
     *
     * @return a context object.
     */
    private Context getApplicationContext() {
        return getContext().getApplicationContext();
    }

    @Override
    public void checkNetworkState(Context context, Boolean isConnected) {
        if (!isConnected) {
            checkNullability(false);
        } else {
            checkNullability(true);
        }
    }

    /**
     *
     * @param enabled
     */
    protected void checkNullability(@NonNull final Boolean enabled) {
        if(null != mUsernameInput) {
            mUsernameInput.setFocusable(enabled);
            mUsernameInput.setFocusableInTouchMode(enabled);
            mUsernameInput.setEnabled(enabled);
        }

        if(null != mPasswordInput)
            mPasswordInput.setEnabled(enabled);

        if(null != mUsernameEditText)
            mUsernameEditText.setEnabled(enabled);

        if(null != mPasswordEditText)
            mPasswordEditText.setEnabled(enabled);

        if(null != mLoginButton)
            mLoginButton.setEnabled(enabled);
    }
}
