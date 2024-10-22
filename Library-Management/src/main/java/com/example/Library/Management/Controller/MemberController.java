package com.example.Library.Management.Controller;

import com.example.Library.Management.Model.Member;
import com.example.Library.Management.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private  MemberService memberService;

    @PostMapping()
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable String memberId) {
        return memberService.getMemberById(memberId);
    }

    @GetMapping()
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PutMapping("/{memberid}")
    public Member updateMember(@RequestBody Member member) {
        return memberService.updateMember(member);
    }

    @DeleteMapping("/{memberId}")
    public String deleteMember(@PathVariable String memberId) {
        boolean isDeleted = memberService.deleteMember(memberId);
        return isDeleted ? "Member deleted successfully." : "Failed to delete the member.";
    }
}
