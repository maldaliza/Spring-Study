package jpabook.core.member.service;

import jpabook.core.member.domain.Member;

public interface MemberService {

    // 회원 가입
    void join(Member member);

    // 회원 조회
    Member findMember(Long memberId);
}
