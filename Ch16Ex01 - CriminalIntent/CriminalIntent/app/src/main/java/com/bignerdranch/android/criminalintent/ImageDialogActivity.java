package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

import java.io.File;

public class ImageDialogActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        File imageFile = (File) getIntent().getSerializableExtra("photoFile");
        DialogFragment fragment = new DialogFragment();
        fragment.setImageFile(imageFile);
        return fragment;
    }
}
