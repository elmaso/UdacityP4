package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by mariosoberanis on 1/3/16.
 */
public class NonEmptyStringTest extends AndroidTestCase {

    private static String LOG_TAG = "NonEmptyStringTest";

    @SuppressWarnings("unchecked")
    public void runTest() {
        // Testeting that the async task dosetn return emptu string
        // To test --> Run  'All Tests'

        String result = null;
        GetJokeAsyncTask endpointsAsyncTask = new GetJokeAsyncTask(getContext());
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
            Log.d(LOG_TAG, "Retrieved a non-empty string successfully: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(result);
    }
}

