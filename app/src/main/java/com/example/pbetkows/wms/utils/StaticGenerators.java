package com.example.pbetkows.wms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StaticGenerators {

    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
}
