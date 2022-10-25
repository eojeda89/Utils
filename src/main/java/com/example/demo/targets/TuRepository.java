package com.example.demo.targets;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TuRepository extends JpaRepository<Tu, Integer>, JpaSpecificationExecutor<Tu> {

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET qainfo=null , qeinfo= null, transcode= :transcode, altcode=0, actorId=0, bundleid=null,"
            + " transstatus= :transstatus ," + " alttransstatus= :alttransstatus ," + " status= :status ,"
            + " qestatus= :qestatus ," + " altqestatus=:altqestatus ," + " altqastatus= :altqastatus ,"
            + " qastatus= :qastatus" + "	WHERE bundleid= :id")
    public void updateByBundleId1(@Param("transstatus") int transstatus, @Param("alttransstatus") int alttransstatus,
                                  @Param("status") int status, @Param("qestatus") int qestatus, @Param("altqestatus") int altqestatus,
                                  @Param("altqastatus") int altqastatus, @Param("qastatus") int qastatus, @Param("id") Integer id, @Param("transcode") int transcode);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET qainfo=null , qeinfo= null, transcode= :transcode, altcode=0, actorId=0, bundleid=null,"
            + " transstatus= :transstatus ," + " alttransstatus= :alttransstatus ," + " status= :status ,"
            + " qestatus= :qestatus ," + " altqestatus=:altqestatus ," + " altqastatus= :altqastatus ,"
            + " qastatus= :qastatus"
            + "	WHERE (transstatus= :transstatus2 or qastatus= :qastatus2 ) and bundleid= :id")
    public void updateByBundleId2(@Param("transstatus") int transstatus, @Param("alttransstatus") int alttransstatus,
                                  @Param("status") int status, @Param("qestatus") int qestatus, @Param("altqestatus") int altqestatus,
                                  @Param("altqastatus") int altqastatus, @Param("qastatus") int qastatus,
                                  @Param("transstatus2") int transstatus2, @Param("qastatus2") int qastatus2, @Param("id") Integer id, @Param("transcode") int transcode);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET qainfo=null , qeinfo= null, transcode= :transcode, altcode=0, actorId=0, bundleid= null , "
            + " transstatus= :transstatus ," + " alttransstatus= :alttransstatus ," + " status= :status ,"
            + " qestatus= :qestatus ," + " altqestatus=:altqestatus ," + " altqastatus= :altqastatus ,"
            + " qastatus= :qastatus ," + " revstatus= :revstatus "
            + "	WHERE id IN :tuids ORDER BY idinjob DESC LIMIT :tus ")
    public void updateByBundleIdLimited(@Param("transstatus") int transstatus,
                                        @Param("alttransstatus") int alttransstatus, @Param("status") int status, @Param("qestatus") int qestatus,
                                        @Param("altqestatus") int altqestatus, @Param("altqastatus") int altqastatus,
                                        @Param("qastatus") int qastatus, @Param("revstatus") int revstatus, @Param("tuids") List<Integer> tuids,
                                        @Param("tus") int tus, @Param("transcode") int transcode);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcode= :transcode, status= :status, actorId=0, bundleid= null ,  revstatus= :revstatus "
            + "	WHERE id IN :tuids ORDER BY idinjob DESC LIMIT :tus ")
    public void updateByBundleIdLimitedAndRevisionType(@Param("status") int status, @Param("revstatus") int revstatus, @Param("tuids") List<Integer> tuids,
                                        @Param("tus") int tus, @Param("transcode") int transcode);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcode= :transcode, status= :status, actorId=0, bundleid= null "
            + "	WHERE revstatus = :revstatus and bundleId = :bundleid")
    public void updateByBundleIdAndRevisionType(@Param("status") int status, @Param("revstatus") int revstatus, @Param("transcode") int transcode,
                                                @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET bundleid= null WHERE bundleid= :id ")
    public void updateByBundleId(@Param("id") Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM tus WHERE bundleid = :bundleid ")
    public Iterable<Tu> findByBundleid(int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus  SET transcode= :transcode , transstatus= :transstatus , qastatus= :qastatus, status= :status, trans= :trans, alttrans= :alttrans , actorid= :actorid, updatets= :updatets WHERE id = :id AND bundleid= :bundleid ")
    void updateTuByTutranslate(@Param("transcode") int transCode, @Param("transstatus") int tuTransstatusDone,
                               @Param("qastatus") int tuQastatusRequired, @Param("status") int tuStatusTranslated,
                               @Param("trans") String translation, @Param("alttrans") String altTranslation, @Param("actorid") int actorId,
                               @Param("id") int tuId, @Param("bundleid") int bundleId, @Param("updatets") Timestamp updatets);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET revstatus= :revstatus,  actorid= :actorid, updatets= :updatets  WHERE id = :id AND bundleid= :bundleid")
    void updateTuReviewed1(@Param("revstatus") int revStatus, @Param("actorid") int actorId,
                           @Param("id") int tuId, @Param("bundleid") int bundleId, @Param("updatets") Timestamp updatets);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET revstatus= :revstatus,  actorid= :actorid, updatets= :updatets, qastatus = :qastatus  WHERE id = :id AND bundleid= :bundleid")
    void updateTuReviewedKO(@Param("revstatus") int revStatus, @Param("actorid") int actorId,
                                  @Param("id") int tuId, @Param("bundleid") int bundleId, @Param("updatets")Timestamp updatets, @Param("qastatus") int qastatus);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcode= :transcode, trans= :trans, alttrans= :alttrans, revstatus= :revstatus,  actorid= :actorid, updatets= :updatets  WHERE id = :id AND bundleid= :bundleid")
    public void updateTuReviewed2(@Param("transcode") int transCode, @Param("trans") String translation,
                                  @Param("alttrans") String altTranslation, @Param("revstatus") int revStatus, @Param("actorid") int actorId,
                                  @Param("id") int tuId, @Param("bundleid") int bundleId, @Param("updatets")Timestamp updatets);

    @Query(nativeQuery = true, value = "SELECT * FROM tus WHERE id = :id")
    public Iterable<Tu> findById2(@Param("id") int tuId);

    @Query(nativeQuery = true, value = "SELECT  COUNT(id) FROM tus WHERE revstatus in (0, 1, 5) AND transstatus=1 AND qastatus IN (3,4) AND bundleId = :id")
    public int countReviewPending(@Param("id") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET revstatus= :revstatus,  actorid= :actorid  WHERE id IN :ids ")
    public void updateAllPending(@Param("revstatus") int tuRevstatusOk, @Param("actorid") int actorid, @Param("ids") List<Integer> ids);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transstatus= :transstatus,  qastatus= :qastatus, status= :status  WHERE id = :id AND bundleid= :bundleid")
    public void updateTuConfirmed(@Param("transstatus") int tuTransstatusDone, @Param("qastatus") int tuQastatusFp,
                                  @Param("status") int tuStatusQaed, @Param("id") int tuid, @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET qescore= :qescore,  altqestatus= :qestatus, status= :status, evaluatorId= :evaluatorId, qeinfo= :qeinfo  WHERE id = :id AND bundleid= :bundleid")
    public void updateEvaluatedAltTu(@Param("qescore") int qEScore, @Param("qestatus") int qeStatus,
                                     @Param("status") int tuStatusQed, @Param("evaluatorId") int evaluatorId, @Param("qeinfo") String string,
                                     @Param("id") int tuid, @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET qescore= :qescore,  qestatus= :qestatus, status= :status, evaluatorId= :evaluatorId, qeinfo= :qeinfo  WHERE id = :id AND bundleid= :bundleid")
    public void updateEvaluatedTu(@Param("qescore") int qEScore, @Param("qestatus") int qeStatus,
                                  @Param("status") int tuStatusQed, @Param("evaluatorId") int evaluatorId, @Param("qeinfo") String string,
                                  @Param("id") int tuid, @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET trans = :trans, alttrans = :alttrans  WHERE id = :id AND bundleid= :bundleid")
    public void updateTransAndAlttrans(@Param("trans") String translation, @Param("alttrans") String altTranslation,
                                       @Param("id") int tuid, @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET trans = :trans  WHERE id = :id AND bundleid= :bundleid")
    public void updateTrans(@Param("trans") String translation, @Param("id") int tuid, @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET alttrans = :alttrans  WHERE id = :id AND bundleid= :bundleid")
    public void updateAltTrans(@Param("alttrans") String altTranslation, @Param("id") int tuid,
                               @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO segments (tuid, segtype, data) values (:tuid, :segtype, :data)")
    public void addSegments1(@Param("tuid") int tuId, @Param("segtype") int segmenttypeTrans,
                             @Param("data") String translation);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET mark = :mark  WHERE id = :id AND bundleid= :bundleid")
    public void updateMark(@Param("mark") int mark, @Param("id") int tuid, @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET status = :status, gtrans = :gtrans  WHERE id = :id ")
    public void updatePreparedStatusGtrans(@Param("status") int tuStatusPrepared, @Param("gtrans") String gTrans,
                                           @Param("id") int tuId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET status = :status, pretrans = :pretrans, origin = :origin  WHERE id = :id ")
    public void updatePreparedStatusPretrans(@Param("status") int tuStatusPrepared, @Param("pretrans") String preTrans,
                                             @Param("origin") int origin, @Param("id") int tuId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET status = :status, pretrans = :pretrans, gtrans = :gtrans, origin = :origin  WHERE id = :id ")
    public void updatePreparedStatus(@Param("status") int tuStatusPrepared, @Param("pretrans") String preTrans,
                                     @Param("gtrans") String gTrans, @Param("origin") int origin, @Param("id") int tuId);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO segments (tuid, segtype, data, origin) values (:tuid, :segtype, :data, :origin)")
    public void addSegmentsOrigin(@Param("tuid") int tuId, @Param("segtype") int segmenttypePretrans,
                                  @Param("data") String preTrans, @Param("origin") int origin);

    @Query(nativeQuery = true, value = "SELECT * FROM tus WHERE jobid = :jobid ")
    public Iterable<Tu> findByJobid(@Param("jobid") int jobId);

    @Query(nativeQuery = true, value = "SELECT * FROM tus WHERE jobid = :jobid AND idinjob>= :start AND idinjob<= :end ")
    public Page<Tu> findJobidWithIdinjob(@Param("jobid") int jobId, @Param("start") int start, @Param("end") int end,
                                         Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM tus WHERE idinjob>= :start AND idinjob<= :end ")
    public Page<Tu> findAllWithIdinjob(@Param("start") int start, @Param("end") int end, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM tus WHERE bundleid = :bundleid AND idinjob>= :start AND idinjob<= :end ")
    public Page<Tu> findByBundleidWithIdinjob(@Param("bundleid") int bundleId, @Param("start") int start,
                                              @Param("end") int end, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "update tus set bundleid = :bundleid where bundleid is null and jobid= :jobid order by id limit :tus")
    void updateTusCount(@Param("bundleid") int bundleid, @Param("jobid") int jobid, @Param("tus") int tus);

    @Modifying
    @Query(nativeQuery = true, value = "update tus set bundleid = :bundleid where jobid= :jobid and idinjob>= :start and idinjob<= :end")
    public void updateInInterval(@Param("bundleid") int bundleid, @Param("jobid") int jobid, @Param("start") int start,
                                 @Param("end") int end);

    @Modifying
    @Query(nativeQuery = true, value = "update tus set qestatus = 1 where  bundleid= :bundleid and id in (select tuid from bundletu where bundleid= :bundleid  and idinbundle<= :first3percent)")
    public void updateQestatus(@Param("bundleid") int bundleid, @Param("first3percent") int first3percent);

    @Modifying
    @Query(nativeQuery = true, value = "update tus set qestatus = 1 where bundleid= :bundleid and  id in (select tuid from bundletu where bundleid= :bundleid and idinbundle> :first3percent and idinbundle< :last2percent and idinbundle mod :midStep = 0)")
    public void updateQestatus1(@Param("bundleid") int bundleid, @Param("first3percent") int i,
                                @Param("last2percent") int j, @Param("midStep") int midStep);

    @Modifying
    @Query(nativeQuery = true, value = "update tus set qestatus = 1 where  bundleid= :bundleid and id in (select tuid from bundletu where bundleid= :bundleid and idinbundle>= :last2percent)")
    public void updateQestatus2(@Param("bundleid") int bundleid, @Param("last2percent") int last2percent);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO segments (tuid, data) values (:tuid, :data)")
    public void addSegments2(@Param("tuid") int id, @Param("data") String srcText);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where gtrans is not null and pretrans is not null and jobid= :jobid ")
    public int getCountPrep(@Param("jobid") int jobid);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where gtrans is not null and pretrans is not null and bundleId= :id ")
    public int getCountPrepTusByBundle(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where transstatus = 1 and bundleId= :id ")
    public int getCountTranslatedTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where transstatus = 2 and bundleId= :id ")
    public int getCountModifiedTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where transcode in (select id from transcodes where type>0) and bundleId= :id ")
    public int getCountUntranslatedTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where altcode>0 and bundleId= :id ")
    public int getCountUnalttranslatedTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where status>= :status and bundleId= :id ")
    public int getCountQAedTus(@Param("id") int id, @Param("status") int tU_STATUS_QAED);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where qastatus= :qastatus and bundleId= :id ")
    public int getCountQAposTus(@Param("id") int id, @Param("qastatus") int tU_QASTATUS_OK);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where qastatus= :qastatus and bundleId= :id ")
    public int getCountQAFPTus(@Param("id") int id, @Param("qastatus") int tU_QASTATUS_FP);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where qastatus= :qastatus and bundleId= :id ")
    public int getCountQAnegTus(@Param("id") int id, @Param("qastatus") int tU_QASTATUS_KO);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where revstatus in (2,3,4) and bundleId= :id ")
    public int getCountReviewedTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where revstatus =2 and bundleId= :id ")
    public int getCountReviewedOKTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where (revstatus in (2,3,4) or transstatus>0) and bundleId= :id ")
    public int getCountTranslatedOrReviewedTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where revstatus =3 and bundleId= :id ")
    public int getCountReviewedChangedTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where revstatus = 4 and bundleId= :id ")
    public int getCountReviewedKOTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where qestatus<> :qestatus and bundleId= :id ")
    public int getCountQEplannedTus(@Param("id") int id, @Param("qestatus") int tU_QESTATUS_NO);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where qestatus= :qestatus and bundleId= :id ")
    public int getCountQEposTus(@Param("id") int id, @Param("qestatus") int tU_QESTATUS_OK);

    @Query(nativeQuery = true, value = "SELECT count(*) as cnt from tus where qestatus= :qestatus and bundleId= :id ")
    public int getCountQEnegTus(@Param("id") int id, @Param("qestatus") int tU_QESTATUS_KO);

    @Query(nativeQuery = true, value = "SELECT avg(qescore) as qes from tus where bundleId= :id and qestatus in ( :qestatusKO , :qestatusOK )")
    public Integer getCountQEScore(@Param("id") int id, @Param("qestatusKO") int tU_QESTATUS_KO, @Param("qestatusOK") int tU_QESTATUS_OK);

    @Query(nativeQuery = true, value = "SELECT avg(qascore) as qas from tus where bundleId= :id and qastatus<> :qastatus")
    public Integer getCountQAScore(@Param("id") int id, @Param("qastatus") int tU_QASTATUS_NOTDOABLE);

    @Query(nativeQuery = true, value = "SELECT avg(gdiss) as gdscore from tus where gdiss>0 and bundleId= :id ")
    public Integer getCountGdAvg(@Param("id") int id);

    @Query(nativeQuery = true, value = "select count(*) from tus where bfuzzyval = 100 and bundleId = :id")
    int getCountEffTus(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT idinbundle FROM `bundletu` where tuid = :id")
    int findInBundletuById(@Param("id") int id);

    @Query(nativeQuery = true, value = "SELECT count(*) FROM tus where bundleid = :bundleid AND status = :status")
    int findByBundleidAndStatus(@Param("bundleid") int bundleid, @Param("status") int status);

    List<Tu> findBySrctextAndJobidIn(String srctext, List<Integer> jobids, Pageable pageable);

    List<Tu> findIdByRevstatusInAndTransstatusAndQastatusInAndBundleid(int[] revstatus, int i, int[] qastatus, int bundleid);

    List<Tu> findByTransstatusAndRevstatusAndBundleid(int tu_transstatus_required, int tu_revstatus_notdoable, int bundleid, Pageable pageable);

    List<Tu> findByTransstatusAndRevstatusAndBundleidAndQastatusNotInAndIdGreaterThanOrderByIdinjob(int tu_transstatus_required, int tu_revstatus_notdoable, int bundleid, List<Integer> qastatus, int tuid);

    List<Tu> findByRevstatusAndBundleidAndIdGreaterThanOrderByIdinjob(int tu_revstatus_notdoable, int bundleid, int tuid);

    List<Tu> findByTransstatusAndBundleid(int tu_transstatus_required, int bundleid);

    List<Tu> findByQastatusAndBundleid(int tu_qastatus, int bundleid);

    List<Tu> findByRevstatusAndBundleid(int tu_revstatus, int bundleid);

    List<Tu> findByRevstatusAndBundleidIs(int tu_revstatus, int bundleid, Pageable pageable);

    List<Tu> findByRevstatusInAndBundleid(List<Integer> turevStatusList, int bundleid);

    List<Tu> findByRevstatusNotInAndBundleid(List<Integer> turevStatusList, int bundleid);

    List<Tu> findByTranscodeAndBundleid(int tu_transcode, int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transstatus = :transstatus,  qastatus = :qastatus, status = :status  WHERE bundleid = :bundleid AND qastatus = :qastatus2")
    void confirmAllFP(@Param("transstatus") int transstatus, @Param("qastatus") int qastatus, @Param("status") int status, @Param("qastatus2") int qastatus2, @Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET qastatus = :qastatus WHERE jobid = :id AND qastatus NOT IN (3,4)")
    void forceqastatus3inJob(@Param("qastatus") int qastatus, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET qastatus = :qastatus WHERE bundleid = :id AND qastatus NOT IN (3,4)")
    void forceqastatus3inBundle(@Param("qastatus") int qastatus, @Param("id") int id);


    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET status = :status, transstatus = :transstatus, qastatus = :qastatus WHERE jobid = :id AND status <> :status")
    void forceTUnottranslatedinJob(@Param("status") int status, @Param("transstatus") int transstatus, @Param("qastatus") int qastatus, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET status = :status, transstatus = :transstatus, qastatus = :qastatus WHERE bundleid = :id AND status <> :status")
    void forceTUnottranslatedinBundle(@Param("status") int status, @Param("transstatus") int transstatus, @Param("qastatus") int qastatus, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET trans = pretrans WHERE jobid = :id AND transcode IN :transcode")
    void forceTranslationinJob(@Param("transcode") List<Integer> transcode, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET trans = pretrans WHERE bundleid = :id AND transcode IN :transcode")
    void forceTranslationinBundle(@Param("transcode") List<Integer> transcode, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcode = :transcode WHERE jobid = :id AND trans is null")
    void forceChangeTranscodeinJob(@Param("transcode") int transcode, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcode = :transcode WHERE bundleid = :id AND trans is null")
    void forceChangeTranscodeinBundle(@Param("transcode") int transcode, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcode = :transcode WHERE jobid = :id AND alttrans is null")
    void forceChangeTranscodeinJob2(@Param("transcode") int transcode, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcode = :transcode WHERE bundleid = :id AND alttrans is null")
    void forceChangeTranscodeinBundle2(@Param("transcode") int transcode, @Param("id") int id);

    List<Tu> findByTransstatusAndRevstatusAndJobid(int tu_transstatus_done, int tu_revstatus_ok, int jobid);

    List<Tu> findByTranscodeAndJobid(int transcode, int jobid);

    List<Tu> findByQastatusInAndJobid(List<Integer> qaConfirmed, int jobid);

    List<Tu> findByStatusAndTransstatusAndQastatusInAndRevstatusIsLessThanAndJobid(int tu_status_qaed, int tu_transstatus_done, List<Integer> qaConfirmed, int tu_revstatus_ko, int jobid);

    List<Tu> findByTransstatusAndJobid(int tu_transstatus_required, int jobid);

    List<Tu> findByQastatusAndJobid(int tu_qastatus_tovalidate, int jobid);

    List<Tu> findByRevstatusAndJobid(int tu_revstatus_ok, int jobid);

    List<Tu> findByRevstatusInAndJobid(List<Integer> turevStatusList, int jobid);

    List<Tu> findByRevstatusNotInAndJobid(List<Integer> turevStatusList, int jobid);

    List<Tu> findByAlttransIsNullAndBundleid(int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET bundleid = null WHERE id IN :ids")
    void updateByRemovingBundleid(@Param("ids") List<Integer> ids);

    List<Tu> findByRevstatusIn(List<Integer> revstatus);

    List<Tu> findByBundleidIsNullAndJobidOrderById(int jobid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET bundleid = :bundleid WHERE id IN :ids")
    void updateBundleidByBundleidIn(@Param("ids") List<Integer> ids, @Param("bundleid") int bundleid);

    List<Tu> findByJobidAndQastatusNotIn(int id, List<Integer> qastatusList);

    List<Tu> findByBundleidAndQastatusNotIn(int id, List<Integer> qastatusList);

    List<Tu> findByQastatusNotAndJobid(int tu_status_qaed, int id);

    List<Tu> findByQastatusNotAndBundleid(int tu_status_qaed, int id);

    List<Tu> findByJobidAndTransIn(int id, List<String> trans);

    List<Tu> findByBundleidAndTransIn(int id, List<String> trans);

    List<Tu> findByJobidAndAlttransIn(int id, List<String> trans);

    List<Tu> findByBundleidAndAlttransIn(int id, List<String> trans);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET translatorworker = :workerid WHERE bundleid = :bundleid AND translatorworker = 0 LIMIT :limit")
    void updateTuByTranslatorWorkerLimited(@Param("workerid") int workerid, @Param("bundleid") int bundleid, @Param("limit") int limit);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET revisorworker = :workerid WHERE bundleid = :bundleid AND revisorworker = 0 LIMIT :limit")
    void updateTuByRevisorWorkerLimited(@Param("workerid") int workerid, @Param("bundleid") int bundleid, @Param("limit") int limit);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET evaluatorworker = :workerid WHERE bundleid = :bundleid AND evaluatorworker = 0 LIMIT :limit")
    void updateTuByEvaluatorWorkerLimited(@Param("workerid") int workerid, @Param("bundleid") int bundleid, @Param("limit") int limit);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET annotatorworker = :workerid WHERE bundleid = :bundleid AND annotatorworker = 0 LIMIT :limit")
    void updateTuByAnnotatorWorkerLimited(@Param("workerid") int workerid, @Param("bundleid") int bundleid, @Param("limit") int limit);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcriberworker = :workerid WHERE bundleid = :bundleid AND transcriberworker = 0 LIMIT :limit")
    void updateTuByTranscriptorWorkerLimited(@Param("workerid") int workerid, @Param("bundleid") int bundleid, @Param("limit") int limit);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET translatorworker = 0 WHERE bundleid = :bundleid ")
    void updateTuByCleanTranslatorWorker(@Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET revisorworker = 0 WHERE bundleid = :bundleid ")
    void updateTuByCleanRevisorWorker(@Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET evaluatorworker = 0 WHERE bundleid = :bundleid ")
    void updateTuByCleanEvaluatorWorker(@Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET annotatorworker = 0 WHERE bundleid = :bundleid ")
    void updateTuByCleanAnnotatorWorker(@Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcriberworker = 0 WHERE bundleid = :bundleid ")
    void updateTuByCleanTranscriptorWorker(@Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET vocalizerworker = 0 WHERE bundleid = :bundleid ")
    void updateTuByCleanVocalizerWorker(@Param("bundleid") int bundleid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET taggerworker = 0 WHERE bundleid = :bundleid ")
    void updateTuByCleanTaggerWorker(@Param("bundleid") int bundleid);

    Page<Tu> findAllByIdIn(List<Integer> ids, Pageable pageable);

    List<Tu> findByIdAndBundleid(int tuId, int bundleId);

    Optional<Tu> findByExtidAndJobid(String tuextid, int jobid);

    List<Tu> findByJobidAndExtidNotIn(int jobid, Set<String> keySet);

    List<Tu> findByRevstatusInAndTranscodeInAndJobid(List<Integer> revstatusList, List<Integer> transcodesIds, int jobid);

    @Query(nativeQuery = true, value = "select transstatus, revstatus, updatets, bundleId, jobId, actorid from tus where jobid in (select id from jobs where projectId = :projectid ) and updatets between :start and :end ")
    List<Object[]> statisticByProject2(@Param("projectid") int projectid, @Param("start") Timestamp start, @Param("end") Timestamp end);

    @Query(nativeQuery = true, value = "select revstatus, updatets, count(*) from tus where jobid in (select id from jobs where projectId = :projectid ) and updatets between :start and :end group by revstatus, updatets")
    List<Object[]> statisticByProject(@Param("projectid") int projectid, @Param("start") Timestamp start, @Param("end") Timestamp end);

    @Query(nativeQuery = true, value = "select transstatus, updatets, count(*) from tus where jobid in (select id from jobs where projectId = :projectid ) and updatets between :start and :end group by transstatus, updatets")
    List<Object[]> statisticTransByProject(@Param("projectid") int projectid, @Param("start") Timestamp start, @Param("end") Timestamp end);

    @Query(nativeQuery = true, value = "SELECT bundleId, revstatus, count(*) FROM `tus` where actorid <> 0 And revstatus <> 0 and actorid = :userid and updatets between :start and :end group by bundleId, revstatus")
    List<Object[]> getUserSummary(@Param("userid") int userid, @Param("start") Timestamp start, @Param("end") Timestamp end);

    @Query(nativeQuery = true, value = "select revstatus, updatets, count(*) from tus where actorid = :userid and revstatus <> 0 and updatets between :start and :end group by revstatus, updatets")
    List<Object[]> statisticByUser(@Param("userid") int userid, @Param("start") Timestamp start, @Param("end") Timestamp end);

    @Query(nativeQuery = true, value = "select * from tus where jobid = :jobid")
    List<Tu> findByJobidSQL(@Param("jobid") int jobid);

    @Query(nativeQuery = true, value = "select * from tus where jobid = :jobid limit :page, :size")
    List<Tu> findByJobidSQLPaged(@Param("jobid") int jobid, @Param("page") int page, @Param("size") int size);

    @Query(nativeQuery = true, value = "select * from tus where jobid = :jobid  and bundleId is null and id > :lastId limit :size")
    List<Tu> findByJobidndBundleidNullSQLPaged(@Param("jobid") int jobid, @Param("size") int size, @Param("lastId") int lastId);

    @Query(nativeQuery = true, value = "select * from tus where bundleId = :bundleid")
    List<Tu> findByBundleidSQL(@Param("bundleid") int bundleid);

    @Query(nativeQuery = true, value = "select tuid, idinbundle from bundletu where bundleid = :bundleid")
    List<Object[]> findIdInBundleandTuidByBundle(@Param("bundleid") int bundleid);

    @Query(nativeQuery = true, value = "select tus.id as tuid, anonindex.* from anonindex, tus where anonindex.tuextid = tus.extId and tus.jobId = anonindex.jobid and anonindex.jobid = :jobid ")
    List<Object[]> findAnonoindexByJobid(@Param("jobid") int jobid);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET trans = :trans, transstatus = :transtatus, status = :status, qastatus = :qastatus, transcode = :transcode WHERE id = :tuid AND bundleId = :bundleid ")
    void voiceTu(@Param("tuid") int tuid, @Param("bundleid") int bundleid, @Param("trans") String trans, @Param("transtatus") int transtatus,
                 @Param("status") int status, @Param("qastatus") int qastatus, @Param("transcode") int transcode);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET transcode = :transcode WHERE jobId = :jobid AND transstatus = 0 ")
    void updateTranscodeByJobDefault(@Param("jobid") int jobid, @Param("transcode") int transcode);

    @Query(nativeQuery = true, value = "SELECT id, srctext FROM tus WHERE jobId = :jobid AND bundleId IS NULL ORDER BY id LIMIT :tus")
    List<Object[]> findByJobidAndBundleidIsNull(@Param("jobid") int jobid, @Param("tus") int tus);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET bundleId = :bundleid WHERE id IN :tuIDsList")
    void setBundle(@Param("bundleid") int bundleid, @Param("tuIDsList") List<Integer> tuIDsList);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET revstatus = :revstatus WHERE jobId = :id")
    void forceRevstatusInJob(@Param("revstatus") int revstatus, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET revstatus = :revstatus WHERE bundleId = :id")
    void forceRevstatusInBundle(@Param("revstatus") int revstatus, @Param("id") int id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET mark = :mark WHERE jobId = :jobId and extId = :tuExtId")
    void updateMarkByExtId(@Param("tuExtId") String tuExtId, @Param("mark") int mark, @Param("jobId") int jobId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE tus SET bundleId = :bundleid WHERE jobId = :jobid and id IN :ids")
    void updateTusBundleId(@Param("bundleid") int bundleid, @Param("jobid") int jobid, @Param("ids") List<Integer> ids);
}
