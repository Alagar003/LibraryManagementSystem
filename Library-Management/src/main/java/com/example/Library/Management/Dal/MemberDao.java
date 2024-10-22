package com.example.Library.Management.Dal;

import com.example.Library.Management.Model.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Repository
public class MemberDao implements MemberManagement {

    private  Map<String, Member> members = new ConcurrentHashMap<>();

    @Override
    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    public Member getMemberById(String id) {
        return members.get(id);
    }

    @Override
    public Member addMember(Member member) {
        members.put(member.getId(), member);
        return member;
    }

    @Override
    public Member updateMember(Member member) {
        members.replace(member.getId(), member);
        return member;
    }

    public boolean deleteMember(String memberId) {
        return members.remove(memberId) != null;
    }
}
