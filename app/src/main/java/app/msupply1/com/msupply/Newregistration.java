package app.msupply1.com.msupply;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

/**
 * Created by uOhmac Technologies on 12-Jun-17.
 */
public class Newregistration extends AppCompatActivity {

    EditText etfirstname,etlastname,etemail,etpassword,etconfirmpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        etfirstname = (EditText)findViewById(R.id.et_firstname);
        etlastname = (EditText)findViewById(R.id.et_lastname);
    }
}
