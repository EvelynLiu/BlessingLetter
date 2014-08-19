package com.example.blessingletter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import com.facebook.*;
import com.facebook.model.*;


public class MainActivity extends Activity {
	private Button fblogin;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fblogin = (Button)findViewById(R.id.login_fb);
        fblogin.setOnClickListener(login_FB);
    }
    
    OnClickListener login_FB = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// start Facebook Login
			Session.openActiveSession(MainActivity.this, true, new Session.StatusCallback() {
			    // callback when session changes state
			    @Override
			    public void call(Session session, SessionState state, Exception exception) {
			    	if (session.isOpened()) {
			            // make request to the /me API
			            Request.newMeRequest(session, new Request.GraphUserCallback() {
			              // callback after Graph API response with user object
			              @Override
			              public void onCompleted(GraphUser user, Response response) {
			                if (user != null) {
			                  TextView welcome = (TextView) findViewById(R.id.welcome);
			                  welcome.setText("Hello " + user.getName() + "!");
			                }
			              }
			            }).executeAsync();
			    	}
			    }
			});
		}
	};
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
    
}
