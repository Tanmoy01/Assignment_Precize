package com.example.demo_Precize.Repository;

import com.example.demo_Precize.Entity.SATResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SATResultRepository extends JpaRepository<SATResult, Long> {
    Optional<SATResult> findByName(String name);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SATResult s WHERE s.name = ?1", nativeQuery = true)
    void deleteByName(String name);

    @Query(value = "select * from SATResult s ORDER By score DESC", nativeQuery = true)
    List<SATResult> findAllByDesc();
}
