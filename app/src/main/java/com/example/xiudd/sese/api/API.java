package com.example.xiudd.sese.api;

import com.example.xiudd.sese.base.BaseGson;
import com.example.xiudd.sese.base.EmptyGson;
import com.example.xiudd.sese.gson.BannerGson;
import com.example.xiudd.sese.gson.BeautifulDataBean;
import com.example.xiudd.sese.gson.BeautifulGson;
import com.example.xiudd.sese.gson.ForeignGson;
import com.example.xiudd.sese.gson.HotVideoGson;
import com.example.xiudd.sese.gson.MarqueenGson;
import com.example.xiudd.sese.gson.NovelGson;
import com.example.xiudd.sese.gson.PictureGson;
import com.example.xiudd.sese.gson.PostListGson;
import com.example.xiudd.sese.gson.ScoreGson;
import com.example.xiudd.sese.gson.SignGson;
import com.example.xiudd.sese.gson.SinglePostDetailGson;
import com.example.xiudd.sese.gson.SystemPushGson;
import com.example.xiudd.sese.gson.UserGson;
import com.example.xiudd.sese.gson.VipVideoGson;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Created by Xuyijie on 2018/10/16.
 */

public interface API {

    @GET("/XiuDD/public/index.php/index/Post/getPostList")
    Observable<BaseGson<PostListGson>> getPostList(@Query("uid") String uid);//获取post集合


    @GET("/XiuDD/public/index.php/index/Post/getHotVideoList")
    Observable<BaseGson<HotVideoGson>> getHotVideoList();//获取post集合

    @GET("/XiuDD/public/index.php/index/Post/getForeignVideoList")
    Observable<BaseGson<ForeignGson>> getForeignVideoList();//获取post集合

    @GET("/XiuDD/public/index.php/index/Post/getTeenVideoList")
    Observable<BaseGson<ForeignGson>> getTeenVideoList();//获取post集合


    @GET("/XiuDD/public/index.php/index/Post/registerWithUser")
    Observable<BaseGson<NovelGson>> getNovelList(@Query("kind") String kind);//获取post集合

    @GET("/XiuDD/public/index.php/index/Post/addLike")
    Observable<BaseGson<EmptyGson>> addLike(@Query("uid") String uid, @Query("pid") String pid);//获取post集合

    @Streaming
    @GET
    Call<ResponseBody> downloadTxt(@Url String url);

    @GET("/XiuDD/public/index.php/index/Post/getPictureList")
    Observable<BaseGson<PictureGson>> getPictureList();//获取post集合


    @GET("/XiuDD/public/index.php/index/Post/getCartoonList")
    Observable<BaseGson<HotVideoGson>> getCartoonList();//获取post集合]


    @GET("/XiuDD/public/index.php/index/Post/getOnlineList")
    Observable<BaseGson<HotVideoGson>> getOnlineList();//获取post集合

    @Multipart
    @POST("/XiuDD/public/index.php/index/Post/setNewPost")
    Observable<BaseGson<EmptyGson>> setNewPost(
            @PartMap() Map<String, RequestBody> partMap,
            @Part List<MultipartBody.Part> file);

    @GET("/XiuDD/public/index.php/index/Post/getBeauPicList")
    Observable<BaseGson<BeautifulGson<BeautifulDataBean>>> getBeauPicList(@Query("page") String page);//获取post集合

    @FormUrlEncoded
    @POST("/XiuDD/public/index.php/index/User/UserLogin")
    Observable<BaseGson<UserGson>> UserLogin(@Field("username") String username, @Field("password") String password);//获取post集合


    @FormUrlEncoded
    @POST("/XiuDD/public/index.php/index/User/UserSignDaily")
    Observable<BaseGson<SignGson>> UserSignDaily(@Field("uid") String uid);//获取post集合

    @GET("/XiuDD/public/index.php/index/Video/getVipVideoList")
    Observable<BaseGson<VipVideoGson>> getVipVideo(@Query("kind") String kind);

