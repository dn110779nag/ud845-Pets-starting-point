package com.example.android.pets.data;

import android.provider.BaseColumns;

/**
 * Created by dn110 on 06.07.2017.
 */

public class PetContract {
    private PetContract() {}


    public static final String CREATE_TABLE =
            "CREATE TABLE "+PetEntry.TABLE_NAME
                    +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + PetEntry.COLUMN_PET_NAME+" TEXT, "
                    + PetEntry.COLUMN_PET_BREED + " TEXT, "
                    + PetEntry.COLUMN_PET_GENDER + " INTEGER, " +
                    PetEntry.COLUMN_PET_WEIGHT+ " INTEGER);";

    public static final String DELETE_TABLE = "DELETE FROM "+PetEntry.TABLE_NAME;

    public static final String DROP_TABLE = "DROP TABLE "+PetEntry.TABLE_NAME;


    public static final class PetEntry implements BaseColumns {
        public static final String TABLE_NAME = "pets";
        public static final String COLUMN_PET_NAME = "name";
        public static final String COLUMN_PET_BREED = "breed";
        public static final String COLUMN_PET_GENDER = "gender";
        public static final String COLUMN_PET_WEIGHT = "weight";


        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }
}
