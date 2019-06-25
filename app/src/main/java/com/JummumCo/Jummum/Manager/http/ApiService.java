package com.JummumCo.Jummum.Manager.http;

import com.JummumCo.Jummum.Model.BankResponse;
import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponse;
import com.JummumCo.Jummum.Model.BranchResponse;
import com.JummumCo.Jummum.Model.DisputeResponse;
import com.JummumCo.Jummum.Model.HotDealResponse;
import com.JummumCo.Jummum.Model.HotDealResponse2;
import com.JummumCo.Jummum.Model.ImageResponse;
import com.JummumCo.Jummum.Model.LuckyDrawResponse;
import com.JummumCo.Jummum.Model.MasterResponse;
import com.JummumCo.Jummum.Model.MenuGetListResponse;
import com.JummumCo.Jummum.Model.NoteListResponse;
import com.JummumCo.Jummum.Model.OderJoinResponse;
import com.JummumCo.Jummum.Model.OderListResponse;
import com.JummumCo.Jummum.Model.PayListResponse;
import com.JummumCo.Jummum.Model.PayResultData;
import com.JummumCo.Jummum.Model.PromotionListResponse;
import com.JummumCo.Jummum.Model.RatingResponse;
import com.JummumCo.Jummum.Model.RewardResponse;
import com.JummumCo.Jummum.Model.SaveOrderInsertData;
import com.JummumCo.Jummum.Model.SummaryResponse;
import com.JummumCo.Jummum.Model.SummaryResultData;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by CRRU0001 on 21/03/2559.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("andJMMBranchAndCustomerTableQRGet.php")
    Flowable<Response<BranchAndCustomerTableResponse>> scanQr(
            @Field("decryptedMessage") String decryptedMessage,
            @Field("modifiedDeviceToken") String modifiedDeviceToken,
            @Field("modifiedUser") String modifiedUser
    );


    @FormUrlEncoded
    @POST("andJMMMenuGetList.php")
    Flowable<Response<MenuGetListResponse>> getMenuList(
            @Field("branchID") String branchID,
            @Field("modifiedDeviceToken") String modifiedDeviceToken,
            @Field("modifiedUser") String modifiedUser
    );

    @FormUrlEncoded
    @POST("andJMMMasterGet.php")
    Flowable<Response<MasterResponse>> getMaster(
            @Field("modifiedDeviceToken") String modifiedDeviceToken,
            @Field("modifiedUser") String modifiedUser
    );

    @POST("andJMMOrderSummaryGet.php")
    Flowable<Response<SummaryResponse>> getSummry(
            @Body SummaryResultData summaryResultData
    );

    @FormUrlEncoded
    @POST("andJMMPromotionGetList.php")
    Flowable<Response<PromotionListResponse>> getPromotion(
            @Field("voucherCode") String voucherCode,
            @Field("userAccountID") String userAccountID,
            @Field("branchID") String branchID,
            @Field("totalAmount") String totalAmount,
            @Field("modifiedDeviceToken") String modifiedDeviceToken,
            @Field("modifiedUser") String modifiedUser
    );

    @POST("andJMMOmiseCheckOut.php")
    Flowable<Response<PayListResponse>> createOrder(@Body PayResultData order);

    @FormUrlEncoded
    @POST("andJMMReceiptSummaryGetList.php")
    Flowable<Response<OderListResponse>> getOrderList(
            @Field("page") String page,
            @Field("perPage") String perPage,
            @Field("memberID") String memberID
    );

    @FormUrlEncoded
    @POST("andJMMReceiptAndPromoCodeUpdate.php")
    Flowable<Response<Void>> getUpdateReceiptAndPromoCode(
            @Field("receiptID") String receiptID
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate
            , @Field("modifiedDeviceToken") String modifiedDeviceToken
            , @Field("actionScreen") String actionScreen
    );

    @FormUrlEncoded
    @POST("andJMMReceiptUpdate.php")
    Flowable<Response<Void>> getUpdateReceipt(
            @Field("receiptID") String receiptID
            , @Field("status") String status
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate
            , @Field("modifiedDeviceToken") String modifiedDeviceToken
            , @Field("actionScreen") String actionScreen
    );

    @FormUrlEncoded
    @POST("andJMMReceiptDisputeRatingGet.php")
    Flowable<Response<RatingResponse>> getRating(
            @Field("receiptID") String receiptID
    );

    @FormUrlEncoded
    @POST("andJMMRatingInsert.php")
    Flowable<Response<Void>> getInsertRating(
            @Field("receiptID") String receiptID
            , @Field("score") String score
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate
            , @Field("modifiedDeviceToken") String modifiedDeviceToken
            , @Field("actionScreen") String actionScreen
    );

    @FormUrlEncoded
    @POST("andJMMRatingUpdate.php")
    Flowable<Response<Void>> getUpdateRating(
            @Field("ratingID") String ratingID
            ,@Field("receiptID") String receiptID
            , @Field("score") String score
            , @Field("comment") String comment
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate
    );

    @FormUrlEncoded
    @POST("andJMMBranchSearchGetList.php")
    Flowable<Response<BranchResponse>> getBranchSearchList(
            @Field("searchText") String searchText,
            @Field("page") String page,
            @Field("perPage") String perPage
    );

    @FormUrlEncoded
    @POST("andJMMDownloadImage.php")
    Flowable<Response<ImageResponse>> getImage(
            @Field("imageFileName") String imagefilename,
            @Field("type") String type,
            @Field("branchID") String branchid
    );

    @FormUrlEncoded
    @POST("andJMMCustomerTableGetList.php")
    Flowable<Response<BranchResponse>> getCustomTableList(
            @Field("branchID") String branchID
    );


    @FormUrlEncoded
    @POST("andJMMMenuNoteGetList.php")
    Flowable<Response<NoteListResponse>> getNoteList(
            @Field("branchID") String branchID,
            @Field("menuID") String menuID
    );


    @FormUrlEncoded
    @POST("andJMMPromotionAndRewardRedemptionGetList.php")
    Flowable<Response<HotDealResponse>> getVoucher(
            @Field("branchID") String branchID,
            @Field("memberID") String memberID
    );

    @FormUrlEncoded
    @POST("andJMMPromotionAndRewardRedemptionGetList.php")
    Flowable<Response<HotDealResponse2>> getVoucher2(
            @Field("branchID") String branchID,
            @Field("memberID") String memberID
    );


    @FormUrlEncoded
    @POST("andJMMHotDealGetList.php")
    Flowable<Response<HotDealResponse>> getHotdeals(
            @Field("page") int page,
            @Field("perPage") int perPage,
            @Field("memberID") String memberID,
            @Field("searchText") String searchText
    );

    @FormUrlEncoded
    @POST("andJMMRewardRedemptionLuckyDrawGet.php")
    Flowable<Response<LuckyDrawResponse>> getLuckyDraw(
            @Field("branchID") String branchID,
            @Field("memberID") String memberID,
            @Field("receiptID") String receiptID
    );

    @FormUrlEncoded
    @POST("andJMMMenuGet.php")
    Flowable<Response<MenuGetListResponse>>getOrderNow(
            @Field("branchID") String branchID,
            @Field("discountGroupMenuID") String discountGroupMenuID
    );

    @FormUrlEncoded
    @POST("andJMMMenuBelongToBuffetGetList.php")
    Flowable<Response<MenuGetListResponse>> menuBelongToBuffetGetList(
            @Field("branchID") String branchID,
            @Field("buffetReceiptID") String buffetReceiptID
    );

    @POST("andJMMBuffetOrderInsertList.php")
    Flowable<Response<PayListResponse>> buffetOrderInsert(@Body PayResultData order);

    @FormUrlEncoded
    @POST("andJMMRewardPointGet.php")
    Flowable<Response<RewardResponse>> getReward(
            @Field("page") int page,
            @Field("perPage") int perPage,
            @Field("memberID") String memberID,
            @Field("searchText") String searchText
    );

    @FormUrlEncoded
    @POST("andJMMDisputeReasonGetList.php")
    Flowable<Response<DisputeResponse>> getDisputData(
            @Field("type") int type
    );

    @FormUrlEncoded
    @POST("andJMMTransferFormAndBankGetList.php")
    Flowable<Response<BankResponse>> getTransferAndBankData(
            @Field("receiptID") String receiptID
    );

    @FormUrlEncoded
    @POST("andJMMDisputeCancelInsert.php")
    Flowable<Response<Void>> getInsertCancel(
            @Field("receiptID") String receiptID
            , @Field("disputeReasonID") String disputeReasonID
            , @Field("refundAmount") String refundAmount
            , @Field("detail") String detail
            , @Field("phoneNo") String phoneNo
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate

    );

    @FormUrlEncoded
    @POST("andJMMTransferFormInsert.php")
    Flowable<Response<Void>> getInsertTransferForm(
            @Field("receiptID") String receiptID
            , @Field("bankID") String bankID
            , @Field("bankAccountNo") String bankAccountNo
            , @Field("amount") String amount
            , @Field("phoneNo") String phoneNo
            , @Field("remark") String remark
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate

    );

    @FormUrlEncoded
    @POST("andJMMDisputeInsert.php")
    Flowable<Response<Void>> getInsertDispute(
            @Field("receiptID") String receiptID
            , @Field("disputeReasonID") String disputeReasonID
            , @Field("refundAmount") String refundAmount
            , @Field("detail") String detail
            , @Field("phoneNo") String phoneNo
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate

    );

    @FormUrlEncoded
    @POST("andJMMCommentInsert.php")
    Flowable<Response<Void>> getInsertComment(
            @Field("userAccountID") String userAccountID
            , @Field("text") String text
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate

    );

    @FormUrlEncoded
    @POST("andJMMRecommendShopInsert.php")
    Flowable<Response<Void>> getInsertRecommend(
            @Field("userAccountID") String userAccountID
            , @Field("text") String text
            , @Field("modifiedUser") String modifiedUser
            , @Field("modifiedDate") String modifiedDate

    );

    @FormUrlEncoded
    @POST("andJMMRewardPointInsert.php")
    Flowable<Response<RewardResponse>> getRedeemReward(
            @Field("memberID") String memberID,
            @Field("rewardRedemptionID") String rewardRedemptionID,
            @Field("modifiedUser") String modifiedUser,
            @Field("modifiedDate") String modifiedDate
    );

    @FormUrlEncoded
    @POST("andJMMRewardPointSpentUsedGetList.php")
    Flowable<Response<RewardResponse>> getMyRewardUsed(
            @Field("memberID") String memberID,
            @Field("page") int page,
            @Field("perPage") int perPage
    );

    @FormUrlEncoded
    @POST("andJMMRewardPointSpentGetList.php")
    Flowable<Response<RewardResponse>> getMyRewardCurrent(
            @Field("memberID") String memberID,
            @Field("page") int page,
            @Field("perPage") int perPage
    );

    @FormUrlEncoded
    @POST("andJMMRewardPointSpentExpiredGetList.php")
    Flowable<Response<RewardResponse>> getMyRewardExpired(
            @Field("memberID") String memberID,
            @Field("page") int page,
            @Field("perPage") int perPage
    );


    @FormUrlEncoded
    @POST("qrcode")
    Flowable<Response<ResponseBody>> getGBPrimePay(
            @Field("token") String gBPrimeQRToken,
            @Field("amount") String amount,
            @Field("detail") String customerTableID,
            @Field("referenceNo") String referenceNo,
            @Field("payType") String payType,
            @Field("backgroundUrl") String backgroundUrl,
            @Field("responseUrl") String responseUrl,
            @Field("merchantDefined1") String receiptID,
            @Field("merchantDefined2") String branchID,
            @Field("merchantDefined3") String deviceToken,
            @Field("merchantDefined4") String memberID,
            @Field("merchantDefined5") String receiptNoID
    );


    @FormUrlEncoded
    @POST("qrcode")
    Call<ResponseBody> getGBPrimePay2(
            @Field("token") String gBPrimeQRToken,
            @Field("amount") String amount,
            @Field("detail") String customerTableID,
            @Field("referenceNo") String referenceNo,
            @Field("payType") String payType,
            @Field("backgroundUrl") String backgroundUrl,
            @Field("responseUrl") String responseUrl,
            @Field("merchantDefined1") String receiptID,
            @Field("merchantDefined2") String branchID,
            @Field("merchantDefined3") String deviceToken,
            @Field("merchantDefined4") String memberID,
            @Field("merchantDefined5") String receiptNoID
    );

    @FormUrlEncoded
    @POST("andJMMPaymentCompleteGet.php")
    Flowable<Response<PayListResponse>> getPaymentComplate(
            @Field("receiptID") String receiptID
    );


    @FormUrlEncoded
    @POST("andJMMGBPrimeSetting.php")
    Flowable<Response<PayListResponse>> GBPrimeSetting(@Field("receiptID") String receiptID);


    @POST("andJMMSaveOrderInsertList.php")
    Flowable<Response<ResponseBody>> getSaveOrderInsertData(@Body SaveOrderInsertData order);

    @FormUrlEncoded
    @POST("andJMMOrderJoiningShareQrGet.php")
    Flowable<Response<ResponseBody>> getOrderJoiningQrGet(@Field("receiptID") String receiptID);

    @FormUrlEncoded
    @POST("andJMMOrderJoiningPageGetList.php")
    Flowable<Response<OderListResponse>> getOrderJoinPageList(
            @Field("page") String page,
            @Field("perPage") String perPage,
            @Field("memberID") String memberID
    );

    @FormUrlEncoded
    @POST("andJMMOrderJoiningScanQrInsert.php")
    Flowable<Response<OderJoinResponse>> getScanOrderJoinQRInsert(
            @Field("memberID") String memberID,
            @Field("decryptedMessage") String decryptedMessage,
            @Field("modifiedUser") String modifiedUser
    );
}
