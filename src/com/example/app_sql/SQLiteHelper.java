package com.example.app_sql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.Data;

import java.sql.Date;
import java.text.SimpleDateFormat;

 
public class SQLiteHelper extends SQLiteOpenHelper {

       private final static  String DATABASE_NAME="library";
	   private final static int DATABASE_VERSION = 1;
	   private String TABLE_NAME="x";
//	   �����������ݿ�
	   private static SQLiteHelper instance;
	   private SQLiteHelper(Context c){
		   super(c,DATABASE_NAME,null,DATABASE_VERSION);
	   }
//	       ����ģʽ
	   public static SQLiteHelper getInstance(Context context) {  
	        if (instance == null) {  
	            instance = new SQLiteHelper(context);  
	        }   
	        return instance;  
	    }  
//	  ���ݿ��������
       public void  getTableName(String n){
		   String name="";
//		  �Ե�ǰ������Ϊ���ݿ����
		   name=n+"Database";
		   TABLE_NAME=name;
	   }
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		 String sql=" CREATE TABLE "+ TABLE_NAME +
			        " (_id INTEGER PRIMARY KEY,"+
				    " GoodName VARCHAR (100) NOT NULL,"+
			        " GoodPrice FLOAT(6,2) NOT NULL,"+
				    " GoodLow FLOAT(6,2),"+
			        " DataTime VARCHAR(8)," +
			        " Note VARCHAR(100))";
		 db.execSQL(sql);
	}
	@Override
//	�ڶ��Σ����ݿ�汾����ʱ���ô˷�����
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
         db.execSQL(sql);
         onCreate(db);
	}
//	query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)�����������ĺ��壺 
//	table���������൱��select���from�ؼ��ֺ���Ĳ��֡�����Ƕ�����ϲ�ѯ�������ö��Ž����������ֿ��� 
//	columns��Ҫ��ѯ�������������൱��select���select�ؼ��ֺ���Ĳ��֡� 
//	selection����ѯ�����Ӿ䣬�൱��select���where�ؼ��ֺ���Ĳ��֣��������Ӿ�����ʹ��ռλ����?�� 
//	selectionArgs����Ӧ��selection�����ռλ����ֵ��ֵ�������е�λ����ռλ��������е�λ�ñ���һ�£�����ͻ����쳣�� 
//	groupBy���൱��select���group by�ؼ��ֺ���Ĳ��� 
//	having���൱��select���having�ؼ��ֺ���Ĳ��� 
//	orderBy���൱��select���order by�ؼ��ֺ���Ĳ��֣��磺personid desc, age asc; 
//	limit��ָ��ƫ�����ͻ�ȡ�ļ�¼�����൱��select���limit�ؼ��ֺ���Ĳ��֡� 
	public Cursor select(){
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null, null);
		return cursor;
	}
	 //����һ����¼
    public long insert(String GoodName,float GoodPrice ,float GoodLow,String time,String note) {
           SQLiteDatabase db = this.getWritableDatabase();
           ContentValues cv = new ContentValues();
           cv.put("GoodName", GoodName);
           cv.put("GoodPrice", GoodPrice);
           cv.put("GoodLow", GoodLow);
           cv.put("DataTime", time);
           cv.put("Note", note);
           long row = db.insert(TABLE_NAME, null, cv);
           return row;
    }
//    ɾ��һ����¼
    public void delete(int id){
    	SQLiteDatabase db =this.getReadableDatabase();
    	String where ="_id =?";
    	String[] whereValue ={Integer.toString(id)};
    	db.delete(TABLE_NAME,where,whereValue);
    }
//    ���¼�¼
    public void updata(int id,String GoodName,float GoodPrice ,float GoodLow,String time,String note){
    	SQLiteDatabase db=this.getReadableDatabase();
    	String where="_id=?";
    	String[] whereValue={Integer.toString(id)};
    	ContentValues cv = new ContentValues();
    	cv.put("GoodName", GoodName);
        cv.put("GoodPrice", GoodPrice);
        cv.put("GoodLow", GoodLow);
        cv.put("DataTime", time);
        cv.put("Note", note);
        db.update(TABLE_NAME, cv, where, whereValue);
    }
//     ����ģ����ѯ
//     ����ת��Ϊutf��8
    public Cursor query(String args[]){
    	SQLiteDatabase db=this.getReadableDatabase();
    	Cursor cursor =db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE GoodName LIKE ?", args);
    	return cursor;
   
    }
//     ���ڲ�ѯ
    public  Cursor queryDate(String args[]){
    	SQLiteDatabase  db=this.getReadableDatabase();
    	Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE DataTime LIKE ?", args);
    	return cursor;
    }

    	
    
}


