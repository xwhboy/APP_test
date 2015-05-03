package com.example.app_sql;



import java.io.FileNotFoundException;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	 
	private TextView view1;
	private TextView view2;
	private TextView view3;
	private TextView view4;
	private TextView view5;
	private Button buse;
	private Button bufun;
    private EditText seain;
    private EditText ev;
    private EditText ediev1;
    private EditText ediev2;
    private EditText ediev3;
    private EditText ediev4;
    private EditText ediev5;
    private SQLiteHelper  hp;
    private Cursor cursor;
    private int id=0;
    public  ListView listview;
    private mydialog dialog;
    private diaedi dialog2;
    private long exitTime=0;
    private int leng=0;;
    private Dataup dataup;
    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//      ����ƥ��
        ev=(EditText)this.findViewById(R.id.search_in);
        bufun=(Button)this.findViewById(R.id.functionall);
        buse=(Button)this.findViewById(R.id.search);
        seain=(EditText)this.findViewById(R.id.search_in);
        listview=(ListView)this.findViewById(R.id.listview);
        bufun.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showdia();
			}
        	
        });
        buse.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				queryRec();
			}
        	
        });
        

//      ����ģʽ  
        hp=SQLiteHelper.getInstance(this);
        cursor=hp.select();
//      �Զ��岼��
        @SuppressWarnings("deprecation") 
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(
        		this,R.layout.lv_layout,
        		cursor,
        		new String[]{"GoodName","GoodPrice","GoodLow","DataTime","Note"},
        		new int[]{R.id.name,R.id.price,R.id.low,R.id.time,R.id.note});      
//       ����Ƿ񴴽�listview
        if(listview==null)
        {
                Log.d("debug"," list null");
                
        }
        listview.addHeaderView(LayoutInflater.from(this).inflate( 
                R.layout.listhead, null));
        listview.setAdapter(adapter);
//      ���ü����¼������ʱ��ʾ�ڱ༭����
//       ����
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
      	  public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3){
      		  cursor.moveToPosition(arg2);			// ��cursor�Ƶ��������ֵ 
      		  id = cursor.getInt(0);				// ȡ���ֶ�_id��ֵ ���α�ѡ�еĵ�һ��
//	  		  ֪ͨ������ѡ��
              Toast.makeText(MainActivity.this, "--"+cursor.getString(1)+"--��ѡ��", Toast.LENGTH_LONG).show(); 
      		   
      	  }
        });
        
   
    }
    public void showdia2(){
//    	      test
//       	  Toast.makeText(MainActivity.this, "����", Toast.LENGTH_LONG).show();

    	  dialog2=new diaedi(this,R.style.dialog,
    			new diaedi.DialogListener() {
					
					@Override
					public void onClick(View view) {
						// TODO Auto-generated method stub
						switch(view.getId()){ 
						case R.id.edibu:{
						edit();	 
						dialog2.dismiss();
						dialog.dismiss();
//				    	֪ͨ�������޸ĳɹ�
				    	Toast.makeText(MainActivity.this, "�������޸�", Toast.LENGTH_LONG).show();	
						}
						break;
						case R.id.qu:{
							dialog2.dismiss();

							
						}
						break;	
						default:
				        break;
						}
						
					}
				} );
    	  dialog2.show();
    	  ediev1=(EditText)dialog2.findViewById(R.id.ediname);
          ediev2=(EditText)dialog2.findViewById(R.id.ediprice);
          ediev3=(EditText)dialog2.findViewById(R.id.edilow);
          ediev4=(EditText)dialog2.findViewById(R.id.editime);
          ediev5=(EditText)dialog2.findViewById(R.id.edinote);
    	  ediev1.setText(cursor.getString(1));	// ȡ���ֶ�Rec_text��ֵ 
		  ediev2.setText(cursor.getString(2));
		  ediev3.setText(cursor.getString(3));
		  ediev4.setText(cursor.getString(4));
		  ediev5.setText(cursor.getString(5));
    	
    }
