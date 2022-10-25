package com.example.demo.targets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SegmentService {

	@Autowired
	SegmentRepository segmentRepository;

	@Autowired
	TuRepository tuRepository;
	
	public Segment save(Segment segment) {
		return segmentRepository.save(segment);
	}

	
	public Optional<Segment> findById(Integer id) {
		return segmentRepository.findById(id);
	}

	public boolean existsById(Integer id) {
		return segmentRepository.existsById(id);
	}

	public Iterable<Segment> findAll() {
		return segmentRepository.findAll();
	}

	
	public void deleteById(Integer id) {
		
		segmentRepository.deleteById(id);
		
	}

	public void delete(Segment entity) {
		
		segmentRepository.delete(entity);
	}

	@Transactional
	public void updateSegmentByBundleId(int segmenttype2, int segmenttype3, int bundleid) {
		segmentRepository.updateSegmentByBundleId(segmenttype2, segmenttype3, bundleid);
	}
	@Transactional
	public void updateSegmentByBundleId2(int segmenttype2, int segmenttype3, int transstatus, int qastatus, int bundleid) {
		segmentRepository.updateSegmentByBundleId2(segmenttype2, segmenttype3, transstatus, qastatus, bundleid);
	}

	@Transactional
	public void addSegments(int tuId, int segmentType, String data, int actorId) {
		segmentRepository.addSegments(tuId, segmentType, data, actorId);
	}

	@Transactional
	public void saveAllSegments(List<Segment> insertSegments) {
		segmentRepository.saveAll(insertSegments);
	}

	public List<Segment> findAllByTuidAndSegtypeOrderByTsDesc(int id, int segmentType) {
		return segmentRepository.findAllByTuidAndSegtypeOrderByTsDesc(id, segmentType);
	}

	public Tu findTuByExtidAndJobid(String extId, int jobId) {
		if (tuRepository.findByExtidAndJobid(extId, jobId).isPresent())
			return tuRepository.findByExtidAndJobid(extId, jobId).get();
		return null;
	}

	public List<Tu> findTusInJob(int jobId) {
		return tuRepository.findByJobidSQL(jobId);
	}
}
