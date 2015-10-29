package in.guanjia.demo.listener;

import java.util.HashMap;

import in.guanjia.demo.bean.IpAddressInfo;
import in.guanjia.demo.bean.WeChat;
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

    @GET("http://int.dpool.sina.com.cn/iplookup/iplookup.php")
    Call<IpAddressInfo> getIpAddressInfo(@Query("format") String format, @Query("ip") String ip);


    //定义完整url，自动忽略baseUrl，扩展性大大提升，赞
    @POST("http://int.dpool.sina.com.cn/iplookup/iplookup.php")
    Call<IpAddressInfo> getIpAddressInfo();

    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Call<WeChat> getAccessToken(@Query("appid") String appId,@Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grantType);

    /**
     * 参数多的话可以写成QueryMap
     * 刚开始就写的QueryMap
     * 死活拿不到数据以为是不支持
     * 原因是自己少写了回调监听
     * 切记～认真点
     */
    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Call<WeChat> getAccessToken(@QueryMap HashMap<String, String> params);

}
