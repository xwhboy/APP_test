package com.example.app_sql;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sql_add extends Activity{
	private SQLiteHelper  hp;
	private Cursor cursor;
	private Button bu;
	private EditText ev1;
    private EditText ev2;
    private EditText ev3;
    private EditText ev4;
    private EditText ev5;
	public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqladdui);
        ev1=(EditText)this.findViewById(R.id.addname);
        ev2=(EditText)this.findViewById(R.id.addprice);
        ev3=(EditText)this.findViewById(R.id.addlow);
        ev4=(EditText)this.findViewById(R.id.addtime);
        ev5=(EditText)this.findViewById(R.id.addnote);
        bu=(Button)this.findViewById(R.id.addbu);
        hp=SQLiteHelper.getInstance(this);
        cursor=hp.select();
        bu.setOnClickListener(new OnClickListener(){
        
			@Override
		public void onClick(View v){
				add();
				 Intent in = new Intent();  
                 in.setClassName( getApplicationContext(), "com.example.app_sql.MainActivity" );  
                 startActivity( in );  
			}
        });
        
	    }
        public void add(){
      	  if (ev1.getText().toString().equals(""))
//        		��ֵ�������޸�
        		return;  
      	  hp.insert( ev1.getText().toString(),Float.parseFloat(ev2.getText().toString()),Float.parseFloat(ev3.getText().toString()),ev4.getText().toString(),ev5.getText().toString());
//          �ؼ�����
        	cursor.requery();
//        	֪ͨ�������޸ĳɹ�
        	Toast.makeText(this, "����������", Toast.LENGTH_LONG).show();
     
        }
}