//    
    public void showdia(){
    	 dialog = new mydialog(this,R.style.dialog,   
                new mydialog.LeaveMyDialogListener() {
					
					@Override
					public void onClick(View view)  {
						// TODO Auto-generated method stub
						switch(view.getId()){   
			               case R.id.addbutton:
			               { 
			            	  dialog.dismiss();
			            	  Intent in = new Intent();  
			                  in.setClassName( getApplicationContext(), "com.example.app_sql.Sql_add" );  
			                  startActivity( in );  

			               }
			              break;
			              case R.id.insbutton:{
			            	 showdia2();
			              }

			              break;
			              case R.id.delbutton:
			              {
			            	  deleteRec();		
			            	  dialog.dismiss();
//			                                                     ֪ͨ������ɾ���ɹ�
			               	  Toast.makeText(MainActivity.this, "������ɾ��", Toast.LENGTH_LONG).show();
			              	
			              }
			                       
			              break;
			              case R.id.daobutton:
			              {
			            	  genxin(); 
			              }
			              
			              break;           
			              default:
			              break;
			                     }   
					}
				}); 
                    dialog.show();
    }
    
    public void genxin(){
    	    dataup=new Dataup();
    	    try {
				dataup.preReadCheck();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
  	        boolean d=dataup.getbool();
  	        
  	        if(d){
//  		
  	             
  		
//		                      �������߳�
		     	new Thread(new Runnable(){ 
			    public void run(){ 
			    	try {
						
						leng = dataup.jisuqi();
					}
					 catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			    	String [][]shuju=new String[leng][5];
			    	try {
						shuju=dataup.readshuzu(leng);
						System.out.println("ok");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	deletealldata();
			    	for(int i=0;i<leng;i++){
			    		add(shuju[i][0],shuju[i][1],shuju[i][2],shuju[i][3],shuju[i][4]);
			    	}
			    						  
			    } 
			}).start(); 
//			 ����main�߳�����»����쳣
		     	cursor.requery();
		
		
		 

         Toast.makeText(MainActivity.this, "������ȫ�����£�������", Toast.LENGTH_LONG).show();
         }
  	         else{
//        ֪ͨ
       Toast.makeText(MainActivity.this, "�����ļ�������", Toast.LENGTH_LONG).show();
     	}
    }
    @SuppressWarnings("deprecation")
    public void add(String t1,String t2,String t3,String t4,String t5){
    	  if (t1.equals("")||t2.equals(""))
//      		��ֵ�������޸�
    	  {
    	    Toast.makeText(this, "�����п�ֵ", Toast.LENGTH_LONG).show();
      		return;  
    	  }
    	  hp.insert(t1,Float.parseFloat(t2),Float.parseFloat(t3),t4,t5);
    	  
   
      }
	public void edit(){
    	if (ediev1.getText().toString().equals("")){
//    		��ֵ�������޸�
//    		  ֪ͨ�������޸�ʧ��
           	Toast.makeText(MainActivity.this, "�����޸�ʧ��", Toast.LENGTH_LONG).show();
    		return;
    	}
//    	 �����޸�
    	hp.updata(id, ediev1.getText().toString(),Float.parseFloat(ediev2.getText().toString()),Float.parseFloat(ediev3.getText().toString()),ediev4.getText().toString(),ediev5.getText().toString());
//      �ؼ�����
    	cursor.requery();

    }
     
      //ɾ����¼
      private void deleteRec()
      {
        hp.delete(id);
        cursor.requery();

     }
      //��������ѯ
      private void queryRec()
      {
        String et=ev.getText().toString();
        String args[]=new String[]{"%"+et+"%"};
        System.out.println("---------------------");
        System.out.println(args[0]);
        cursor=hp.query(args);
        @SuppressWarnings("deprecation")

		SimpleCursorAdapter adapter=new SimpleCursorAdapter(
        		this,R.layout.lv_layout,
        		cursor,
        		new String[]{"GoodName","GoodPrice","GoodLow","DataTime","Note"},
        		new int[]{R.id.name,R.id.price,R.id.low,R.id.time,R.id.note}
         );      
        
        listview.setAdapter(adapter);
     
       
      }
     public void deletealldata(){
    	 
    	 hp.onUpgrade(hp.getReadableDatabase(),0,1);
     }
     
//     �����·����˳�
     @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) 
     {
 			     if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
 			     {
 			             
 					     if((System.currentTimeMillis()-exitTime) > 2000)  //System.currentTimeMillis()���ۺ�ʱ���ã��϶�����2000
 					     {
 					      Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",Toast.LENGTH_SHORT).show();                                
 					      exitTime = System.currentTimeMillis();
 					     }
 					     else
 					     {
 					         finish();
 					         System.exit(0);
 					     }
 					             
 					     return true;
 			     }
 			     return super.onKeyDown(keyCode, event);
     }
     @Override
//     ������enter����
 	public boolean dispatchKeyEvent(KeyEvent event) {
 		if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
 			/*���������*/
 			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
 			if(inputMethodManager.isActive()){
 				inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), 0);
 			}
 			
 			queryRec();
 			return true;
 		}
 		return super.dispatchKeyEvent(event);
 	}
    
}
