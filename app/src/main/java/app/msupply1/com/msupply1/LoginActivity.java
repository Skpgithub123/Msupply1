package app.msupply1.com.msupply1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by uOhmac Technologies on 12-Jun-17.
 */
public class LoginActivity extends AppCompatActivity {

    EditText etmobile,etpassword;
    Button btnlogin;
    TextView tvnewuser,tv,textView,ytt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        etmobile = (EditText)findViewById(R.id.txtMobile);
        etpassword = (EditText)findViewById(R.id.etPassword);
        btnlogin = (Button)findViewById(R.id.btn_login);
        tvnewuser = (TextView)findViewById(R.id.txt_registernew);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etmobile.getText().toString().length()>0&&etpassword.getText().toString().length()>0){
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this, "Please fill the form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Newregistration.class);
                startActivity(i);
                finish();
            }
        });
    }
}
