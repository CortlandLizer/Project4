package com.example.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


	ListView my_listview;
	JSONHelper jsonHelper;
	List<BikeData> bikeData= new ArrayList<>();
	//Context cont;
	private String JSON_URL;
	private String FULL_URL;


	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Change title to indicate sort by
		setTitle("Sort by:");

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

		}

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


	 	bikeData = jsonHelper.parseAll(JSONString);

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

		default:
			break;
		}
		return true;
	}
}
