package mobileapp.bmi_calculator;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

public class MainActivity extends ActionBarActivity {

	// some variables

	final String BOX_BMI = "";
	final String BOX_NAME = "";
	final String BOX_WEIGHT = "";
	final String BOX_HEIGHT = "";
	final Context context = this;
	public static final String PREFS_NAME = "MyPrefsFile";
	String indexSI = "";

	// method that create the activity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // we choice the layout xml we
												// use for this activity

		final Spinner spinner = (Spinner) findViewById(R.id.Sp_background); // Create
																			// an
																			// ArrayAdapter
																			// using
																			// the
																			// string
																			// array
																			// and
																			// a
																			// default
																			// spinner

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.b_choice, android.R.layout.simple_spinner_item); // Specify
																				// the
																				// layout
																				// to
																				// use
																				// when
																				// the
																				// list
																				// of
																				// choices
																				// appears

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply
																						// the
																						// adapter
																						// to
																						// the
																						// spinner
		spinner.setAdapter(adapter);

		// we initilaze variables to be in relation with a specific component
		final Button submit_Btn = (Button) findViewById(R.id.Bt_Submit);
		final Button calcul_Btn = (Button) findViewById(R.id.Bt_Calcul);
		final EditText Et_Name = (EditText) findViewById(R.id.EditTextName);
		final EditText Et_Size = (EditText) findViewById(R.id.EditTextHeight);
		final EditText Et_Weight = (EditText) findViewById(R.id.EditTextWeight);

		// we enable a button
		calcul_Btn.setEnabled(false);

		// we put an event for the spinner depending of the idem selected in order to change the background
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView adapter, View v, int i,
					long lng) {

				// if first item selected
				if (i == 0) {
					
					//select the view
					View background = findViewById(R.id.background);
					//and set it the image as background
					background.setBackgroundResource(R.drawable.galaxy_wallpaper);

				}

				if (i == 1) {
					View background = findViewById(R.id.background);
					background.setBackgroundResource(R.drawable.background2);

				}

				if (i == 2) {
					View background = findViewById(R.id.background);
					background.setBackgroundResource(R.drawable.background3);

				}

			}

			
			//in the case, no item is selected, show a message for the user
			@Override
			public void onNothingSelected(AdapterView arg0) {
				Toast.makeText(getApplicationContext(), "Nothing selected",
						Toast.LENGTH_SHORT).show();

			}
		});
		
		
		//event for the submit button if the user click on it
		submit_Btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				//put the length of what the user write into variables
				final int l1 = Et_Name.getText().toString().length();
				final int l2 = Et_Size.getText().toString().length();
				final int l3 = Et_Weight.getText().toString().length();

				//then, verify if it's not null and if it's the case, show an Error in the EditText concerned
				if ((l1 == 0)) {
					Et_Name.setError(getString(R.string.error_required));
				}

				if ((l2 == 0)) {
					Et_Size.setError(getString(R.string.error_required));
				}

				if ((l3 == 0)) {
					Et_Weight.setError(getString(R.string.error_required));
				}

				//if no EditText are null, process this event
				if ((l1 != 0) && (l2 != 0) && (l3 != 0)) {

					//display calcul_Btn and enable the other
					calcul_Btn.setEnabled(true);
					submit_Btn.setEnabled(false);

					//initialize an AlertDialog
					AlertDialog alertDialog = new AlertDialog.Builder(
							MainActivity.this).create();

					// Setting Dialog Title
					alertDialog.setTitle("Alert Dialog");

					// Setting Dialog Message
					alertDialog.setMessage("Details Submitted, thanks :)");

					// Setting Icon to Dialog
					alertDialog.setIcon(R.drawable.tick);

					// Setting OK Button
					alertDialog.setButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// Write your code here to execute after
									// dialog closed
									Toast.makeText(getApplicationContext(),
											"You clicked on OK",
											Toast.LENGTH_SHORT).show();
								}
							});

					// Showing Alert Message
					alertDialog.show();

				}

			}
		});

		
		//event for the button calcul
		calcul_Btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// we initilaze variables to be in relation with a specific component
				EditText Et_Size = (EditText) findViewById(R.id.EditTextHeight);
				EditText Et_Weight = (EditText) findViewById(R.id.EditTextWeight);

				//put the content of the EditText into a variable
				double size = Double.parseDouble(Et_Size.getText().toString());
				double weight = Double.parseDouble(Et_Weight.getText().toString());
				
				//calcul the BMI
				String BMI = Double.toString(Math.round(weight / (size * size)));

				//Initialize the launcher
				Intent launcher = new Intent(MainActivity.this, SecondActivity.class);

				//put the BMI value into a Box that will be send to the Second Activity
				launcher.putExtra(BOX_BMI, BMI);

				//Start the launcher
				startActivityForResult(launcher, 1);

				//display a loading circle for a specific time waiting that the Second Activity start
				final ProgressDialog dialog = ProgressDialog.show(
						MainActivity.this, "", "Loading..Wait..", true);
				dialog.show();
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {
						dialog.dismiss();
					}
				}, 1000); 

			}
		});

		
		
		//if user click on cancel, leave the application
		Button cancel_Btn = (Button) findViewById(R.id.Bt_Cancel);
		cancel_Btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
				System.exit(0);

			}
		});

	}
	
	
	//use to save the state of the application in a specific time and restore it if the user change the orientation
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		// Save the user's current index state
		EditText Et_Name = (EditText) findViewById(R.id.EditTextName);
		savedInstanceState.putString("index", Et_Name.getText().toString());

		// Always call the superclass so it can save the view hierarchy state
		super.onSaveInstanceState(savedInstanceState);
	}

	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Always call the superclass so it can restore the view hierarchy
		super.onRestoreInstanceState(savedInstanceState);

		// Restore state members from saved instance
		EditText Et_Name = (EditText) findViewById(R.id.EditTextName);
		Et_Name.setText(savedInstanceState.getString("index"));

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
