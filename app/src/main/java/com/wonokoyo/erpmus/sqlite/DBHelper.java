package com.wonokoyo.erpmus.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "muserp.db";
    public static final String TABLE_ATTACHMENT = "attachment";
    public static final String TABLE_MITRA = "mitra";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sql = "CREATE TABLE " + TABLE_ATTACHMENT + "(id integer, id_rhk int, tipe text, url text)";
        database.execSQL(sql);

        String sql2 = "CREATE TABLE " + TABLE_MITRA + "(id integer, nama text, noreg text, kandang text, populasi integer, umur integer)";
        database.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertAttachment(int id, int id_rhk, String tipe, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_rhk", id_rhk);
        cv.put("tipe", tipe);
        cv.put("url", url);

        long result = db.insert(TABLE_ATTACHMENT, null, cv);
        return result;
    }

    public long insertMitra(String id, String nama, String noreg, String kandang, int populasi, int umur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("nama", nama);
        cv.put("noreg", noreg);
        cv.put("kandang", kandang);
        cv.put("populasi", populasi);
        cv.put("umur", umur);

        long result = db.insert(TABLE_MITRA, null, cv);
        return result;
    }

    public int jumlahMitra() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_MITRA;
        Cursor c = db.rawQuery(sql, null);

        return c.getCount();
    }

    public void hapusSemuaMitra() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + TABLE_MITRA;
        db.execSQL(sql);
    }

    public Cursor ambilSemuaMitra() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT DISTINCT nama FROM " + TABLE_MITRA;
        Cursor c = db.rawQuery(sql, null);

        return c;
    }

    public Cursor ambilNoregMitra(String nama) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_MITRA + " WHERE nama = '" + nama + "'";
        Cursor c = db.rawQuery(sql, null);

        return c;
    }

    public Cursor ambilDetailMitra(String noreg) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_MITRA + " WHERE noreg = '" + noreg + "'";
        Cursor c = db.rawQuery(sql, null);

        return c;
    }

    public int ambilIdRhkAttachment() {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_ATTACHMENT + " ORDER BY id DESC LIMIT 1";
        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() > 0) {
            c.moveToLast();

            return c.getInt(c.getColumnIndex("id"));
        } else {
            return 0;
        }
    }

    public Cursor ambilRhkAttachmentByRhk(int id_rhk) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_ATTACHMENT + " WHERE id_rhk = '" + id_rhk + "'";
        Cursor c = db.rawQuery(sql, null);

        return c;
    }
}
