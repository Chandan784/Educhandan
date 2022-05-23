package com.educhandan.educhandan.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.educhandan.educhandan.CourseSubjectsActivity;
import com.educhandan.educhandan.R;
import com.educhandan.educhandan.classes.DBqueries;


public class CourseTestsFragment extends Fragment {

    public CourseTestsFragment() {
        // Required empty public constructor
    }
    public static Dialog loadingDialog;
    public static String subjectNameTests;
    public static RecyclerView courseTestsRecyclerview;
    public static TextView moreTv, noMat;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_tests, container, false);

        ///loading Dialog
        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();
        /////end loading dialog

        Intent intent = getActivity().getIntent();
        subjectNameTests = intent.getStringExtra("subject_name");

        courseTestsRecyclerview = view.findViewById(R.id.course_tests_recyclerview);
        moreTv = view.findViewById(R.id.moreTestsTv);
        noMat = view.findViewById(R.id.noMatTestsTv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        courseTestsRecyclerview.setLayoutManager(layoutManager);

        DBqueries.loadCourseTests(getContext(), loadingDialog, courseTestsRecyclerview, CourseSubjectsActivity.productId, subjectNameTests, moreTv, noMat);


        return view;
    }
}