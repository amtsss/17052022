package com.example.mybank.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String DB_NAME = "fb_mei_bank";
    private static final int DB_VERSION=1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: started");
        //Them bảng user
        String createUserTable = " CREATE TABLE users(_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL," +
                "password TEXT NOT NULL,"+
        "first_name TEXT, last_name TEXT, address TEXT, image_url TEXT, remained_amount DOUBLE)";

        // thêm bảng shopping

        String createShoppingTable=" CREATE TABLE shopping (_id INTEGER PRIMARY KEY AUTOINCREMENT, item_id INTEGER," +
                "user_id INTEGER, transaction_id INTEGER, price DOUBLE, date DATE, discription TEXT)";
        // Them bangr

        String createInvestmentTable =" CREATE TABLE investments (_id INTEGER PRIMARY KEY AUTOINCREMENT, amount DOUBLE," +
                "monthly_roi DOUBLE, name TEXT, init_date DATE, finish_date DATE, user_id INTEGER, transaction_id INTEGER)";

        // Them

        String createLoansTable= " CREATE TABLE loans (_id INTEGER PRIMARY KEY AUTOINCREMENT, init_date DATE," +
                "finish_date DATE, init_amount DOUBLE, remained_amount DOUBLE, monthly_payment DOUBLE, monthly_roi DOUBLE," +
                "name TEXT, user_id INTERGER)";

        // Them
        String createTransactionTable ="CREATE TABLE transactions (_id INTEGER PRIMARY KEY AUTOINCREMENT, amount DOUBLE," +
                "date DATE, type TEXT, user_id INTERGER, recipient TEXT, description TEXT)";

        // Them

        String createItemsTable = "CREATE TABLE items (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image_url TEXT," +
                "description TEXT)";

        sqLiteDatabase.execSQL(createUserTable);
        sqLiteDatabase.execSQL(createShoppingTable);
        sqLiteDatabase.execSQL(createInvestmentTable);
        sqLiteDatabase.execSQL(createLoansTable);
        sqLiteDatabase.execSQL(createTransactionTable);
        sqLiteDatabase.execSQL(createItemsTable);

        addInitialItems(sqLiteDatabase);

    }
    private void addInitialItems(SQLiteDatabase db){
        Log.d(TAG, "addInitialItems: started");
        ContentValues values = new ContentValues();
        values.put("name", "Bike");
        values.put("image_url","https://cdn.shopify.com/static/sample-images/teapot.jpg");
        values.put("description","The perfect mountain bike");
        db.insert("items",null,values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
