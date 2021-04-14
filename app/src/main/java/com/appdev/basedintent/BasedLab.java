package com.appdev.basedintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BasedLab {

    private static BasedLab sCrimeLab;

    private List<Based> mCrimes;
    private List<UUID> mId;


    public static BasedLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab = new BasedLab(context);
        }
        return sCrimeLab;
    }

    private BasedLab(Context context){
        mCrimes = new ArrayList<>();
        mId = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            Based crime = new Based();
            crime.setTitle("Based Act #" + i);
            crime.setBased(i%2 == 0);
            mCrimes.add(crime);
            mId.add(crime.getId());
        }
    }

    public List<Based> getCrimes(){
        return mCrimes;
    }

    public Based getCrime(UUID id){
        if (mId.contains(id)){
            return mCrimes.get(mId.indexOf(id));
        }

        return null;
    }


}
