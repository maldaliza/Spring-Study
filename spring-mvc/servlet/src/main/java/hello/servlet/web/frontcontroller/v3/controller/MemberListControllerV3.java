package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // 1. 저장소의 Member 객체 인스턴스들을 전부 불러온다.
        List<Member> members = memberRepository.findAll();

        // 2. 논리이름이 "members"인 ModelView 객체를 생성.
        ModelView modelView = new ModelView("members");

        // 3. ModelView 객체에서 Model을 가져와서 보관. (View에서 데이터를 참조하기 위해..)
        modelView.getModel().put("members", members);

        // 4. ModelView를 반환.
        return modelView;
    }
}
