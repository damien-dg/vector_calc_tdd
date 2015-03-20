package com.example.damien.vector_calc_tdd;

import android.app.AlertDialog;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;



import junit.framework.TestCase;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private TextView textView;

    public MainActivityTest(){
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {

            super.setUp();

        mainActivity = getActivity();
        textView =
                (TextView) mainActivity
                        .findViewById(R.id.editText);

    }

    public void testMainActivityExists(){

        assertNotNull(mainActivity);
    }

    @UiThreadTest
    public void testProcessInput (){
        //coords that are correct doubles,
        assertEquals(true, mainActivity.processInput("-5","5","0","14.5", "", "", "Cartesian", "Addition"));
        assertEquals(true, mainActivity.processInput("-5","5","0","14.5", "3.23", "5", "Cartesian", "Addition"));
        assertEquals(true, mainActivity.processInput("-5","5","0","14.5", "", "", "Cartesian", "Dot Product"));
        assertEquals(true, mainActivity.processInput("-5","5","0","14.5", "", "", "Cartesian", "Vector Product"));
    }

    @UiThreadTest
    public void testDisplayResultVectorAddition(){
        Button btnSubmit = (Button) mainActivity.findViewById(R.id.btnSubmit);
        EditText textField1 = (EditText) mainActivity.findViewById(R.id.editText);
        EditText textField2 = (EditText) mainActivity.findViewById(R.id.editText5);
        EditText textField3 = (EditText) mainActivity.findViewById(R.id.editText2);
        EditText textField4 = (EditText) mainActivity.findViewById(R.id.editText4);
        Spinner spinner = (Spinner) mainActivity.findViewById(R.id.spinner);
        spinner.setSelection(0);
        RadioGroup rg= (RadioGroup)mainActivity.findViewById(R.id.radioGroup);
        RadioButton radio1 = (RadioButton) mainActivity.findViewById(rg.getCheckedRadioButtonId());
        radio1.setChecked(true);
        EditText result1 = (EditText) mainActivity.findViewById(R.id.result1);
        EditText result2 = (EditText) mainActivity.findViewById(R.id.result2);
        textField1.setText("-1");
        textField2.setText("2");
        textField3.setText("4");
        textField4.setText("3");
        btnSubmit.performClick();
        assertEquals(result1.getText().toString(), "3.0");
        assertEquals(result2.getText().toString(), "5.0");
    }

    @UiThreadTest
        public void testDisplayResultDotProduct(){

        Button btnSubmit = (Button) mainActivity.findViewById(R.id.btnSubmit);
        EditText textField1 = (EditText) mainActivity.findViewById(R.id.editText);
        EditText textField2 = (EditText) mainActivity.findViewById(R.id.editText5);
        EditText textField3 = (EditText) mainActivity.findViewById(R.id.editText2);
        EditText textField4 = (EditText) mainActivity.findViewById(R.id.editText4);
        Spinner spinner = (Spinner) mainActivity.findViewById(R.id.spinner);
        spinner.setSelection(1);
        RadioGroup rg= (RadioGroup)mainActivity.findViewById(R.id.radioGroup);
        RadioButton radio1 = (RadioButton) mainActivity.findViewById(rg.getCheckedRadioButtonId());
        radio1.setChecked(true);
        EditText result1 = (EditText) mainActivity.findViewById(R.id.result1);

        textField1.setText("-1");
        textField2.setText("2");
        textField3.setText("4");
        textField4.setText("3");
        btnSubmit.performClick();
        assertEquals(result1.getText().toString(), "2.0");


    }

    @UiThreadTest
    public void testDisplayResultVectorProduct(){

        Button btnSubmit = (Button) mainActivity.findViewById(R.id.btnSubmit);
        EditText textField1 = (EditText) mainActivity.findViewById(R.id.editText);
        EditText textField2 = (EditText) mainActivity.findViewById(R.id.editText5);
        EditText textField3 = (EditText) mainActivity.findViewById(R.id.editText2);
        EditText textField4 = (EditText) mainActivity.findViewById(R.id.editText4);
        Spinner spinner = (Spinner) mainActivity.findViewById(R.id.spinner);
        spinner.setSelection(2);
        RadioGroup rg= (RadioGroup)mainActivity.findViewById(R.id.radioGroup);
        RadioButton radio1 = (RadioButton) mainActivity.findViewById(rg.getCheckedRadioButtonId());
        radio1.setChecked(true);
        EditText result1 = (EditText) mainActivity.findViewById(R.id.result1);

        textField1.setText("-1");
        textField2.setText("2");
        textField3.setText("4");
        textField4.setText("3");
        btnSubmit.performClick();
        assertEquals(result1.getText().toString(), "-11.0");
    }
    @UiThreadTest
    public void testInvalidPolarRadius(){

        Button btnSubmit = (Button) mainActivity.findViewById(R.id.btnSubmit);
        EditText textField1 = (EditText) mainActivity.findViewById(R.id.editText);
        EditText textField2 = (EditText) mainActivity.findViewById(R.id.editText5);
        EditText textField3 = (EditText) mainActivity.findViewById(R.id.editText2);
        EditText textField4 = (EditText) mainActivity.findViewById(R.id.editText4);
        Spinner spinner = (Spinner) mainActivity.findViewById(R.id.spinner);
        spinner.setSelection(2);
        RadioGroup rg= (RadioGroup)mainActivity.findViewById(R.id.radioGroup);
        RadioButton radio2 = (RadioButton) mainActivity.findViewById(R.id.Polar);
        radio2.setChecked(true);
        EditText result1 = (EditText) mainActivity.findViewById(R.id.result1);
        textField1.setText("-1");
        textField2.setText("30");
        textField3.setText("-3");
        textField4.setText("3");
        btnSubmit.performClick();
        EditText errorMessage = (EditText) mainActivity.findViewById(R.id.errorMessage);
        assertEquals(errorMessage.getText().toString(), "Please enter a non-negative Radius and an angle between -360 and 360");

    }
    @UiThreadTest
    public void testInvalidPolarAngle(){

        Button btnSubmit = (Button) mainActivity.findViewById(R.id.btnSubmit);
        EditText textField1 = (EditText) mainActivity.findViewById(R.id.editText);
        EditText textField2 = (EditText) mainActivity.findViewById(R.id.editText5);
        EditText textField3 = (EditText) mainActivity.findViewById(R.id.editText2);
        EditText textField4 = (EditText) mainActivity.findViewById(R.id.editText4);
        Spinner spinner = (Spinner) mainActivity.findViewById(R.id.spinner);
        spinner.setSelection(2);
        RadioGroup rg= (RadioGroup)mainActivity.findViewById(R.id.radioGroup);
        RadioButton radio2 = (RadioButton) mainActivity.findViewById(R.id.Polar);
        radio2.setChecked(true);
        EditText result1 = (EditText) mainActivity.findViewById(R.id.result1);
        textField1.setText("1");
        textField2.setText("-370");
        textField3.setText("3");
        textField4.setText("399");
        btnSubmit.performClick();
        EditText errorMessage = (EditText) mainActivity.findViewById(R.id.errorMessage);
        assertEquals(errorMessage.getText().toString(), "Please enter a non-negative Radius and an angle between -360 and 360");

    }

    @UiThreadTest
    public void testInvalidPairsOfVectors(){
        Button btnSubmit = (Button) mainActivity.findViewById(R.id.btnSubmit);
        EditText textField1 = (EditText) mainActivity.findViewById(R.id.editText);
        EditText textField2 = (EditText) mainActivity.findViewById(R.id.editText5);
        EditText textField3 = (EditText) mainActivity.findViewById(R.id.editText2);
        EditText textField4 = (EditText) mainActivity.findViewById(R.id.editText4);
        EditText textField5 = (EditText) mainActivity.findViewById(R.id.editText6);
        EditText textField6 = (EditText) mainActivity.findViewById(R.id.editText3);
        Spinner spinner = (Spinner) mainActivity.findViewById(R.id.spinner);
        spinner.setSelection(0);
        RadioGroup rg= (RadioGroup)mainActivity.findViewById(R.id.radioGroup);
        RadioButton radio2 = (RadioButton) mainActivity.findViewById(R.id.Cartesian);
        radio2.setChecked(true);
        EditText result1 = (EditText) mainActivity.findViewById(R.id.result1);
        textField1.setText("1");
        textField3.setText("3");
        textField5.setText("399");
        textField6.setText("4");
        btnSubmit.performClick();
        EditText errorMessage = (EditText) mainActivity.findViewById(R.id.errorMessage);
        assertEquals(errorMessage.getText().toString(), "Please enter at least two valid vectors");
    }
}