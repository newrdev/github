package com.experimental.newrdev.github.ui.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.experimental.newrdev.github.App;
import com.experimental.newrdev.github.AppComponent;
import com.experimental.newrdev.github.R;
import com.experimental.newrdev.github.models.User;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by newrdev on 7/1/15.
 */
public class SearchActivity extends Activity implements SearchView{

    @Inject
    SearchPresenter presenter;

    @Bind(R.id.searchText)
    EditText searchText;

    //@Bind(R.id.searchButton)
    //Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent((AppComponent) App.get(this).component());
        setContentView(R.layout.search_activity);
        //searchButton.setOnClickListener(this);

        ButterKnife.bind(this);
    }

    private void setupComponent(AppComponent component){
        DaggerSearchComponent.builder()
                .appComponent(component)
                .searchModule(new SearchModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void navigateToUserActivity(User user) {
        Toast.makeText(this, "Found User! " + user.getAvatarUrl(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void noUserFound() {

    }

    @OnClick(R.id.searchButton) void search(){
        presenter.search("newrdev");
    }
}
