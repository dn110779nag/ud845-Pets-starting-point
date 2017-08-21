/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetCursorAdapter;
import com.example.android.pets.data.PetDbHelper;
import com.example.android.pets.data.PetContract.PetEntry;

import static com.example.android.pets.data.PetContract.CONTENT_URI;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private PetDbHelper petDbHelper;
    private PetCursorAdapter adapter;

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        petDbHelper = new PetDbHelper(this);
        ListView lvItems = (ListView) findViewById(R.id.list_view_pet);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        lvItems.setEmptyView(emptyView);
        adapter = new PetCursorAdapter(this, null);
        lvItems.setAdapter(adapter);
        displayDatabaseInfo();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertPets();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertPets() {
//        long rowId = petDbHelper.insertPet("Toto", "Terrier", PetEntry.GENDER_MALE, 7);
        getContentResolver().insert(
                CONTENT_URI,
                petDbHelper.createValues("Toto", "Terrier", PetEntry.GENDER_MALE, 7));
        displayDatabaseInfo();
    }



    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
//        SQLiteDatabase db = petDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
//        Cursor cursor = db.query(
//                PetEntry.TABLE_NAME,
//                null,
//                null, null, null, null, PetEntry.COLUMN_PET_NAME);
//        Cursor cursor = getContentResolver().query(PetContract.CONTENT_URI,
//                null, null, null,
//                PetEntry.COLUMN_PET_NAME);



        getLoaderManager().initLoader(0, null, this);

    }



    @Override
    public Loader onCreateLoader(int id, Bundle args) {
//        Cursor cursor = getContentResolver().query(PetContract.CONTENT_URI,
//                null, null, null,
//                PetEntry.COLUMN_PET_NAME);
        return new CursorLoader( this, CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        this.adapter.swapCursor(data);
    }


    @Override
    public void onLoaderReset(Loader loader) {
        this.adapter.swapCursor(null);
    }
}
