package com.learn.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();

    ImageView ivShow;

    private Disposable disposable = Disposables.empty();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_load).setOnClickListener(this);
        ivShow = findViewById(R.id.iv_show);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.tv_load: {
                loadImage();
                useRxJava();
            }
            default:
                break;
        }
    }

    private void loadImage() {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        Glide.with(this)
                .load(url).into(ivShow);
    }

    /**
     *
     */
    private void useRxJava() {
        // https://www.jianshu.com/p/0cd258eecf60
        //创建一个发布者（上游）
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onComplete();
                emitter.onNext("4");
                emitter.onNext("5");
                emitter.onNext("6");
            }
        });
        //创建一个观察者（下游）
        Observer<String> observer = new Observer<String>() {
            Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                Log.i(TAG, "onSubscribe " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, "onNext " + s);
                if (TextUtils.equals(s, "2")) {
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        };

        //绑定订阅关系（建立连接）可观察对象订阅观察者
        observable.subscribe(observer);
        Log.i(TAG, "" + disposable.isDisposed() + " " + disposable.hashCode());
        //TODO:
        disposable.dispose();
        Log.i(TAG, "" + disposable.isDisposed() + " " + disposable.hashCode());
        disposable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) throws Exception {
                Log.i(TAG, "accept " + i);
            }
        });
//        Log.i(TAG, "" + disposable.isDisposed() + " " + disposable.hashCode());
//        disposable.dispose();
        Log.i(TAG, "" + disposable.isDisposed()+ " " + disposable.hashCode());
    }
}
