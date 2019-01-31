package mobileapp.bmi_calculator;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FourthActivity extends ActionBarActivity {

	final String BOX_BMI = "";
	final String BOX_BMI2 = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);

		
		//get value of the BMI
		Intent launcher = getIntent();
		final double BMI2 = Double
				.parseDouble(launcher.getStringExtra(BOX_BMI));

		
		//create variable from image of pin
		ImageView attach = (ImageView) findViewById(R.id.Img_attach);

		//and deplace it depending of the value of BMI
		if (BMI2 < 18.5) {
			attach.scrollBy(80, 20);

		}

		if (BMI2 >= 18.5 && BMI2 < 24.9) {
			attach.scrollBy(20, 20);

		}

		if (BMI2 >= 24.9 && BMI2 < 29.9) {
			attach.scrollBy(-20, 20);
		}

		if (BMI2 >= 29.9 && BMI2 < 34.9) {
			attach.scrollBy(-40, 20);
		}

		if (BMI2 >= 34.9) {
			attach.scrollBy(-90, 20);
		}

		Button previous_Btn = (Button) findViewById(R.id.Previous);
		previous_Btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				finish();
				System.exit(0);

			}
		});

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
