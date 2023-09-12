import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Final {

	public static void main(String[] args) throws FileNotFoundException{
		String line = "";
		String tfline = "";
		File f = null;
		File[] paths;
		Integer ni=0;
		int document = 0;
		int count = 0;
		double[] tmpArray = new double[1475];
        final String delimiter = " ";
        String[][] textArray = new String[17500][2];
        String[][] idfArray = new String[1475][2];
        String[][] tfArray = new String[175][101]; 
        String[][] OutputArray = new String[1475][101];
        String[][] tmpTFArray = new String[175][2];
        
        try
        {	
        	//--------------------------------------------------------------------------//
        	
        	String pathname = "./ir/word1475.txt";
        	File filename = new File(Final.class.getResource(pathname).getFile()); 
			InputStreamReader idfReader = new InputStreamReader(new FileInputStream(filename)); 
			BufferedReader br = new BufferedReader(idfReader); 
			String wordline = "";
			
			while ((wordline= br.readLine())!=null)
			{
				if(wordline != null)
				{	
					OutputArray[count][0]=wordline;
					idfArray[count][0]=wordline;
					idfArray[count][1]="0";
					count++;
				}
			}count =0;
        	//System.out.println(Arrays.deepToString(idfArray));
			/*----------------------------------------------------------------*/
			//String filesPath = "../Final/src/ir/";
			String filesPath = "/ir/";
	        //File folder = new File("../Final/src/ir");
	        File folder = new File(Final.class.getResource("/ir").getFile());

			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
	              if (listOfFiles[i].isFile())
	              {
	            	String filePath = filesPath+listOfFiles[i].getName();
	            	if(filePath.contains(".wrd"))
	            	{
	            		//FileReader fileReader = new FileReader(filePath);
	            		//InputStream inputStream = Main.class.getResourceAsStream("/ir/0801_012.txt");
	            		InputStream inputStream = Final.class.getResourceAsStream(filePath);
	            		InputStreamReader inputReader = new InputStreamReader(inputStream);
		  	            BufferedReader reader = new BufferedReader(inputReader);
		               //System.out.println("File " + listOfFiles[i].getName());//read file name
		  	            
		  	            while((line=reader.readLine())!=null) 
			            {	
		  	            	
			            	if(line != null)
			            	{
			            		String[] token = line.split(delimiter);  
				            	textArray[count][0] = token[0];
				            	textArray[count][1] = token[1];
				            	
				            	//System.out.println(textArray[count][0]+" "+textArray[count][1]);
				            	
			            	}count++;
			            }
		  	            
		              }
	            	
	            	}	            	
	            }
			for(int idfC=0;idfC <1475;idfC++)
			{
				for(int cnt=0 ; cnt<textArray.length ;cnt++) 
				{
					 
					if(idfArray[idfC][0] != null) 
					{
						if(idfArray[idfC][0].equals(textArray[cnt][0])) 
						{
							ni++;
						}
					}
					
				}	
				if(ni!=0)
				{
					idfArray[idfC][1]= Double.toString(Math.log10(100/ni));
				}
				ni = 0;
			}
			//System.out.println(Arrays.deepToString(idfArray));
			//----------------------------------------------------------------------------//
			
			
			//-----------------------------------------------------------------------------//
			for(int tmp=0 ; tmp<1475 ; tmp++) 
			{
				tmpArray[tmp] = Double.parseDouble(idfArray[tmp][1]);
			}
			//File folder = new File(Final.class.getResource("/ir").getFile());
			//String tfFilesPath = "../Final/src/ir/";
			String tfFilesPath = "/ir/";
	        //File tfFolder = new File("../Final/src/ir");
			File tfFolder = new File(Final.class.getResource("/ir").getFile());
	        File[] tfListOfFiles = tfFolder.listFiles();
			for (int x = 0; x < tfListOfFiles.length; x++) 
			{
				if (tfListOfFiles[x].isFile())
	            {
					String tfFilePath = tfFilesPath+tfListOfFiles[x].getName();
					if(tfFilePath.contains(".wrd")) 
					{
						//InputStream inputStream = Final.class.getResourceAsStream(filePath);
	            		//InputStreamReader inputReader = new InputStreamReader(inputStream);
		  	            
						
				        //FileReader tffileReader = new FileReader(tfFilePath);
						InputStream tfStream = Final.class.getResourceAsStream(tfFilePath);
						InputStreamReader tfReader = new InputStreamReader(tfStream);
					  	BufferedReader tfreader = new BufferedReader(tfReader);
						count = 0;
						document++;
					  	while((tfline=tfreader.readLine())!=null) 
						{						  	            	
					  		if(tfline != null)
							{
					  			String[] token = tfline.split(delimiter);  
					  	    	tmpTFArray[count][0] = token[0];
					  	    	tmpTFArray[count][1] = token[1];
					  	    	
					  	    	//System.out.println(tmpTFArray[count][0]+" "+tmpTFArray[count][1]); 	
							}count++;
						}
					  	
					  	for(int tmpTF=0; tmpTF<tmpTFArray.length;tmpTF++)
					  	{
					  		for(int outC=0;outC<OutputArray.length;outC++)
					  		{
					  			if(tmpTFArray[tmpTF][0]!=null) 
					  			{
					  				if(tmpTFArray[tmpTF][0].equals(OutputArray[outC][0])) 
								  	{
								  		OutputArray[outC][document]=tmpTFArray[tmpTF][1];
								  	}
					  			}
					  			
					  		}
					  	}
					  	for(int outC=0;outC<OutputArray.length;outC++)
					  	{
					  		if(OutputArray[outC][document]==null) 
							  {
							  	OutputArray[outC][document]="0";
							  }
					  	}
					  	for (int i = 0; i < tmpTFArray.length; i++) {
					  		tmpTFArray[i][1] = "";
					  		tmpTFArray[i][0] = "";
				        }
					  	//System.out.println(Arrays.deepToString(OutputArray));
					  	
					  	
			        }
				}
			}
			//System.out.println(Arrays.deepToString(tfArray)); 	
			
			
			//--------------------------------------�B��TF---------------------------------------//
			//-------------------------------------TF*idf---------------------------------------//	
			for(int j=0; j<OutputArray.length; j++)
			{
				for(int i=1; i<OutputArray[0].length;i++ )
				{	
					double tf = Double.parseDouble(OutputArray[j][i]);
					double idf = Double.parseDouble(idfArray[j][1]);
					double weight =tf*idf;
					weight = Math.round(weight*10000.0)/10000.0;;
					//OutputArray[j][i]= Math.round((weight)*100.0/100.0);
					OutputArray[j][i]= weight+"";
				}
			}
			
			//-------------------------------------TF*idf---------------------------------------//
            /*----------------------------------count the repeat----------------------------------
			for(int word = 0; word<idfArray.length ;word++)
            {
            	for(int text = 0; text<textArray[0].length ;text++)
            	{
            		if(textArray[0][text]!=null) 
            		{
            			if(textArray[0][text].equals(idfArray[word][0])) 
		  	            {
		  	            	Integer counter = Integer.parseInt(idfArray[word][1])+Integer.parseInt(textArray[1][text]);
		  	            	idfArray[word][1] = counter.toString();
		  	            }
            		}
            	}
            }
			--------------------------------------------------------------------------------------*/
			/*File writename = new File("../Final/weight2.txt"); 
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			for(int j=0;j<OutputArray.length;j++)
			{
				for(int i =0;i <OutputArray[0].length;i++)
				{	
					if(OutputArray[j][0].length()==4 && i==0)
					{
						out.write(OutputArray[j][0]+"\t");
					}else
					{
						out.write(OutputArray[j][i]+"\t\t");
					}		
				}
				out.write("\n");
			}
			out.flush();
			
			out.close(); */
			GUI gui = new GUI(OutputArray);		
        }        
        
		
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        

	}
	public static int MaxArray(int[] array) {
		int temp;  
		for (int i = 0; i < array.length; i++)   
		        {  
		            for (int j = i + 1; j < array.length; j++)   
		            {  
		                if (array[i] > array[j])   
		                {  
		                    temp = array[i];  
		                    array[i] = array[j];  
		                    array[j] = temp;  
		                }  
		            }  
		        }  
		       return array[array.length-1];  
	}  
}
