package com.JummumCo.Jummum.Manager.http;

import com.JummumCo.Jummum.Model.BaseResponse;
import com.JummumCo.Jummum.Model.UserAccountAuthenResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by CRRU0001 on 21/03/2559.
 */
public interface MemberService {

    @FormUrlEncoded
    @POST("andJMMUserAccountValidate.php")
    Flowable<Response<UserAccountAuthenResponse>> userAuthen(
            @Field("password") String password,
            @Field("username") String username,
            @Field("modifiedUser") String modifiedUser,
            @Field("deviceToken") String deviceToken,
            @Field("modifiedDate") String modifiedDate,
            @Field("model") String medel

    );

    @FormUrlEncoded
    @POST("andJMMUserAccountInsert.php")
    Flowable<Response<BaseResponse>> createUser(
            @Field("modifiedUser") String modifiedUser,
            @Field("birthDate") String birthDate,
            @Field("deviceToken") String deviceToken,
            @Field("password") String password,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("username") String username,
            @Field("phoneNo") String phoneNo,
            @Field("modifiedDate") String modifiedDate,
            @Field("email") String email,
            @Field("modifiedDeviceToken") String modifiedDeviceToken
    );


    @FormUrlEncoded
    @POST("andJMMUserAccountForgotPasswordInsert.php")
    Flowable<Response<BaseResponse>> frogetPassword(
            @Field("username") String username,
            @Field("modifiedDeviceToken") String modifiedDeviceToken,
            @Field("modifiedUser") String modifiedUser,
            @Field("actionScreen") String birthDate
    );

    @FormUrlEncoded
    @POST("andJMMUserAccountGet.php")
    Flowable<Response<UserAccountAuthenResponse>> getAccountData(
            @Field("username") String username,
            @Field("modifiedDeviceToken") String modifiedDeviceToken,
            @Field("modifiedUser") String modifiedUser
    );

    @FormUrlEncoded
    @POST("andJMMLogInUserAccountInsert.php")
    Flowable<Response<UserAccountAuthenResponse>> loginFB(
            @Field("modifiedUser") String modifiedUser,
            @Field("userAccountID") String userAccountID,
            @Field("lineID") String lineID,
            @Field("birthDate") String birthDate,
            @Field("deviceToken") String deviceToken,
            @Field("password") String password,
            @Field("fullName") String fullName,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("nickName") String nickName,
            @Field("roleID") String roleID,
            @Field("username") String username,
            @Field("phoneNo") String phoneNo,
            @Field("modifiedDate") String modifiedDate,
            @Field("email") String email,
            @Field("modifiedDeviceToken") String modifiedDeviceToken,
            @Field("actionScreen") String actionScreen,
            @Field("model") String model
    );

    @FormUrlEncoded
    @POST("andJMMUserAccountUpdate.php")
    Flowable<Response<BaseResponse>> updateUserAccount(
            @Field("userAccountID") String userAccountID,
            @Field("modifiedUser") String modifiedUser,
            @Field("birthDate") String birthDate,
            @Field("deviceToken") String deviceToken,
            @Field("password") String password,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("username") String username,
            @Field("phoneNo") String phoneNo,
            @Field("modifiedDate") String modifiedDate,
            @Field("email") String email,
            @Field("modifiedDeviceToken") String modifiedDeviceToken
    );



    @FormUrlEncoded
    @POST("andJMMLogOutInsert.php")
    Flowable<Response<BaseResponse>> getLogout(
            @Field("username") String username,
            @Field("logInID") String logInID,
            @Field("status") String status,
            @Field("modifiedDate") String modifiedDate,
            @Field("modifiedUser") String modifiedUser,
            @Field("deviceToken") String deviceToken,
            @Field("modifiedDeviceToken") String modifiedDeviceToken,
            @Field("actionScreen") String actionScreen,
            @Field("model") String model

    );



}
