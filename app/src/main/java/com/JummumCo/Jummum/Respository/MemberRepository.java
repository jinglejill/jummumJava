package com.JummumCo.Jummum.Respository;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.HttpManager;
import com.JummumCo.Jummum.Manager.http.MemberService;
import com.JummumCo.Jummum.Model.BaseResponse;
import com.JummumCo.Jummum.Model.UserAccountAuthenResponse;
import com.JummumCo.Jummum.Model.UserAccountAuthenResultData;
import com.JummumCo.Jummum.Util.Util;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import retrofit2.Response;

/**
 * Created by Phitakphong on 23/5/2560.
 */

public class MemberRepository {
    MemberService apiService;

    public MemberRepository() {
        apiService = HttpManager.getInstance().getMemberService();
    }

    /*
    public void register(RegisterDto registerDto, final HTTPCallback httpCallback) {
        Flowable<Response<RegisterResponse>> register = memberService.register(
                registerDto.getName(),
                registerDto.getSurname(),
                registerDto.getEmail(),
                registerDto.getPassword(),
                registerDto.getConfirmPassword()
        );
        register.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<RegisterResponse>>() {
                    @Override
                    public void onNext(Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            RegisterResponse registerResponse = response.body();
                            if (registerResponse.isSuccessStatus()) {
                                httpCallback.OnSuccess(registerResponse.getMessage());
                            } else {
                                httpCallback.OnError(registerResponse.getMessage());
                            }
                        } else {
                            httpCallback.OnError(Util.getErrorMessage(response.errorBody()));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        httpCallback.OnError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    */

    public void login(String password, String username, String modifireUser,String token,String model, final IHttpCallback<List<List<UserAccountAuthenResultData>>> httpCallback) {
        Flowable<Response<UserAccountAuthenResponse>> flowable = apiService.userAuthen(password,username,username,token,Util.getModifireDate(),model);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<UserAccountAuthenResponse>>() {
                    @Override
                    public void onNext(Response<UserAccountAuthenResponse> response) {
                        if (response.isSuccessful()) {
                            UserAccountAuthenResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                //UserAccountAuthenResultData userAccountAuthenResultData = resp.getDataJson().get(0).get(0);
                                httpCallback.onSuccess(resp.getUserAccountAuthenResultData());
                            } else {
                                httpCallback.onError("Username หรือ Password ไม่ถูกต้อง");
                            }
                        } else {
                            httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        httpCallback.onError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getCreateUser(String modifiedUser, String birthDate,String deviceToken, String password
            ,String firstname,String lastname,String username,String phoneNo,String modifiedDate,String email
            ,String modifiedDeviceToken ,final IHttpCallback<BaseResponse> httpCallback) {
        Flowable<Response<BaseResponse>> flowable = apiService.createUser(modifiedUser, birthDate, deviceToken,
                password, firstname, lastname, username, phoneNo, modifiedDate, email, modifiedDeviceToken);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<BaseResponse>>() {
                    @Override
                    public void onNext(Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                BaseResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res);
                                } else {
                                    httpCallback.onError(res.getError());
                                }
                            } else {
                                httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                            }
                        } else {
                            httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        httpCallback.onError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getUpdateUserAccount(String userAccountID,String modifiedUser, String birthDate,String deviceToken, String password
            ,String firstname,String lastname,String username,String phoneNo,String modifiedDate,String email
            ,String modifiedDeviceToken ,final IHttpCallback<BaseResponse> httpCallback) {
        Flowable<Response<BaseResponse>> flowable = apiService.updateUserAccount(userAccountID,modifiedUser, birthDate, deviceToken,
                password, firstname, lastname, username, phoneNo, modifiedDate, email, modifiedDeviceToken);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<BaseResponse>>() {
                    @Override
                    public void onNext(Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                BaseResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res);
                                } else {
                                    httpCallback.onError(res.getError());
                                }
                            } else {
                                httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                            }
                        } else {
                            httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        httpCallback.onError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getForgetPassword(String username, String modifiedDeviceToken, String modifiedUser, String actionScreen
        ,final IHttpCallback<BaseResponse> httpCallback) {
            Flowable<Response<BaseResponse>> flowable = apiService.frogetPassword(username,modifiedDeviceToken,modifiedUser,actionScreen);
            flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(new DefaultSubscriber<Response<BaseResponse>>() {
                        @Override
                        public void onNext(Response<BaseResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.isSuccessful()) {
                                    BaseResponse res = response.body();
                                    if (res.isSuccessStatus()) {
                                        httpCallback.onSuccess(res);
                                    } else {
                                        httpCallback.onError(res.getError());
                                    }
                                } else {
                                    httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                                }
                            } else {
                                httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                            }
                        }
                        @Override
                        public void onError(Throwable t) {
                            httpCallback.onError(t.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }


    public void getUserData(String username, String modifiedDeviceToken, String modifiedUser
            ,final IHttpCallback<List<List<UserAccountAuthenResultData>>> httpCallback) {
        Flowable<Response<UserAccountAuthenResponse>> flowable = apiService.getAccountData(username,modifiedDeviceToken,modifiedUser);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<UserAccountAuthenResponse>>() {
                    @Override
                    public void onNext(Response<UserAccountAuthenResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                UserAccountAuthenResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getUserAccountAuthenResultData());
                                } else {
                                    httpCallback.onError(res.getError());
                                }
                            } else {
                                httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                            }
                        } else {
                            httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        httpCallback.onError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getLoginFb(String modifiedUser, String userAccountID, String lineID, String birthDate, String deviceToken, String password
            , String fullName, String firstName, String lastName, String nickName, String roleID, String username, String phoneNo, String modifiedDate, String email
            , String modifiedDeviceToken, String actionScreen, String model, final IHttpCallback<List<List<UserAccountAuthenResultData>>> httpCallback) {
        Flowable<Response<UserAccountAuthenResponse>> flowable = apiService.loginFB(modifiedUser, userAccountID, lineID, birthDate, deviceToken,
                password, fullName, firstName, lastName, nickName, roleID, username, phoneNo, modifiedDate, email, modifiedDeviceToken, actionScreen,model);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<UserAccountAuthenResponse>>() {
                    @Override
                    public void onNext(Response<UserAccountAuthenResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                UserAccountAuthenResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getUserAccountAuthenResultData());
                                } else {
                                    httpCallback.onError(res.getError());
                                }
                            } else {
                                httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                            }
                        } else {
                            httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        httpCallback.onError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getLogout(String username, String logInID,String status,String modifiedDate, String modifireUser,String deviceToken, String modifiedDeviceToken,String actionScreen,String model, final IHttpCallback<BaseResponse> httpCallback) {
        Flowable<Response<BaseResponse>> flowable = apiService.getLogout(username,logInID,status,modifiedDate,modifireUser,deviceToken,modifiedDeviceToken,actionScreen,model);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<BaseResponse>>() {
                    @Override
                    public void onNext(Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            BaseResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                //UserAccountAuthenResultData userAccountAuthenResultData = resp.getDataJson().get(0).get(0);
                                httpCallback.onSuccess(resp);
                            } else {
                                httpCallback.onError(resp.getError());
                            }
                        } else {
                            httpCallback.onError(Util.getErrorMessage(response.errorBody()));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        httpCallback.onError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