    @GET("/XiuDD/public/index.php/index/Home/getBanner")
    Observable<BaseGson<BannerGson>> getBanner();

    @GET("/XiuDD/public/index.php/index/Home/getAroundUserList")
    Observable<BaseGson<UserGson>> getAroundUserList(@Query("city") String city, @Query("limit") int limit);

    @GET("/XiuDD/public/index.php/index/User/getUserContactList")
    Observable<BaseGson<UserGson>> getUserContactList(@Query("uid") String uid);

    @GET("/XiuDD/public/index.php/index/Home/getMarqueen")
    Observable<BaseGson<MarqueenGson>> getMarqueen();

    @GET("/XiuDD/public/index.php/index/Home/getSystemPush")
    Observable<BaseGson<SystemPushGson>> getSystemPush();

    @GET("/XiuDD/public/index.php/index/User/getUserSore")
    Observable<BaseGson<ScoreGson>> getUserScore(@Query("uid") String uid);

    @GET("/XiuDD/public/index.php/index/Post/getUserPost")
    Observable<BaseGson<PostListGson>> getUserPost(@Query("uid") String uid);

    @GET("/XiuDD/public/index.php/index/Post/getUserCollectionPost")
    Observable<BaseGson<PostListGson>> getUserCollectionPost(@Query("uid") String uid);

    @GET("/XiuDD/public/index.php/index/Post/addCollection")
    Observable<BaseGson<EmptyGson>> addCollection(@Query("uid") String uid, @Query("postid") String postId, @Query("isdelete") String isDelete);

    @FormUrlEncoded
    @POST("/XiuDD/public/index.php/index/User/qqLogin")
    Observable<BaseGson<UserGson>> QQUserLogin(@Field("openid") String openid, @Field("username") String username,
                                               @Field("avatar") String avatar, @Field("sex") String sex, @Field("location") String location, @Field("tel") String tel, @Field("sign") String sign);

    @FormUrlEncoded
    @POST("/XiuDD/public/index.php/index/User/UserRegister")
    Observable<BaseGson<UserGson>> UserRegister(
            @Field("username") String username,
            @Field("password") String password,
            @Field("tel") String tel,
            @Field("sex") String sex,
            @Field("head") String head,
            @Field("sign") String sign,
            @Field("location") String location
    );

    @FormUrlEncoded
    @POST("/XiuDD/public/index.php/index/Post/FeedBack")
    Observable<BaseGson<EmptyGson>> feedBack(@Field("uid") String uid, @Field("content") String content);


    @FormUrlEncoded
    @POST("/XiuDD/public/index.php/index/User/updatePassword")
    Observable<BaseGson<EmptyGson>> updatePassword(@Field("password") String password,
                                                   @Field("password1") String password1,
                                                   @Field("uid") String uid);


    @GET("/XiuDD/public/index.php/index/Post/getPostById")
    Observable<BaseGson<SinglePostDetailGson>> getPostById(@Query("pid") String pid, @Query("uid") String uid);

    @GET("/XiuDD/public/index.php/index/Post/getPostListByKind")
    Observable<BaseGson<PostListGson>> getPostListByKind(@Query("uid") String uid, @Query("kind") String kind);

    @GET("/XiuDD/public/index.php/index/Post/getPostListBySearch")
    Observable<BaseGson<PostListGson>> getPostListBySearch(@Query("uid") String uid, @Query("search") String kind);

    @GET("/XiuDD/public/index.php/index/Post/setComment")
    Observable<BaseGson<EmptyGson>> setComment(@Query("pid") String pid, @Query("uid") String uid, @Query("comment") String comment);

    @FormUrlEncoded
    @POST("/XiuDD/public/index.php/index/Pay/VipPayment")
    Observable<BaseGson<EmptyGson>> vipCharge(@Field("uid") String uid, @Field("rank") String rank);
}
