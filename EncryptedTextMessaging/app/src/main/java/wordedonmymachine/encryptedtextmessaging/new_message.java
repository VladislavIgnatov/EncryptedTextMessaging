package wordedonmymachine.encryptedtextmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class new_message extends AppCompatActivity {
    Button send;
    EditText phone, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        // Enables back button on action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        send = (Button)findViewById(R.id.sendBtn);
        phone = (EditText)findViewById(R.id.phoneNumberBox);
        message = (EditText)findViewById(R.id.messageTextBox);
        send.setOnClickListener(new View.OnClickListener() {
            @Override            public void onClick(View v) {
                sendmessage();

            }
        });
    }
    public  void sendmessage(){
        String number= phone.getText().toString().trim();
        String mess= message.getText().toString().trim();
        if(number==null || number.equals("")||mess==null|| mess.equals("")){
            Toast.makeText(this,"field can't be empty",Toast.LENGTH_LONG).show();

        }
        else        {
            if(TextUtils.isDigitsOnly(number)){
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number,null,mess,null,null);
                Toast.makeText(this,"message send successfully" +number,Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this,"please enter integer only",Toast.LENGTH_LONG).show();
            }
        }

    }
}