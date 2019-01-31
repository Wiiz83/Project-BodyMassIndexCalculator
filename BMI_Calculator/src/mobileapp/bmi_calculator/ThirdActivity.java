package mobileapp.bmi_calculator;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;




//THIS ACTIVITY DOESN'T WORK BUT HIS GOAL COULD BE TO SEND USER'S DETAILS FROM FIRST ACTIVITY TO DATABASE 
//IT WAS SUPPOSED TO NOT APPEAR ON THE SCREEN : PROGRESS IN BACKGROUND




public class ThirdActivity extends ActionBarActivity{
	

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_third);
	        
	    }
	 @Override
	 protected void onResume() {
	     // TODO Auto-generated method stub
	     super.onResume();

	     new AsyncCaller().execute();

	 }

	 private class AsyncCaller extends AsyncTask<Void, Void, Void>
	 {
	     ProgressDialog pdLoading = new ProgressDialog(ThirdActivity.this);

	     @Override
	     protected void onPreExecute() {
	         super.onPreExecute();

	         //this method will be running on UI thread
	         pdLoading.setMessage("\tLoading...");
	         pdLoading.show();
	     }
	     @Override
	     protected Void doInBackground(Void... params) {

	         //this method will be running on background thread so don't update UI frome here
	         //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here


	         return null;
	     }

	     @Override
	     protected void onPostExecute(Void result) {
	         super.onPostExecute(result);

	         //this method will be running on UI thread

	         pdLoading.dismiss();
	     }

	     }

    
	 
	 
	 
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
   
}
