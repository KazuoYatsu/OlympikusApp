package com.appolympikus.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.CallbackManager;
import com.facebook.login.LoginResult;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.FacebookException;
import com.facebook.FacebookCallback;
import com.facebook.AccessToken;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;


import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Base64;
import android.util.Log;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Produto> lsProduto;
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private Boolean LogadoNoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;

        if(loggedIn){
            Log.d("Estou logado","Nao");
            FacebookSdk.sdkInitialize(getApplicationContext());
            //AppEventsLogger.activateApp(this);
            callbackManager = CallbackManager.Factory.create();

            loginButton = (LoginButton) findViewById(R.id.login_button);
            loginButton.setReadPermissions("email");
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
            LoginManager.getInstance().logInWithPublishPermissions(
                    this,
                    Arrays.asList("publish_actions"));


            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code
                    Context context = getApplicationContext();
                    CharSequence text = "Hello face!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

                @Override
                public void onCancel() {
                    // App code
                }

                @Override
                public void onError(FacebookException exception) {
                    // App code

                    Context context = getApplicationContext();
                    CharSequence text = "Erro ao conectar ao facebook, verifique sua conexão!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            });





        }else{

            Log.d("Estou logado","Sim");


        }


        LogadoNoDatabase = false;

        if(LogadoNoDatabase)
        {

        }
        else
        {

            Toast.makeText(this, "Fazer Cadastro Activity", Toast.LENGTH_LONG).show();
            //Log.d("TAG", )
        }
        carregarListaProdutos();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    private void carregarListaProdutos() {

        lsProduto = new ArrayList<>();
        lsProduto.add(new Produto("RF.:X99-9999-999-999","Masculino", "Descricao produto - ",R.drawable.img1_small));
        lsProduto.add(new Produto("RF.:X88-8888-888-888","Feminino", "Descricao produto - ",R.drawable.img2_small));
        lsProduto.add(new Produto("RF.:77-7777-777-777","Masculino", "Descricao produto - ",R.drawable.img3_small));
        lsProduto.add(new Produto("RF.:X69-9999-999-999","Masculino", "Descricao produto - ",R.drawable.img4_small));
        lsProduto.add(new Produto("RF.:X58-8888-888-888","Feminino", "Descricao produto - ",R.drawable.img5_small));
        lsProduto.add(new Produto("RF.:47-7777-777-777","Masculino", "Descricao produto - ",R.drawable.img6_small));
        lsProduto.add(new Produto("RF.:37-7777-777-777","Masculino", "Descricao produto - ",R.drawable.img7_small));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.reciclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, lsProduto);


        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_banners) {


        } else if (id == R.id.nav_videos) {

        } else if (id == R.id.nav_roupas) {

        } else if (id == R.id.nav_acess) {

        } 


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
