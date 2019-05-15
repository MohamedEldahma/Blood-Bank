package com.example.pr_pro.newbloodapplication.data.rest;



import com.example.pr_pro.newbloodapplication.data.model.bloodtypes.BloodTypes;
import com.example.pr_pro.newbloodapplication.data.model.cities.Cities;
import com.example.pr_pro.newbloodapplication.data.model.contactus.ContactUs;
import com.example.pr_pro.newbloodapplication.data.model.donation_request.DonationRequest;
import com.example.pr_pro.newbloodapplication.data.model.donation_request_creat.DonationRequestCreat;
import com.example.pr_pro.newbloodapplication.data.model.donationrequests.DonationRequests;
import com.example.pr_pro.newbloodapplication.data.model.getprofiledata.GetProfileData;
import com.example.pr_pro.newbloodapplication.data.model.governorates.Governorates;
import com.example.pr_pro.newbloodapplication.data.model.login.Login;
import com.example.pr_pro.newbloodapplication.data.model.logs.Logs;
import com.example.pr_pro.newbloodapplication.data.model.my_favourites.MyFavourites;
import com.example.pr_pro.newbloodapplication.data.model.newpassword.NewPassword;
import com.example.pr_pro.newbloodapplication.data.model.notificationssettings.NotificationSsettings;
import com.example.pr_pro.newbloodapplication.data.model.post_toggle_favourite.PostToggleFavourite;
import com.example.pr_pro.newbloodapplication.data.model.postdetail.Post;
import com.example.pr_pro.newbloodapplication.data.model.posts.Posts;
import com.example.pr_pro.newbloodapplication.data.model.profile.Profile;
import com.example.pr_pro.newbloodapplication.data.model.register.Register;
import com.example.pr_pro.newbloodapplication.data.model.register_token.RegisterToken;
import com.example.pr_pro.newbloodapplication.data.model.remove_token.RemoveToken;
import com.example.pr_pro.newbloodapplication.data.model.report.Report;
import com.example.pr_pro.newbloodapplication.data.model.resetpassword.ResetPassword;
import com.example.pr_pro.newbloodapplication.data.model.settings.Settings;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ModelApiServices {

    @POST("register")
    @FormUrlEncoded
    Call<Register> addRegister(@Field("name") String name, @Field("email") String email, @Field("birth_date") String birth_date,
                               @Field("city_id") String city_id, @Field("phone") String phone, @Field("donation_last_date") String donation_last_date,
                               @Field("password")String password, @Field("password_confirmation") int password_confirmation,
                               @Field("blood_type")String blood_type);
    @POST("login")
    @FormUrlEncoded
    Call<Login> addLogin(@Field("phone") String phone,
                         @Field("password") String password );

    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> addResetPassword(@Field("phone") String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> inputNewPassword(@Field("password") String password
                                        , @Field("password_confirmation") String password_confirmation
                                        , @Field("pin_code") String pin_code
                                        , @Field("phone") String phone);



    @POST("profile")
    @FormUrlEncoded
    Call<Profile> addProfile(@Field("name") String name, @Field("email") String email, @Field("birth_date") String birth_date
            , @Field("city_id") int city_id, @Field("phone") String phone, @Field("donation_last_date") String donation_last_date
            , @Field("password") String password, @Field("password_confirmation") String password_confirmation
            , @Field("blood_type") String blood_type, @Field(" api_token")String api_token );

   @POST("get-profil-data")
   @FormUrlEncoded
   Call<GetProfileData>getProfilData(@Field("api_token")String api_token);



    @POST("register-token")
    @FormUrlEncoded
    Call<RegisterToken> addRegisterToken(@Field("token") String token, @Field("api_token") String api_token
            , @Field("platform")String platform);


    @POST("remove-token")
    @FormUrlEncoded
    Call<RemoveToken> addRemoveToken(@Field("token") String token, @Field("api_token") String api_token );


    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSsettings> addNotificationsSettings(@Field("governorates[1]") Object[]  governorates
                                                         , @Field("api_token") String api_token
                                                         , @Field("blood_types[1]")Object[]blood_types);


    @GET("notifications")
    Call<Settings> getNotifications(@Query("api_token") String api_token);


    @GET("notifications-count")
    Call<Settings> getNotificationsCount(@Query("api_token") String api_token);





    @POST("donation-request/create")
    @FormUrlEncoded
    Call<DonationRequestCreat> addDonationRequestCreate(@Field("api_token") String api_token, @Field("patient_name") String patient_name, @Field("patient_age") String patient_age
                                   , @Field("blood_type") String blood_type, @Field("bags_num") int bags_num, @Field("hospital_name") String hospital_name
                                   , @Field("hospital_address") String hospital_address
                                   , @Field("city_id") int city_id, @Field("phone") String phone, @Field("notes") String notes
                                   , @Field("latitude") double latitude, @Field("longitude") double longitude );


    @GET("donation-requests")
    Call<DonationRequests> getDonationRequests(@Query("api_token") String api_token);
//                                               , @Query("page") int page);

    @GET("donation-request")
    Call<DonationRequest> getDonationRequest(@Query("api_token") String api_token, @Query("donation_id") String donation_id);


    @GET("posts")
    Call<Posts> getPosts(@Query("api_token") String api_token, @Query("page") int page);


    @GET("post")
    Call<Post> getPost(@Query("api_token") String api_token, @Query("post_id") String post_id);

    @GET("my-favourites")
    Call<MyFavourites> getMyFavourites(@Query("api_token") String api_token);

    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<PostToggleFavourite> addPost(@Field("api_token") String api_token, @Field("post_id") String post_id);




    @GET("governorates")
    Call<Governorates> getGovernorates();

    @GET("cities")
    Call<Cities> getCities(@Query("governorate_id") long  governorate_id );


    @GET("settings")
    Call<Settings> getSettings(@Query("api_token") String api_token);





    @POST("contact")
    @FormUrlEncoded
    Call<ContactUs> addContact(@Field("api_token") String api_token, @Field(" title")String  title
                      , @Field("  message ")String   message   );


    @POST("report")
    @FormUrlEncoded
    Call<Report> addReport(@Field("api_token") String api_token, @Field("  message ")String   message );



    @GET("logs")
    Call<Logs> getLogs();

    @GET("blood-types")
    Call<BloodTypes> getBloodTyp();


    Call<NotificationSsettings> addNotificationsSettings(String apiToken, Object[] bloodArray, Object[] governratArray);
}
