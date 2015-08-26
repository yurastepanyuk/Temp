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

public class SellingUI {

    private Shop shop;

    JTextField tfCustomer;
    JLabel lCustomer;
    JRadioButton rbutton;
    JFormattedTextField iQty;
    ButtonGroup buttonGroup;
    int idxProduct;

    public SellingUI(Shop shop) {
        this.shop = shop;

        JFrame frame = new JFrame("Sale");
        //frame.setMinimumSize(new Dimension(600, 300));
        frame.setSize(new Dimension(200, 300));
        frame.setLocation(300,100);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(creatingSellingPanel());

        frame.pack();
        frame.setVisible(true);

    }

    public static JRadioButton getSelection(ButtonGroup group) {
        for (Enumeration e = group.getElements(); e.hasMoreElements();) {
            JRadioButton b = (JRadioButton) e.nextElement();
            if (b.getModel() == group.getSelection()) {
                return b;
            }
        }
        return null;
    }

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

                                        }
                                    });

        panel.add(buttonBuy, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.LINE_START, 0, new Insets(0, 0, 0, 0), 0, 0));

        return panel;
    }

    private class RBListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            idxProduct = Integer.parseInt(e.getActionCommand());
        }
    }
}
