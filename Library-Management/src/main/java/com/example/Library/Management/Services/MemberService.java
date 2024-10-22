package com.example.Library.Management.Services;

import com.example.Library.Management.Dal.MemberManagement;
import com.example.Library.Management.Model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private  MemberManagement memberDao;



    public Member addMember(Member member) {
        return memberDao.addMember(member);
    }

    public Member getMemberById(String memberId) {
        return memberDao.getMemberById(memberId);
    }

    public List<Member> getAllMembers() {
        return memberDao.getAllMembers();
    }

    public Member updateMember(Member member) {
        return memberDao.updateMember(member);
    }

    public boolean deleteMember(String memberId) {
        return memberDao.deleteMember(memberId);
    }
}
