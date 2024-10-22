package com.example.Library.Management.Dal;

import com.example.Library.Management.Model.Member;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MemberManagement {
    List<Member> getAllMembers();
    Member getMemberById(String id);
    Member addMember(Member member);
    Member updateMember(Member member);
    public boolean deleteMember(String memberId);
}
