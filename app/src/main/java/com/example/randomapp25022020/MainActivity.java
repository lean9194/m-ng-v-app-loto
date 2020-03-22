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
    //    Các biến toàn cục (biến tạo một lần và không có sự thay đổi) tạo ở đây
    EditText mEdtSoMin, mEdtSoMax;
    Button mBtnRandom, mBtnAddBound, mBtnReset;
    TextView mTvKetQua;
    String mTextmin, mTextmax;
    int mSmin, mSmax, mValue, mi;
    Random mRandom;
    final ArrayList<Integer> mArrayNumber = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Ánh xạ (gán các biến đã tạo cho từng view bên activity_main.xml)
        mEdtSoMin = findViewById(R.id.edtSomin);
        mEdtSoMax = findViewById(R.id.edtSomax);
        mBtnRandom = findViewById(R.id.btnRandom);
        mBtnAddBound = findViewById(R.id.btnAddBound);
        mTvKetQua = findViewById(R.id.tvKetqua);
        mBtnReset = findViewById(R.id.btnReset);
//        Tạo phương thức onclick cho nút mBtnReset
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Cú pháp hiện nút đã ẩn (mở khóa để có thể nhập liệu lại) ---(nút cần hiện).setEnabled(true)--
                mEdtSoMin.setEnabled(true);
                mEdtSoMax.setEnabled(true);
                mBtnAddBound.setEnabled(true);
//                Cú pháp xóa dữ liệu trong mảng (dùng để xóa dữ liệu từ lần nhập trước)
                mArrayNumber.clear();
//                hàm ---(vị trí cần ghi đè).set.Text("nội dung cần ghi đè")--- dùng để ghi đè lên một nội dung đã được hiển thị từ trước đó. Ví dụ: ô somax đang chứa giá trị là 5, sử dụng hàm dươi đây sẽ thay thế số 5 bằng "" (chuỗi rỗng)
                mTvKetQua.setText("");
                mEdtSoMin.setText("");
                mEdtSoMax.setText("");
            }
        });
//        Tạo phương thức onclick nút AddBound
        mBtnAddBound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hàm ---(vị trí cần lấy dữ liệu).get.Text().toString()--- dùng để lấy dữ liệu từ ô "Nhập số min" và "Nhập số max" mà người dùng đã nhập, dữ liệu lấy về ở dạng String.
//                Tạm thời đặt một biến mới kiểu dữ liệu String để chứa dữ liệu lấy về.
                mTextmin = mEdtSoMin.getText().toString();
                mTextmax = mEdtSoMax.getText().toString();
//                Tạo điều kiệu cho trường hợp người dùng nhập chuỗi rỗng (không nhập gì cả mà nhấn AddBound). Nếu người dùng nhập vào 1 trong 2 ô "Nhập số max" và "Nhập số min" là chuỗi rỗng thì hiện thông báo "Hãy Nhập Lại".
                if (mTextmin.equals("") || mTextmax.equals("")) {
                    Toast.makeText(MainActivity.this, "Hãy nhập lại!!!", Toast.LENGTH_SHORT).show(); //Phương thức Toast dùng để hiện dòng thông báo lên màn hình.
                    return; // hàm return; dùng để kết thúc chương trình nếu người dùng nhập chuỗi rỗng
                }
//  hàm Interger.parseInt(biến cần chuyển đổi) dùng để chuyển kiểu dữ liệu khác sang Int.
//  Vì mTextmin và mTextmax lấy được từ phương thức getText() bên trên là kiểu String (chuỗi) nên khi muốn sử dụng để tính toán +-x/ thì phải chuyển về kiểu Int (kiểu số).
                mSmin = Integer.parseInt(mTextmin);
                mSmax = Integer.parseInt(mTextmax);
