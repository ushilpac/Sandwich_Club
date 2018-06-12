package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Log.d("DetailActivity", json);
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.mipmap.sandwich_default)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView  name = (TextView) findViewById(R.id.main_name_tv);
        name.setText(sandwich.getMainName());

        TextView  knownAs = (TextView) findViewById(R.id.known_tv_value);
        TextView known_as_label_tv = (TextView) findViewById(R.id.known_as_tv);
        List<String> knownList = sandwich.getAlsoKnownAs();

        if(knownList.size()>0) {
            if(known_as_label_tv.getVisibility() == View.INVISIBLE) {
                known_as_label_tv.setVisibility(View.VISIBLE);
            }
            knownAs.setText("" +knownList);
        }else{
            known_as_label_tv.setVisibility(View.INVISIBLE);
        }

        TextView origin = (TextView) findViewById(R.id.origin_tv_value);
        origin.setText(sandwich.getPlaceOfOrigin());

        TextView  ingredient = (TextView) findViewById(R.id.ingredient_tv_value);
        List<String> ingredientList = sandwich.getIngredients();
        if(ingredientList.size()>0) {
            ingredient.setText("" + sandwich.getIngredients());
        }

        TextView description = (TextView) findViewById(R.id.description_tv_value);
        description.append(sandwich.getDescription());

    }
}
