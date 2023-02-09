package com.brockhaus.SuperDuperMarkt.repo;

import com.brockhaus.SuperDuperMarkt.model.DailyPriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPriceIdRepository extends JpaRepository<DailyPriceId, Long> {
}
