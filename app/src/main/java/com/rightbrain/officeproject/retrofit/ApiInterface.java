package com.rightbrain.officeproject.retrofit;


import com.rightbrain.officeproject.model.Category;
import com.rightbrain.officeproject.model.ModelBrand;
import com.rightbrain.officeproject.model.ModelOrderList;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.model.ModelSetup;
import com.rightbrain.officeproject.model.ModelUserUpdate;
import com.rightbrain.officeproject.model.ModelUsers;
import com.rightbrain.officeproject.model.modelProductDetails.ModelLocation;
import com.rightbrain.officeproject.model.modelProductDetails.ModelProductDetails;
import com.rightbrain.officeproject.model.modelProductDetails.ModelTimePeriod;
import com.rightbrain.officeproject.model.modelProductDetails.ProductDetails;
import com.rightbrain.officeproject.model.modelProductDetails.RelatedProduct;
import com.rightbrain.officeproject.model.ordermodel.ModelOrderItems;
import com.rightbrain.officeproject.model.ordermodel.ModelRecentOrder;
import com.rightbrain.officeproject.model.ordermodel.Modeljson;
import com.rightbrain.officeproject.model.multirecycermodel.ModelMain;
import com.rightbrain.officeproject.model.multirecycermodel.Product;
import com.rightbrain.officeproject.model.ordermodel.Modelstatus;
import com.rightbrain.officeproject.model.sidebar.ModelMenu;
import com.rightbrain.officeproject.model.sidebar.SidebarResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

 /*@GET("get_category.php")
 Call<List<ModelAll>> getcategory();*/

/* @GET("stock-category")
 Observable<Response<List<Category>>> getCategory(@Header("X-API-SECRET") String secret);*/

/* @POST("insert_json_array.php")
 Call<ModelCartRoom> postOrder(@Body List<ModelCartRoom> cartList);*/
/*
 @POST("insert_json_array.php")
 Call<ModelCartRoom> postOrder(@Body JSONArray jsonArray);*/

 @POST("insert_json_array.php")
 Call<Modeljson> postOrder(@Body List<Modeljson> cartList);

