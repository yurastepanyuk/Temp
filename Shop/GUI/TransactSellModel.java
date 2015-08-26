package day9.Shop.GUI;

import day9.Shop.Shop;
import day9.Shop.documents.Sale;
import day9.Shop.reference.AutoParts;
import day9.Shop.reference.Client;

import javax.swing.table.AbstractTableModel;

/**
 * Created by stepanyuk on 14.08.2015.
 */
public class TransactSellModel extends AbstractTableModel {

    private Shop shop;

    private String [] columnNames;
    private Object [][] data;

    public TransactSellModel(Shop shop) {
        columnNames = new String[]{"id", "date", "Client", "AutoParts", "qty", "cena"};
        this.shop = shop;
        readData();
    }

    private void readData() {

        Sale[] sales = shop.getDb().getSale();

        data = new Object[sales.length][getColumnCount()];

        int idx = 0;
        for (Sale sale : sales) {

            int idSale          = sale.getId();
            AutoParts autoParts = new AutoParts().getObjectById(sale.getIdAutoParts());
            String dateDoc      = sale.getDateToString();
            Client client       = new Client().getObjectById(sale.getIdClient());

            data[idx] = new Object[]{idSale, dateDoc, client.toString(), autoParts.toString(), new Integer(sale.getQty()), new Float(sale.getCena())};

            idx++;
        }

    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return data[rowIndex][columnIndex];
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }


}
