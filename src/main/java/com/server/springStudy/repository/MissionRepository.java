package com.server.springStudy.repository;

import com.server.springStudy.domain.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>  {
}
