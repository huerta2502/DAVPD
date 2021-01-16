import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Rectangle;

import java.util.ArrayList;

public class Calculator extends JPanel implements ActionListener, ItemListener {
    private static final long serialVersionUID = 7872053894321484049L;
    private JPanel davpdJPanel, paramsJPanel, varJPanel;
    private JButton clearJButton, calculateJButton;
    private JLabel varJLabel;
    private JTextField varJTextField, resultJTextField;
    private JComboBox davpdJComboBox, functionJComboBox;
    private ArrayList<JLabel> paramJLabels;
    private ArrayList<JTextField> paramJTextFields;
    private ArrayList<Davpd> davpds;
    public Calculator(int width, int height){
        setSize(width, height);
        setLayout(new GridLayout(1,2,4,4));
        setDavpd();
        add(davpdJPanel);
        setCalculator();
        add(varJPanel);
    }
    private void setDavpds() {
        davpds = new ArrayList<Davpd>();
        davpds.add(new Bernoulli());
        davpds.add(new Binomial());
        davpds.add(new NegativeBinomial());
        davpds.add(new Geometric());
        davpds.add(new Hipergeometric());
        davpds.add(new Poisson());
    }
    private void enableParams(int c){
        switch (c) {
            case 0:
            paramJLabels.get(0).setText("");
            paramJLabels.get(0).setEnabled(false);
            paramJLabels.get(1).setText("");
            paramJTextFields.get(1).setEnabled(false);
            paramJLabels.get(2).setText("");
            paramJTextFields.get(2).setEnabled(false);
            break;
            case 1://Bernoulli Geometric
            paramJLabels.get(0).setText("p: ");
            paramJLabels.get(0).setEnabled(true);
            paramJLabels.get(1).setText("NA");
            paramJTextFields.get(1).setEnabled(false);
            paramJLabels.get(2).setText("NA");
            paramJTextFields.get(2).setEnabled(false);
            break;
            case 2://Binomial
            paramJLabels.get(0).setText("p: ");
            paramJTextFields.get(0).setEnabled(true);
            paramJLabels.get(1).setText("n: ");
            paramJTextFields.get(1).setEnabled(true);
            paramJLabels.get(2).setText("NA");
            paramJTextFields.get(2).setEnabled(false);
            break;
            case 3://NegativeBinomial
            paramJLabels.get(0).setText("p: ");
            paramJTextFields.get(0).setEnabled(true);
            paramJLabels.get(1).setText("k: ");
            paramJTextFields.get(1).setEnabled(true);
            paramJLabels.get(2).setText("NA");
            paramJTextFields.get(2).setEnabled(false);
            break;
            case 4:
            paramJLabels.get(0).setText("p: ");
            paramJLabels.get(0).setEnabled(true);
            paramJLabels.get(1).setText("NA");
            paramJTextFields.get(1).setEnabled(false);
            paramJLabels.get(2).setText("NA");
            paramJTextFields.get(2).setEnabled(false);
            break;
            case 5://Hipergeometric
            paramJLabels.get(0).setText("r1: ");
            paramJTextFields.get(0).setEnabled(true);
            paramJLabels.get(1).setText("r2: ");
            paramJTextFields.get(1).setEnabled(true);
            paramJLabels.get(2).setText("n: ");
            paramJTextFields.get(2).setEnabled(true);
            break;
            case 6://Poisson
            paramJLabels.get(0).setText("lamda: ");
            paramJTextFields.get(0).setEnabled(true);
            paramJLabels.get(1).setText("NA");
            paramJTextFields.get(1).setEnabled(false);
            paramJLabels.get(2).setText("NA");
            paramJTextFields.get(2).setEnabled(false);
            break;
            default://Undefined
            break;
        }
    }
    private void setParams(){
        paramJLabels.add(new JLabel());
        paramJTextFields.add(new JTextField());
        paramJLabels.add(new JLabel());
        paramJTextFields.add(new JTextField());
        paramJLabels.add(new JLabel());
        paramJTextFields.add(new JTextField());
        paramJTextFields.get(0).setEnabled(false);
        paramJTextFields.get(1).setEnabled(false);
        paramJTextFields.get(2).setEnabled(false);
        enableParams(0);
        for(int i = 0; i < paramJLabels.size(); i++){
            paramJLabels.get(i).setSize(250, 50);
            paramJLabels.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
            paramJTextFields.get(i).setSize(250, 50);
            paramsJPanel.add(paramJLabels.get(i));
            paramsJPanel.add(paramJTextFields.get(i));
        }
        davpdJPanel.add(paramsJPanel);
    }
    private void setDavpd(){
        setDavpds();
        davpdJPanel = new JPanel(new GridLayout(2, 1, 4, 4));
        davpdJComboBox = new JComboBox();
        davpdJComboBox.addItem("Select distribution");
        davpds.forEach( (d) -> { davpdJComboBox.addItem(d.getClass().getSimpleName()); } );
        davpdJComboBox.setSelectedIndex(0);
        davpdJComboBox.addItemListener(this);
        davpdJPanel.add(davpdJComboBox);
        paramsJPanel = new JPanel(new GridLayout(3, 2, 4, 4), true);
        paramJLabels = new ArrayList<JLabel>();
        paramJTextFields = new ArrayList<JTextField>();
        setParams();
    }
    private void setCalculator(){
        varJPanel = new JPanel(new GridLayout(3, 2, 4, 4));        
        varJLabel = new JLabel("X", SwingConstants.RIGHT);
        varJLabel.setSize(250, 50);
        varJTextField = new JTextField();
        varJTextField.setSize(250, 50);
        varJPanel.add(varJLabel);
        varJPanel.add(varJTextField);
        functionJComboBox = new JComboBox();
        functionJComboBox.setSize(250, 50);
        functionJComboBox.addItem("Select function");
        functionJComboBox.addItem("P(X = x): ");
        functionJComboBox.addItem("P(X ≤ x): ");
        functionJComboBox.addItem("P(X >= x): ");
        functionJComboBox.setSelectedIndex(0);
        functionJComboBox.addActionListener(this);
        varJPanel.add(functionJComboBox);
        resultJTextField = new JTextField();
        resultJTextField.setSize(250, 50);
        resultJTextField.setToolTipText("Result");
        varJPanel.add(resultJTextField);
        clearJButton = new JButton("Clear");
        clearJButton.setSize(250, 50);
        clearJButton.addActionListener(this);
        calculateJButton = new JButton("Calculate");
        calculateJButton.setSize(250, 50);
        calculateJButton.addActionListener(this);
        varJPanel.add(clearJButton);
        varJPanel.add(calculateJButton);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getActionCommand().equals("Calculate")){
            try{
                switch(davpdJComboBox.getSelectedIndex()){
                    case 1://Bernoulli
                    davpds.get(davpdJComboBox.getSelectedIndex() + 1);
                    break;
                    case 2://Binomial
                    break;
                    case 3://NegativeBinomial
                    break;
                    case 4://Geometric
                    break;
                    case 5://Hipergeometric
                    break;
                    case 6://Poisson
                    break;
                    default:
                    JOptionPane.showMessageDialog(null, "Selecciona la distribuciñon");
                    break;
                }
                resultJTextField.setText(String.valueOf(davpds.get(davpdJComboBox.getSelectedIndex() + 1).getValue()));
            } catch (Exception e){
                System.out.println("Error en la entrada");
                JOptionPane.showMessageDialog(null, "Formato de entrada inválido");
            } finally {}
        }
    }
    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if(itemEvent.getItem().equals(davpds.get(0).getClass().getSimpleName()) || itemEvent.getItem().equals(davpds.get(3).getClass().getSimpleName()))
            enableParams(1);
        if(itemEvent.getItem().equals(davpds.get(1).getClass().getSimpleName()))
            enableParams(2);
        if(itemEvent.getItem().equals(davpds.get(2).getClass().getSimpleName()))
            enableParams(3);
        if(itemEvent.getItem().equals(davpds.get(4).getClass().getSimpleName()))
            enableParams(5);
        if(itemEvent.getItem().equals(davpds.get(5).getClass().getSimpleName()))
            enableParams(6);
    }
}