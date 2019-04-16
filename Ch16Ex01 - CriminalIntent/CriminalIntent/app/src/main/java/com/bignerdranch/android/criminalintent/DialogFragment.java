package com.bignerdranch.android.criminalintent;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends Fragment {

    private File mImageFile;

    private ImageView mImageView;

    public File getImageFile() {
        return mImageFile;
    }

    public void setImageFile(File imageFile) {
        mImageFile = imageFile;
    }

    public DialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        mImageView = (ImageView) v.findViewById(R.id.image_view);
        Bitmap bitmap = PictureUtils.getScaledBitmap(
                mImageFile.getPath(), getActivity());
        mImageView.setImageBitmap(bitmap);

        return v;
    }

}