//  Sử dụng hàm if else hoặc toán từ 3 ngôi để xử lý trường hợp người dùng nhập số min >= số max.
//  Nếu số min >= số max thì gán cho (số max = số min + 1) tức số max sẽ lớn hơn số min trở lại. Cú pháp toán tử 3 ngôi ---x = (điều kiện) ? (nếu điều kiện true thì x sẽ ...) : (nếu điều kiện false thì x sẽ...)---
                mSmax = mSmin >= mSmax ? mSmin + 1 : mSmax;
//Hàm set.Text tương tự như trên. Trường hợp này dùng để hiển thị lại số max mới trong trường hợp người dùng nhập số min > số max.
// Ví dụ min = 5; max = 3, toán tử 3 ngôi trên sẽ tự động gắn lại cho max = 5 + 1 = 6.
// Tuy nhiên tại ô "Nhập số max" sẽ vẫn hiển thị là 3 chứ không phải là 6, set.Text() chổ này sẽ đè số 6 của Số max lên số 3 của ô "Nhập số max", hiển thị lại ô "Nhập số max" giá trị 6 để đúng với giá trị.
// Lưu ý trong setText phải là giá trị String (chuỗi), mà mSmax ở trên là số Int nên phải dùng String.valueOf() để chuyển về chuỗi.
                mEdtSoMax.setText(String.valueOf(mSmax));
//                dùng vòng lặp để gán giá trị của dãy từ Số min đến Số max vào trong chuỗi
                for (mi = mSmin; mi <= mSmax; mi++) {
                    mArrayNumber.add(mi); //Hàm dùng để gán giá trị vào trong chuỗi
                }
//                Đoạn điều kiện if này dùng để disable (khóa khả năng nhập liệu của người dùng) tại các vị trí ô,nút mong muốn. Tương tự như trên nút Reset, khi nhấn nút Reset sẽ mở lại để người dùng nhập số min số max lại
                if (mArrayNumber.size() > 0) {
                    mEdtSoMin.setEnabled(false); //Khóa nút nhập số min
                    mEdtSoMax.setEnabled(false); //Khóa nút nhập số max
                    mBtnAddBound.setEnabled(false); //Khóa nút AddBound
//                    Phương thức Toaat Như trên, kiểm tra nếu người dùng nhập đúng thì thông báo nhập thành công, nhập sai chuỗi rỗng thì báo nhập thất bại.
                    Toast.makeText(MainActivity.this, "Thêm số thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Thêm số thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        Tạo Phương thức Onclick cho nút Random
        mBtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Task 1: Tạo phương thức random, nếu như số đó được random rồi thì không được xuất hiện nữa
                if (mArrayNumber.size() != 0) {
                    mRandom = new Random(); //Khai báo phương thức Random
                    mValue = mRandom.nextInt(mArrayNumber.size()); //Random trong khoảng chiều dài của chuỗi, đồng nghĩa với index của các số trong chuỗi hàm arraynumber.size() trả về độ dài của chuỗi
                    mTvKetQua.append(mArrayNumber.get(mValue) + " - "); //Xuất giá trị trong chuỗi ra tương ứng với index vừa random được bên trên. hàm arratnumber.get(vị trí của chuỗi) trả về giá trị tại 1 index vừa random được (ở đây là mValue) để xuất ra giá trị tại index đó.
                    mArrayNumber.remove(mValue); //Xóa đi index vừa xuất ra để đảm bảo không trùng, lặp lại nữa. hàm arraynumber.remove(index) dùng để xóa vị trí tại index vừa random được, đồng thời xóa luôn giá trị tại index đó, đảm bảo khi random không lặp lại giá trị đó nữa. Các index phía sau sẽ được đẩy lên để thế chỗ, chiều dài mảnh sẽ giảm đi 1
//                Task 2: Nếu random hết số rồi thì thông báo "Hết số rồi" khi tiếp tục nhấn nút Random, khi hết số thì chiều dài chuỗi sẽ = 0, khi đó sẽ thông báo hết số
                } else {
                    Toast.makeText(MainActivity.this, "Hết số rồi!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
