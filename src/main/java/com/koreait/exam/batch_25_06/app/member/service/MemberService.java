package com.koreait.exam.batch_25_06.app.member.service;


import com.koreait.exam.batch_25_06.app.cash.entity.CashLog;
import com.koreait.exam.batch_25_06.app.cash.service.CashService;
import com.koreait.exam.batch_25_06.app.member.entity.Member;
import com.koreait.exam.batch_25_06.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final CashService cashService;

    @Transactional
    public Member join(String username, String password, String email) { // 객체 조립 (new)
        Member member = Member.builder()
                .username(username)
                .password(password)
                .email(email).build();

        memberRepository.save(member);

        return member;
    }

    @Transactional
    public void addCash(Member member, long price, String eventType) {

        CashLog cashLog = cashService.addCash(member, price, eventType);

        long newRestCash = member.getRestCash() + cashLog.getPrice();

        member.setRestCash(newRestCash);

        memberRepository.save(member);
    }

    public long getRestCash(Member member) {

        return member.getRestCash();
    }
}
