package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
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
    public List<String> getAccountNumbersList() {


        return null;
    }

    /***
     * Get a list of accounts.
     *
     * @return - list of Account objects.
     */
    @Override
    public List<Account> getAccountsList() {
        return null;
    }

    /***
     * Get the account given the account number.
     *
     * @param accountNo as String
     * @return - the corresponding Account
     * @throws InvalidAccountException - if the account number is invalid
     */
    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        return null;
    }

    /***
     * Add an account to the accounts collection.
     *
     * @param account - the account to be added.
     */
    @Override
    public void addAccount(Account account) {

    }

    /***
     * Remove an account from the accounts collection.
     *
     * @param accountNo - of the account to be removed.
     * @throws InvalidAccountException - if the account number is invalid
     */
    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {

    }

    /***
     * Update the balance of the given account. The type of the expense is specified in order to determine which
     * action to be performed.
     * <p/>
     * The implementation has the flexibility to figure out how the updating operation is committed based on the type
     * of the transaction.
     *
     * @param accountNo   - account number of the respective account
     * @param expenseType - the type of the transaction
     * @param amount      - amount involved
     * @throws InvalidAccountException - if the account number is invalid
     */
    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {

    }
}
