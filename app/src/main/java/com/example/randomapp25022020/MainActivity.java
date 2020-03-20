package com.example.randomapp25022020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText mSomin, mSomax;
    Button mBtnRandom;
    TextView mTvKetQua;
    String mTextmin, mTextmax;
    int mSmin,mSmax,mValue;
    Random mRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSomin = findViewById(R.id.edtSomin);
        mSomax = findViewById(R.id.edtSomax);
        mBtnRandom = findViewById(R.id.btnRandom);
        mTvKetQua = findViewById(R.id.tvKetqua);
//        Khai báo mảng ArrayList<kiểu phần tử trong mảng> tên mảng = new ArrayList<>;
        ArrayList<Integer> arrayList = new ArrayList<>;
//        Thêm phần tử vào mảng bằng hàm tênmang.add();
        arrayList.add(10);
//        Lấy kích thước mảng arrayList.size()
        Log.d("BBB",String.valueOf(arrayList.size()));
//        Xóa phần tử trong mảng = arrayList.remove(index mảng); Lưu ý khi xóa thì phần từ phía sau tự động nhảy lên phía trước
        arrayList.remove(1);
//        set lại giá trị phần từ trong mảng = arrayList.set(index,giá trị cần set);
        mBtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              lấy dữ liệu từ edittext bằng hàm gettext().toString(), lấy bằng cách tạo một biến khác kiểu String, vì text luôn nằm dạng string
                mTextmin = mSomin.getText().toString();
                mTextmax = mSomax.getText().toString();
//              Tạo điều kiện nếu nhập chuỗi rỗng, so sánh String luôn dùng .equals
                if (mTextmin.equals("") || mTextmax.equals("")) {
//                    Hiển thị nội dung để nhập lại
                    Toast.makeText(MainActivity.this, "Hãy nhập lại nha!!!", Toast.LENGTH_SHORT).show();
//              Dùng hàm return; để kết thúc chương trình
                    return;
                }
//                chuyển dữ liệu của EdtText từ String về Int để tính toán, dùng hàm int x = Integer.Parseint
                mSmin = Integer.parseInt(mTextmin);
                mSmax = Integer.parseInt(mTextmax);
//                tạo điều kiện nếu mSmin > mSmax thì mSmax = mSmin + 1, tạo bằng toán tử 3 ngôi x = (x<y) ? (true) : (false)
                mSmax = mSmin >= mSmax ? mSmin + 1 : mSmax;
//                dùng hàm setText() để set lại giá trị của text hiển thị, có thể đè lên text đã được tuyên bố (vị trí cần xét VD:mSomin,mSomax).setText(String.valueOf(int)) trong setText mặc định là text (String)
                mSomax.setText(String.valueOf(mSmax));
//                khai báo để dùng hàm random
                mRandom = new Random();
                mValue = mRandom.nextInt(mSmax - mSmin + 1) + mSmin;
//                Xuất ra kết quả trên textview kết quả bằng setText()
//                mTvKetQua.setText(String.valueOf(mValue));
//                Dùng hàm apend để nối chuỗi các kết quả cú pháp mTvKetQua(giá trị set text).append(mValue + " - "); thay thế được cho setText
                mTvKetQua.append(mValue + " - ");

            }
        });
    }
}
