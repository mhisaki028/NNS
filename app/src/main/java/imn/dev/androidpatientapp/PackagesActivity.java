package imn.dev.androidpatientapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class PackagesActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_packages);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("Package 1");
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Package 2");
        tabLayout.addTab(secondTab);

        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Package 3");
        tabLayout.addTab(thirdTab);

        TabLayout.Tab fourthTab = tabLayout.newTab();
        thirdTab.setText("Package 4");
        tabLayout.addTab(fourthTab);

        TabLayout.Tab fifthTab = tabLayout.newTab();
        thirdTab.setText("Package 5");
        tabLayout.addTab(fifthTab);

        TabLayout.Tab sixthTab = tabLayout.newTab();
        thirdTab.setText("Package 6");
        tabLayout.addTab(sixthTab);

        TabLayout.Tab seventhTab = tabLayout.newTab();
        thirdTab.setText("Package 7");
        tabLayout.addTab(seventhTab);

        TabLayout.Tab eightTab = tabLayout.newTab();
        thirdTab.setText("Package 8");
        tabLayout.addTab(eightTab);

        TabLayout.Tab ninthTab = tabLayout.newTab();
        thirdTab.setText("Package 9");
        tabLayout.addTab(ninthTab);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
