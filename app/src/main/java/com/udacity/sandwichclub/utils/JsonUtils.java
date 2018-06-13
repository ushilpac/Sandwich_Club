package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String JSON_KEY_NAME="name";
    private static final String JSON_KEY_MAIN_NAME="mainName";
    private static final String JSON_KEY_ALSO_KNOWN="alsoKnownAs";
    private static final String JSON_KEY_INGREDIENTS="ingredients";
    private static final String JSON_KEY_IMAGE="image";
    private static final String JSON_KEY_DESCRIPTION="description";
    private static final String JSON_KEY_ORIGIN="placeOfOrigin";

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {
            JSONObject sandwichJson = new JSONObject(json);

            if(sandwichJson != null) {
                sandwich = new Sandwich();

                JSONObject name = sandwichJson.getJSONObject(JSON_KEY_NAME);
                if(name != null){
                    sandwich.setMainName(name.getString(JSON_KEY_MAIN_NAME));

                    JSONArray alsoKnown = name.getJSONArray(JSON_KEY_ALSO_KNOWN);
                    if(alsoKnown !=null){
                        List<String> listNames = new ArrayList<>();
                        for(int i=0; i<alsoKnown.length(); i++) {
                            String str = alsoKnown.getString(i);
                            listNames.add(str);
                        }

                        sandwich.setAlsoKnownAs(listNames);
                    }

                    String origin = sandwichJson.getString(JSON_KEY_ORIGIN);
                    sandwich.setPlaceOfOrigin(origin);

                    String description = sandwichJson.getString(JSON_KEY_DESCRIPTION);
                    sandwich.setDescription(description);

                    String image = sandwichJson.getString(JSON_KEY_IMAGE);
                    sandwich.setImage(image);

                    JSONArray ingredients = sandwichJson.getJSONArray(JSON_KEY_INGREDIENTS);
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
