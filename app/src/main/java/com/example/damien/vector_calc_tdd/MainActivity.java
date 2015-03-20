package com.example.damien.vector_calc_tdd;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Vector;

import android.view.View.OnClickListener;


public class MainActivity extends ActionBarActivity {


//instanciating all UI elements that will interact
    private Spinner spinner;
    private Button btnSubmit;
    private Canvas myCanvas;
    private Paint myPaint;
    private Bitmap bg;
    SurfaceView vectorGraph;
    double initialIncrementX;
    double initialIncrementY;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addItemOnSpinner();

        //creating objects referring to UI: submit Button, graph canvas spinner
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(bg);
        myPaint = new Paint();
        LinearLayout ll = (LinearLayout) findViewById(R.id.rect);
        ll.setBackgroundDrawable(new BitmapDrawable(bg));

        myCanvas.drawRGB(255, 255, 255);

        //onclick event for submit button
        btnSubmit.setOnClickListener (new View.OnClickListener() {

            //getting all text box objects to extract data
            EditText firstX = (EditText) findViewById(R.id.editText);
            EditText firstY = (EditText) findViewById(R.id.editText5);
            EditText secondX =(EditText) findViewById(R.id.editText2);
            EditText secondY =(EditText) findViewById(R.id.editText4);
            EditText thirdX =(EditText) findViewById(R.id.editText6);
            EditText thirdY =(EditText) findViewById(R.id.editText3);

            @Override

            public void onClick(View view) {
                //getting spinner value
                Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
                EditText errorBox = (EditText) findViewById(R.id.errorMessage);
                errorBox.setText("");

                //getting coordinate type from radio button
                String rbutton = "";
                RadioGroup rg=(RadioGroup)findViewById(R.id.radioGroup);
                RadioButton radioValue= (RadioButton) findViewById(rg.getCheckedRadioButtonId());

                String spinnerChoice = mySpinner.getSelectedItem().toString();
                String radioChoice = radioValue.getText().toString();

                //getting all text from input boxes
                String textForm = firstX.getText().toString();
                addListenerOnSpinnerItemSelection();
                ((EditText) findViewById(R.id.result1)).setText("");
                ((EditText) findViewById(R.id.result2)).setText("");
                processInput(firstX.getText().toString(),
                        firstY.getText().toString(),
                        secondX.getText().toString(),
                        secondY.getText().toString(),
                        thirdX.getText().toString(),
                        thirdY.getText().toString(),
                        radioChoice, spinnerChoice);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedValue = parent.getItemAtPosition(position).toString();

    }


    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addItemOnSpinner(){

        spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.calculation_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        addListenerOnSpinnerItemSelection();
    }

    public void addListenerOnSpinnerItemSelection(){
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.spinner);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


            }

        });
    }

    //input checking for all pairs of doubles entered into the text box. Calls the respective perform functions
    //for each type of calculation according to the inputted data
    public boolean parsePairs(double x1, double y1,
                              double x2, double y2,
                              double x3, double y3,
                              boolean firstPairValid, boolean secondPairValid, boolean thirdPairValid,
                              String coordinateType, String calculationType){
        //setting calcualted variable according to entered pairs
        double operationX1, operationX2, operationY1, operationY2;
        if(firstPairValid){
            if(secondPairValid){

               operationX1 = x1;
               operationY1 = y1;
               operationX2 = x2;
               operationY2 = y2;
            }
            else{
                operationX1 = x1;
                operationY1 = y1;
                operationX2 = x3;
                operationY2 = y3;
           }
        }
        else{
            operationX1 = x2;
            operationY1 = y2;
            operationX2 = x3;
            operationY2 = y3;

        }
        //calling perform function for each operation
        switch(calculationType){
            case "vectorProduct":
                return performVectorProduct(operationX1, operationY1, operationX2, operationY2, coordinateType);
            case "dotProduct":
                return performDotProduct(operationX1, operationY1, operationX2, operationY2, coordinateType);
            case "addition":
                return performVectorAddition(operationX1, operationY1, operationX2, operationY2, coordinateType);
        }
        return false;
    }

    //performs all error checking for inputs
    public boolean processInput(String xCoord1, String yCoord1, String xCoord2, String yCoord2, String xCoord3, String yCoord3, String coordinateType, String calculationType) {

        myCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY);
        double x1 =0.0;
        double y1 =0.0;
        double x2 =0.0;
        double y2 =0.0;
        double x3 =0.0;
        double y3 =0.0;
        int validCounter = 0;
        boolean firstPairValid = false;
        boolean secondPairValid = false;
        boolean thirdPairValid = false;

        //check if first pair of double is entered validly
        if(stringCheck(xCoord1) && stringCheck(yCoord1)){
            x1 = Double.parseDouble(xCoord1);
            y1 = Double.parseDouble(yCoord1);
            firstPairValid = true;
            validCounter++;
        }
        //checks second pair
        if(stringCheck(xCoord2) && stringCheck(yCoord2)){
            x2 = Double.parseDouble(xCoord2);
            y2 = Double.parseDouble(yCoord2);
            secondPairValid = true;
            validCounter++;
        }
        //checks third pair
        if(stringCheck(xCoord3) && stringCheck(yCoord3)){
            x3 = Double.parseDouble(xCoord3);
            y3 = Double.parseDouble(yCoord3);
            thirdPairValid = true;
            validCounter++;
        }

        //depending on operation type, performs calculations or applies further processing
        //to call operations only on the valid doubles extracted
        switch (calculationType){
            case "Addition":
                if(validCounter == 3){
                    return performVectorAddition(x1, y1, x2, y2, x3, y3, coordinateType);
                }else if (validCounter == 2){
                   return parsePairs(x1,y1,x2,y2,x3,y3,firstPairValid,secondPairValid,thirdPairValid,coordinateType,"addition");
                }
                else{
                    EditText errorBox = (EditText) findViewById(R.id.errorMessage);
                    errorBox.setText("Please enter at least two valid vectors");
                }
                break;

            case "Dot Product":
                if(validCounter ==2 ) {
                   return parsePairs(x1,y1,x2,y2,x3,y3,firstPairValid,secondPairValid,thirdPairValid,coordinateType,"dotProduct");
                }
                else{
                    EditText errorBox = (EditText) findViewById(R.id.errorMessage);
                    errorBox.setText("Please enter exactly two valid vectors");
                }
                break;

            case "Vector Product":
                if(validCounter == 2) {
                   return parsePairs(x1,y1,x2,y2,x3,y3,firstPairValid,secondPairValid,thirdPairValid,coordinateType,"vectorProduct");
                }
                else{
                    EditText errorBox = (EditText) findViewById(R.id.errorMessage);
                    errorBox.setText("Please enter exactly two valid vectors");
                }
                break;

        }



    return false;

    }
    //check wether given polar (r, theta) are within valid ranges
    public boolean filterPolarInputs(double r, double theta){

        if(r>0 && theta>=-360 && theta<=360) {
            return true;
        }
        return false;
    }

    //given 2 input vectors, creates the graph scale(domain and range), then draws the vectors
    public void makeScale( double x1, double y1, double x2, double y2, String coordinateType){
        double scaleDomain;
        double scaleRange;
        //cartesian, simply draws scale and vector
        if(coordinateType.equals("Cartesian")) {
           scaleDomain = compareDomain(x1, x2);
           scaleRange = compareRange(y1, y2);
            drawScale(scaleDomain, scaleRange);
            drawVector(x1,y1);
            drawVector(x2,y2);
       }
        //for polar, converts to cartesian and draws
        else{
           double cartesianX1 = x1 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y1)));
           double cartesianY1 = x1 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y1)));
           double cartesianX2 = x2 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y2)));
           double cartesianY2 = x2 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y2)));

            scaleDomain = compareDomain(cartesianX1, cartesianX2);
            scaleRange = compareRange(cartesianY1, cartesianY2);
            drawScale(scaleDomain, scaleRange);
            drawVector(cartesianX1,cartesianY1);
            drawVector(cartesianX2, cartesianY2);

       }

    }

    //identical as the above functon, for 3 vectors (addition of 2)

    public void makeScale(double x1, double y1, double x2, double y2, double x3, double y3, String coordinateType){
        double scaleDomain;
        double scaleRange;
        if(coordinateType.equals("Cartesian")) {

            scaleDomain = compareDomain(x1, x2, x3);

            scaleRange = compareDomain(y1, y2, y3);
            drawScale(scaleDomain, scaleRange);
            drawVector(x1,y1);
            drawVector(x2,y2);
            drawVector(x3,y3);
        }
        else{
            double cartesianX1 = x1 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y1)));
            double cartesianY1 = x1 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y1)));
            double cartesianX2 = x2 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y2)));
            double cartesianY2 = x2 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y2)));
            double cartesianX3 = x3 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y3)));
            double cartesianY3 = x3 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y3)));

            scaleDomain = compareDomain(cartesianX1, cartesianX2, cartesianX3);
            scaleRange = compareDomain(cartesianY1, cartesianY2, cartesianY3);
            drawScale(scaleDomain, scaleRange);
            drawVector(cartesianX1,cartesianY1);
            drawVector(cartesianX2, cartesianY2);
            drawVector(cartesianX3, cartesianY3);
        }

    }

    //identical as the above function, but for 4 vectors (addition of 3)
    public void makeScale(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4, String coordinateType){
        double scaleDomain;
        double scaleRange;
        if(coordinateType.equals("Cartesian")) {

            if(Math.abs(compareDomain(x1, x2, x3))<Math.abs(x4)){
                scaleDomain = x4;
            }
            else scaleDomain = compareDomain(x1, x2, x3);

            if(Math.abs(compareDomain(y1, y2, y3))<Math.abs(y4)){
                scaleRange = y4;
            }
            else{
                scaleRange = compareDomain(y1, y2, y3);
            }
            drawScale(scaleDomain, scaleRange);
            drawVector(x1,y1);
            drawVector(x2,y2);
            drawVector(x3,y3);
            drawVector(x4,y4);

        }
        else{
            double cartesianX1 = x1 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y1)));
            double cartesianY1 = x1 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y1)));
            double cartesianX2 = x2 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y2)));
            double cartesianY2 = x2 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y2)));
            double cartesianX3 = x3 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y3)));
            double cartesianY3 = x3 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y3)));
            double cartesianX4 = x4 * VectorCalculator.formatCosin(Math.cos(Math.toRadians(y4)));
            double cartesianY4 = x4 * VectorCalculator.formatCosin(Math.sin(Math.toRadians(y4)));

            if (Math.abs(compareDomain(cartesianX1, cartesianX2, cartesianX3))<Math.abs(cartesianX4)){
                scaleDomain = cartesianX4;
            } else
                scaleDomain = compareDomain(cartesianX1, cartesianX2, cartesianX3);
            if (Math.abs(compareDomain(cartesianY1, cartesianY2, cartesianY3))<Math.abs(cartesianY4)){
                scaleRange = cartesianY4;
            } else {
                scaleRange = compareDomain(cartesianY1, cartesianY2, cartesianY3);
            }
            drawScale(scaleDomain, scaleRange);
            drawVector(cartesianX1,cartesianY1);
            drawVector(cartesianX2, cartesianY2);
            drawVector(cartesianX3, cartesianY3);
            drawVector(cartesianX4,cartesianY4);
        }

    }

    //checking which vector x coordinate is the largest, in order for all the vectors to be displayed within the scale
    //for 2 vectors
    public double compareDomain( double x1, double x2){
        if(Math.abs(x1)>Math.abs(x2)) return x1;
        else return x2;
    }
    //for 3 vectors
    public double compareDomain(double x1, double x2, double x3){
        if(Math.abs(x1)>Math.abs(x2)){
            if(Math.abs(x1)>Math.abs(x3)){
                return x1;
            }
            else{
                return x3;
            }
        }
        else{
            if(Math.abs(x2)>Math.abs(x3)){
                return x2;
            }
            else return x3;
        }
    }
    //identical as compareDomain
    public double compareRange( double y1, double y2){
        if(Math.abs(y1)>Math.abs(y2)) return y1;
        else return y2;

    }
    //parses the given double to call the appropriate vector calculating function
    //and displays the result
    private boolean performDotProduct(double x1, double y1, double x2, double y2, String coordinateType) {


        boolean returnValue = false;
        double result = 0;
        Vector<Double> vector1 = new Vector<Double>();
        Vector<Double> vector2 = new Vector<Double>();
        vector1.add(x1);
        vector1.add(y1);
        vector2.add(x2);
        vector2.add(y2);

        //checks if cartesian
        if (coordinateType.equals("Cartesian")) {
            result = VectorCalculator.vectorScalar(vector1, vector2);
            returnValue = true;
            //displays values in result boxes
            EditText result1 = (EditText) findViewById(R.id.result1);
            EditText result2 = (EditText) findViewById(R.id.result1);
            result1.setText(result + "");
        } else {
            //for polar, checks if coordinates are in range
             if(filterPolarInputs(x1, y1) && filterPolarInputs(x2,y2)) {
                 result = VectorCalculator.vectorScalarPolar(vector1, vector2);
                 returnValue = true;
                 //displays result
                 EditText result1 = (EditText) findViewById(R.id.result1);
                 EditText result2 = (EditText) findViewById(R.id.result1);
                 result1.setText(result + "");
             }
            else{
                 //if not within range, displays error
                 EditText errorBox = (EditText) findViewById(R.id.errorMessage);
                 errorBox.setText("Please enter a non-negative Radius and an angle between -360 and 360");
                 return returnValue;
             }
            }

            //creates graph
            makeScale(x1, y1, x2, y2, coordinateType);

            return returnValue;
        }


    //parses the given double to call the appropriate vector calculating function
    //and displays the result
    private boolean performVectorProduct(double x1, double y1, double x2, double y2, String coordinateType) {
        boolean returnValue = false;
        double result = 0;
        Vector<Double> vector1 = new Vector<Double>();
        Vector<Double> vector2 = new Vector<Double>();
        vector1.add(x1);
        vector1.add(y1);
        vector2.add(x2);
        vector2.add(y2);
        //cartesian
        if (coordinateType.equals("Cartesian")) {
            //calculates
            result = VectorCalculator.vectorProductCartesian(vector1, vector2);
            returnValue = true;
            //displays result
            EditText result1 = (EditText)findViewById(R.id.result1);
            result1.setText(result + "");
        } else {
            //polar filtering
                if(filterPolarInputs(x1, y1) && filterPolarInputs(x2,y2)) {
                    //calculates
                    result = VectorCalculator.vectorProductPolar(vector1, vector2);
                    returnValue = true;
                    //displays result
                    EditText result1 = (EditText)findViewById(R.id.result1);
                    result1.setText(result + "");

                }
            else{
                    EditText errorBox = (EditText) findViewById(R.id.errorMessage);
                    errorBox.setText("Please enter a non-negative Radius and an angle between -360 and 360");
                    return returnValue;
                }
            }
        //draws graph
        makeScale(x1, y1, x2, y2, coordinateType);

        return returnValue;

        }

    //parses the given double to call the appropriate vector calculating function
    //and displays the result
    private boolean performVectorAddition(double x1, double y1, double x2, double y2, String coordinateType) {
        boolean returnValue = false;
        Vector<Double> result;
        Vector<Double> vector1 = new Vector<Double>();
        Vector<Double> vector2 = new Vector<Double>();
        vector1.add(x1);
        vector1.add(y1);
        vector2.add(x2);
        vector2.add(y2);
        //cartesian
        if (coordinateType.equals("Cartesian")) {
            //calculates
            result = VectorCalculator.vectorAddition(vector1, vector2);
            returnValue = true;
            //displays result
            EditText result1 = (EditText) findViewById(R.id.result1);
            EditText result2 = (EditText) findViewById(R.id.result2);
            result1.setText(result.elementAt(0).toString());
            result2.setText(result.elementAt(1).toString());
        }
         else{
            //polar
                if(filterPolarInputs(x1, y1) && filterPolarInputs(x2,y2)) {
                    //calculates
                    result = VectorCalculator.vectorAdditionPolar(vector1, vector2);
                    returnValue = true;
                    //displays result
                    EditText result1 = (EditText) findViewById(R.id.result1);
                    EditText result2 = (EditText) findViewById(R.id.result2);
                    result1.setText(result.elementAt(0).toString());
                    result2.setText(result.elementAt(1).toString());
                }
            else{
                    EditText errorBox = (EditText) findViewById(R.id.errorMessage);
                    errorBox.setText("Please enter a non-negative Radius and an angle between -360 and 360");

                    return returnValue;
                }
            }
            //draws graph
            makeScale(result.elementAt(0),result.elementAt(1),x1,y1, x2, y2, coordinateType);

            return returnValue;

        }

    //parses the given double to call the appropriate vector calculating function
    //and displays the result
    private boolean performVectorAddition(double x1, double y1, double x2, double y2, double x3, double y3, String coordinateType) {
        boolean returnValue = false;
        Vector<Double> result;
        Vector<Double> vector1 = new Vector<Double>();
        Vector<Double> vector2 = new Vector<Double>();
        Vector<Double> vector3 = new Vector<Double>();
        vector1.add(x1);
        vector1.add(y1);
        vector2.add(x2);
        vector2.add(y2);
        vector3.add(x3);
        vector3.add(y3);

        if (coordinateType.equals("Cartesian")) {
            //calculates
            result = VectorCalculator.vectorAddition(vector1, vector2, vector3);
            returnValue = true;
            //dsplays result
            EditText result1 = (EditText) findViewById(R.id.result1);
            EditText result2 = (EditText) findViewById(R.id.result2);
            result1.setText(result.elementAt(0).toString());
            result2.setText(result.elementAt(1).toString());
        }
        else{
            //polar filter
            if(filterPolarInputs(x1, y1) && filterPolarInputs(x2,y2) && filterPolarInputs(x3,y3)) {
                //calculates
                result = VectorCalculator.vectorAdditionPolar(vector1, vector2, vector3);
                returnValue = true;
                //displays result
                EditText result1 = (EditText) findViewById(R.id.result1);
                EditText result2 = (EditText) findViewById(R.id.result2);
                result1.setText(result.elementAt(0).toString());
                result2.setText(result.elementAt(1).toString());
            }
            else{
                return returnValue;
            }
        }
        //draw graph
        makeScale(x1, y1, x2, y2,x3,y3,result.elementAt(0), result.elementAt(1),coordinateType);

        return returnValue;

    }





    //checks if a given string is a double and converts it or returns false if it isn't a valid double
    public boolean stringCheck( String numberToConvert){

        try {
          Double.parseDouble(numberToConvert);
        } catch (NumberFormatException e){

                return false;
        }

        return true;
    }
    //draws a given vector according to the globally defined scale
    public boolean drawVector(double vectorX, double vectorY){

        myPaint.setColor(Color.RED);
        myPaint.setStrokeWidth(5);
        myPaint.setStyle(Paint.Style.STROKE);
        myCanvas.drawLine(myCanvas.getWidth()/2,
                          myCanvas.getHeight()/2,
                          myCanvas.getWidth()/2+(float)vectorX*50/(float)initialIncrementX,
                          (myCanvas.getHeight()/2+(float)-vectorY*50/(float)initialIncrementY),
                          myPaint);


        return true;
    }

    //incrementally draws a series of small lines and their corresponding x-y value on aset of crosshairs
    //in our canvas
    public boolean drawScale(double scaleX1, double scaleY1) {
        myPaint.setColor(Color.BLACK);
        myPaint.setStrokeWidth(2);
        myPaint.setStyle(Paint.Style.STROKE);
        myCanvas.drawLine(myCanvas.getWidth() / 2, 0, myCanvas.getWidth() / 2, myCanvas.getHeight(), myPaint);
        myCanvas.drawLine(0, myCanvas.getHeight() / 2, myCanvas.getWidth(), myCanvas.getHeight() / 2, myPaint);

        initialIncrementX = VectorCalculator.format(Math.abs(scaleX1)/3);
        initialIncrementY = VectorCalculator.format(Math.abs(scaleY1)/3);
        double incrementorX = VectorCalculator.format(Math.abs(scaleX1)/3);
        double incrementorY = VectorCalculator.format(Math.abs(scaleY1)/3);
        //positive x
        for (int i = 50; i < myCanvas.getWidth(); i = i + 50){
            myCanvas.drawLine(myCanvas.getWidth() / 2 + i,
                    myCanvas.getHeight() / 2 - 15,
                    myCanvas.getWidth() / 2 + i,
                    myCanvas.getHeight() / 2 + 15,
                    myPaint);
                    myPaint.setTextSize(20);
                    incrementorX = round(incrementorX, 2);
                    myCanvas.drawText(incrementorX + "", myCanvas.getWidth() / 2 + i, myCanvas.getHeight() / 2 - 17, myPaint);
                    incrementorX += initialIncrementX;
        }
                    incrementorX = -initialIncrementX;
        //negative x
        for (int i = 50; i < myCanvas.getWidth(); i = i + 50){
            myCanvas.drawLine(myCanvas.getWidth() / 2 - i,
                    myCanvas.getHeight() / 2 - 15,
                    myCanvas.getWidth() / 2 - i,
                    myCanvas.getHeight() / 2 + 15,
                    myPaint);
                    myPaint.setTextSize(20);
                    incrementorX = round(incrementorX, 2);
                    myCanvas.drawText(incrementorX + "", myCanvas.getWidth() / 2 - i, myCanvas.getHeight() / 2 - (float)(Math.pow(-1,i%50)*17), myPaint);
                    incrementorX -= initialIncrementX;
        }
        incrementorY = -initialIncrementY;
        //positive y
        for (int i = 50; i < myCanvas.getWidth(); i = i + 50){
            myCanvas.drawLine(myCanvas.getWidth() / 2 + 15,
                    myCanvas.getHeight() / 2 + i,
                    myCanvas.getWidth() / 2 - 15,
                    myCanvas.getHeight() / 2 + i,
                    myPaint);
                    myPaint.setTextSize(20);
                    incrementorY = round(incrementorY, 2);
                    myCanvas.drawText(incrementorY + "", myCanvas.getWidth() / 2 - 17, myCanvas.getHeight() / 2 + i, myPaint);
                    incrementorY -= initialIncrementY;
        }
        //negative y
        incrementorY = initialIncrementY;
        for (int i = 50; i < myCanvas.getWidth(); i = i + 50){
            myCanvas.drawLine(myCanvas.getWidth() / 2 - 15,
                    myCanvas.getHeight() / 2 - i,
                    myCanvas.getWidth() / 2 + 15,
                    myCanvas.getHeight() / 2 - i,
                    myPaint);
                    myPaint.setTextSize(20);
                    incrementorY = round(incrementorY, 2);
                    myCanvas.drawText(incrementorY + "", myCanvas.getWidth() / 2 - 17, myCanvas.getHeight() / 2 - i, myPaint);
                    incrementorY += initialIncrementY;
        }


        return true;
    }
    //rounds a double down to a given number of decimal places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
