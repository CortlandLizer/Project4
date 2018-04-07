package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.FutureTask;

public class MainActivity extends AppCompatActivity {


	ListView my_listview;
	JSONHelper jsonHelper;
	List<BikeData> bikeData= new ArrayList<>();

	private final String JSON_URL = "bikes.json";
	private String FULL_URL;

	private MyAdapter myAdapter;
	private String[] json;


	private String urlBad = "http://www.tetonsoftware.com/bikes/" + JSON_URL;
	private SharedPreferences.OnSharedPreferenceChangeListener listener;
	String url;

    public static String stringURL = "http://www.tetonsoftware.com/bikes/bikes.json";



	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Change title to indicate sort by
		setTitle("Sort by:");

        spinner = (Spinner)findViewById(R.id.spinner);

        //listview that you will operate on
		my_listview = (ListView)findViewById(R.id.lv);
		json = getResources().getStringArray(R.array.JSON_URL);
		FULL_URL = json[0] + JSON_URL;
		//toolbar
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();

		setupSimpleSpinner();

		//set the listview onclick listener
		setupListViewOnClickListener(this);





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

			myTask.execute(FULL_URL);

		}

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
                //url is getting null for some reson
                //
                // Toast.makeText(MainActivity.this, "Key=" + key, Toast.LENGTH_SHORT).show();
                url = pref.getString("settings", "Nothing Found");
                //if ( url == null){
                //	url = getResources().getStringArray(R.array.JSON_URL)[0];
                //Toast.makeText(MainActivity.this, "Key=" + key, Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
                String jsonURL = url + "bikes.json";

                stringURL = jsonURL;
                //getURL();

                FULL_URL = url + JSON_URL;
                if(url == "http://www.tetonsoftware.com/bikes/"){
                    Toast.makeText(MainActivity.this, "ERROR when connecting to:" + FULL_URL + "Server returned 404", Toast.LENGTH_SHORT).show();
                }

                if(!FULL_URL.equals("http://www.pcs.cnu.edu/~kperkins/bikes/bikes.json")){
                    Log.d("refresh", "BAD");
                    Toast.makeText(MainActivity.this, "ERROR when connecting to:" + FULL_URL + "Server returned 404", Toast.LENGTH_SHORT).show();
                    my_listview.setVisibility(View.GONE);
                }else {
                    Log.d("refresh", "GOOD");

                    my_listview.setVisibility(View.VISIBLE);

                }
                //	Log.d("url?", FULL_URL);
                Log.d("long full url",jsonURL);
                Log.d("short no json", url  );
                // Toast.makeText(MainActivity.this, "ERROR when connecting to:" + FULL_URL + "Server returned 404", Toast.LENGTH_SHORT).show();


            }
        };
        pref.registerOnSharedPreferenceChangeListener(listener);

		//TODO call a thread to get the JSON list of bikes
		//TODO when it returns it should process this data with bindData
	}

	private void setupListViewOnClickListener(final Context con) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(con);
		my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				builder.setMessage(bikeData.get(position).toString());
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});



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


	 	myAdapter = new MyAdapter(bikeData.size(), this,bikeData);

	 	my_listview.setAdapter(myAdapter);


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
					Collections.sort(bikeData, new ComparatorCompany());
                    myAdapter.notifyDataSetChanged();
                }
                else if(position == 1){
                    // set in order of model
					Collections.sort(bikeData, new ComparatorModel());
					myAdapter.notifyDataSetChanged();

                }
                else if (position == 2){
					Collections.sort(bikeData, new ComparatorPrice());
					myAdapter.notifyDataSetChanged();
                    // set in order of price
                }
                else if (position == 3){
					Collections.sort(bikeData, new ComparatorLast());
					myAdapter.notifyDataSetChanged();

				}
                else{
                    // nothing, will never hit
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
				Intent intent = new Intent(this, SettingsActivity.class);
				startActivity(intent);
                break;

            case R.id.refresh:
                DownloadTask refresh = new DownloadTask(this);

				//add
                if(!FULL_URL.equals("http://www.pcs.cnu.edu/~kperkins/bikes/bikes.json")){
                    //Log.d("refresh", "BAD");
                    Toast.makeText(MainActivity.this, "ERROR when connecting to:" + FULL_URL + "Server returned 404", Toast.LENGTH_SHORT).show();
                    //	my_listview.setVisibility(View.GONE);
                }
                else {

                    refresh.execute(FULL_URL);

                }
                spinner.setSelection(0);
		default:
			break;
		}
		return true;
	}

}
