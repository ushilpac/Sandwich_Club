package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {
            JSONObject sandwichJson = new JSONObject(json);

            if(sandwichJson != null) {
                sandwich = new Sandwich();

                JSONObject name = sandwichJson.getJSONObject("name");
                if(name != null){
                    sandwich.setMainName(name.getString("mainName"));

                    JSONArray alsoKnown = name.getJSONArray("alsoKnownAs");
                    if(alsoKnown !=null){
                        List<String> listNames = new ArrayList<>();
                        for(int i=0; i<alsoKnown.length(); i++) {
                            String str = alsoKnown.getString(i);
                            listNames.add(str);
                        }

                        sandwich.setAlsoKnownAs(listNames);
                    }

                    String origin = sandwichJson.getString("placeOfOrigin");
                    sandwich.setPlaceOfOrigin(origin);

                    String description = sandwichJson.getString("description");
                    sandwich.setDescription(description);

                    String image = sandwichJson.getString("image");
                    sandwich.setImage(image);

                    JSONArray ingredients = sandwichJson.getJSONArray("ingredients");
                    if(ingredients !=null){
                        List<String> ingredientList = new ArrayList<>();
                        for(int i=0; i<ingredients.length(); i++){
                            String str = ingredients.getString(i);
                            ingredientList.add(str);
                        }

                        sandwich.setIngredients(ingredientList);
                    }

                }

            }
        }catch(JSONException e){
            e.printStackTrace();
        }

        return sandwich;
    }
}
