package mobileapp.bmi_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends ActionBarActivity {

	final String BOX_BMI = "";
	String BOX_BMI2 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		final ImageView top = (ImageView) findViewById(R.id.Img_top);
		final ImageView bad = (ImageView) findViewById(R.id.Img_bad);
		Button next_Btn = (Button) findViewById(R.id.Bt_Next);
		Button previous_Btn = (Button) findViewById(R.id.Bt_Previous);

		// set the images invisible
		top.setVisibility(View.INVISIBLE);
		bad.setVisibility(View.INVISIBLE);

		// recover the BMI value
		Intent intent = getIntent();
		final double BMI = Double.parseDouble(intent.getStringExtra(BOX_BMI));

		// and set the BMI value in the TextView
		if (intent != null) {
			TextView Value_BMI = (TextView) findViewById(R.id.Value_BMI);
			Value_BMI.setText(intent.getStringExtra(BOX_BMI));

		}

		// in function of the BMI value, show a face happy or unhappy
		if (BMI >= 18.5 && BMI < 24.9) {
			top.setVisibility(View.VISIBLE);
		} else {
			bad.setVisibility(View.VISIBLE);
		}

		// show additional information about the Value BMI of the user
		TextView classf = (TextView) findViewById(R.id.Tv_class);
		classf.setText(Classification(BMI));
		TextView pbs = (TextView) findViewById(R.id.Tv_pbs);
		pbs.setText(Problems(BMI));

		// if user click next Button, show a new activity and pass value of BMI
		next_Btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				String BMI2 = Double.toString(BMI);

				Intent launcher = new Intent(getApplicationContext(),
						FourthActivity.class);
				launcher.putExtra(BOX_BMI, BMI2);
				startActivityForResult(launcher, 1);

			}
		});

		// exit if user click cancel
		previous_Btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
				System.exit(0);

			}
		});

	}

	// configure the detail of classification depending of the user's BMI
	public String Classification(double BMI) {
		String value = "";

		if (BMI < 18.5) {
			value = "Poor Weight";
		}

		if (BMI >= 18.5 && BMI < 24.9) {
			value = "Normal Weight";
		}

		if (BMI >= 24.9 && BMI < 29.9) {
			value = "Overweight";
		}

		if (BMI >= 29.9 && BMI < 34.9) {
			value = "Moderate Obesity";
		}

		if (BMI >= 34.9 && BMI < 39.9) {
			value = "Severe Obesity";
		}

		if (BMI >= 39.9) {
			value = "Massive Obesity";
		}

		return value;
	}

	// configure the detail of problem depending of the user's BMI
	public String Problems(double BMI) {
		String value = "";

		if (BMI < 18.5) {
			value = "Health Problems Increased";
		}

		if (BMI >= 18.5 && BMI < 24.9) {
			value = "Health Problems Lesser";
		}

		if (BMI >= 24.9 && BMI < 29.9) {
			value = "Health Problems Increased";
		}

		if (BMI >= 29.9 && BMI < 34.9) {
			value = "Health Problems Moderate";
		}

		if (BMI >= 34.9 && BMI < 39.9) {
			value = "Health Problems High";
		}

		if (BMI >= 39.9) {
			value = "Health Problems Very High";
		}

		return value;

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
