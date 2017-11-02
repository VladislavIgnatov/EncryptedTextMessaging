package wordedonmymachine.encryptedtextmessaging;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mylist = (ListView)findViewById(R.id.SMSList);

        if(fetchInbox() != null){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,fetchInbox());
            mylist.setAdapter(adapter);
        }

        FloatingActionButton newmsgbutton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        newmsgbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent newmessagepage = new Intent(MainActivity.this, new_message.class);
                startActivity(newmessagepage);
            }
        });
    }

    public ArrayList<String> fetchInbox() {
        ArrayList<String> sms = new ArrayList<String>();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uri, new String[]{"_id", "address", "date", "body"}, null, null,null );

        cursor.moveToFirst();
        while(cursor.moveToNext()){
            String address = cursor.getString(1);
            String body = cursor.getString(3);

            sms.add("Contact:  " + address + "\nSmS:  " + body);
        }

        return sms;
    }

    // Action bar search and settings
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openSearch() {
    }
    public void openSettings() {
    }


}
