package com.JummumCo.Jummum.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.JummumCo.Jummum.Adapter.NoteGridViewAdapter;
import com.JummumCo.Jummum.Interface.IHttpCallback;
import com.JummumCo.Jummum.Model.NoteListResponseResultData;
import com.JummumCo.Jummum.Respository.CommonRepository;
import com.JummumCo.Jummum.Util.Constant;
import com.JummumCo.Jummum.Util.Util;
import com.android.jummum.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NodeDialogActivity extends BaseActivity {

    @BindView(R.id.btnCancle)
    Button btnCancle;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_content)
    LinearLayout mainContent;
    @BindView(R.id.btnDeleteAll)
    Button btnDeleteAll;
    @BindView(R.id.layout_not_note)
    RelativeLayout layoutNotNote;

    private CommonRepository commonRepository;
    private NoteGridViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private List<NoteListResponseResultData> noteData;
    private List<NoteListResponseResultData> dataOld;
    private List<NoteListResponseResultData> noteListSelect;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_node_dialog);
        ButterKnife.bind(this);
        initinstall();
    }

    private void initinstall() {


        dataOld = Parcels.unwrap(getIntent().getParcelableExtra("noteData"));
        commonRepository = new CommonRepository();
        showProgressDialog();
        commonRepository.getNote(Constant.menuNoteDataGlobal.getBranchID()
                , Constant.menuNoteDataGlobal.getMenuID()
                , getHttpCallback());
    }


    @NonNull
    private IHttpCallback<List<NoteListResponseResultData>> getHttpCallback() {
        return new IHttpCallback<List<NoteListResponseResultData>>() {
            @Override
            public void onSuccess(List<NoteListResponseResultData> response) {
                hideProgressDialog();
                noteData = response;

                if (noteData.size() > 0) {
                    linearLayoutManager = new LinearLayoutManager(NodeDialogActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    adapter = new NoteGridViewAdapter(noteData, dataOld);
                    recyclerView.setAdapter(adapter);
                    layoutNotNote.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    layoutNotNote.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onError(String message) {
                hideProgressDialog();
                Util.showToast(mainContent, "Cannot connect to server");
//                Util.showToast(mainContent, message);
            }
        };
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        //if (noteListSelect == null) {
        noteListSelect = Constant.noteListData;
        Constant.noteListData = new ArrayList<>();
        //}
        Intent intent = getIntent();
        intent.putExtra("notepush", Parcels.wrap(noteListSelect));
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.btnCancle)
    public void onClickCancle() {
        finish();
    }

    @OnClick(R.id.btnDeleteAll)
    public void onViewClickedDeletaAll() {
        noteListSelect = new ArrayList<>();
        Constant.noteListData.clear();
        Intent intent = getIntent();
        intent.putExtra("notepush", Parcels.wrap(noteListSelect));
        setResult(RESULT_OK, intent);
        finish();

    }
}
