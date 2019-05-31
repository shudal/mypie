package moe.perci.pie.model.service;

import moe.perci.pie.model.Saying;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SayingService extends JpaRepository<Saying, Long> {
    Saying findSayingByCreateTime(BigInteger createTime);
    Saying findSayingById(BigInteger id);
}
