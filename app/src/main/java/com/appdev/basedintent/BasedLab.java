package com.appdev.basedintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;
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
    }

    public List<Based> getBasedActions(){
        return mCrimes;
    }

    public Based getBased(UUID id){
        if (mId.contains(id)){
            return mCrimes.get(mId.indexOf(id));
        }

        return null;
    }


    public void addBased(Based based) {
        mCrimes.add(based);
        mId.add(based.getId());
    }

}
