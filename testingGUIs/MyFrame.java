package testingGUIs;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyFrame extends JFrame {

  private JButton btnSubmit  = new JButton("Submit");
  private JButton btnTurnOff = new JButton("Turn off");

  private JTextField txtA = new JTextField();
  private JTextField txtB = new JTextField();
  private JTextField txtC = new JTextField();

  private JLabel lblA = new JLabel("Enter number A :");
  private JLabel lblB = new JLabel("Enter number B :");
  private JLabel lblC = new JLabel("Enter number C :");

  public MyFrame(){
    setTitle("A little Test GUI");
    setSize(400,200);
    setLocation(new Point(300,200));
    setLayout(null);    
    setResizable(false);

    initComponent();    
    initEvent();    
  }

  private void initComponent(){
	btnTurnOff.setBounds(300,130, 80,25);
	btnSubmit.setBounds(300,100, 80,25);

	// text boxes for user input
    txtA.setBounds(150,10,100,20);
    txtB.setBounds(150,35,100,20);
    txtC.setBounds(150,65,100,20);

    // text labels beside the boxes prompting user
    lblA.setBounds(20,10,200,20);
    lblB.setBounds(20,35,200,20);
    lblC.setBounds(20,65,200,20);

    // add buttons into JFrame
    add(btnSubmit);
    add(btnTurnOff);

    // add text labels into JFrame
    add(lblA);
    add(lblB);
    add(lblC);

    // add text boxes into JFrame
    add(txtA);
    add(txtB);
    add(txtC);
  }

  private void initEvent(){

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
       System.exit(1);
      }
    });

    // exits the program when btnTurnOff is clicked (via function call)
    btnTurnOff.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnTutupClick(e);
      }
    });

    // stores the user input into the variables when btnSubmit is clicked (via function call)
    btnSubmit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnTambahClick(e);
      }
    });
  }
  
  private void btnTutupClick(ActionEvent evt){
    System.exit(0);
  }
  
  private void btnTambahClick(ActionEvent evt){
    Integer x,y,z;
    try{
      x = Integer.parseInt(txtA.getText());
      y = Integer.parseInt(txtB.getText());
      z = x + y;
      txtC.setText(z.toString());

    }catch(Exception e){
      System.out.println(e);
      JOptionPane.showMessageDialog(null, 
          e.toString(),
          "Error", 
          JOptionPane.ERROR_MESSAGE);
    }
  }
}
