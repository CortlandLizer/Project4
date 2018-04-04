package com.example.listview;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


	ListView my_listview;
	JSONHelper jsonHelper;
	List<BikeData> bikeData= new ArrayList<>();
	//Context cont;
	private String JSON_URL;
	private String FULL_URL;
	private String test = "http://www.pcs.cnu.edu/~kperkins/bikes/bikes.json";
	TextView testString;
	private MyAdapter myAdapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Change title to indicate sort by
		setTitle("Sort by:");
		testString = (TextView)findViewById(R.id.test);
        spinner = (Spinner)findViewById(R.id.spinner);

        //listview that you will operate on
		my_listview = (ListView)findViewById(R.id.lv);
		JSON_URL = "@listprefs/JSON_URL";

		//toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();

		setupSimpleSpinner();

		//set the listview onclick listener
		setupListViewOnClickListener();


		if (ConnectivityCheck.isNetworkReachable(this) || ConnectivityCheck.isWifiReachable(this)){
			DownloadTask myTask = new DownloadTask(this);

			myTask.setnameValuePair("company","companyValue");
			myTask.setnameValuePair("model","modelValue");
			myTask.setnameValuePair("location","locationValue");
			myTask.setnameValuePair("price","priceValue");
			myTask.setnameValuePair("date","dateValue");
			myTask.setnameValuePair("description","descriptionValue");
			myTask.setnameValuePair("picture","pictureValue");
			myTask.setnameValuePair("link","linkValue");

			myTask.execute(test);

		}

		myAdapter = new MyAdapter(bikeData.size(), this);
        // both of these done
		//TODO call a thread to get the JSON list of bikes
		//TODO when it returns it should process this data with bindData
	}

	private void setupListViewOnClickListener() {
		//TODO you want to call my_listviews setOnItemClickListener with a new instance of android.widget.AdapterView.OnItemClickListener() {

	}

	/**
	 * Takes the string of bikes, parses it using JSONHelper
	 * Sets the adapter with this list using a custom row layout and an instance of the CustomAdapter
	 * binds the adapter to the Listview using setAdapter
	 *
	 * @param JSONString  complete string of all bikes
	 */
	 void bindData(String JSONString) {
		//testString.setText(JSONString);

	 	bikeData = jsonHelper.parseAll(JSONString);
	 	//testString.setText(bikeData.toString());


	}

	Spinner spinner;
	/**
	 * create a data adapter to fill above spinner with choices(Company,Location and Price),
	 * bind it to the spinner
	 * Also create a OnItemSelectedListener for this spinner so
	 * when a user clicks the spinner the list of bikes is resorted according to selection
	 * dontforget to bind the listener to the spinner with setOnItemSelectedListener!
	 */
	private void setupSimpleSpinner() {

        String[] name = {"Company", "Model", "Price", "Location"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,name);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // set in order relative to whatever is clicked depending on position
                if (position == 0){
                    // set in order of company

                }
                else if(position == 1){
                    // set in order of model

                }
                else if (position == 2){

                    // set in order of price
                }
                else if (position == 3){
                    // set in order of location
                }
                else{
                    // error
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
            case R.id.action_settings:
                // take to settings activity
                break;

            case R.id.refresh:
                // set back to company layout
				// still need to add part that sets back to company

		default:
			break;
		}
		return true;
	}
	public void refresh(){
		if (ConnectivityCheck.isNetworkReachable(this) || ConnectivityCheck.isWifiReachable(this)){
			// set back to company layout
		}
		else {
			ConnectivityCheck.isNetworkReachableAlertUserIfNot(this);
		}
	}
}
