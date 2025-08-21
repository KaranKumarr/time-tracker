package org.karankumarr.timetracker.skill.repository;

import org.karankumarr.timetracker.skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> { }
