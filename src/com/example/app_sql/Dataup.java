package com.example.app_sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;






import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class Dataup {
	private boolean ifexit=true;
	private String filePath;
	private static final String EXTENSION_XLS = "xls";
    private static final String EXTENSION_XLSX = "xlsx";
    private String [][] shuzu;
    private int changdu;
    private int kuangdu;
	private File file;
	
	
	
	//���sd���ĸ�Ŀ¼

	public String getpath(){
		  String path="���±�.txt";
		  return path;
		  
	}
	
//	�ж��ļ��Ƿ����
//  �ļ����
  public  void preReadCheck() throws FileNotFoundException {
      // ������
	   
       file =new File("/storage/sdcard1" ,
    		   getpath());  
      if (!file.exists()) {
    	  ifexit=false;
    	  System.out.println("������");
          throw new FileNotFoundException("������ļ������ڣ�" + filePath+"/���±�.txt");  
      }
      
 
  }
  public String [][] readshuzu(int n) throws IOException{
	  n=jisuqi();
	  String [][] shuju=new String[n][5];
	  BufferedReader in = new BufferedReader(new FileReader(file));  //
	  String line;  //һ������
	  int row=0;
	  //���ж�ȡ������ÿ��������뵽������
	  while((line = in.readLine()) != null){
	   String[] temp = line.split("\t"); 
	   for(int j=0;j<temp.length;j++){
	    shuju[row][j] =temp[j];
//	    System.out.println(shuju[row][j]);
	   }
	   row++;
	  }
	  in.close();
	  return shuju;
	
}
     public int jisuqi() throws IOException{
  	  int count = 0;
  	  
  	  InputStream input = new FileInputStream(file);
  	  BufferedReader b = new BufferedReader(new InputStreamReader(input));String value = b.readLine();
  	     if(value != null)
  		     while(value !=null){
  	                      count++;
  	                      value = b.readLine();
  	                     }
      	b.close();
  	    input.close();
//  	  System.out.println("���");
  	    return count;
  }
  public boolean  getbool(){
	  return ifexit;
  }

	    public String[][]getshuzhu(){
	    	return shuzu;
	    }
	    
	    

}
