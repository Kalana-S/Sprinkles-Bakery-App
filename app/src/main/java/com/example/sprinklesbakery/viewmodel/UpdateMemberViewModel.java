package com.example.sprinklesbakery.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.sprinklesbakery.data.repository.MemberRepository;
import com.example.sprinklesbakery.data.model.Member;

public class UpdateMemberViewModel extends AndroidViewModel{

    private final MemberRepository repository;
    private final MutableLiveData<Member> memberLiveData = new MutableLiveData<>();

    public LiveData<Member> getMemberLiveData() {
        return memberLiveData;
    }

    public void loadMemberData(int id) {
        new Thread(() -> {
            Member member = repository.getMemberById(id);
            memberLiveData.postValue(member);
        }).start();
    }

    public UpdateMemberViewModel(Application application) {
        super(application);
        repository = new MemberRepository(application);
    }

    public void updateMember(int id, String name, String email, String password) {
        repository.updateMember(id, name, email, password);
    }

}
