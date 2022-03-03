package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.databinding.ActivitySetupBinding;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class SetupActivity extends AppCompatActivity {
    ActivitySetupBinding binding;
    MysharedPreferanceSetup mysharedPreferanceSetup;
    MysharedPreferance mysharedPreferanc;
    CompositeDisposable compositeDisposable;
    Spinner spinner;
    ArrayList viewtype = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       /* spinner = findViewById(R.id.spinnerview);

        viewtype.add("Select View");
        viewtype.add("grid");
        viewtype.add("list");
        viewtype.add("large");
*/
        compositeDisposable = new CompositeDisposable();
        mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        mysharedPreferanc = MysharedPreferance.getPreferences(getApplicationContext());

        String viewtypee = mysharedPreferanc.getViewType();

        if (viewtypee.equals("large")) {
            binding.radioLarge.setChecked(true);

        } else if (viewtypee.equals("list")) {
            binding.radioList.setChecked(true);
        } else if (viewtypee.equals("grid")) {
            binding.radioGrid.setChecked(true);
        }


        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);

                if (index == 0) {

                    mysharedPreferanc.setViewType("large");
                    Intent intent = new Intent(SetupActivity.this, DrawerMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (index == 1) {

                    mysharedPreferanc.setViewType("grid");
                    Intent intent = new Intent(SetupActivity.this, DrawerMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    mysharedPreferanc.setViewType("list");
                    Intent intent = new Intent(SetupActivity.this, DrawerMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });


      /*  ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, viewtype);

// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter2);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String pos = ""+i;
                if (pos.equals("0")){

                } else if (pos.equals("1")) {

                    mysharedPreferanc.setViewType("grid");
                    Intent intent = new Intent(SetupActivity.this,DrawerMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else if (pos.equals("2")) {

                    mysharedPreferanc.setViewType("list");
                    Intent intent = new Intent(SetupActivity.this,DrawerMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else {
                    mysharedPreferanc.setViewType("large");
                    Intent intent = new Intent(SetupActivity.this,DrawerMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/


    }


    public void btn_back(View view) {
        onBackPressed();
    }
}