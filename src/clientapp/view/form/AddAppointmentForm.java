package clientapp.view.form;

import commonlib.domain.Dog;
import commonlib.domain.Salon;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Lenovo
 */
public class AddAppointmentForm extends javax.swing.JFrame {

    public AddAppointmentForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTime = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbDog = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbSalon = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cbxBath = new javax.swing.JCheckBox();
        cbxTrimming = new javax.swing.JCheckBox();
        cbxNail = new javax.swing.JCheckBox();
        cbxEar = new javax.swing.JCheckBox();
        cbxTeeth = new javax.swing.JCheckBox();
        cbxStyling = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("(Add Appointment) Dog Grooming Salon Management App");

        jLabel1.setText("Date");

        jLabel2.setText("Time");

        jLabel3.setText("Dog");

        jLabel4.setText("Salon");

        btnAdd.setText("Add Appointment");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("New Appointment Parameters");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Services"));

        cbxBath.setText("Bath");

        cbxTrimming.setText("Trimming");

        cbxNail.setText("Nail care");

        cbxEar.setText("Ear care");

        cbxTeeth.setText("Teeth care");

        cbxStyling.setText("Styling");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxTeeth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxEar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxNail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxTrimming, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxBath, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxStyling, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(cbxBath)
                .addGap(8, 8, 8)
                .addComponent(cbxTrimming)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxNail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTeeth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxStyling)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDate)
                            .addComponent(txtTime)
                            .addComponent(cmbDog, 0, 117, Short.MAX_VALUE)
                            .addComponent(cmbSalon, 0, 117, Short.MAX_VALUE)))
                    .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSalon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JCheckBox cbxBath;
    private javax.swing.JCheckBox cbxEar;
    private javax.swing.JCheckBox cbxNail;
    private javax.swing.JCheckBox cbxStyling;
    private javax.swing.JCheckBox cbxTeeth;
    private javax.swing.JCheckBox cbxTrimming;
    private javax.swing.JComboBox<Dog> cmbDog;
    private javax.swing.JComboBox<Salon> cmbSalon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public void setBtnAdd(JButton btnAdd) {
        this.btnAdd = btnAdd;
    }

    public JCheckBox getCbxBath() {
        return cbxBath;
    }

    public void setCbxBath(JCheckBox cbxBath) {
        this.cbxBath = cbxBath;
    }

    public JCheckBox getCbxEar() {
        return cbxEar;
    }

    public void setCbxEar(JCheckBox cbxEar) {
        this.cbxEar = cbxEar;
    }

    public JCheckBox getCbxNail() {
        return cbxNail;
    }

    public void setCbxNail(JCheckBox cbxNail) {
        this.cbxNail = cbxNail;
    }

    public JCheckBox getCbxStyling() {
        return cbxStyling;
    }

    public void setCbxStyling(JCheckBox cbxStyling) {
        this.cbxStyling = cbxStyling;
    }

    public JCheckBox getCbxTeeth() {
        return cbxTeeth;
    }

    public void setCbxTeeth(JCheckBox cbxTeeth) {
        this.cbxTeeth = cbxTeeth;
    }

    public JCheckBox getCbxTrimming() {
        return cbxTrimming;
    }

    public void setCbxTrimming(JCheckBox cbxTrimming) {
        this.cbxTrimming = cbxTrimming;
    }

    public JComboBox<Dog> getCmbDog() {
        return cmbDog;
    }

    public void setCmbDog(JComboBox<Dog> cmbDog) {
        this.cmbDog = cmbDog;
    }

    public JComboBox<Salon> getCmbSalon() {
        return cmbSalon;
    }

    public void setCmbSalon(JComboBox<Salon> cmbSalon) {
        this.cmbSalon = cmbSalon;
    }

    public JTextField getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(JTextField txtDate) {
        this.txtDate = txtDate;
    }

    public JTextField getTxtTime() {
        return txtTime;
    }

    public void setTxtTime(JTextField txtTime) {
        this.txtTime = txtTime;
    }
    
    

    // ACTION LISTENERS
    
    public void btnAddActionListener (ActionListener actionListener){
        btnAdd.addActionListener(actionListener);
    }


}


