package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by amutheezan on 12/5/15.
 */
public class MYSQLiteHelper extends SQLiteOpenHelper {
    private static final String dbname ="130029B.db";
    private static final SQLiteDatabase.CursorFactory factory=null;
    private static final int version =1;
    private static final String ACCOUNT_TB="ACCOUNT";
    private static final String TRANSACTION_TB="TRANSACTION";
    private static final String ACCOUNT_NO="account_number";
    private static final String ACCOUNT_HOLDER="account_holder";
    private static final String BANK="bank_name";
    private static final String BALANCE ="balance";
    private static final String DATE="date";
    private static final String AMOUNT="amount";
    private static final String EXPENSE_TYPE="expense_type";

    public static String getTransactionTb() {
        return TRANSACTION_TB;
    }

    public static String getAccountTb() {
        return ACCOUNT_TB;
    }

    public static String getAccountNo() {
        return ACCOUNT_NO;
    }

    public static String getAccountHolder() {
        return ACCOUNT_HOLDER;
    }

    public static String getBANK() {
        return BANK;
    }

    public static String getBALANCE() {
        return BALANCE;
    }

    public static String getAMOUNT() {
        return AMOUNT;
    }

    public static String getDATE() {
        return DATE;
    }

    public static String getExpenseType() {
        return EXPENSE_TYPE;
    }

    private static final String CREATE_ACCOUNT_TB = "create table IF NOT EXISTS "
            + ACCOUNT_TB + "(" + ACCOUNT_NO
            + " TEXT primary key , " + BANK

            + " TEXT not null, " + ACCOUNT_HOLDER
            + " TEXT not null, " + BALANCE
            + " REAL not null);";

    private static final String CREATE_TRANSACTION_TB = "create table IF NOT EXISTS "
            + TRANSACTION_TB + "(" + DATE
            + " TEXT not null , " + ACCOUNT_NO
            + " TEXT not null, " + AMOUNT
            + " REAL not null, " + EXPENSE_TYPE
            + " TEXT not null);";

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database

     */
    public MYSQLiteHelper(Context context) {
        super(context, dbname, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_TB);
        db.execSQL(CREATE_TRANSACTION_TB);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("vvvvvvvvvvvvvvvvv",
                "Upgrading database from version " + oldVersion + " to " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TRANSACTION_TB);
        db.execSQL(CREATE_TRANSACTION_TB);
    }
}