/* @FormUrlEncoded
 @POST("insert_json_array.php")
 Call<Response<ResponseBody>> postOrderFields(
         @Field("jsonUser") String userInfo,
         @Field("jsonOrderItem") String orderItem
 );*/

 @FormUrlEncoded
 @POST("android-api-ecommerce/order-create")
 Observable<Response<Modelstatus>> postOrderFields(
         @Header("X-API-SECRET") String secret,
         @Field("jsonOrder") String orderItem,
         @Field("jsonOrderItem") String cartlist,
         @Field("jsonUser") String userInfo

 );

 @GET("android-api-ecommerce/all-category")
 Observable<Response<List<Category>>> getCategory(@Header("X-API-SECRET") String secret);


 @GET("android-api-ecommerce/portal-store")
 Observable<Response<List<ModelSetup>>> getShop(@Header("X-API-SECRET") String secret);


 @GET("android-api-ecommerce/order-time-period")
 Observable<Response<List<ModelTimePeriod>>> getTimePeriod(@Header("X-API-SECRET") String secret);

 @GET("android-api-ecommerce/order-location")
 Observable<Response<List<ModelLocation>>> getOrderLocation(@Header("X-API-SECRET") String secret);

 @GET("android-api-ecommerce/home-feature-slider")
 Observable<Response<List<Category>>> getFeatureSlider(@Header("X-API-SECRET") String secret);

 @GET("android-api-ecommerce/feature-category")
 Observable<Response<List<Category>>> getFeatureCategory(@Header("X-API-SECRET") String secret);

 @GET("android-api-ecommerce/promotion")
 Observable<Response<List<Category>>> getFeaturePromotion(@Header("X-API-SECRET") String secret);

 @GET("android-api-ecommerce/all-brand")
 Observable<Response<List<ModelBrand>>> getBrand(@Header("X-API-SECRET") String secret);

 @GET("android-api-ecommerce/feature-brand")
 Observable<Response<List<ModelBrand>>> getFeatureBrand(@Header("X-API-SECRET") String secret);

 @POST("android-api-ecommerce/product")
 Observable<Response<List<ModelProducts>>> getProduct(@Header("X-API-SECRET") String secret,@Query("category") String category);

 @POST("android-api-ecommerce/related-product")
 Observable<Response<List<RelatedProduct>>> getRelatedProduct(@Header("X-API-SECRET") String secret, @Query("category") String category);

 @GET("android-api-ecommerce/product-details")
 Observable<Response<ProductDetails>> getProductDetails(@Header("X-API-SECRET") String secret, @Query("id") String id);

 @GET("android-api-ecommerce/page")
 Observable<Response<ModelMenu>> getPageMenuDetails(@Header("X-API-SECRET") String secret, @Query("id") String id);

 @GET("android-api-ecommerce/page-menu")
 Observable<Response<List<ModelMenu>>> getPageMenu(@Header("X-API-SECRET") String secret);

 @GET("android-api-ecommerce/product-details")
 Observable<Response<ModelProductDetails>> getProductDetailsWithColor(@Header("X-API-SECRET") String secret, @Query("id") String id);

 @POST("android-api-ecommerce/feature-product")
 Observable<Response<List<ModelProducts>>> getFeatureProduct(@Header("X-API-SECRET") String secret,@Query("module") String module,@Query("feature_id") String id);

 @GET("android-api-ecommerce/sidebar-menu")
 Single<Response<SidebarResponse>> getSidebarItems(@Header("X-API-SECRET") String api_secret);

 @POST("android-api-ecommerce/all-feature-product")
 Observable<Response<List<ModelMain>>> getMultiFeatureProduct(@Header("X-API-SECRET") String secret, @Query("module") String category);

 @POST("android-api-ecommerce/all-feature-product")
 Observable<Response<List<Product>>> getMultiFeatureProducts(@Header("X-API-SECRET") String secret, @Query("module") String category);


 @POST("android-api-ecommerce/search")
 Observable<Response<List<ModelProducts>>> getPromotionProduct(@Header("X-API-SECRET") String secret,@Query("promotion") String promotion);


 @POST("android-api-ecommerce/user-register")
 Observable<Response<ModelUsers>> createAccount(@Header("X-API-SECRET") String secret, @Body ModelUsers modelUsers);

 @POST("android-api-ecommerce/update-register")
 Observable<Response<ModelUserUpdate>> userUpdate(@Header("X-API-SECRET") String secret, @Body ModelUserUpdate modelUserUpdate);


 @POST("android-api-ecommerce/setup")
 Observable<Response<ModelSetup>> setupShop(@Body ModelSetup modelSetup);

 @POST("android-api-ecommerce/user-login")
 Observable<Response<ModelUsers>> loginAccount(@Header("X-API-SECRET") String secret, @Body ModelUsers modelUsers);

 @POST("android-api-ecommerce/order-list")
 Observable<Response<List<ModelOrderList>>> orderList(@Header("X-API-SECRET") String secret, @Query("user") String user);


 @GET("android-api-ecommerce/order-details")
 Observable<Response<ModelOrderItems>> orderDetails(@Header("X-API-SECRET") String secret, @Query("id") String id);

 @GET("android-api-ecommerce/order-delete")
 Observable<Response<ModelOrderItems>> orderDelete(@Header("X-API-SECRET") String secret, @Query("id") String id, @Query("user_id") String userid);

 @POST("android-api-ecommerce/process-order")
 Observable<Response<List<ModelRecentOrder>>> orderProcess(@Header("X-API-SECRET") String secret, @Query("user") String user);

 @POST("android-api-ecommerce/search")
 Observable<Response<List<ModelProducts>>> productSearch(@Header("X-API-SECRET") String secret,@Query("keyword") String keyword);

}
