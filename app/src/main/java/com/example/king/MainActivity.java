package com.example.king;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* when we click plus button  */

    int numberOfCoffee = 0;                // this has now become the global variable which  cna be access everywhere

    public void increment(View view) {
        if (numberOfCoffee == 20) {
            Toast.makeText(this, "You cannot have more than 20 coffee", Toast.LENGTH_SHORT).show();

            return;

        }
        numberOfCoffee = numberOfCoffee + 1;
        displayQuantity(numberOfCoffee);
    }

    /* when we click minus button  */

    public void decrement(View view) {
        if (numberOfCoffee == 0) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        numberOfCoffee = numberOfCoffee - 1;
        displayQuantity(numberOfCoffee);
    }

    /* this method  is  called when the order button is called */

    public void submitOrder(View view) {
//       1. display(numberOfCoffee);
//        displayPrice(numberOfCoffee * 5);
//        or // int totalPrice = numberOfCoffee * 5;   //either we can define inside this and call the price

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolateCream = (CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean hasChocolateCream = chocolateCream.isChecked();


        int price = calculatePrice(hasWhippedCream, hasChocolateCream);       //or we can create another method and call it from that

        String priceMessage = "Total : $" + (price);
        priceMessage = priceMessage + " \nName : " + name;
        priceMessage += "\nAdd Whipped Cream: " + hasWhippedCream;
        priceMessage += "\nAdd Chocolate Cream: " + hasChocolateCream;
        priceMessage += "\n" + getString(R.string.thank_you);               // string connected
        displayMessage(priceMessage);


       /*
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo :47.6,-123.3"));
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }       //THIS IS USED TO ADD ANY FEATURES  LOCATION
        */

        /*  THIS IS TO SENT MAIL TO USER  // this is implicit intent */
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("mailto:"));               // parse  is used to convert something
        // intent.putExtra(Intent.EXTRA_EMAIL,addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "just order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT, "just order for" + priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //price method
    private int calculatePrice(boolean whipped, boolean choco) {       //        int price =  numberOfCoffee *5;        // we can perform in both way to short the code     //    return price;     //    return numberOfCoffee * 5;    // value is directly getting store in calculatePrice

        int basePrice = 5;

        if (whipped) {
            basePrice = basePrice + 1;
        }
        if (choco) {
            basePrice += 2;
        }
        return numberOfCoffee * basePrice;

    }

/*      private void createOrderSummary(int numberOfCoffee ){
        String priceMessage = "Name : tik";
        priceMessage +="\nQuantity :" + numberOfCoffee;
        priceMessage = priceMessage +"\nTotal : $";
    }
*/


    /* This is to display message */
    private void displayMessage(String Message) {
        TextView priceMessage = (TextView) findViewById(R.id.price_text_view);
        priceMessage.setText(Message);
    }

    /* this method displays the given quantity value on the screen  */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }



/*

    1.this is another way to display price
*/
    /* this is to display th given price value on the screen  *//*


    private void displayPrice(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText("$" + "" + number);
    }

*/


}