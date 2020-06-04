package com.kpi.mst.repository;

import com.kpi.mst.domain.SimpleStatistics;
import com.kpi.mst.domain.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    List<Statistics> findAllByIdLessThan(Long id);

    Long countByAdditionalAndDiaphazone(boolean additional, String diaphazone);
    Long countByDiaphazone(String diaphazone);

    @Query( value =" select distinct number_matrix as numberMatrix, size_matrix as sizeMatrix, diaphazone, \"time\" from statistics where diaphazone = ? order by diaphazone, sizeMatrix ",
    nativeQuery = true)
    List<SimpleStatistics> getStatistictByDiaphazone(String diaphazone);

}
