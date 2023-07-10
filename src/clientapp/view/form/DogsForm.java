package clientapp.view.form;

import commonlib.domain.Person;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class DogsForm extends javax.swing.JFrame {

    public DogsForm() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tblDogs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnEditDog = new javax.swing.JButton();
        btnAddDog = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDogs = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        btnRemoveDog = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("(Dogs) Dog Grooming Salon Management App");

        jLabel4.setText("Search by name");
        jLabel4.setToolTipText("");

        btnEditDog.setText("View and edit dog");

        btnAddDog.setText("Add new dog");

        tblDogs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDogs.setPreferredSize(new java.awt.Dimension(1000, 800));
        jScrollPane1.setViewportView(tblDogs);

        btnSearch.setText("Search");

        btnRemoveDog.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        btnRemoveDog.setForeground(new java.awt.Color(204, 0, 0));
        btnRemoveDog.setText("Remove dog");

        btnReset.setText("Reset");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Dogs Form");

        txtSearch.setToolTipText("Enter dog's or owner's name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtSearch)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset)
                                .addGap(876, 876, 876))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEditDog))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAddDog, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                            .addComponent(btnRemoveDog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(btnReset)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnEditDog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAddDog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRemoveDog)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDog;
    private javax.swing.JButton btnEditDog;
    private javax.swing.JButton btnRemoveDog;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDogs;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnAddDog() {
        return btnAddDog;
    }

    public void setBtnAddDog(JButton btnAddDog) {
        this.btnAddDog = btnAddDog;
    }

    public JButton getBtnEditDog() {
        return btnEditDog;
    }

    public void setBtnEditDog(JButton btnEditDog) {
        this.btnEditDog = btnEditDog;
    }

    public JButton getBtnRemoveDog() {
        return btnRemoveDog;
    }

    public void setBtnRemoveDog(JButton btnRemoveDog) {
        this.btnRemoveDog = btnRemoveDog;
    }

    public JButton getBtnReset() {
        return btnReset;
    }

    public void setBtnReset(JButton btnReset) {
        this.btnReset = btnReset;
    }

    public JTable getTblDogs() {
        return tblDogs;
    }

    public void setTblDogs(JTable tblDogs) {
        this.tblDogs = tblDogs;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public void setBtnSearch(JButton btnSearch) {
        this.btnSearch = btnSearch;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(JTextField txtSearch) {
        this.txtSearch = txtSearch;
    }
    
    

    // ACTION LISTENERS
    
    public void btnSearchActionListener(ActionListener actionListener) {
        btnSearch.addActionListener(actionListener);
    }

    public void btnResetActionListener(ActionListener actionListener) {
        btnReset.addActionListener(actionListener);
    }

    public void btnAddDogActionListener(ActionListener actionListener) {
        btnAddDog.addActionListener(actionListener);
    }
    
    public void btnEditDogActionListener(ActionListener actionListener) {
        btnEditDog.addActionListener(actionListener);
    }
    
    public void btnRemoveDogActionListener(ActionListener actionListener) {
        btnRemoveDog.addActionListener(actionListener);
    }
    

}
