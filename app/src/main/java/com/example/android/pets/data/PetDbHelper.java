package com.example.android.pets.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static com.example.android.pets.data.PetContract.*;

/**
 * Created by dn110 on 08.07.2017.
 */

public class PetDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "Pets.db";
    private Context context;

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public ContentValues createValues(String name, String breed, int gender, int weight){
        ContentValues v = new ContentValues();
        v.put(PetEntry.COLUMN_PET_NAME, name);
        v.put(PetEntry.COLUMN_PET_BREED, breed);
        v.put(PetEntry.COLUMN_PET_GENDER, gender);
        v.put(PetEntry.COLUMN_PET_WEIGHT, weight);
        return v;
    }

    public long insertPet(String name, String breed, int gender, int weight){
        ContentValues v = createValues(name, breed, gender, weight);
        long ret = this.getWritableDatabase().insert(PetEntry.TABLE_NAME, null, v);
        Toast.makeText(context, "Pet saved with id: "+ret, Toast.LENGTH_SHORT).show();
        return ret;
    }


}
