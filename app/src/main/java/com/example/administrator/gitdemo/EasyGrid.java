package com.example.administrator.gitdemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class EasyGrid extends GridLayout {
    private List mData;

    public EasyGrid(Context context) {
        this(context, null);
    }

    public EasyGrid(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mData = new ArrayList();
    }

    @LayoutRes
    private int mCellLayoutRes;
    private CellProcessor mCellProcessor;


    private int mRowCount;
    private int mColCount;


    public EasyGrid rowsAndCols(int rows, int cols) {
        mRowCount = rows;
        mColCount = cols;
        return this;
    }

    public EasyGrid cellLayout(@LayoutRes int cellLayoutRes) {
        mCellLayoutRes = cellLayoutRes;
        return this;
    }


    public EasyGrid processCell(CellProcessor cellProcessor) {
        mCellProcessor = cellProcessor;
        return this;
    }

    private int parseOrder(int row, int col) {
        int order = row * mColCount + col;
        return order;
    }


    public void initialise() {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (int row = 0; row < mRowCount; row++) {
            for (int col = 0; col < mColCount; col++) {
                View cellView = inflater.inflate(mCellLayoutRes, this, false);
                int order = parseOrder(row, col);
                GridLayout.Spec rowSpec = GridLayout.spec(row, 1, 1);
                GridLayout.Spec colSpec = GridLayout.spec(col, 1, 1);
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams(rowSpec, colSpec);
                lp.width = 1;
                lp.height = 1;
                if (mCellProcessor != null) {
                    mCellProcessor.process(order, row, col, cellView);
                }

                addView(cellView, lp);
            }
        }
    }


    public interface CellProcessor {
        void process(int order, int row, int col, View cellView);
    }


}
