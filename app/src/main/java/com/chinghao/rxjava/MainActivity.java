package com.chinghao.rxjava;

import android.database.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    public  static final String TAG="MaoinRxJava";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mButton= (Button) findViewById(R.id.button);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                init();
//            }
//        });
        io.reactivex.Observable<Integer> observable= io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG,"Observable thread is :"+Thread.currentThread().getName());
                Log.e(TAG,"emit 1");
                e.onNext(1);
            }
        });
        Consumer<Integer> consumer=new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.e(TAG,"Observable thread is :"+Thread.currentThread().getName());
                Log.e(TAG,"onNext:"+integer);
            }
        };

        observable.subscribe(consumer);

    }

    private void init() {
        io.reactivex.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("Tag","subscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.e("Tag",integer+"");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Tag","error");
            }

            @Override
            public void onComplete() {
                Log.e("Tag","complete");
            }
        });
    }


}
