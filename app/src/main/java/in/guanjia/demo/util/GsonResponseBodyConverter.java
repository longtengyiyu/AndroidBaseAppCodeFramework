package in.guanjia.demo.util;

import com.google.gson.TypeAdapter;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.io.Reader;

import retrofit.Converter;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/29
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/29        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public final class GsonResponseBodyConverter <T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        Reader reader = value.charStream();
        try {
            return adapter.fromJson(reader);
        } finally {
            Utils.getInstance().closeQuietly(reader);
        }
    }
}