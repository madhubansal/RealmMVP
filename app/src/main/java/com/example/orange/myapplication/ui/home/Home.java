package com.example.orange.myapplication.ui.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.orange.myapplication.R;
import com.example.orange.myapplication.helper.Constant;
import com.example.orange.myapplication.ui.base.BaseActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends BaseActivity {
    private IHomePresenter presenter;
    FloatingActionButton fab;
    TextView name, email, dob;
    CircleImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getSupportFragmentManager();
        initView();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        presenter = new HomePresenter(this, this);


        name = (TextView) findViewById(R.id.name);
        dob = (TextView) findViewById(R.id.dob);
        email = (TextView) findViewById(R.id.email);
        profileImg = (CircleImageView) findViewById(R.id.profileImg);
        name.setText(getPreferenceString(Constant.NAME));
        email.setText(getPreferenceString(Constant.EMAIL));
        dob.setText(getPreferenceString(Constant.DOB));

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribeCallbacks();
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        presenter = null;
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                presenter.signout();
                break;
        }
    }
}
