package com.jaclon.mistakesOfBuz.nullValue.pojonotnull;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/28 17:42
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
}