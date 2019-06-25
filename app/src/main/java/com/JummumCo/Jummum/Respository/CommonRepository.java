package com.JummumCo.Jummum.Respository;

import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Manager.HttpManager;
import com.JummumCo.Jummum.Manager.http.ApiService;
import com.JummumCo.Jummum.Model.BankResponse;
import com.JummumCo.Jummum.Model.BankResultData;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponse;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.BranchResponse;
import com.JummumCo.Jummum.Model.DisputeResponse;
import com.JummumCo.Jummum.Model.DisputeResultData;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.HotDealResponse;
import com.JummumCo.Jummum.Model.ImageResponse;
import com.JummumCo.Jummum.Model.ImageResultData;
import com.JummumCo.Jummum.Model.LuckyDrawData;
import com.JummumCo.Jummum.Model.LuckyDrawResponse;
import com.JummumCo.Jummum.Model.MasterResponse;
import com.JummumCo.Jummum.Model.MasterResultData;
import com.JummumCo.Jummum.Model.MenuGetListResponse;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteListResponse;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OderJoinResponse;
import com.JummumCo.Jummum.Model.OderListResponse;
import com.JummumCo.Jummum.Model.OrderJoinResultData;
import com.JummumCo.Jummum.Model.OrderListResultData;
import com.JummumCo.Jummum.Model.PayListResponse;
import com.JummumCo.Jummum.Model.PayResponseResultData;
import com.JummumCo.Jummum.Model.PayResultData;
import com.JummumCo.Jummum.Model.PromotionListResponse;
import com.JummumCo.Jummum.Model.PromotionResultData;
import com.JummumCo.Jummum.Model.RatingResponse;
import com.JummumCo.Jummum.Model.RatingResultData;
import com.JummumCo.Jummum.Model.RewardListResultData;
import com.JummumCo.Jummum.Model.RewardResponse;
import com.JummumCo.Jummum.Model.SaveOrderInsertData;
import com.JummumCo.Jummum.Model.SummaryResponse;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;
import com.JummumCo.Jummum.Model.SummaryResultData;
import com.JummumCo.Jummum.Util.Util;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by Phitakphong on 23/5/2560.
 */

public class CommonRepository {
    ApiService apiService;

    public CommonRepository() {
        apiService = HttpManager.getInstance().getApiService();
    }

