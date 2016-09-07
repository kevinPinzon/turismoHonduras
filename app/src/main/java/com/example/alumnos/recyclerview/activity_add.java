package com.example.alumnos.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_add extends AppCompatActivity {

    private Place_Helper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_add);

        db = Place_Helper.newInstance(this);

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPlace();
            }
        });
    }

    public void processPlace() {
        EditText title = (EditText) findViewById(R.id.title);
        EditText description = (EditText) findViewById(R.id.description);
        EditText pictureUrl = (EditText) findViewById(R.id.urlPic);

        Place place = new Place();
        place.setTitle(title.getText().toString());
        place.setDescription(description.getText().toString());
        place.setPictureUrl(pictureUrl.getText().toString());


        db.addPlace(place);
        finish();
    }
}
