package com.example.sprinklesbakery.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.sprinklesbakery.data.model.Member;
import com.example.sprinklesbakery.data.repository.MemberRepository;

public class MemberViewModel extends AndroidViewModel {
    private final MemberRepository repository;
    private final MutableLiveData<Member> memberLiveData = new MutableLiveData<>();

    public MemberViewModel(@NonNull Application application) {
        super(application);
        repository = new MemberRepository(application);
    }

    public void registerMember(Member member) {
        repository.insert(member);
    }

    public void login(String email, String password) {
        new Thread(() -> {
            Member member = repository.login(email, password);
            memberLiveData.postValue(member);
        }).start();
    }

    public LiveData<Member> getMemberLiveData() {
        return memberLiveData;
    }

}
