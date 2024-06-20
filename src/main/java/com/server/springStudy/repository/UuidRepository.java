package com.server.springStudy.repository;

import com.server.springStudy.domain.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UuidRepository extends JpaRepository<Uuid, Long>  {
}
