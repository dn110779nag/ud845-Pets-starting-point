package com.example.android.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by dn110 on 06.07.2017.
 */

public class PetContract {
    private PetContract() {}

    public static final String LOG_TAG = "pets";

    public static final String CONTENT_AUTHORITY = "com.example.android.pets";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+ CONTENT_AUTHORITY);
    public static final String PATH_PETS = "pets";
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

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
        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;


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
