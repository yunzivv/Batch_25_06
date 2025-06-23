package com.koreait.exam.batch_25_06.app.repository;

import com.koreait.exam.batch_25_06.app.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, Long> {

}