package com.example.demo.targets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentRepository extends JpaRepository<Segment, Integer>, JpaSpecificationExecutor<Segment> {

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE segments SET data= null WHERE segtype IN ( :segmenttype2 , :segmenttype3 ) AND  "
			+ " tuid IN (SELECT id FROM tus WHERE bundleid= :bundleid )")
	public void updateSegmentByBundleId(@Param("segmenttype2") int segmenttype2,
			@Param("segmenttype3") int segmenttype3, @Param("bundleid") int bundleid);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE segments SET data= null WHERE segtype IN ( :segmenttype2 , :segmenttype3 ) AND  "
			+ " tuid IN (SELECT id FROM tus WHERE (transstatus= :transstatus OR qastatus= :qastatus )  AND bundleid= :bundleid )")
	public void updateSegmentByBundleId2(@Param("segmenttype2") int segmenttype2,
			@Param("segmenttype3") int segmenttype3, @Param("transstatus") int transstatus,
			@Param("qastatus") int qastatus, @Param("bundleid") int bundleid);

	@Modifying
	@Query(nativeQuery = true, value = "UPDATE segments SET data= null WHERE segtype IN ( :segmenttype2 , :segmenttype3 ) AND  "
			+ " tuid IN (SELECT id FROM tus WHERE (transstatus= :transstatus OR qastatus= :qastatus )  AND bundleid= :bundleid )")
	public void updateSegmentByBundleId3(@Param("segmenttype2") int segmenttype2,
			@Param("segmenttype3") int segmenttype3, @Param("transstatus") int transstatus,
			@Param("qastatus") int qastatus, @Param("bundleid") int bundleid);

    List<Segment> findAllByTuidAndSegtypeOrderByTsDesc(int id, int segtype);

	@Modifying
	@Query(nativeQuery = true, value = "INSERT INTO segments (tuid, segtype, data, actorid) values (:tuid, :segtype, :data, :actorid)")
	public void addSegments(@Param("tuid") int tuId, @Param("segtype") int segmenttypeTrans,
							@Param("data") String translation, @Param("actorid") int actorId);
}