    public void getScanQr(String decryptedMessage, String modifiedDeviceToken, String modifiedUser, final IHttpCallback<List<List<BranchAndCustomerTableResponseResultData>>> httpCallback) {
        Flowable<Response<BranchAndCustomerTableResponse>> flowable = apiService.scanQr(decryptedMessage, modifiedDeviceToken, modifiedUser);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<BranchAndCustomerTableResponse>>() {
                    @Override
                    public void onNext(Response<BranchAndCustomerTableResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.isSuccessful()) {
                                BranchAndCustomerTableResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getData());
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


    public void getMenuList(String branchID, String modifiedDeviceToken, String modifiedUser, final IHttpCallback<List<List<MenuListResultData>>> httpCallback) {
        Flowable<Response<MenuGetListResponse>> flowable = apiService.getMenuList(branchID, modifiedDeviceToken, modifiedUser);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<MenuGetListResponse>>() {
                    @Override
                    public void onNext(Response<MenuGetListResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                MenuGetListResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getDataMenuList());
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


    public void getMaster(String modifiedDeviceToken, String modifiedUser, final IHttpCallback<List<List<MasterResultData>>> httpCallback) {
        Flowable<Response<MasterResponse>> flowable = apiService.getMaster(modifiedDeviceToken, modifiedUser);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<MasterResponse>>() {
                    @Override
                    public void onNext(Response<MasterResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                MasterResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getMasterResultData());
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


    public void getPromotion(String voucherCode, String userAccountId, String branchId, String totalAmount, String modifiedDeviceToken, String modifiedUser, final IHttpCallback<List<List<PromotionResultData>>> httpCallback) {
        Flowable<Response<PromotionListResponse>> flowable = apiService.getPromotion(voucherCode, userAccountId, branchId, totalAmount, modifiedDeviceToken, modifiedUser);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<PromotionListResponse>>() {
                    @Override
                    public void onNext(Response<PromotionListResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                PromotionListResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getPromotionlistData());
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


    public void getCreateOrder(PayResultData orderData, final IHttpCallback<List<List<PayResponseResultData>>> httpCallback) {
        Flowable<Response<PayListResponse>> flowable = apiService.createOrder(orderData);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<PayListResponse>>() {
                    @Override
                    public void onNext(Response<PayListResponse> response) {
                        if (response.isSuccessful()) {
                            PayListResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getPayResponseResultData());
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

    public void buffetOrderInsert(PayResultData orderData, final IHttpCallback<List<List<PayResponseResultData>>> httpCallback) {
        Flowable<Response<PayListResponse>> flowable = apiService.buffetOrderInsert(orderData);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<PayListResponse>>() {
                    @Override
                    public void onNext(Response<PayListResponse> response) {
                        if (response.isSuccessful()) {
                            PayListResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getPayResponseResultData());
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

    public void getOrderList(String page, String perPage, String memberId, final IHttpCallback<List<OrderListResultData>> httpCallback) {
        Flowable<Response<OderListResponse>> flowable = apiService.getOrderList(page, perPage, memberId);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<OderListResponse>>() {
                    @Override
                    public void onNext(Response<OderListResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                OderListResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getOrderListResultData());
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


    public void getRating(String reciptId, final IHttpCallback<List<RatingResultData>> httpCallback) {
        Flowable<Response<RatingResponse>> flowable = apiService.getRating(reciptId);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<RatingResponse>>() {
                    @Override
                    public void onNext(Response<RatingResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                RatingResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getRatingResultData());
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


    public void getInsertRating(
            String reciptId
            , String score
            , String modifiedUser
            , String modifiedDate
            , String modifiedDeviceToken
            , String actionScreen
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getInsertRating(reciptId, score, modifiedUser, modifiedDate, modifiedDeviceToken, actionScreen);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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

    public void getUpdateReceiptAndPromoCode(
            String reciptId
            , String modifiedUser
            , String modifiedDate
            , String modifiedDeviceToken
            , String actionScreen
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getUpdateReceiptAndPromoCode(reciptId, modifiedUser, modifiedDate, modifiedDeviceToken, actionScreen);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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

    public void getUpdateReceipt(
            String reciptId
            , String status
            , String modifiedUser
            , String modifiedDate
            , String modifiedDeviceToken
            , String actionScreen
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getUpdateReceipt(reciptId, status, modifiedUser, modifiedDate, modifiedDeviceToken, actionScreen);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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

    public void getBranch(String txt, String page, String perpage, final IHttpCallback<List<BranchAndCustomerTableResponseResultData>> httpCallback) {
        Flowable<Response<BranchResponse>> flowable = apiService.getBranchSearchList(txt, page, perpage);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<BranchResponse>>() {
                    @Override
                    public void onNext(Response<BranchResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.isSuccessful()) {
                                BranchResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getData());
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

    public void getImage(String file, String type, String branchid, final IHttpCallback<List<ImageResultData>> httpCallback) {
        Flowable<Response<ImageResponse>> flowable = apiService.getImage(file, type, branchid);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<ImageResponse>>() {
                    @Override
                    public void onNext(Response<ImageResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.isSuccessful()) {
                                ImageResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getImageResultData());
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


    public void getCustomTable(String branchId, final IHttpCallback<List<BranchAndCustomerTableResponseResultData>> httpCallback) {
        Flowable<Response<BranchResponse>> flowable = apiService.getCustomTableList(branchId);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<BranchResponse>>() {
                    @Override
                    public void onNext(Response<BranchResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.isSuccessful()) {
                                BranchResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getData());
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

    public void getNote(String branchId, String menuid, final IHttpCallback<List<NoteListResponseResultData>> httpCallback) {
        Flowable<Response<NoteListResponse>> flowable = apiService.getNoteList(branchId, menuid);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<NoteListResponse>>() {
                    @Override
                    public void onNext(Response<NoteListResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.isSuccessful()) {
                                NoteListResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getData());
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


    public void getSummary(SummaryResultData resultData, final IHttpCallback<List<List<SummaryResponseResultData>>> httpCallback) {
        Flowable<Response<SummaryResponse>> flowable = apiService.getSummry(resultData);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<SummaryResponse>>() {
                    @Override
                    public void onNext(Response<SummaryResponse> response) {
                        if (response.isSuccessful()) {
                            SummaryResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getSummaryResponseResultData());
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

    public void getVoucher(String branchID, String memberID, final IHttpCallback<List<HotDealData>> httpCallback) {
        Flowable<Response<HotDealResponse>> flowable = apiService.getVoucher(branchID, memberID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<HotDealResponse>>() {
                    @Override
                    public void onNext(Response<HotDealResponse> response) {
                        if (response.isSuccessful()) {
                            HotDealResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getHotDealData().get(0));
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

    public void getVoucher2(String branchID, String memberID, final IHttpCallback<List<HotDealData>> httpCallback) {
        Flowable<Response<HotDealResponse>> flowable = apiService.getVoucher(branchID, memberID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<HotDealResponse>>() {
                    @Override
                    public void onNext(Response<HotDealResponse> response) {
                        if (response.isSuccessful()) {
                            HotDealResponse resp = response.body();
                            if (resp.isSuccessStatus()) {

                                List<HotDealData> data = new ArrayList<>();

                                for(HotDealData h : resp.getHotDealData().get(0)){
                                    h.setHotDeal0Reward1("0");
                                    data.add(h);

                                }
                                for(HotDealData h : resp.getHotDealData().get(1)){
                                    h.setHotDeal0Reward1("1");
                                    data.add(h);
                                }
                                httpCallback.onSuccess(data);
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

    public void getHotdeals(int page, int perPage, String memberID, String seachText, final IHttpCallback<List<HotDealData>> httpCallback) {
        Flowable<Response<HotDealResponse>> flowable = apiService.getHotdeals(page, perPage, memberID, seachText);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<HotDealResponse>>() {
                    @Override
                    public void onNext(Response<HotDealResponse> response) {
                        if (response.isSuccessful()) {
                            HotDealResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getHotDealData().get(0));
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


    public void getLuckyDraw(String branchID,
                             String memberID,
                             String receiptID,
                             final IHttpCallback<List<List<LuckyDrawData>>> httpCallback) {

        Flowable<Response<LuckyDrawResponse>> flowable = apiService.getLuckyDraw(branchID, memberID, receiptID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<LuckyDrawResponse>>() {
                    @Override
                    public void onNext(Response<LuckyDrawResponse> response) {
                        if (response.isSuccessful()) {
                            LuckyDrawResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getData());
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

    public void getOrderNow(String branchID,
                            String discountGroupMenuID,
                            final IHttpCallback<List<List<MenuListResultData>>> httpCallback) {

        Flowable<Response<MenuGetListResponse>> flowable = apiService.getOrderNow(branchID, discountGroupMenuID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<MenuGetListResponse>>() {
                    @Override
                    public void onNext(Response<MenuGetListResponse> response) {
                        if (response.isSuccessful()) {
                            MenuGetListResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getDataMenuList());
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

    public void menuBelongToBuffetGetList(String branchID,
                                          String buffetReceiptID,
                                          final IHttpCallback<List<List<MenuListResultData>>> httpCallback) {

        Flowable<Response<MenuGetListResponse>> flowable = apiService.menuBelongToBuffetGetList(branchID, buffetReceiptID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<MenuGetListResponse>>() {
                    @Override
                    public void onNext(Response<MenuGetListResponse> response) {
                        if (response.isSuccessful()) {
                            MenuGetListResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getDataMenuList());
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


    public void getReward(int page, int perPage, String memberID, String seachText, final IHttpCallback<List<List<RewardListResultData>>> httpCallback) {
        Flowable<Response<RewardResponse>> flowable = apiService.getReward(page, perPage, memberID, seachText);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<RewardResponse>>() {
                    @Override
                    public void onNext(Response<RewardResponse> response) {
                        if (response.isSuccessful()) {
                            RewardResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getRewardListResultdata());
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

    public void getSummary2(SummaryResultData resultData, final IHttpCallback<List<List<SummaryResponseResultData>>> httpCallback) {
        Flowable<Response<SummaryResponse>> flowable = apiService.getSummry(resultData);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<SummaryResponse>>() {
                    @Override
                    public void onNext(Response<SummaryResponse> response) {
                        if (response.isSuccessful()) {
                            SummaryResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getSummaryResponseResultData());
                            } else {
                                httpCallback.onSuccess(resp.getSummaryResponseResultData());
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

    public void getDispute(int type, final IHttpCallback<List<List<DisputeResultData>>> httpCallback) {
        Flowable<Response<DisputeResponse>> flowable = apiService.getDisputData(type);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<DisputeResponse>>() {
                    @Override
                    public void onNext(Response<DisputeResponse> response) {
                        if (response.isSuccessful()) {
                            DisputeResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getDisputeResultData());
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

    public void getTransferFormAndBank(String receiptID, final IHttpCallback<List<List<BankResultData>>> httpCallback) {
        Flowable<Response<BankResponse>> flowable = apiService.getTransferAndBankData(receiptID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<BankResponse>>() {
                    @Override
                    public void onNext(Response<BankResponse> response) {
                        if (response.isSuccessful()) {
                            BankResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getBankResultData());
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

    public void getInsertCancel(
            String reciptId
            , String disputeId
            , String refundAmount
            , String detail
            , String phone
            , String modifiedUser
            , String modifiedDate
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getInsertCancel(reciptId,disputeId,refundAmount,detail,phone, modifiedUser, modifiedDate);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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

    public void getInsertTransferForm(
            String receiptID
            , String bankID
            , String bankAccountNo
            , String amount
            , String phoneNo
            , String remark
            , String modifiedUser
            , String modifiedDate
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getInsertTransferForm(receiptID,bankID,bankAccountNo,amount,phoneNo,remark, modifiedUser, modifiedDate);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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

    public void getInsertDispute(
            String reciptId
            , String disputeId
            , String refund
            , String detail
            , String phone
            , String modifiedUser
            , String modifiedDate
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getInsertDispute(reciptId,
                disputeId,refund,detail,phone, modifiedUser, modifiedDate);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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


    public void getInsertComment(
            String userAccountID
            , String text
            , String modifiedUser
            , String modifiedDate
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getInsertComment(userAccountID,
                text, modifiedUser, modifiedDate);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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

    public void getInsertRecommend(
            String userAccountID
            , String text
            , String modifiedUser
            , String modifiedDate
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getInsertRecommend(userAccountID,
                text, modifiedUser, modifiedDate);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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


    public void getRedeenReward(String memberID, String rewardRedemptionID,String modifiedUser,String modifiedDate, final IHttpCallback<List<List<RewardListResultData>>> httpCallback) {
        Flowable<Response<RewardResponse>> flowable = apiService.getRedeemReward(memberID, rewardRedemptionID,modifiedUser,modifiedDate);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<RewardResponse>>() {
                    @Override
                    public void onNext(Response<RewardResponse> response) {
                        if (response.isSuccessful()) {
                            RewardResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getRewardListResultdata());
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

    public void getMyRewardCurrent(String memberID, int page,int perpage, final IHttpCallback<List<List<RewardListResultData>>> httpCallback) {
        Flowable<Response<RewardResponse>> flowable = apiService.getMyRewardCurrent(memberID, page,perpage);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<RewardResponse>>() {
                    @Override
                    public void onNext(Response<RewardResponse> response) {
                        if (response.isSuccessful()) {
                            RewardResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getRewardListResultdata());
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

    public void getMyRewardUsed(String memberID, int page,int perpage, final IHttpCallback<List<List<RewardListResultData>>> httpCallback) {
        Flowable<Response<RewardResponse>> flowable = apiService.getMyRewardUsed(memberID, page,perpage);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<RewardResponse>>() {
                    @Override
                    public void onNext(Response<RewardResponse> response) {
                        if (response.isSuccessful()) {
                            RewardResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getRewardListResultdata());
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

    public void getMyRewardExpired(String memberID, int page,int perpage, final IHttpCallback<List<List<RewardListResultData>>> httpCallback) {
        Flowable<Response<RewardResponse>> flowable = apiService.getMyRewardExpired(memberID, page,perpage);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<RewardResponse>>() {
                    @Override
                    public void onNext(Response<RewardResponse> response) {
                        if (response.isSuccessful()) {
                            RewardResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getRewardListResultdata());
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

    public void getGBPrimePay(String gbToken, String amount,String customerTableID,String referenceNo,
            String payType,String backgroundUrl,String responseUrl,String receiptID,
                              String branchID,String deviceToken,String memberID,
                              String receiptNoID
            ,final IHttpCallback<ResponseBody> httpCallback) {

        apiService = HttpManager.getInstance().getApiService();

        Flowable<Response<ResponseBody>> flowable = apiService.getGBPrimePay(gbToken,amount,customerTableID,
                referenceNo,payType,backgroundUrl,responseUrl,receiptID,branchID,deviceToken,memberID,receiptNoID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<ResponseBody>>() {
                    @Override
                    public void onNext(Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            ResponseBody resp = response.body();
                            httpCallback.onSuccess(resp);

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

    public void getPaymentComplate(String receiptID ,final IHttpCallback<List<List<PayResponseResultData>>> httpCallback) {
        Flowable<Response<PayListResponse>> flowable = apiService.getPaymentComplate(receiptID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<PayListResponse>>() {
                    @Override
                    public void onNext(Response<PayListResponse> response) {
                        if (response.isSuccessful()) {
                            PayListResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getPayResponseResultData());
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

    public void getGBPrimeSetting(String receiptID ,final IHttpCallback<List<List<PayResponseResultData>>> httpCallback) {
        Flowable<Response<PayListResponse>> flowable = apiService.GBPrimeSetting(receiptID);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<PayListResponse>>() {
                    @Override
                    public void onNext(Response<PayListResponse> response) {
                        if (response.isSuccessful()) {
                            PayListResponse resp = response.body();
                            if (resp.isSuccessStatus()) {
                                httpCallback.onSuccess(resp.getPayResponseResultData());
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

    public void getSaveOrderInsertData(SaveOrderInsertData orderData,
                                       final IHttpCallback<Response<ResponseBody>> httpCallback) {
        Flowable<Response<ResponseBody>> flowable = apiService.getSaveOrderInsertData(orderData);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<ResponseBody>>() {
                    @Override
                    public void onNext(Response<ResponseBody> response) {

                        httpCallback.onSuccess(response);
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

    public void getOrderJoiningQrGet(String receiptId,
                                       final IHttpCallback<Response<ResponseBody>> httpCallback) {
        Flowable<Response<ResponseBody>> flowable = apiService.getOrderJoiningQrGet(receiptId);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<ResponseBody>>() {
                    @Override
                    public void onNext(Response<ResponseBody> response) {
                        httpCallback.onSuccess(response);
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


    public void getOrderJoinPageList(String page, String perPage, String memberId, final IHttpCallback<List<OrderListResultData>> httpCallback) {
        Flowable<Response<OderListResponse>> flowable = apiService.getOrderJoinPageList(page, perPage, memberId);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<OderListResponse>>() {
                    @Override
                    public void onNext(Response<OderListResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {
                                OderListResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getOrderListResultData());
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


    public void getScanQrderJoinQRInsert(String memberID, String decryptedMessage, String modifiedUser
            , final IHttpCallback<List<OrderJoinResultData>> httpCallback) {
        Flowable<Response<OderJoinResponse>> flowable = apiService.getScanOrderJoinQRInsert(memberID
                , decryptedMessage
                , modifiedUser);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<OderJoinResponse>>() {
                    @Override
                    public void onNext(Response<OderJoinResponse> response) {
                        if (response.isSuccessful()) {

                            if (response.isSuccessful()) {
                                OderJoinResponse res = response.body();
                                if (res.isSuccessStatus()) {
                                    httpCallback.onSuccess(res.getOrderJoinResultData());
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

    public void getUpdateRating(String ratingID
            , String receiptID
            , String score
            , String comment
            , String modifiedUser
            , String modifiedDate
            , final IHttpCallback<String> httpCallback) {
        Flowable<Response<Void>> flowable = apiService.getUpdateRating(ratingID,receiptID,score,comment, modifiedUser, modifiedDate);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new DefaultSubscriber<Response<Void>>() {
                    @Override
                    public void onNext(Response<Void> response) {
                        if (response.isSuccessful()) {
                            if (response.isSuccessful()) {

                                httpCallback.onSuccess("Success");
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
}
