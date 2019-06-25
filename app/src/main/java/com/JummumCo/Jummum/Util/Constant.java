package com.JummumCo.Jummum.Util;

import com.JummumCo.Jummum.Model.BranchAndCustomerTableResponseResultData;
import com.JummumCo.Jummum.Model.HotDealData;
import com.JummumCo.Jummum.Model.ImageMenuBaseData;
import com.JummumCo.Jummum.Model.MenuListResultData;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Model.OrderSummary;
import com.JummumCo.Jummum.Model.PayResponseResultData;
import com.JummumCo.Jummum.Model.SummaryResponseResultData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chawdee on 09/04/2015.
 */
public class Constant {

//    public static String BASE_URL = "http://www.jummum.co/android/and_jummum/";
//    public static String BASE_URL = "http://www.jummum.co/App/AND_JUMMUM1.2.1/";

//    public static String BASE_URL = "http://www.jummum.co/app/and_jummum_1.3/";
//    public static String BASE_URL_API = "http://www.jummum.co/app/and_jummum_1.3/";

    public static String BASE_URL = "https://www.jummum.co/app/demo_jummum_2.4/";
    public static String BASE_URL_API = "https://www.jummum.co/app/demo_jummum_2.4/";

    public static String BASE_URL_QR = "https://api.gbprimepay.com/gbp/gateway/";
    public static String BASE_URL_MENU_IMAGE = BASE_URL+"AND_JUMMUM_OM_MAMARIN7/Image/Menu/";

    public static String DEVICE_TYPE = "android";

    public static String JSON_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String EMAIL_STAFF = "denim_market@outlook.com";


    public static final int REQUEST_CODE_SEARCH_REGISTER = 1000;
    public static final int REQUEST_CODE_SEARCH_NATIONS = 1001;
    public static final int REQUEST_CODE_VOUCHER = 1002;
    public static final int REQUEST_CODE_LUCKY = 1003;
    public static final int REQUEST_CODE_SAVE_RECEIPT = 1004;
    public static final int REQUEST_CODE_HISTORY_DETAIL = 1005;
    public static final int REQUEST_CODE_ORDER_JOIN_QR = 1006;

    public static float MAX_IMAGE_SIZE = 300;

    public static List<MenuListResultData> menuListResultDataGlobal = new ArrayList<>();
    public static List<List<BranchAndCustomerTableResponseResultData>> tableQrCode;
    public static String Omise_Pkey;
    public static boolean status_form_payment = false;
    public static int payment_status = 3;      // 1 = form payment , 2 show creditcard , 3 โอนเงิน

    public static boolean status_choose_new_credit = false;
    public static MenuListResultData menuNoteDataGlobal;
    public static String selectNoteMenuId;
    public static List<NoteListResponseResultData> noteListData;
    public static boolean reOrder = false;
    public static boolean creditCard = false;
    public static int myReward = 0;

    public static final int ADD_NODE_ORDER_REQUEST_CODE = 1002;
    public static final int ADD_QR_ORDER_REQUEST_CODE = 1006;
    public static final int ADD_CREDITCARD_REQUEST_CODE = 1007;
    public static boolean commentMe = false;
    public static boolean payQrCode = false;

    public static List<List<PayResponseResultData>> responsePay;
    public static List<OrderSummary> orderListPay;
    public static List<SummaryResponseResultData> summayDataPay;
    public static int qtyPay;
    public static HotDealData hotDealPay;
    public static String namePay;
    public static List<List<BranchAndCustomerTableResponseResultData>> tableResponseResultDataPay;

    public static List<ImageMenuBaseData> imageMenuBaseData64 = new ArrayList<>();

}

