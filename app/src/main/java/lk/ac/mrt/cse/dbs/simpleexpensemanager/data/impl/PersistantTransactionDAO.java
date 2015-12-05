package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by amutheezan on 12/5/15.
 */
public class PersistantTransactionDAO  implements TransactionDAO{
    /***
     * Log the transaction requested by the user.
     *
     * @param date        - date of the transaction
     * @param accountNo   - account number involved
     * @param expenseType - type of the expense
     * @param amount      - amount involved
     */
    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {

    }

    /***
     * Return all the transactions logged.
     *
     * @return - a list of all the transactions
     */
    @Override
    public List<Transaction> getAllTransactionLogs() {
        return null;
    }

    /***
     * Return a limited amount of transactions logged.
     *
     * @param limit - number of transactions to be returned
     * @return - a list of requested number of transactions
     */
    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        return null;
    }
}
