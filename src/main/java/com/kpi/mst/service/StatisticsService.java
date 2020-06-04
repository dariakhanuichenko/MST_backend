package com.kpi.mst.service;

import com.kpi.mst.domain.CoutersDTO;
import com.kpi.mst.domain.Statistics;
import com.kpi.mst.domain.StatisticsDTO;
import com.kpi.mst.repository.StatisticsRepository;
import com.kpi.mst.service.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    public  final StatisticsRepository statisticsRepository;

    public StatisticsService(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }


    public List<StatisticsDTO> findAll () {
        return Mapper.listToDto(this.statisticsRepository.findAllByIdLessThan(562L));
    }

    public void save(Statistics statistics) {
        this.statisticsRepository.save(statistics);
    }

    public List<StatisticsDTO> getByDiaphazone (String diaphazone) {
        return Mapper.simpleListToDto(this.statisticsRepository.getStatistictByDiaphazone(diaphazone));
    }

    public CoutersDTO countersByDiaphazone (String diaphazone) {
        CoutersDTO counters = new CoutersDTO();
        counters.setCounterAdditional(statisticsRepository.countByAdditionalAndDiaphazone(true, diaphazone));
        counters.setCounterNotAdditional(statisticsRepository.countByDiaphazone( diaphazone) - counters.getCounterAdditional());
        return counters;
    }
}
