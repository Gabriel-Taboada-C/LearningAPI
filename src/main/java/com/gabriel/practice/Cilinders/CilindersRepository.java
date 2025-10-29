package com.gabriel.practice.Cilinders;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CilindersRepository extends JpaRepository <CilindersEntity, Long> {
    List<CilindersEntity> findByProduct(CilindersEntity product);
}
