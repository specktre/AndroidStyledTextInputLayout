package com.specktre.android.styledtil;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DemoActivity extends AppCompatActivity {

    @Bind(R.id.tilEditText)
    TextInputLayout editTextTil;

    @Bind(R.id.tilAutoCompleteTextView)
    TextInputLayout autoCompleteTextTil;

    @Bind(R.id.actvAutoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;

    String[] items = {"Android ", "iOS", "WindowsPhone", "BlackBerry"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);

        setStyleForTextForAutoComplete(getResources().getColor(R.color.greyLight));
        autoCompleteTextView.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus) {
                setStyleForTextForAutoComplete(getResources().getColor(R.color.blue));
            } else {
                if(autoCompleteTextView.getText().length() == 0) {
                    setStyleForTextForAutoComplete(getResources().getColor(R.color.greyLight));
                }
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
    }

    private void setStyleForTextForAutoComplete(int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(autoCompleteTextView.getBackground());
        DrawableCompat.setTint(wrappedDrawable, color);
        autoCompleteTextView.setBackgroundDrawable(wrappedDrawable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.showErrorsButton)
    public void onShowErrorsButtonClick() {
        editTextTil.setErrorEnabled(true);
        autoCompleteTextTil.setErrorEnabled(true);
        editTextTil.setError("Showcase of error on EditText");
        autoCompleteTextTil.setError("Showcase of error on AutoCompleteTextView ");
    }
}
