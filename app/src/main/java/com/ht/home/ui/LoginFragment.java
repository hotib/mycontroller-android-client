package com.ht.home.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ht.home.ApplicationObject;
import com.ht.home.R;
import com.ht.home.bl.PreferencesManager;
import com.ht.home.bl.UserManager;
import com.ht.home.util.GenericCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements GenericCallback {

    @BindView(R.id.et_host)
    TextView mHost;

    @BindView(R.id.et_user)
    TextView mUser;

    @BindView(R.id.et_pass)
    TextView mPass;

    @OnClick(R.id.btn_login) void onloginClick() {

        if (!mHost.getText().toString().startsWith("http://") || !mHost.getText().toString().startsWith("https://"))
            mHost.setText("http://"+mHost.getText().toString());

        if (!mHost.getText().toString().endsWith("/"))
            mHost.setText(mHost.getText().toString()+"/");

        PreferencesManager.getInstance().saveHost(mHost.getText().toString());
        ApplicationObject.getApplication().createAPI();

        UserManager.getInstance().login(mUser.getText().toString(), mPass.getText().toString(), this);
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onSuccess(Object response) {
        ((MainActivity)getActivity()).showDashboard();
    }

    @Override
    public void onError(String error) {

    }
}
