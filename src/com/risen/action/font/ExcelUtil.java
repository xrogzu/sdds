package com.risen.action.font;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class ExcelUtil {
	public static List readExcel(String excelFileName) throws BiffException, IOException{
		  
		  List<String[]> list = new ArrayList();
		  Workbook rwb = null;
		  Cell cell = null;
		  
		  InputStream stream = new FileInputStream(excelFileName);
		  
		  rwb = Workbook.getWorkbook(stream);
		  
		  Sheet sheet = rwb.getSheet(0);  
		 
		  for(int i=0; i<sheet.getRows(); i++){
		   String[] str = new String[sheet.getColumns()];
		   for(int j=0; j<sheet.getColumns(); j++){
			    cell = sheet.getCell(j,i);    
			    str[j] = cell.getContents();
		   }
		   list.add(str);
		  }
		  
		  return list;
		 }
	
	
  public static void writeExcel(String fileName,List<List<String>> list){ 
  	
      WritableWorkbook wwb = null;   
      try {   
      	File file=new File(fileName);
      	if(!file.exists()){
      		file.createNewFile();
      	}
          wwb = Workbook.createWorkbook(file);   
      } catch (IOException e) {   
          e.printStackTrace();   
      }   
      if(wwb!=null){   
          WritableSheet ws = wwb.createSheet("sheet1", 0);   
             
          for(int i=0;i<list.size();i++){
          	List<String> l=list.get(i);
          	for(int j=0;j<l.size();j++){
                  Label labelC = new Label(j, i, l.get(j));   
                  try {   
                      ws.addCell(labelC);   
                  } catch (RowsExceededException e) {   
                      e.printStackTrace();   
                  } catch (WriteException e) {   
                      e.printStackTrace();   
                  }
          	}
          }
          
          try {   
              wwb.write();   
              wwb.close();   
          } catch (IOException e) {   
              e.printStackTrace();   
          } catch (WriteException e) {   
              e.printStackTrace();   
          }   
      }   
  }  
  
  public static void main(String[] args) {
  	List<List<String>> list=new ArrayList<List<String>>();
  	for(int i=0;i<5;i++){
  		List<String> l=new ArrayList<String>();
  		l.add("x");
  		l.add("y");
  		l.add("z");
  		list.add(l);
  	}
		writeExcel("D:/test.xls",list);
	}
}
