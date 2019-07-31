/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mdi;

import Negocio.Bien;
import Negocio.Inventario;
import Negocio.Responsable;
import Negocio.Usuario;
import Persistencia.BienJpaController;
import Presentacion.CtrlVista;
import Utilidades.FechaHora;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author diego
 */
public class FrmImpresionEtiquetas extends javax.swing.JPanel {

    /**
     * Creates new form FrmImpresionEtiquetas
     */
    private Bien unBien;
    private final CtrlVista logica = new CtrlVista();
    private MainMdi miparent;
    private BienJpaController dao = new BienJpaController();
    private Usuario user;
    public DefaultTableModel tblBienes;
    public ArrayList<Bien> listadeBienes = new ArrayList();

     
    public FrmImpresionEtiquetas() {
        initComponents();
        tblBienes = new DefaultTableModel();
        this.colocarEncabezadoGrid();
    }
    
    public final void colocarEncabezadoGrid(){
        String[] columnNames = {"Nro de Inventario",
                                "Descripcion",
                                "Estado",
                                "Responsable",
                                "Area",
                                "Fecha"};
        this.tblBienes.setColumnIdentifiers(columnNames);
        this.gridBienes.setModel(tblBienes);
    } 
    
    public final void cargarGrid(){
        Inventario inv = new Inventario();
        int cantBienes = this.listadeBienes.size();
        int COLS = 6;
        Object[][] data = new Object[cantBienes][COLS];
        int rowIndex = 0;
        FechaHora fecha = new FechaHora();
        
        //this.limpiarGrid();
        //this.colocarEncabezadoGrid();
        for (Bien bien : this.listadeBienes) {
            Integer nroInventario = bien.getNroInventario();
            data[rowIndex][0] = nroInventario;
            data[rowIndex][1] = bien.getDescripcion();
            data[rowIndex][2] = bien.getEstado();    
            data[rowIndex][5] = fecha.DateToString(bien.getFechaAlta());
            Responsable responsableBien = inv.responsableBienID(bien.getId());
            //Responsable responsableBien = null;
            addResponsableDataToRow(data, rowIndex, responsableBien);           
            tblBienes.addRow(data[rowIndex]);
        }
    }
    
    private void addResponsableDataToRow(Object[][] data, int rowIndex, Responsable responsableBien) {
        if (responsableBien == null) {
            data[rowIndex][3] = "-";
            data[rowIndex][4] = "-";
        }
        else {
            data[rowIndex][3] = responsableBien.getNombre();
            data[rowIndex][4] = responsableBien.getSector();
        }
    } 

    public void setParent(MainMdi miparent) {
        this.miparent = miparent;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    
    
    public void limpiarComponentes(){
        this.unBien = null;
    }
    
    public void agregarAlGrid(){
        
    }
    
    public boolean isInteger( String input ){
        try{
            Integer.parseInt( input );
            return true;
        }catch( Exception e){
            return false;
        }
}
    
    public void buscar(){
        // TODO add your handling code here:
        String nro = this.txtNroInventario.getText();
        //if(!"".equals(nro)){
            if(this.isInteger(nro) && !"".equals(nro) ){
                List<Bien> lista = this.dao.findBienByNroInventario(Integer.parseInt(this.txtNroInventario.getText()));
                this.limpiarComponentes();
                if(lista.size() > 0){
                    this.unBien = lista.get(0);
                    this.agregarAlGrid();
            //this.b
                }else{
                    JOptionPane.showMessageDialog(null, "No se encontro ningun bien con el nro ingresado.");
                }
             
            }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNroInventario = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridBienes = new javax.swing.JTable();
        BtnImprimir = new javax.swing.JButton();

        jLabel1.setText("N de Inventario");

        txtNroInventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNroInventarioKeyPressed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        gridBienes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(gridBienes);

        BtnImprimir.setText("Imprimir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNroInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnImprimir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNroInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar)
                    .addComponent(BtnImprimir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
       
            String nro = this.txtNroInventario.getText();
            if(!"".equals(nro)){
                this.buscar();

            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un Nro de Inventario.");
            }
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtNroInventarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroInventarioKeyPressed
        if(evt.getKeyCode() == 10){
            String nro = this.txtNroInventario.getText();
            if(!"".equals(nro)){
                this.buscar();

            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un Nro de Inventario.");
            }
        }
    }//GEN-LAST:event_txtNroInventarioKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JTable gridBienes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtNroInventario;
    // End of variables declaration//GEN-END:variables
}