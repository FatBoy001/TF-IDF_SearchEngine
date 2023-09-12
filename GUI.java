import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.JOptionPane;


public class GUI extends JFrame implements ActionListener{

	JButton button;
	JTextField textField;
	JLabel Label;
	Border border = BorderFactory.createLineBorder(Color.black,3);
	String[][] arr;
	String[] tmp = new String[101];
	String[][] Output = new String[100][2];
	String[][] result;
	int c;
	int sp;
	String[] boubles= new String[2];
	StringBuilder stringBuilder = new StringBuilder();
	String joinedString;
	
	GUI(String[][] x)
	{
		arr=x;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setLayout(new FlowLayout());
		this.setSize(500,700);
		this.setLayout(null);
		
		
		button = new JButton("Submit");
		button.addActionListener(this);
		button.setBounds(50,50,100,50);

		
		textField = new JTextField();
		textField.setText("");
		textField.setPreferredSize(new Dimension(250,40));
		textField.setBounds(155,50,245,50);

		
		Label = new JLabel();
		Label.setText("Search conclusion...");
		Label.setBackground(Color.white);
		Label.setOpaque(true);
		Label.setBorder(border);
		
		Label.setHorizontalTextPosition(JLabel.CENTER);
		Label.setVerticalTextPosition(JLabel.TOP);
		Label.setVerticalAlignment(JLabel.CENTER);
		Label.setHorizontalAlignment(JLabel.CENTER);
		Label.setBounds(50,105,350,500);
	
		this.add(button);
		this.add(textField);
		this.add(Label);
		//this.pack();
		
		this.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) 
		{
			System.out.println(textField.getText());
			for(String[] row: arr){
				if(row[0]==null) {break;}
				if(row[0].equals(textField.getText())){
					tmp = row;
				}
				System.out.println(Arrays.toString(row));
			}
			for(int i=0;i+1<tmp.length;i++){
				Output[i][1]=tmp[i+1];
			}
			Arrays.fill(tmp, null);
	        //File folder = new File("../Final/src/ir");
			File folder = new File(Final.class.getResource("/ir").getFile());
	        File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {//¤å¥ó°j°é
	              if (listOfFiles[i].isFile())
	              {
	            	  if(listOfFiles[i].getName().contains(".txt")&&listOfFiles[i].getName().contains("_")) 
	                  {
	            		  Output[c][0]=listOfFiles[i].getName();
	            		  c++;
	                      
	                  }
	            	  
	              }
	        }c=0;
			//System.out.println(Arrays.deepToString(Output)); 	
			for(int y = 99;y>0;y--) {
				sp=1;
				for(int u=0;u<y;u++){
					if(Output[u][1]==null) {break;}
					if(Double.parseDouble(Output[u][1])<Double.parseDouble(Output[u+1][1])){
						boubles[0] = Output[u][0];
						boubles[1] = Output[u][1];
						Output[u][0] = Output[u+1][0];
						Output[u][1] = Output[u+1][1];
						Output[u+1][0] = boubles[0];
						Output[u+1][1] = boubles[1];
						sp=0;
					}
					if(sp==1) {continue;}
				}
			}

			//Label.setText(textField.getText());
			System.out.println(Arrays.deepToString(Output));
		
			for(int end =0;end<100;end++) {
				if(Output[end][1]==null) {break;}
				if(Double.parseDouble(Output[end][1])!=0.0){
					stringBuilder.append(Output[end][0]+" "+Output[end][1]+"<br/>");
				}
			}
			joinedString=stringBuilder.toString();

			Label.setText("<html>"+joinedString+"</html>");
			
		}
		
		
	}
	
}
