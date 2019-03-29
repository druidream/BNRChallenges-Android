package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private Map<UUID, Crime> mCrimes;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.put(crime.getId(), crime);

        }
    }

    public List<Crime> getCrimes() {
        List<Crime> list = new ArrayList<>(mCrimes.values());
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String[] array1 = ((Crime)o1).getTitle().split("#");
                String[] array2 = ((Crime)o2).getTitle().split("#");
                try {
                    int index1 = Integer.parseInt(array1[1]);
                    int index2 = Integer.parseInt(array2[1]);
                    if (index1 < index2) {
                        return -1;
                    } else if (index1 > index2) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    return 0;
                }
            }
        });
        return list;
    }

    public Crime getCrime(UUID id) {
        return mCrimes.get(id);
    }
}
