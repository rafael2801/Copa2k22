package com.mycompany.copaqatar.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RegisterGroup {
    private JPanel container;
    private JPanel header;
    private JPanel content;
    private JButton copa2k22Button;
    private JTextField textField1;
    private JButton button1;
    private JScrollPane groupScroll;
    private JTable teamTable;

    private JFrame frame;

    public static void main(String[] args) {
        RegisterGroup r = new RegisterGroup();
        r.makeFrame();
    }

    public void makeFrame () {
        frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) screenSize.getWidth();
        int frameWidth = (width * 75) / 100;
        int frameHeight = (screenSize.height * 75) / 100;

        frame.setContentPane(this.container);
        frame.setTitle("Copa 2k22!");
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);

        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.makeActions();
        Object[][] data = {
                {"olá", "delete"},
                {"olá 2", "delete 2"},
                {"olá 4", "delete 4"},

        };
        this.populateComponents(data);

    }

    public void makeActions () {

    }
    public void fixComponents (Object[][] data) {
        String[] columNames = {"Time", ""};

        DefaultTableModel model = new DefaultTableModel(data, columNames);
        this.teamTable.setModel(model);
    }
    public void populateComponents (Object[][] passedData) {
        String[] columNames = {"Time", ""};
        Object[][] dataOrigin = {
                {"olá", "delete"},
                {"olá 2", "delete 2"},
                {"olá 4", "delete 4"},

        };





        if (passedData.length > 0) dataOrigin[0] = passedData;

        DefaultTableModel model = new DefaultTableModel(dataOrigin, columNames);
        this.teamTable.setModel(model);
        this.teamTable.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
        this.teamTable.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JTextField(), new SetModel(){
            @Override
            public void setModel(Object data) {
                super.setModel(data);
                modifyTableModel(data);
            }
        }));
    }

    public void modifyTableModel (Object row) {
        String[] columNames = {"Time", ""};

        Object[][] dataOrigin = {
                {"olá", "delete"},
                {"olá 2", "delete 2"},
                {"olá 4", "delete 4"},

        };

//        dataOrigin = Arrays.stream(dataOrigin).filter(arr -> true).);
//        dataOrigin.
//        DefaultTableModel model = new DefaultTableModel(dataOrigin, columNames);
//        this.teamTable.setModel(model);
    }
}
interface ISetModel {
    public void setModel (Object data);
}
class SetModel implements ISetModel {

    @Override
    public void setModel(Object data) {
    }
}

//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements TableCellRenderer
{

    //CONSTRUCTOR
    public ButtonRenderer() {
        //SET BUTTON PROPERTIES
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row, int col) {


        //SET PASSED OBJECT AS BUTTON TEXT
        setText((obj==null) ? "":obj.toString());

        return this;
    }

}

//BUTTON EDITOR CLASS
class ButtonEditor extends DefaultCellEditor
{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;

    private int row;

    private int col;

    public ButtonEditor(JTextField txt, SetModel setmodel ) {
        super(txt);

        btn=new JButton();
        btn.setOpaque(true);

        //WHEN BUTTON IS CLICKED
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setmodel.setModel(row);
                fireEditingStopped();
            }
        });
    }

    //OVERRIDE A COUPLE OF METHODS
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {

        //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
        lbl=(obj==null) ? "":obj.toString();
        btn.setText(lbl);
        clicked=true;
        this.row = row;
        this.col = col;
        return btn;
    }

    //IF BUTTON CELL VALUE CHNAGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {

        if(clicked)
        {
            System.out.println("Obj: " + this.row);
            System.out.println("Obj: " + this.col);
            //SHOW US SOME MESSAGE
            Object[][] data = {
                    {"olá", "delete"},

            };
            new RegisterGroup().populateComponents(data);
            JOptionPane.showMessageDialog(btn, lbl+" Clicked");
        }
        //SET IT TO FALSE NOW THAT ITS CLICKED
        clicked=false;
        return new String(lbl);
    }

    @Override
    public boolean stopCellEditing() {

        //SET CLICKED TO FALSE FIRST
        clicked=false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        // TODO Auto-generated method stub
        super.fireEditingStopped();
    }

    public void addCellEditorListener() {
    }
}
