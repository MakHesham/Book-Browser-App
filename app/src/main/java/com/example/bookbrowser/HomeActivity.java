package com.example.bookbrowser;

import android.content.Intent;
import android.os.Bundle;

import com.example.bookbrowser.Prevalent.Prevalent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private Button  dramaBtn,comicBtn,thrillerBtn;
    private Button religiousBtn,biopicBtn,educationalBtn;
    private Button scienceFictionBtn,novelBtn;




    ViewFlipper imageBanner;
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);







        scienceFictionBtn =(Button) findViewById(R.id.science_fictionBtn);
        novelBtn =(Button) findViewById(R.id.novelBtn);
        thrillerBtn =(Button) findViewById(R.id.thrillerBtn);
        dramaBtn =(Button) findViewById(R.id.dramaBtn);
        comicBtn =(Button) findViewById(R.id.comicBtn);
        educationalBtn =(Button) findViewById(R.id.educationalBtn);
        religiousBtn =(Button) findViewById(R.id.religiousBtn);
        biopicBtn =(Button) findViewById(R.id.biopicBtn);



        scienceFictionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this,Science_fictionActivity.class);
                //intent.putExtra("category","Science Fiction");
                startActivity(intent);

            }
        });



        novelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this,NovelActivity.class);
                //intent.putExtra("category","Novel");
                startActivity(intent);

            }
        });



        thrillerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this,ThrillerActivity.class);
               // intent.putExtra("category","Thriller");
                startActivity(intent);

            }
        });



        dramaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this,DramaActivity.class);
                //intent.putExtra("category","Drama");
                startActivity(intent);

            }
        });



        comicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this,ComicActivity.class);
               // intent.putExtra("category","Comic");
                startActivity(intent);

            }
        });


        educationalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this,EducationalActivity.class);
               // intent.putExtra("category","Educational");
                startActivity(intent);

            }
        });


        religiousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this,ReligiousActivity.class);
              //  intent.putExtra("category","Religious");
                startActivity(intent);

            }
        });



        biopicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HomeActivity.this,BiopicActivity.class);
               // intent.putExtra("category","Biopic");
                startActivity(intent);

            }
        });



        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        Paper.init(this);


        imageBanner = (ViewFlipper) findViewById(R.id.imageBanner);
        int sliders[] = {
                R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4,R.drawable.banner5,R.drawable.banner6 ,R.drawable.banner7};
        for(int slide:sliders){
            bannerFlipper(slide);
        }






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);


            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);



            userNameTextView.setText(Prevalent.currentOnlineUser.getName());
            Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        }



        public  void  bannerFlipper(int image)
        {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(image);
            imageBanner.addView(imageView);
            imageBanner.setFlipInterval(6000);
            imageBanner.setAutoStart(true);
            imageBanner.setInAnimation(this, android.R.anim.fade_in);
            imageBanner.setOutAnimation(this,android.R.anim.fade_out);
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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

//        if (id == R.id.action_settings)
//        {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {

            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);



        }
        else if (id == R.id.nav_cart)
        {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_order)
        {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_settings)
        {
            Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
            startActivity(intent);


        }
        else if (id == R.id.nav_logout)
        {

                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this, WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
