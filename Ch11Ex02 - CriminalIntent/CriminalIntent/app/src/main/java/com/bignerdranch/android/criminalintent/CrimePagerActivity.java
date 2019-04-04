package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mJumpToFirstButton;
    private Button mJumpToLastButton;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mJumpToFirstButton = (Button) findViewById(R.id.jump_to_first);
        mJumpToFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCrimes.size() == 0)
                    return;
                mViewPager.setCurrentItem(0);
            }
        });
        mJumpToLastButton = (Button) findViewById(R.id.jump_to_last);
        mJumpToLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCrimes.size() == 0)
                    return;
                mViewPager.setCurrentItem(mCrimes.size() - 1);
            }
        });

        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager) findViewById(R.id.crime_view_pager);

        mCrimes = CrimeLab.get(this).getCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        mViewPager.addOnPageChangeListener(this);

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                // the two buttons must be initialized before the view pager
                // or here will be a crash
                setButtonState(i);
                break;
            }
        }
    }

    private void setButtonState(int position) {
        if (mCrimes.size() == 0) {
            mJumpToFirstButton.setEnabled(false);
            mJumpToLastButton.setEnabled(false);
        }
        if (position == 0) {
            mJumpToFirstButton.setEnabled(false);
        } else {
            mJumpToFirstButton.setEnabled(true);
        }
        if (position == mCrimes.size() - 1) {
            mJumpToLastButton.setEnabled(false);
        } else {
            mJumpToLastButton.setEnabled(true);
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setButtonState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
