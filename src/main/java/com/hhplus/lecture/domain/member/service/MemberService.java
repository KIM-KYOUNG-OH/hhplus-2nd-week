package com.hhplus.lecture.domain.member.service;

import com.hhplus.lecture.domain.member.entity.Member;
import com.hhplus.lecture.infraStructure.member.repository.MemberRepository;
import com.hhplus.lecture.interfaces.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public Member findBy(Long memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow(() -> new NotFoundException(String.format("%s(memberId) is not found", memberId)));
    }
}
