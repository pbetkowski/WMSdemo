package com.example.pbetkows.wms.services;

import android.content.Context;
import android.widget.ListView;

import java.util.List;

public interface DBSample {

    void getAllPostToList(ListView listView, List<String> result,  SampleService service,  Context context);
}
