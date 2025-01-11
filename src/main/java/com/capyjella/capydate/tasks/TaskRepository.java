package com.capyjella.capydate.tasks;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByIdAndDeletedFalse(Integer id);

    @Query(value = """
            SELECT task
            FROM Task task
            WHERE task.user.id = :userId
            AND (
                ( task.date >= :startTime AND task.date <= :endTime )
                OR ( task.startTime >= :startTime AND task.startTime <= :endTime )
                OR ( task.endTime >= :startTime AND task.endTime <= :endTime )
            )
            """,
            countQuery = """
                            SELECT COUNT(task)
                            FROM Task task
                            WHERE task.user.id = :userId
                            AND (
                                ( task.date >= :startTime AND task.date <= :endTime )
                                OR ( task.startTime >= :startTime AND task.startTime <= :endTime )
                                OR ( task.endTime >= :startTime AND task.endTime <= :endTime )
                            )
                    """)
    Page<Task> findAllInPeriod(Pageable pageable, @Param("startTime") long startTime, @Param("endTime") long endTime, @Param("userId") Integer userId);
}
