package com.studyolle.modules.study;

import com.studyolle.modules.account.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface StudyRepository extends JpaRepository<Study, Long>, StudyRepositoryExtension {

    @EntityGraph(attributePaths = {"zones", "tags"})
    List<Study> findFirst9ByPublishedAndClosedOrderByPublishedDateTimeDesc(boolean published, boolean closed);

    @EntityGraph(attributePaths = {"tags", "zones", "managers", "members"}, type = EntityGraph.EntityGraphType.LOAD)
    Study findByPath(String path);

    @EntityGraph(attributePaths = "members")
    Study findStudyWithMembersByPath(String path);

    @EntityGraph(attributePaths = "managers")
    Study findStudyWithManagersByPath(String path);

    boolean existsByPath(String path);

    Study findStudyOnlyByPath(String path);

    List<Study> findFirst5ByManagersContainingAndClosedOrderByPublishedDateTimeDesc(Account account, boolean closed);

    List<Study> findFirst5ByMembersContainingAndClosedOrderByPublishedDateTimeDesc(Account account, boolean closed);

    @EntityGraph(attributePaths = {"zones", "managers"})
    Study findStudyWithZonesByPath(String path);

    @EntityGraph(attributePaths = {"tags", "managers"})
    Study findStudyWithTagsByPath(String path);

}
