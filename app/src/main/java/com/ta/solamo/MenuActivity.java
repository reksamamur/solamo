package com.ta.solamo;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ta.solamo.adapter.MenuPagerAdapter;
import com.ta.solamo.bottomsheetdialog.BottomSheetDialogMenuDetail;
import com.ta.solamo.fragment.ExpressBowlFragment;
import com.ta.solamo.fragment.LainnyaFragment;
import com.ta.solamo.fragment.MieFragment;
import com.ta.solamo.fragment.MinumanFragment;
import com.ta.solamo.fragment.SnackFragment;
import com.ta.solamo.fragment.SpesialFragment;

public class MenuActivity extends AppCompatActivity implements BottomSheetDialogMenuDetail.BottomSheetListenerDetail{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ViewPager viewPager = findViewById(R.id.viewpager);
        initBack();
        initTabs(viewPager);
        setupViewPager(viewPager);
    }

    private void initBack(){
        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initTabs(ViewPager pager){
        TabLayout tabLayout = findViewById(R.id.tabs_menu);
        tabLayout.setupWithViewPager(pager);
    }

    private void setupViewPager(ViewPager viewPager){
        MenuPagerAdapter adapter = new MenuPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExpressBowlFragment(), "Express Bowl");
        adapter.addFragment(new SpesialFragment(), "Spesial");
        adapter.addFragment(new MieFragment(), "Mie");
        adapter.addFragment(new MinumanFragment(),"Minuman");
        adapter.addFragment(new SnackFragment(), "Snack");
        adapter.addFragment(new LainnyaFragment(), "Lainnya");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onBottomSheetClickedDetail() {

    }
}