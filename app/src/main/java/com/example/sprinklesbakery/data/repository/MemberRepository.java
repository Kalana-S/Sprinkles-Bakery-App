package com.example.sprinklesbakery.data.repository;

import android.content.Context;
import android.os.AsyncTask;
import com.example.sprinklesbakery.data.database.AppDatabase;
import com.example.sprinklesbakery.data.dao.MemberDao;
import com.example.sprinklesbakery.data.model.Member;

public class MemberRepository {
    private final MemberDao memberDao;

    public MemberRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        memberDao = db.memberDao();
    }

    public void insert(Member member) {
        new InsertMemberTask(memberDao).execute(member);
    }

    public Member login(String email, String password) {
        return memberDao.login(email, password);
    }

    private static class InsertMemberTask extends AsyncTask<Member, Void, Void> {
        private final MemberDao dao;

        InsertMemberTask(MemberDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Member... members) {
            dao.insert(members[0]);
            return null;
        }
    }

    public void updateMember(int id, String name, String email, String password) {
        AsyncTask.execute(() -> memberDao.updateMember(id, name, email, password));
    }

    public Member getMemberById(int id) {
        return memberDao.getMemberById(id);
    }

}
