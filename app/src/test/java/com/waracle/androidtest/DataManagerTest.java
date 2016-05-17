package com.waracle.androidtest;

import com.waracle.androidtest.util.DataManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by DonKamillo on 17.05.2016.
 */
public class DataManagerTest {


    @Test
    public void testParseCharset() {
        String result = null;
        String code = null;

        result = DataManager.parseCharset(code);
        assertEquals(result, "UTF-8");

        code = "";
        result = DataManager.parseCharset(code);
        assertEquals(result, "UTF-8");

        code = "Content-Type: text/html; charset=UTF-8";
        result = DataManager.parseCharset(code);
        assertEquals(result, "UTF-8");

        code = "Content-Type: text/html; charset=ISO-8859-1";
        result = DataManager.parseCharset(code);
        assertEquals(result, "ISO-8859-1");

    }

    @Test
    public void testLoadData() {
        JSONArray result = null;

        try {
            result = DataManager.loadData();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        assertNotNull(result);
    }

}
