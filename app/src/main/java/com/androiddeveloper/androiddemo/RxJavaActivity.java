package com.androiddeveloper.androiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zzh on 16/4/22.
 */
public class RxJavaActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_rxjava);
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.d("zzh","在UI线程中显示后台线程获取到的数据" );
            }
        };

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d("zzh","在后台线程从IO中获取数据");
                subscriber.onNext("niaho");
                subscriber.onCompleted();
            }

        });

        observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(subscriber);

        List<String> list = new ArrayList<>();
        list.add("math");
        list.add("Englisg");
        list.add("computer");
        final Student student1 = new Student(22,list);
        Student student2 = new Student(23,list);
        //打印学生的年龄
        Subscriber subscriber2 = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d("zzh","age = " + integer );
            }
        };

        Observable observable1 = Observable.create(new Observable.OnSubscribe<Student>() {
            @Override
            public void call(Subscriber<? super Student> subscriber) {
                Log.d("zzh","call onNext");
                subscriber.onNext(student1);
            }
        }).map(new Func1<Student,Integer>() {
            @Override
            public Integer call(Student student) {
                return student.age;
            }
        });

        observable1.subscribe(subscriber2);



        Subscriber subscriber3 = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String strings) {
                Log.d("zzh",strings);
            }

        };

        //打印学生课程
        Observable.just(student1,student2).flatMap(new Func1<Student, Observable<String>>() {
            @Override
            public Observable<String> call(Student student) {
                return Observable.from(student.cources);
            }
        }).subscribe(subscriber3);

    }


    public class Student{
        public int age;
        public List<String> cources;
        public Student(int age, List<String> cources){
            Student.this.age = age;
            Student.this.cources = cources;
        }
    }
}
