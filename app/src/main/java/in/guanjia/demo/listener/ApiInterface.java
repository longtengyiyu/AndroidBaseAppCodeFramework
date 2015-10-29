package in.guanjia.demo.listener;

import java.util.HashMap;

import in.guanjia.demo.bean.IpAddressInfo;
import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

/**
 * Description:請求註解类
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/27
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/27        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public interface ApiInterface {

    @GET("/user")
    void login(@Query("limit") int limit , Callback<String> callback);

    @GET("/group/{id}/users")
    void register(@Path("id") int groupId, Callback<String> callback);

    @FormUrlEncoded
    @POST("/user")
    void stop(@QueryMap HashMap<String, String> mapParam, Callback<String> callback);

    @GET("/user")
    void load(@Body String param, Callback<String> callback);

    @FormUrlEncoded
    @POST("/user")
    void update(@Header("head") String param, Callback<String> callback);

    @FormUrlEncoded
    @POST("/user/edit")
    void updateUser(@Field("firstName") String first, @Field("lastName") String last);

    @GET("/USER")
    String upload(@Field("firstName") String first, @Field("lastName") String last);

    @GET("/iplookup/iplookup.php")
    Call<IpAddressInfo> getIpAddressInfo(@Query("format") String format, @Query("ip") String ip);


    //定义完整url，自动忽略baseUrl，扩展性大大提升，赞
    @POST("http://int.dpool.sina.com.cn/iplookup/iplookup.php")
    Call<IpAddressInfo> getIpAddressInfo();



}
