package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        final String TAG = Sandwich.class.getName();

        Sandwich requestedSandwich;

        JSONObject sandwichDataObject, nameDataObject;
        JSONArray alsoKnownAsList, ingredientList;

        String mainName;
        List<String> alsoKnownAs = new ArrayList<String>() {};
        List<String> ingredients = new ArrayList<String>() {};
        String description;
        String placeOfOrigin;
        String image;

        try {
            sandwichDataObject = new JSONObject(json);
            nameDataObject = new JSONObject(sandwichDataObject.getString("name"));

            mainName = nameDataObject.getString("mainName");
            alsoKnownAsList = nameDataObject.getJSONArray("alsoKnownAs");
            placeOfOrigin = sandwichDataObject.getString("placeOfOrigin");
            description = sandwichDataObject.getString("description");
            image = sandwichDataObject.getString("image");
            ingredientList = sandwichDataObject.getJSONArray("ingredients");

            for (int i = 0; i < ingredientList.length(); i++) {
                ingredients.add(ingredientList.getString(i));
            }

            for (int i = 0; i < alsoKnownAsList.length(); i++) {
                alsoKnownAs.add(alsoKnownAsList.getString(i));
            }

            requestedSandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            Log.e(TAG, "Unable to load requested sandwich from data received.");
            return null;
        }

        return requestedSandwich;
    }
}
