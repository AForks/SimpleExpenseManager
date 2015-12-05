package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.database.MYSQLiteHelper;

/**
 * Created by amutheezan on 12/5/15.
 */
public class PersistantAccountDAO implements AccountDAO {
    /***
     * Get a list of account numbers.
     *
     * @return - list of account numbers as String
     *
     * */


    private SQLiteDatabase database;
    private MYSQLiteHelper dbHelper;
    private String[] allColumns = {MYSQLiteHelper.getAccountNo(), MYSQLiteHelper.getBANK(),
            MYSQLiteHelper.getAccountHolder(), MYSQLiteHelper.getBALANCE()};

    public PersistantAccountDAO(Context context) {

        dbHelper = new MYSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    @Override
    public List<String> getAccountNumbersList() {
        List<String> accountNumberList = new ArrayList<String>();
        String args[] = {String.valueOf(MYSQLiteHelper.getAccountNo())};

        Cursor cursor = database.query(MYSQLiteHelper.getAccountTb(),args,null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            accountNumberList.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return accountNumberList;
    }

    @Override
    public List<Account> getAccountsList() {
        List<Account> accounts = new ArrayList<Account>();

        Cursor cursor = database.query(MYSQLiteHelper.getAccountTb(),
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Account account = cursorToAccount(cursor);
            accounts.add(account);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return accounts;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        String args[] = {accountNo};
        Cursor cursor = database.rawQuery("SELECT * FROM "+MYSQLiteHelper.getAccountTb()+" WHERE "+MYSQLiteHelper.getAccountNo()+"=?", args);
        cursor.moveToFirst();

        Account account = cursorToAccount(cursor);
        return account;
    }

    @Override
    public void addAccount(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MYSQLiteHelper.getAccountNo(),account.getAccountNo());
        contentValues.put(MYSQLiteHelper.getAccountHolder(),account.getAccountHolderName());
        contentValues.put(MYSQLiteHelper.getBANK(),account.getBankName());
        contentValues.put(MYSQLiteHelper.getBALANCE(), account.getBalance());

        database.insert(MYSQLiteHelper.getAccountTb(), null, contentValues);
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        String[] args = {accountNo};
        database.delete(MYSQLiteHelper.getAccountTb(), MYSQLiteHelper.getAccountNo()
                + " =?", args);
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Account account = getAccount(accountNo);
        Double balance = account.getBalance();
        if(expenseType == ExpenseType.EXPENSE){
            balance = balance - amount;
        }else{
            balance = balance + amount;
        }
        account.setBalance(balance);
        removeAccount(accountNo);
        addAccount(account);
    }

    private Account cursorToAccount(Cursor cursor){
        String accountName = cursor.getString(0);
        String bankName = cursor.getString(1);
        String accountHolderName = cursor.getString(2);
        Double balance = cursor.getDouble(3);

        return new Account(accountName,bankName,accountHolderName,balance);
    }
}
