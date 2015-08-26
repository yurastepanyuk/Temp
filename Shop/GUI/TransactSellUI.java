package day9.Shop.GUI;

import day9.Shop.Shop;
import day9.Shop.documents.Sale;
import day9.Shop.enums.CategoryPrice;
import day9.Shop.inforamation.Prices;
import day9.Shop.reference.AutoParts;
import day9.Shop.reference.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Enumeration;

/**
 * Created by stepanyuk on 14.08.2015.
 */
public class TransactSellUI {

    private Shop        shop;

    JFrame              frame;
    JPanel              pSale;
    JPanel              pTransactionSell;

    JPanel              sellingPanel;
    JTextField          tfCustomer;
    JLabel              lCustomer;
    JRadioButton        rbutton;
    JFormattedTextField iQty;
    ButtonGroup         buttonGroup;
    int                 idxProduct;


    public TransactSellUI(Shop shop) {
        this.shop = shop;


        frame = new JFrame("Transactions of sell");
        //frame.setMinimumSize(new Dimension(800,400));
        frame.setSize(new Dimension(800,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menuAddSale= new JMenu("Sales");
        menuBar.add(menuAddSale);
        JMenuItem menuItemNewSale   = new JMenuItem("Buy Autoparts");
        menuAddSale.add(menuItemNewSale);
        menuItemNewSale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SellingUI sellingUI = new SellingUI(getShop());
                showSellingForm();
            }
        });

        frame.setJMenuBar(menuBar);

        pSale               = creatingSellingPanel();
        pTransactionSell    = creatingTrabsactionPanel();

        frame.getContentPane().add(pTransactionSell);
        frame.pack();
        frame.setVisible(true);

    }

    //Creating Jpanel for sell
    private JPanel creatingSellingPanel(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        lCustomer = new JLabel("Customer name: ");
        //JTextField tfCustomer = new JTextField("", 30);
        tfCustomer = new JTextField();
        tfCustomer.setColumns(30);

        panel.add(lCustomer, new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));
        panel.add(tfCustomer, new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0),0,0));

        JLabel lGoods = new JLabel("Select good: ");
        buttonGroup = new ButtonGroup();
        AutoParts[] products = shop.getDb().getGoods();
        ActionListener rbListener = new RBListener();

        JPanel panelGoods = new JPanel();
        panelGoods.setLayout(new GridLayout(products.length, 0));
        panelGoods.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        int k = 0;
        for (AutoParts autoParts : products) {

            rbutton = new JRadioButton(autoParts.getName());

            rbutton.addActionListener(rbListener);
            rbutton.setActionCommand(String.valueOf(autoParts.getId()));

            if (k == 0) {
                rbutton.setSelected(true);
                k++;
            }

            buttonGroup.add(rbutton);

            panelGoods.add(rbutton);
        }

        panel.add(lGoods, new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.LINE_START,0,new Insets(0,0,0,0),0,0));
        panel.add(panelGoods, new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0),0,0));

        Format general = NumberFormat.getNumberInstance();
        JLabel lQty = new JLabel("Input qty: ");
        iQty = new JFormattedTextField(general);
        iQty.setColumns(5);
        Font font = new Font("SansSerif", Font.BOLD, 16);
        iQty.setFont(font);
        iQty.setValue(1);

        panel.add(lQty, new GridBagConstraints(0,2,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0), 0, 0));
        panel.add(iQty, new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.LINE_START,0, new Insets(0,0,0,0), 0, 0));

        JButton buttonBuy = new JButton("Buy");
        buttonBuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Sale documSale;
                AutoParts autoParts;
                Client client;
                int idxAutoParts;

                rbutton = getSelection(buttonGroup);

                client    = new Client().getObjectByName(tfCustomer.getText().trim());

                if (client == null){
                    System.err.println("Not client in DataBase");
                    return;
                }

                documSale = new Sale();
                autoParts = new AutoParts().getObjectByName(rbutton.getText().trim());
                idxAutoParts = idxProduct;
                documSale.setIdClient(client.getId());
                documSale.setIdAutoParts(autoParts.getId());
                documSale.setCena(new Prices().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
                documSale.setQty(Byte.valueOf(iQty.getText()));
                documSale.setDate(shop.getCurrentDate());
                documSale.Save();

                System.out.println(client.getName() + "    " + autoParts.getName() + "    " +
                        documSale.getQty() + "    " + documSale.getCena());

                showTransactionSellForm();

            }
        });

        panel.add(buttonBuy, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        return panel;
    }

    //For ButtonGroup search selection buttton
    public static JRadioButton getSelection(ButtonGroup group) {
        for (Enumeration e = group.getElements(); e.hasMoreElements();) {
            JRadioButton b = (JRadioButton) e.nextElement();
            if (b.getModel() == group.getSelection()) {
                return b;
            }
        }
        return null;
    }

    //Listener for RadiButton. Update idx current product
    private class RBListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            idxProduct = Integer.parseInt(e.getActionCommand());
        }
    }

    //Creating Jpanel for transaction sell
    private JPanel creatingTrabsactionPanel(){
        JPanel pTransaction = new JPanel();

        TransactSellModel   transactSellModel   = new TransactSellModel(shop);
        JTable              transactTable       = new JTable(transactSellModel);
        JScrollPane         trabsactScrollPane  = new JScrollPane(transactTable);
        trabsactScrollPane.setPreferredSize(new Dimension(800,400));

        transactTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        transactTable.getColumnModel().getColumn(1).setPreferredWidth(50);
        transactTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        transactTable.getColumnModel().getColumn(3).setPreferredWidth(220);
        transactTable.getColumnModel().getColumn(4).setPreferredWidth(30);
        transactTable.getColumnModel().getColumn(5).setPreferredWidth(50);

        pTransaction.add(trabsactScrollPane, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

        return pTransaction;
    }

    //Show selling panel
    private void showSellingForm(){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(pSale);

        frame.pack();
        frame.repaint();
    }

    //Show transaction panel
    private void showTransactionSellForm(){
        frame.getContentPane().removeAll();
        pTransactionSell = creatingTrabsactionPanel();
        frame.add(pTransactionSell);

        frame.pack();
        frame.setVisible(true);
    }

    public Shop getShop(){
        return this.shop;
    }

}
