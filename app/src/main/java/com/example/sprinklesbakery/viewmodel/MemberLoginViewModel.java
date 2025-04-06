package com.example.sprinklesbakery.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.sprinklesbakery.data.repository.MemberRepository;
import com.example.sprinklesbakery.data.model.Member;

public class MemberLoginViewModel extends AndroidViewModel {

    private final MemberRepository repository;
    private final MutableLiveData<Member> loggedInMember = new MutableLiveData<>();

    public MemberLoginViewModel(Application application) {
        super(application);
        repository = new MemberRepository(application);
    }

    public void login(String email, String password) {
        new Thread(() -> {
            Member member = repository.login(email, password);
            loggedInMember.postValue(member);
        }).start();
    }

    public LiveData<Member> getLoggedInMember() {
        return loggedInMember;
    }

}
