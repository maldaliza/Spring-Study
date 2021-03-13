package jpabook.exercise2.member.service;

import jpabook.exercise2.member.domain.Member;

public interface MemberService {

    /**
     * 회원 가입
     */
    void join(Member member);

    /**
     * 회원 조회
     */
    Member findMember(Long memberId);
}